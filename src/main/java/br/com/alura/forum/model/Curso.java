package br.com.alura.forum.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Curso {

    private Long id;
    private String nome;
    private String categoria;

    public Curso(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
}
