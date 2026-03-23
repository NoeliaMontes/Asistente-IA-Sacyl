package es.upsa.tfg.posologias.infraestructure.persistence;

import es.upsa.tfg.domain.entities.Posologia;
import es.upsa.tfg.domain.exceptions.PosologiaNotFoundException;
import es.upsa.tfg.posologias.adapters.output.persistence.Dao;
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
    public Optional<Posologia> getById(String id)
    {
        final String SQL =
                            """
                            SELECT id,medicamento_id,medico_id, dosis, frecuencia, unidad
                            FROM posologia
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
                return (resultSet.next()) ? Optional.of(
                        Posologia.builder()
                                .id(resultSet.getString(1))
                                .id_medicina(resultSet.getString(2))
                                .id_medico(resultSet.getString(3))
                                .id_paciente(id)
                                .dosis(resultSet.getInt(4))
                                .frecuencia(resultSet.getDouble(5))
                                .unidad(resultSet.getString(6))
                                .build()
                ) : Optional.empty();
            }
        } catch (SQLException e)
        {
            throw new PosologiaNotFoundException();
        }
    }
}
