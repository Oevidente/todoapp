package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.project;
import util.ConnectionFactory;

public class ProjectController {

  public void save(project project) {
    String sql = "INSERT INTO projects (name," 
    + "description,"
    + "createdAt," 
    + "updatedAt) VALUES (?,?,?,?)";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      //Instancia a conexão com o banco de dados
      connection = ConnectionFactory.getConnection();

      //Cria o statement para inserir os dados
      statement = connection.prepareStatement(sql);

      //Seta os valores
      statement.setString(1, project.getName());
      statement.setString(2, project.getDescription());
      statement.setDate(3, new Date(project.getCreatedAt().getTime()));
      statement.setDate(4, new Date(project.getUpdatedAt().getTime()));

      //Executa o statement
      statement.execute();
    } catch (SQLException ex) {
      throw new RuntimeException("Erro ao salvar projeto" + ex.getMessage(), ex);
    } finally {
      ConnectionFactory.closeConnection(connection, statement);

    }
  }

  public void update(project project) {

    String sql = "UPDATE projects SET name = ?, description = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        //Cria uma conex�o com o banco
        conn = ConnectionFactory.getConnection();
        //Cria um PreparedStatment, classe usada para executar a query
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, project.getName());
        stmt.setString(2, project.getDescription());
        stmt.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
        stmt.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
        stmt.setInt(5, project.getId());

        //Executa a sql para inser��o dos dados
        stmt.execute();
    } catch (SQLException ex) {
        throw new RuntimeException("Erro em atualizar o projeto\n" + ex.getMessage(), ex);
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar a conexão", ex);
        }
    }
}
  
  public void removeById(int idProject) {
    String sql = "DELETE FROM projects WHERE id = ?";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      //Instancia a conexão com o banco de dados
      connection = ConnectionFactory.getConnection();

      //Cria o statement para inserir os dados
      statement = connection.prepareStatement(sql);

      //Seta os valores
      statement.setInt(1, idProject);

      //Executa o statement
      statement.execute();
    } catch (SQLException ex) {
      throw new RuntimeException("Erro ao remover projeto" + ex.getMessage(), ex);
    } finally {
      ConnectionFactory.closeConnection(connection, statement);
    }
  }

  public List<project> getAll() {
    String sql = "SELECT * FROM projects";
    
    List<project> projects = new ArrayList<>();

    Connection connection = null;
    PreparedStatement statement = null;

    //Classe que vai armazenar os dados do banco de dados
    ResultSet resultSet = null;

    try {
      //Instancia a conexão com o banco de dados
      connection = ConnectionFactory.getConnection();

      //Cria o statement para inserir os dados
      statement = connection.prepareStatement(sql);

      //Executa o statement
      resultSet = statement.executeQuery();

      //Percorre os resultados
      while (resultSet.next()) {
        project project = new project();
        project.setId(resultSet.getInt("id"));
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        project.setCreatedAt(resultSet.getDate("createdAt"));
        project.setUpdatedAt(resultSet.getDate("updatedAt"));

        projects.add(project);
      }
    } catch (SQLException ex) {
      throw new RuntimeException("Erro ao buscar projetos" + ex.getMessage(), ex);
    } finally {
      ConnectionFactory.closeConnection(connection, statement, resultSet);
    }
    return projects;
  }

}
