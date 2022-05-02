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
  
  public void save(task task) {
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
      //Instancia a conexão com o banco de dados
      connection = ConnecionFactory.getConnection();

      //Cria o statement para inserir os dados
      statement = connection.prepareStatement(sql);

      //Seta os valores
      statement.setInt(1, task.getIdProject());
      statement.setString(2, task.getName());
      statement.setString(3, task.getDescription());
      statement.setBoolean(4, task.isIsCompleted());
      statement.setString(5, task.getNote());
      statement.setDate(6, new Date(task.getDeadline().getTime()));
      statement.setDate(7, new Date(task.getCreatedAt().getTime()));
      statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
      
      //Executa o statement
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
      //Estabelecendo conexão com o banco de dados
      connection = ConnecionFactory.getConnection();

      //Preparando a query
      statement = connection.prepareStatement(sql);

      //Setando os valores do statement
      statement.setInt(1, task.getIdProject());
      statement.setString(2, task.getName());
      statement.setString(3, task.getDescription());
      statement.setBoolean(4, task.isIsCompleted());
      statement.setString(5, task.getNote());
      statement.setDate(6, new Date(task.getDeadline().getTime()));
      statement.setDate(7, new Date(task.getCreatedAt().getTime()));
      statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
      statement.setInt(9, task.getId());
      
      //Executando a query
      statement.execute();
    } catch (Exception ex) {
      throw new RuntimeException("Erro ao atualizar tarefa" + ex.getMessage(), ex);
    } 

  }

  public void removeById(int taskId) throws SQLException {
    String sql = "DELETE FROM tasks WHERE id = ?";

    Connection connection = null;
    PreparedStatement statement = null;
    
    try {
      //Criando a conexão com o banco de dados
      connection = ConnecionFactory.getConnection();
      
      //Preparando o statement
      statement = connection.prepareStatement(sql);

      //Setando os valores
      statement.setInt(1, taskId);

      //Executando o statement
      statement.execute();
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
    
    //nova lista de tarefas
    List<task> tasks = new ArrayList<task>();

    try {
      //conecta ao banco de dados
      connection = ConnecionFactory.getConnection(); 

      //prepara o statement
      statement = connection.prepareStatement(sql); 

      //setando o valor que corresponde ao id do projeto
      statement.setInt(1, idProject);

      //valor retornado pelo statement
      resultSet = statement.executeQuery();

      //Enquanto houverem valores no resultSet
      while(resultSet.next()) {
        task task = new task(); 
        task.setId(resultSet.getInt("id"));
        task.setIdProject(resultSet.getInt("idProject"));
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));
        task.setIsCompleted(resultSet.getBoolean("completed"));
        task.setNote(resultSet.getString("notes"));
        task.setDeadline(resultSet.getDate("deadline"));
        task.setCreatedAt(resultSet.getDate("createdAt"));
        task.setUpdatedAt(resultSet.getDate("updatedAt"));

        //adiciona a tarefa na lista
        tasks.add(task);
            }
    } catch (Exception e) {
      throw new RuntimeException("Erro ao inserir a tarefa" + e.getMessage(), e);
    } finally {
      ConnecionFactory.closeConnection(connection, statement, resultSet);
    }

    //Lista de tarefas que foi criada e carregada com as tarefas do banco de dados
    return null;
  }

}
