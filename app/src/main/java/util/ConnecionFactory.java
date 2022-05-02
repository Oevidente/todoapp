package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnecionFactory {
  
  //Estipular o driver de conexão (banco de dados.driver)
  //jdbc é uma dependência do java para acessar o banco de dados
  public static final String DRIVER = "com.mysql.jdbc.Driver";

  //Estipular o caminho do banco de dados (driver:banco mysql:servidor:porta/banco de dados)
  public static final String URL = "jdbc:mysql://localhost:3306/todoapp";

  //Estipular o usuário do banco de dados
  public static final String USER = "root";

  //Estipular a senha do banco de dados
  public static final String PASS = "";

//--------------------------------------------/

  //Método para estabelecer a conexão com o banco de dados
  //Métodos estáticos não precisam de instância para serem chamados
  public static Connection getConnection() {
    //método de captura de erros (tratar erros)
    try {
      //diz à classe qual o driver de conexão (no caso o jdbc)
      Class.forName(DRIVER);
    
      //diz ao gerenciador de driver a url, usuário e senha que serão utilizados para solicitar uma conexão
      return DriverManager.getConnection(URL, USER, PASS);
    } 
    //se ocorrer algum erro no código acima, o catch irá capturar o erro e retornar uma exceção
    catch (Exception ex) {
      throw new RuntimeException("Erro na conexão com o banco de dados", ex);
    }
  }

  public static void closeConnection(Connection con) {
    try {
      if (con != null) {
        con.close();
      }
    } catch (Exception ex) {
      throw new RuntimeException("Erro ao fechar conexão com o banco de dados", ex);
    }
  }

}
