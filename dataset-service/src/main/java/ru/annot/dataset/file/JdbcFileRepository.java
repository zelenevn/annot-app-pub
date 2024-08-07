package ru.annot.dataset.file;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@ApplicationScoped
public class JdbcFileRepository implements FileRepository {

    private static final String SAVE_FILE_QUERY = "INSERT INTO file VALUES(DEFAULT, ?, ?)";

    private static final String GET_ALL_BY_DATASET_ID_QUERY = "SELECT * FROM file WHERE dataset_id = ?";

    @Inject
    DataSource dataSource;

    @Override
    public Long save(Long datasetId, String key) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FILE_QUERY, RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, datasetId);
            preparedStatement.setString(2, key);
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }

        } catch (SQLException e) {

        }

        throw new RuntimeException("Не удалось сохранить файл.");
    }
}
