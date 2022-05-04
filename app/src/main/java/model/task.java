package model;

import java.util.Date;

public class task {
  
  private int id;
  private int idProject;
  private String name;
  private String description;
  private String note;
  private boolean isCompleted;
  private Date deadline;
  private Date createdAt;
  private Date updatedAt;

  public task(int id, int idProject, String name, String description, String note, boolean isCompleted, Date deadline, Date createdAt, Date updatedAt) {
      this.id = id;
      this.idProject = idProject;
      this.name = name;
      this.description = description;
      this.note = note;
      this.isCompleted = isCompleted;
      this.deadline = deadline;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
  }
  
  public task(){
    this.isCompleted = false;
    this.deadline = new Date();
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }

  public int getId() {
      return id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public int getIdProject() {
      return idProject;
  }

  public void setIdProject(int idProject) {
      this.idProject = idProject;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  public String getDescription() {
      return description;
  }

  public void setDescription(String description) {
      this.description = description;
  }

  public String getNote() {
      return note;
  }

  public void setNote(String note) {
      this.note = note;
  }

  public boolean isIsCompleted() {
      return isCompleted;
  }

  public void setIsCompleted(boolean isCompleted) {
      this.isCompleted = isCompleted;
  }

  public Date getDeadline() {
      return deadline;
  }

  public void setDeadline(Date deadline) {
      this.deadline = deadline;
  }

  public Date getCreatedAt() {
      return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
      return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
      return "task{" + "id=" + id + ", idProject=" + idProject + ", name=" + name + ", description=" + description + ", note=" + note + ", isCompleted=" + isCompleted + ", deadline=" + deadline + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
  }



}
