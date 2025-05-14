package com.examen.examen.model;

import java.time.LocalDate;
import jakarta.persistence.*;


@Entity
public class libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 25, nullable = false)
    private String titulo;

    @Column(length = 25, nullable = false)
    private String autor;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "id_biblioteca", nullable = false)
    private biblioteca biblioteca;

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public LocalDate getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDate fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public biblioteca getBiblioteca() { return biblioteca; }
    public void setBiblioteca(biblioteca biblioteca) { this.biblioteca = biblioteca; }
}
