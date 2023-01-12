package se.setodostudykihyun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.setodostudykihyun.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
}
