package controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
      ConnecionFactory.closeConnection(connection, statement);
      
    }

  }

  public void update(task task) {
    String sql = "UPDATE tasks SET "
    + "idProject = ?"
    + "name = ?"
    + "description = ?"
    + "completed = ?"
    + "notes = ?"
    + "deadline = ?"
    + "createdAt = ?"
    + "updatedAt = ?"
    + "WHERE id = ?";

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
      throw new RuntimeException("Erro ao atualizar tarefa" + ex.getMessage(), ex);
    } 

  }

  public void removeById(int taskId) throws SQLException {
    String sql = "DELETE FROM tasks WHERE id = ?";

    Connection connection = null; //conexão
    PreparedStatement statement = null; //statement

    try {
      connection = ConnecionFactory.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setInt(1, taskId); //seta o valor do id
      statement.execute(); //executa o sql
    } catch (Exception ex) {      
      throw new RuntimeException("Erro ao deletar tarefa" + ex.getMessage());
    } finally {
      ConnecionFactory.closeConnection(connection, statement);
    }

  }

  public List<task> getAll(int idProject) {
    String sql = "SELECT * FROM tasks WHERE idProject = ?";

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
    List<task> tasks = new ArrayList<task>();//nova lista de tarefas

    try {
      connection = ConnecionFactory.getConnection();
      statement = connection.prepareStatement(sql);
      statement.setInt(1, idProject);
      statement.executeQuery(); //devolve um valor de resposta do select que aconteceu no banco de dados
      resultSet = statement.getResultSet(); //pega o resultado do select (tarefas)
      while(resultSet.next()) {
        task task = new task(); //iniciar uma nova tarefa
        task.setId(resultSet.getInt("id"));
        task.setIdProject(resultSet.getInt("idProject"));
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));
        task.setIsCompleted(resultSet.getBoolean("completed"));
        task.setNote(resultSet.getString("notes"));
        task.setDeadline(resultSet.getDate("deadline"));
        task.setCreatedAt(resultSet.getDate("createdAt"));
        task.setUpdatedAt(resultSet.getDate("updatedAt"));
        tasks.add(task);
      }
    } catch (Exception e) {

    }

    return null;
  }

}
