package se.setodostudykihyun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.setodostudykihyun.dto.request.TodoRegisterRequest;
import se.setodostudykihyun.dto.response.TodoResponse;
import se.setodostudykihyun.entity.Todo;
import se.setodostudykihyun.repository.TodoRepository;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponse register(TodoRegisterRequest request){
        Todo todo = new Todo(
                request.getTitle(),
                request.getContent()
        );

        Todo saveTodo = todoRepository.save(todo);

        return new TodoResponse(
                saveTodo.getId(),
                saveTodo.getTitle(),
                saveTodo.getContent()
        );
    }

    @Transactional(readOnly = true)
    public TodoResponse get(Long id){
        Todo todo = todoRepository.findById(id).get();

        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContent()
        );
    }
}
