package es.upsa.tfg.medicos.infraestructure.persistence;

import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.domain.exceptions.MedicoNotFoundException;
import es.upsa.tfg.medicos.adapters.output.persistence.Dao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao
{
    @Inject
    DataSource dataSource;


    @Override
    public List<Medico> getMedicos()
    {
        List<Medico> medicos = new ArrayList<>();
        final String SQL =
                            """
                            SELECT din, nombre
                            FROM medicos
                            """;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL);
        )
        {
            while (resultSet.next())
            {
                medicos.add(Medico.builder()
                        .id(resultSet.getString(1))
                        .nombre(resultSet.getString(2))
                        .build());
            }
            return medicos;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
