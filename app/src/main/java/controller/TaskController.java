package controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.task;
import util.ConnecionFactory;

public class TaskController {
  
  public void save(task task) { //Cria uma nova tarefa
    String sql = "INSERT INTO tasks (idProject," 
    + "name," 
    + "description," 
    + "completed," 
    + "notes," 
    + "deadline," 
    + "createdAt," 
    + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnecionFactory.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setInt(1, task.getIdProject());
      statement.setString(2, task.getName());
      statement.setString(3, task.getDescription());
      statement.setBoolean(4, task.isIsCompleted());
      statement.setString(5, task.getNote());
      statement.setDate(6, new Date(task.getDeadline().getTime()));
      statement.setDate(7, new Date(task.getCreatedAt().getTime()));
      statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
      statement.execute();
    } catch (Exception ex) {
      throw new RuntimeException("Erro ao salvar tarefa" + ex.getMessage(), ex);
    } finally {
      ConnecionFactory.closeConnection(connection);
    }

  }

  public void update(task task) {
  
  }

  public void removeById(int taskId) throws SQLException {
    String sql = "DELETE FROM tasks WHERE id = ?";

    Connection conn = null; //conexão
    PreparedStatement statement = null; //statement

    try {
      conn = ConnecionFactory.getConnection();
      statement = conn.prepareStatement(sql);
      statement.setInt(1, taskId); //seta o valor do id
      statement.execute(); //executa o sql
    } catch (SQLException e) {      
      throw new SQLException("Erro ao deletar tarefa");
    } finally {
      ConnecionFactory.closeConnection(conn);
    }

  }

  public List<task> getAll(int idProject) {
    return null;
  }

}
