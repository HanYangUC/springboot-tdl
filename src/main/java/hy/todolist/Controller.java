package hy.todolist;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.System;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static java.lang.System.out;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/todo")
public class Controller {
    @Autowired
    private TodoRepository todoRepository;

    // @GetMapping("/todo/{id}")
    // public

    @CrossOrigin()
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewTodo(@RequestBody Todo todoRequest) {
        // System.out.println(todoRequest);
        // System.out.println(todoRequest.getTodo());
        // System.out.println(todoRequest.getTodoDesc());
        String tod = todoRequest.getTodo();
        String des = todoRequest.getTodoDesc();
        // out.println(tod + des);
        if (tod.isEmpty() || des.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error while saving\"}");
        }
        Todo tdl = new Todo();
        tdl.setTodo(todoRequest.getTodo());
        tdl.setTodoDesc(todoRequest.getTodoDesc());
        tdl.setStatus(false);
        todoRepository.save(tdl);
        return ResponseEntity.ok().body("{\"message\": \"saved successfully\"}");

        // return "saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Todo> getAllTodos() {
        // System.out.println("asdasd???????????????????");
        return todoRepository.findAll();
    }

    // @CrossOrigin()
    // @GetMapping(path = "/alla")
    // public @ResponseBody Iterable<Todo> getAllTodosa() {
    //     // out.println("asdasd???????????????????");

    //     return todoRepository.findAll();
    // }

    // @GetMapping(path="/{id}")
    // public static Optional<Todo> search(@RequestParam Integer id) {
    // return TodoRepository.findById(id);
    // }
    @CrossOrigin()
    @GetMapping(path = "/{id}")
    public @ResponseBody Todo getTodoById(@PathVariable Integer id) {
        // Retrieve the Todo by its ID
        // out.println("hoho todo/id");
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
    }

    // @CrossOrigin()
    // @PostMapping(path = "/update")
    // public @ResponseBody ResponseEntity<String> updateTodo(@RequestBody Todo todo) {
    //     Todo updateTodo = todoRepository.findById(todo.getId())
    //             .orElseThrow(() -> new RuntimeException("Todo not found with id " + todo.getId()));
    //     out.println("or this");

    //     updateTodo.setTodo(todo.getTodo());
    //     updateTodo.setTodoDesc(todo.getTodoDesc());
    //     updateTodo.setStatus(todo.getStatus());
    //     todoRepository.save(updateTodo);

    //     return ResponseEntity.ok().body("{\"message\": \"saved successfully\"}");
    // }

    @CrossOrigin()
    @PutMapping(path = "/update/{id}")
    public @ResponseBody ResponseEntity<String> updateTodoo(@PathVariable Integer id, @RequestBody Todo todoRequest) {
        Todo updateTodo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
        // out.println("this right");
        updateTodo.setTodo(todoRequest.getTodo());
        updateTodo.setTodoDesc(todoRequest.getTodoDesc());
        updateTodo.setStatus(todoRequest.getStatus());
        todoRepository.save(updateTodo);

        return ResponseEntity.ok().body("{\"message\": \"updated successfully\"}");
    }
    // @CrossOrigin()
    // @PutMapping(path = "/update/{id}")
    // public @ResponseBody String updateTodoo(@PathVariable Integer id, @RequestBody Todo todoRequest) {
    //     Todo updateTodo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
        
    //     updateTodo.setTodo(todoRequest.getTodo());
    //     updateTodo.setTodoDesc(todoRequest.getTodoDesc());
    //     updateTodo.setStatus(todoRequest.getStatus());
    //     todoRepository.save(updateTodo);

    //     return "ok done";
    // }

    @CrossOrigin()
    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody ResponseEntity<String> deleteTodo(@PathVariable Integer id) {
        // out.println("delete" + id + "a");
        todoRepository.deleteById(id);

        return ResponseEntity.ok().body("{\"message\": \"Deleted successfully\"}");
    }
}
