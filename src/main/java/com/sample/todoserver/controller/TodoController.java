package com.sample.todoserver.controller;

import com.sample.todoserver.model.TodoModel;
import com.sample.todoserver.model.TodoRequest;
import com.sample.todoserver.model.TodoResponse;
import com.sample.todoserver.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController //해당 class는 REST API를 처리하는 Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/")
public class TodoController {
    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){
        log.info("CREAT");
        if (ObjectUtils.isEmpty(request.getTitle()))
            return ResponseEntity.badRequest().build();
        if (ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);
        if (ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);

        TodoModel result = this.service.add(request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        log.info("Read One");
        TodoModel result = this.service.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll(){
        log.info("Read All");
        List<TodoModel> list = this.service.searchAll();
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request){
        log.info("UPDATE");
        TodoModel result = this.service.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        log.info("DELETE");
        this.service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        log.info("DELETE ALL");
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }

}
