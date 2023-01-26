package se.setodostudykihyun.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import se.setodostudykihyun.dto.response.TodoResponse;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SortRepository extends PagingAndSortingRepository<TodoResponse, LocalDateTime > {
    Page<TodoResponse> findAll(Pageable pageable);
    Page<TodoResponse> finnAllByCreatedAt(LocalDateTime localDateTime, Pageable pageable);
}
