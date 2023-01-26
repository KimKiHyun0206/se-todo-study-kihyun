package se.setodostudykihyun.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.setodostudykihyun.dto.request.TodoRegisterRequest;
import se.setodostudykihyun.dto.request.TodoUpdateRequest;
import se.setodostudykihyun.dto.response.TodoResponse;
import se.setodostudykihyun.entity.Todo;
import se.setodostudykihyun.repository.TodoRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {
    private final TodoRepository todoRepository;


    @Transactional
    public TodoResponse register(TodoRegisterRequest request) {
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
    public TodoResponse get(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Resource"));

        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContent()
        );
    }

    @Transactional(readOnly = true)
    public Page<TodoResponse> getAll(Pageable pageable) {
        return todoRepository.findAll(pageable)
                .map(
                        todo -> new TodoResponse(
                                todo.getId(),
                                todo.getTitle(),
                                todo.getContent()
                        )
                );
    }

    @Transactional
    public TodoResponse update(Long id, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Resource"));

        todo.updateTodo(request.getTitle(), request.getContent());

        log.info("update todo. {}", todo.getId());

        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContent()
        );
    }

    @Transactional
    public void delete(Long id){
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
            log.info("delete todo. {}", id);
        }
    }
}
