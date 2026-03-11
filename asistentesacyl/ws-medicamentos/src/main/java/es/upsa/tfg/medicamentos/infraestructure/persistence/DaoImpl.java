package es.upsa.tfg.medicamentos.infraestructure.persistence;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.exceptions.MedicamentoNotFoundException;
import es.upsa.tfg.medicamentos.adapters.output.persistence.Dao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao
{
    @Inject
    DataSource dataSource;


    @Override
    public Optional<Medicamento> getById(String id)
    {
        final String SQL =
                            """
                            SELECT nombre
                            FROM animales
                            WHERE id = ?
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
                    Medicamento.builder()
                            .id(id)
                            .nombre(resultSet.getString(1))
                            .build()
                ) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new MedicamentoNotFoundException();
        }
    }
}
