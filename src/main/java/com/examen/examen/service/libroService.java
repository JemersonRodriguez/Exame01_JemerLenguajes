package com.examen.examen.service;

import com.examen.examen.model.biblioteca;
import com.examen.examen.model.libro;
import com.examen.examen.repository.bibliotecaRepository;
import com.examen.examen.repository.libroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class libroService {

    private final libroRepository libroRepository;
    private final bibliotecaRepository bibliotecaRepository;

    public libroService(libroRepository libroRepository,
                        bibliotecaRepository bibliotecaRepository) {
        this.libroRepository = libroRepository;
        this.bibliotecaRepository = bibliotecaRepository;
    }

    public libro guardar(libro libro) {
        if (libro.getBiblioteca() == null || libro.getBiblioteca().getId() == null) {
            throw new IllegalArgumentException("Debe especificar una biblioteca válida");
        }
        biblioteca bib = bibliotecaRepository.findById(libro.getBiblioteca().getId())
            .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada con id: " + libro.getBiblioteca().getId()));
        libro.setBiblioteca(bib);
        return libroRepository.save(libro);
    }

    public List<libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Optional<libro> obtenerPorId(Integer id) {
        return libroRepository.findById(id);
    }

    public libro actualizar(Integer id, libro libroActualizado) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setAutor(libroActualizado.getAutor());
            libro.setFechaPublicacion(libroActualizado.getFechaPublicacion());
            // reasignar biblioteca si se cambió
            if (libroActualizado.getBiblioteca() != null) {
                libro.setBiblioteca(
                    bibliotecaRepository.findById(libroActualizado.getBiblioteca().getId())
                        .orElseThrow(() -> new RuntimeException("Biblioteca no encontrada"))
                );
            }
            return libroRepository.save(libro);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
    }

    public void eliminar(Integer id) {
        libroRepository.deleteById(id);
    }
}
