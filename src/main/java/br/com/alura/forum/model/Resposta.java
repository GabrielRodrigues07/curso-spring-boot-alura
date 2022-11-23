package br.com.alura.forum.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @ManyToOne
    private Topico topico;
    private OffsetDateTime dataCriacao = OffsetDateTime.now();

    @ManyToOne
    private Usuario autor;
    private Boolean solucao = false;
}
