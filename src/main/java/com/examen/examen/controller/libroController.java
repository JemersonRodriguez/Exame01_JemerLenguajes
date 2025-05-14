package com.examen.examen.controller;

import com.examen.examen.model.libro;
import com.examen.examen.service.libroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
    
@RestController
@RequestMapping("/libros")
public class libroController {

    private final libroService libroService;

    public libroController(libroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody libro libro) {
        try {
            libro guardado = libroService.guardar(libro);
            // Evita la recursión: eliminamos la lista de libros de la biblioteca antes de devolverla
            guardado.getBiblioteca().setLibros(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<libro>> listar() {
        List<libro> todos = libroService.obtenerTodos();
        // romper recursión en cada uno
        todos.forEach(l -> l.getBiblioteca().setLibros(null));
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return libroService.obtenerPorId(id)
            .map(l -> {
                l.getBiblioteca().setLibros(null);
                return ResponseEntity.ok(l);
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody libro libro) {
        try {
            libro actualizado = libroService.actualizar(id, libro);
            actualizado.getBiblioteca().setLibros(null);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
