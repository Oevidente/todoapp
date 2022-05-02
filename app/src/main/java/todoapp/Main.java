package todoapp;

import java.sql.Connection;
import java.util.List;

import controller.ProjectController;
import model.project;
import util.ConnectionFactory;

public class Main {
    
    public static void main(String[] args) {
        // //Instancia o controlador
        // ProjectController projectController = new ProjectController();

        // //Instancia o objeto
        // project projeto = new project();

        // //Seta os valores
        // projeto.setName("Projeto de teste");
        // projeto.setDescription("Lorem impsum dolor sit amet consectetur adipiscing elit");

        // //Salva o objeto
        // projectController.save(projeto);

        ProjectController projectController = new ProjectController();
        project project = new project();
        project.setId(1);
        project.setName("NOVO NOME");
        project.setDescription("Lorem impsum dolor sit amet consectetur adipiscing elit");
        projectController.update(project);
        project.toString();

        
        //Instancia a lista
        List<project> projetos = projectController.getAll();
        System.out.println("Lista de projetos = " + projetos.size());
    }

    public Object getGreeting() {
      return null;
    }
}
