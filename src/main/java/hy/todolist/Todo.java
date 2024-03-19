package hy.todolist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private String todo;
    private String todoDesc;
    private Boolean isCompleted;

    public Integer getId() {
        return id;
    }

    public String getTodo() {
        return todo;
    }

    public String getTodoDesc() {
        return todoDesc;
    }

    public Boolean getStatus() {
        return isCompleted;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setTodoDesc(String todoDesc) {
        this.todoDesc = todoDesc;
    }
    public void setStatus(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    // public getaa() {
    //     return "aa"
    // }
}
