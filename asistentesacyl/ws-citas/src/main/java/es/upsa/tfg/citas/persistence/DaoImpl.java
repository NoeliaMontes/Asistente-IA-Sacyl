package es.upsa.tfg.citas.persistence;


import es.upsa.tfg.citas.adapters.output.persistence.Dao;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.exceptions.PacienteNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao
{
    @Inject
    DataSource dataSource;


    @Override
    public List<Cita> getById(String id)
    {

        List<Cita> citas = new ArrayList<>();
        final String SQL =
                            """
                            SELECT id,medico_id, 
                            FROM citas
                            WHERE paciente_id = ?
                            """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            )
        {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next())
                {
                    citas.add(Cita.builder()
                            .id(resultSet.getString(1))
                            .id_medico(resultSet.getString(3))
                            .id_paciente(id)
                            .build());
                }

             return citas;
            }
        } catch (SQLException e)
        {
            throw new PacienteNotFoundException();
        }
    }
}
