package ru.annot.dataset.task;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class JdbcTasksRepository implements TasksRepository {

    private static final String GET_ALL_QUERY = "select * from task";

    private final DataSource dataSource;

    @Override
    public Iterable<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY)) {
                 while (resultSet.next()) {
                     Long id = resultSet.getLong("id");
                     String name = resultSet.getString("name");
                     Task task = new Task(id, name);
                     tasks.add(task);
                 }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }
}
