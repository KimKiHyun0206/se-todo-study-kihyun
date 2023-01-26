package se.setodostudykihyun.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.setodostudykihyun.dto.request.TodoRegisterRequest;
import se.setodostudykihyun.dto.request.TodoUpdateRequest;
import se.setodostudykihyun.dto.response.TodoResponse;
import se.setodostudykihyun.repository.SortRepository;
import se.setodostudykihyun.service.TodoService;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/todos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final SortRepository sortRepository;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TodoResponse register(@RequestBody TodoRegisterRequest request) {
        return todoService.register(request);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id) {
        return todoService.get(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public Page<TodoResponse> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return todoService.getAll(pageable);
    }

    // TODO: 2023-01-26 정렬
    public Page<TodoResponse> getAllAndSort(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<TodoResponse> result = sortRepository
                .finnAllByCreatedAt(
                        LocalDateTime.parse("createdAt"),
                        PageRequest.of(
                                0,
                                10,
                                Sort.by(Sort.Order.desc("createdAt"))
                        )
                );

        return result;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/{id}")
    public TodoResponse update(
            @PathVariable Long id,
            @RequestBody TodoUpdateRequest request
    ) {
        return todoService.update(id, request);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }
}