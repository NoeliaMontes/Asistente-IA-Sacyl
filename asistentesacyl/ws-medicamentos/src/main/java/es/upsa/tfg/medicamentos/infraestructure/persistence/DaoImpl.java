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
    //Utilizamos este Inject dataSource ya que la dataSource la declaramos en el docker compose
    @Inject
    DataSource dataSource;


    //Esta función accede a la base de datos y busca el medicamento utilizando el id
    @Override
    public Optional<Medicamento> getById(String id)
    {
        //Secuencia SQL para obtener el medicamento
        final String SQL =
                            """
                            SELECT nombre
                            FROM medicamentos
                            WHERE id = ?
                            """;

        //Usamos try en caso de error en la conexión a la base de datos
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            )
        {
            //Introducimos el id a la secuencia SQL
            preparedStatement.setString(1, id);
            //Vemos si la secuencia SQL ejecuta
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                //En caso de que ejecute si encuentra algo utilizamos el builder de medicamento y lo devolvemos
                //En el caso de que no lo encuentre devolvemos un optional vacío.
                return (resultSet.next()) ? Optional.of(
                    Medicamento.builder()
                            .id(id)
                            .nombre(resultSet.getString(1))
                            .build()
                ) : Optional.empty();
            }
        } catch (SQLException e)
        {
            //En caso de que la excepción sea SQL como no estamos injectando el único caso posible es el id introducido se erróneo
            //Si el id es erróneo entonces el medicamento no será encontrado por lo que devolvemos la excepción MedicamentoNotFoundException
            //Para saber más sobre esta excepción consultar en el módulo domain carpeta exceptions y ver la excepción
            throw new MedicamentoNotFoundException();
        }
    }
}
