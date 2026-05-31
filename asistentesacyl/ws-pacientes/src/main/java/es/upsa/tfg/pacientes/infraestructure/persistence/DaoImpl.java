package es.upsa.tfg.pacientes.infraestructure.persistence;

import es.upsa.tfg.domain.dtos.PacienteDto;
import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Paciente;
import es.upsa.tfg.domain.exceptions.MedicamentoNotFoundException;
import es.upsa.tfg.domain.exceptions.PacienteNotFoundException;
import es.upsa.tfg.pacientes.adapters.output.persistence.Dao;
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
    public Optional<Paciente> getById(PacienteDto pacienteDto)
    {
        final String SQL =
                            """
                            SELECT nombre
                            FROM pacientes
                            WHERE cipaut = ? and apellido1 = ?
                            """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            )
        {
            preparedStatement.setString(1, pacienteDto.getId());
            preparedStatement.setString(2, pacienteDto.getApellido());
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                return (resultSet.next()) ? Optional.of(
                    Paciente.builder()
                            .id(pacienteDto.getId())
                            .nombre(resultSet.getString(1))
                            .apellido(pacienteDto.getApellido())
                            .build()
                ) : Optional.empty();
            }
        } catch (SQLException e)
        {
           throw new PacienteNotFoundException();
        }
    }
}
