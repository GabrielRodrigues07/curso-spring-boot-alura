package br.com.alura.forum.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
}
