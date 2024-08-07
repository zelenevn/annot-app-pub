package ru.annot.dataset.dataset;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@ApplicationScoped
public class JdbcDatasetRepository implements DatasetRepository {

    private static final Logger LOG = Logger.getLogger(JdbcDatasetRepository.class);

    private static final String SAVE_QUERY = "INSERT INTO dataset VALUES(DEFAULT, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM dataset WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM dataset";
    private static final String DELETE_QUERY = "DELETE FROM dataset WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE dataset SET name = ?, description = ?, task = ? WHERE id = ?";

    @Inject
    DataSource dataSource;


    @Override
    public Dataset save(Dataset dataset) {
        String name = dataset.getName();
        String description = dataset.getDescription();
        String task = dataset.getTask();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement saveStatement = connection.prepareStatement(SAVE_QUERY, RETURN_GENERATED_KEYS)) {
            saveStatement.setString(1, name);
            saveStatement.setString(2, description);
            saveStatement.setString(3, task);
            saveStatement.executeUpdate();

            try (ResultSet generatedKeys = saveStatement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    dataset.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            LOG.error("Can't save dataset", e);
        }
        return dataset;
    }

    @Override
    public Dataset getById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                String task = resultSet.getString(4);
                return new Dataset(id, name, description, task);
            }

        } catch (SQLException e) {
            LOG.error(e);
        }
        throw new RuntimeException("Не удалось найти набор данных с заданным идентификатором.");
    }

    @Override
    public Iterable<Dataset> getAll() {
        List<Dataset> datasets = new LinkedList<>();

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);

            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                String task = resultSet.getString(4);
                datasets.add(new Dataset(id, name, description, task));
            }
    } catch (SQLException e) {
            LOG.error(e);
        }
        return datasets;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_QUERY)){
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    @Override
    public void update(Dataset dataset) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_QUERY)) {
            updateStatement.setString(1, dataset.getName());
            updateStatement.setString(2, dataset.getDescription());
            updateStatement.setString(3, dataset.getTask());
            updateStatement.setLong(4, dataset.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }


}
