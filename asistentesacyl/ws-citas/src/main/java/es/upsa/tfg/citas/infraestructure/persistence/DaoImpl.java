package es.upsa.tfg.citas.infraestructure.persistence;


import es.upsa.tfg.citas.adapters.output.persistence.Dao;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.exceptions.CitaNotFoundException;
import es.upsa.tfg.domain.exceptions.PacienteNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao {
    @Inject
    DataSource dataSource;


    @Override
    public List<Cita> getById(String id) {

        List<Cita> citas = new ArrayList<>();
        final String SQL =
                """
                            
                        SELECT id,medico_id, 
                            FROM citas
                            WHERE paciente_id = ?
                            """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection. prepareStatement(SQL);
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

    @Override
    public void deleteById(String id)
    {
        final String SQL =
                """
                            
                DELETE FROM citas
                            WHERE id = ?
                            """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        { preparedStatement.setString(1, id);
            int deleted = preparedStatement.executeUpdate();
            if(deleted==0) throw new CitaNotFoundException();

        } catch (SQLException e) {
            throw new CitaNotFoundException();
        }

    }

    @Override
    public Cita post(CitaDto citaDto)
    {
        final String SQL =
                """
                            INSERT INTO CITAS (ID,      MEDICO_ID,PACIENTE_ID,LUGAR,MOTIVO,
                FECHA, HORA,TIPO)
                             VALUES (nextval('seq_citas'),    ?,        ?,       ?,      ?,   ?,     ?,  ?),
                            """;

        final String[] fields= {"id"};
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, fields);
        )
        {
            preparedStatement.setString(1, citaDto.getId_medico());
            preparedStatement.setString(2, citaDto.getId_paciente());
            preparedStatement.setString(3, citaDto.getLugar());
            preparedStatement.setString(4, citaDto.getMotivo());
            preparedStatement.setDate(5, Date.valueOf(String.valueOf(citaDto.getFecha())));
            preparedStatement.setTime(6, citaDto.getHora()); preparedStatement.setString(7, citaDto.getTipo());

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys())
            {
                resultSet.
                        next();
                String id = resultSet.getString(1);
                    return Cita.builder()
                                .id(id)
                                .id_medico(citaDto.getId_medico())
                                .id_paciente(citaDto.getId_paciente())
                                .lugar(citaDto.getLugar())
                                .motivo(citaDto.getMotivo())
                                .fecha(citaDto.getFecha())
                                .hora(citaDto.getHora())
                                .tipo(citaDto.getTipo())
                                .build();
            }


        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
}
