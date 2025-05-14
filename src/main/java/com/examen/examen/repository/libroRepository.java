package com.examen.examen.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.examen.examen.model.libro;

public interface libroRepository extends JpaRepository<libro, Integer> {
}
