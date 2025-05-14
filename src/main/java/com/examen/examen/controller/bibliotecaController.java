package com.examen.examen.controller;

import com.examen.examen.model.biblioteca;
import com.examen.examen.service.bibliotecaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin // permite llamadas desde tu HTML estático
@RestController
@RequestMapping("/bibliotecas")
public class bibliotecaController {

    private final bibliotecaService bibliotecaService;

    public bibliotecaController(bibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    /**
     * Obtiene la lista completa de bibliotecas.
     */
    @GetMapping
    public ResponseEntity<List<biblioteca>> obtenerTodas() {
        List<biblioteca> lista = bibliotecaService.obtenerTodas();
        return ResponseEntity.ok(lista);
    }

    /**
     * Obtiene una biblioteca por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<biblioteca> obtenerPorId(@PathVariable Integer id) {
        return bibliotecaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva biblioteca y devuelve la lista actualizada.
     */
    @PostMapping
    public ResponseEntity<List<biblioteca>> crear(@RequestBody biblioteca biblioteca) {
        bibliotecaService.guardar(biblioteca);
        return ResponseEntity.ok(bibliotecaService.obtenerTodas());
    }

    /**
     * Actualiza una biblioteca existente y devuelve la lista actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<List<biblioteca>> actualizar(@PathVariable Integer id,
                                                       @RequestBody biblioteca bibliotecaActualizada) {
        biblioteca actual = bibliotecaService.actualizar(id, bibliotecaActualizada);
        if (actual == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bibliotecaService.obtenerTodas());
    }

    /**
     * Elimina una biblioteca y devuelve la lista actualizada.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<List<biblioteca>> eliminar(@PathVariable Integer id) {
        bibliotecaService.eliminar(id);
        return ResponseEntity.ok(bibliotecaService.obtenerTodas());
    }
}