package com.examen.examen.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.examen.examen.repository.bibliotecaRepository;

import lombok.AllArgsConstructor;

import java.util.*;
import com.examen.examen.model.biblioteca; // Ensure this is the correct package for the biblioteca class
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class bibliotecaService {
    @Autowired
    private bibliotecaRepository bibliotecaRepository;

    // Listar todas las bibliotecas
    public List<biblioteca> obtenerTodas() {
        return bibliotecaRepository.findAll();
    }

    // Obtener una biblioteca por ID
    public Optional<biblioteca> obtenerPorId(Integer id) {
        return bibliotecaRepository.findById(id);
    }

    // Guardar una nueva biblioteca
    public biblioteca guardar(biblioteca biblioteca) {
        return bibliotecaRepository.save(biblioteca);
    }

    // Actualizar una biblioteca existente
    public biblioteca actualizar(Integer id, biblioteca bibliotecaActualizada) {
        return bibliotecaRepository.findById(id).map(biblioteca -> {
            biblioteca.setNombre(bibliotecaActualizada.getNombre());
            biblioteca.setUbicacion(bibliotecaActualizada.getUbicacion());
            biblioteca.setCapacidadTotal(bibliotecaActualizada.getCapacidadTotal());
            return bibliotecaRepository.save(biblioteca);
        }).orElse(null);
    }

    // Eliminar una biblioteca por ID
    public void eliminar(Integer id) {
        bibliotecaRepository.deleteById(id);
    }
}
