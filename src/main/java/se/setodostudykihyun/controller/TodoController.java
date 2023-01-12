package se.setodostudykihyun.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.setodostudykihyun.dto.request.TodoRegisterRequest;
import se.setodostudykihyun.dto.response.TodoResponse;
import se.setodostudykihyun.service.TodoService;

@RestController
@RequestMapping(value = "/api/v1/todos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String TodoResponse register(@RequestBody TodoRegisterRequest request){
        return todoService.register(request);
    }

    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id){
        return todoService.get(id);
    }
}
