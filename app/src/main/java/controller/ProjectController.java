package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.project;
import util.ConnecionFactory;

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
      connection = ConnecionFactory.getConnection();

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
      ConnecionFactory.closeConnection(connection, statement);

    }
  }

  public void update(project project) {
    String sql = "UPDATE projects SET "
    + "name = ?"
    + "description = ?"
    + "createdAt = ?"
    + "updatedAt = ?"
    + "WHERE id = ?";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      //Instancia a conexão com o banco de dados
      connection = ConnecionFactory.getConnection();

      //Cria o statement para inserir os dados
      statement = connection.prepareStatement(sql);

      //Seta os valores
      statement.setString(1, project.getName());
      statement.setString(2, project.getDescription());
      statement.setDate(3, new Date(project.getCreatedAt().getTime()));
      statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
      statement.setInt(5, project.getId());

      //Executa o statement
      statement.execute();
    } catch (Exception ex) {
      throw new RuntimeException("Erro ao atualizar projeto" + ex.getMessage(), ex);
    }
  }

  public List<project> findAll() {
    String sql = "SELECT * FROM projects";
    
    List<project> projects = new ArrayList<>();

      Connection connection = null;
      PreparedStatement statement = null;

      //Classe que vai armazenar os dados do banco de dados
      ResultSet resultSet = null;

      try {
        //Instancia a conexão com o banco de dados
        connection = ConnecionFactory.getConnection();

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
        ConnecionFactory.closeConnection(connection, statement, resultSet);
      }
      return projects;
    }

    public void removeById(int idProject) {
      String sql = "DELETE FROM projects WHERE id = ?";

      Connection connection = null;
      PreparedStatement statement = null;

      try {
        //Instancia a conexão com o banco de dados
        connection = ConnecionFactory.getConnection();

        //Cria o statement para inserir os dados
        statement = connection.prepareStatement(sql);

        //Seta os valores
        statement.setInt(1, idProject);

        //Executa o statement
        statement.execute();
      } catch (SQLException ex) {
        throw new RuntimeException("Erro ao remover projeto" + ex.getMessage(), ex);
      } finally {
        ConnecionFactory.closeConnection(connection, statement);
      }
    }

}
