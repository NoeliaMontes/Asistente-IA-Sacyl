package es.upsa.tfg.citas.infraestructure.persistence;


import es.upsa.tfg.citas.adapters.output.persistence.Dao;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.exceptions.CitaNotFoundException;
import es.upsa.tfg.domain.exceptions.SacylException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao {
    @Inject
    DataSource dataSource;


    @Override
    public List<Cita> getByIdPaciente(String id) {

        List<Cita> citas = new ArrayList<>();
        final String SQL =
                """
                            
                        SELECT ID,MEDICO_ID,LUGAR,MOTIVO, FECHA, HORA,TIPO
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
                    citas.add(  Cita.builder()
                                    .id(resultSet.getString(1))
                                    .id_medico(resultSet.getString(2))
                                    .id_paciente(id)
                                    .lugar(resultSet.getString(3))
                                    .motivo(resultSet.getString(4))
                                    .fecha(resultSet.getDate(5).toLocalDate())
                                    .hora(resultSet.getTime(6).toLocalTime())
                                    .tipo(resultSet.getString(7))
                                    .build());
                }

             return citas;
            }
        } catch (SQLException e)
        {
            throw new SacylException(e);
        }
    }

    @Override
    public void deleteById(String id, String idPaciente)
    {
        final String SQL =
                """
                            
                DELETE FROM citas
                            WHERE id = ? AND paciente_id = ?
                            """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        { preparedStatement.setString(1, id);
          preparedStatement.setString(2, idPaciente);
            int deleted = preparedStatement.executeUpdate();
            if(deleted==0) throw new CitaNotFoundException();

        } catch (SQLException e) {
            throw new SacylException(e);
        }

    }

    @Override
    public Optional<Cita> getById(String id) {

        final String SQL =
                """
                            
                        SELECT MEDICO_ID,PACIENTE_ID,LUGAR,MOTIVO, FECHA, HORA,TIPO
                            FROM citas
                            WHERE id = ?
                            """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection. prepareStatement(SQL);
        )
        {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                return (resultSet.next()) ? Optional.of(
                        Cita.builder()
                        .id(id)
                        .id_medico(resultSet.getString(1))
                        .id_paciente(resultSet.getString(2))
                        .lugar(resultSet.getString(3))
                        .motivo(resultSet.getString(4))
                        .fecha(resultSet.getDate(5).toLocalDate())
                        .hora(resultSet.getTime(6).toLocalTime())
                        .tipo(resultSet.getString(7))
                        .build()
                ) : Optional.empty();

            }
        } catch (SQLException e)
        {
            throw new SacylException(e);
        }
    }

    @Override
    public Cita post(CitaDto citaDto)
    {
        final String SQL =
                """
                            INSERT INTO CITAS (ID,      MEDICO_ID,PACIENTE_ID,LUGAR,MOTIVO, FECHA, HORA,TIPO)
                             VALUES (nextval('seq_citas'),    ?,        ?,       ?,      ?,   ?,     ?,  ?)
                            """;

        final String[] fields= {"id"};
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, fields);
        )
        {
            preparedStatement.setInt(1, Integer.parseInt(citaDto.getId_medico()));
            preparedStatement.setString(2, citaDto.getId_paciente());
            preparedStatement.setString(3, citaDto.getLugar());
            preparedStatement.setString(4, citaDto.getMotivo());
            preparedStatement.setDate(5, Date.valueOf(String.valueOf(citaDto.getFecha())));
            preparedStatement.setTime(6, Time.valueOf(citaDto.getHora()));
            preparedStatement.setString(7, citaDto.getTipo());

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
            throw new SacylException(e);
        }

    }

    @Override
    public List<Cita> getByTime(LocalDate fecha, LocalTime hora)
    {
        List<Cita> citas = new ArrayList<>();
        LocalTime horaA= hora.minusMinutes(30);
        LocalTime horaD= hora.plusMinutes(30);

        final String SQL =
                """
                            
                        SELECT id,MEDICO_ID,PACIENTE_ID,LUGAR,MOTIVO,TIPO
                            FROM citas
                            WHERE fecha = ? AND hora BETWEEN ? AND ?
                            """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection. prepareStatement(SQL);
        )
        {
            preparedStatement.setDate(1, Date.valueOf(fecha));
            preparedStatement.setTime(2, Time.valueOf(horaA));
            preparedStatement.setTime(3, Time.valueOf(horaD));
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next())
                {
                    citas.add(  Cita.builder()
                            .id(resultSet.getString(1))
                            .id_medico(resultSet.getString(2))
                            .id_paciente(resultSet.getString(3))
                            .lugar(resultSet.getString(4))
                            .motivo(resultSet.getString(5))
                            .fecha(fecha)
                            .hora(hora)
                            .tipo(resultSet.getString(6))
                            .build());
                }
                return citas;
            }
        } catch (SQLException e)
        {
            throw new SacylException(e);
        }
    }

    @Override
    public List<Cita> getCitas() {
        List<Cita> citas = new ArrayList<>();
        final String SQL =
                """
                            
                        SELECT ID,MEDICO_ID,PACIENTE_ID,LUGAR,MOTIVO, FECHA, HORA,TIPO
                            FROM citas
                            """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection. prepareStatement(SQL);
        )
        {
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next())
                {
                    citas.add(  Cita.builder()
                            .id(resultSet.getString(1))
                            .id_medico(resultSet.getString(2))
                            .id_paciente(resultSet.getString(3))
                            .lugar(resultSet.getString(4))
                            .motivo(resultSet.getString(5))
                            .fecha(resultSet.getDate(6).toLocalDate())
                            .hora(resultSet.getTime(7).toLocalTime())
                            .tipo(resultSet.getString(8))
                            .build());
                }

                return citas;
            }
        } catch (SQLException e)
        {
            throw new SacylException(e);
        }
    }
}
