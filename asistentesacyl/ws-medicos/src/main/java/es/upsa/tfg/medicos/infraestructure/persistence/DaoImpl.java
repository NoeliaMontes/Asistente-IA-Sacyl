package es.upsa.tfg.medicos.infraestructure.persistence;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.domain.exceptions.MedicamentoNotFoundException;
import es.upsa.tfg.domain.exceptions.MedicoNotFoundException;
import es.upsa.tfg.medicos.adapters.output.persistence.Dao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao
{
    @Inject
    DataSource dataSource;


    @Override
    public Optional<Medico> getById(String id)
    {
        final String SQL =
                            """
                            SELECT nombre
                            FROM medicos
                            WHERE din = ?
                            """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            )
        {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                return (resultSet.next()) ? Optional.of(
                    Medico.builder()
                            .id(id)
                            .nombre(resultSet.getString(1))
                            .build()
                ) : Optional.empty();
            }
        } catch (SQLException e)
        {
            throw new MedicoNotFoundException();
        }
    }
}
