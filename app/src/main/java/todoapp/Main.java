package todoapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import controller.ProjectController;
import controller.TaskController;
import model.project;
import model.task;
import util.ConnectionFactory;

public class Main {
    
    public static void main(String[] args) throws SQLException {
        // //Instancia o controlador
        // ProjectController projectController = new ProjectController();
        // //Instancia o objeto
        // project projeto = new project();
        // //Seta os valores
        // projeto.setName("Projeto de teste 1");
        // projeto.setDescription("Descrição do projeto de teste 1");
        // //Salva o objeto
        // projectController.save(projeto);

        // //atualiza o projeto
        ProjectController projectController = new ProjectController();
        // project projeto = new project();
        // projeto.setId(1);
        // projeto.setName("Nome do projeto teste");
        // projeto.setDescription("Uma descrição do projeto teste");
        // projectController.update(projeto);
        // // projeto.toString();

        
        //Instancia a lista
        // List<project> projetos = projectController.getAll();
        // System.out.println("Total de projetos = " + projetos.size());

        //Remove o projeto
        // projectController.removeById(4);

        //---------------------------------------------------------------------------------------------------------------------//

        //Instancia o controlador
        TaskController taskController = new TaskController();
        //Instancia o objeto
        // task tarefa = new task();
        //Seta os valores
        // tarefa.setName("Tarefa de teste");
        // tarefa.setDescription("Lorem impsum dolor sit amet consectetur adipiscing elit");
        // tarefa.setNote("Anotação da tarefa de teste");
        // tarefa.setIdProject(5);
        // tarefa.setIsCompleted(false);
        
        // taskController.save(tarefa);

        //atualizando a tarefa
        // tarefa.setIdProject(1);
        // tarefa.setName("Tarefa de teste 1");
        // tarefa.setDescription("Lorem");
        // tarefa.setIsCompleted(false);
        // tarefa.setNote("Anotação da tarefa de teste");
        // tarefa.setId(1);

        //Deletando a tarefa
        taskController.removeById(1);
    }

    public Object getGreeting() {
      return null;
    }
}
