package com.examen.examen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 25, nullable = false)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ubicacion;

    @Column(nullable = false)
    private Integer capacidadTotal;

    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // Evita la recursión infinita al serializar
    private List<libro> libros;

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public Integer getCapacidadTotal() { return capacidadTotal; }
    public void setCapacidadTotal(Integer capacidadTotal) { this.capacidadTotal = capacidadTotal; }

    public List<libro> getLibros() { return libros; }
    public void setLibros(List<libro> libros) { this.libros = libros; }
}
