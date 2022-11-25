package br.com.alura.forum.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AtualizacaoTopicoDtoRecebido {

    @NotBlank
    @Length(min = 5)
    private String titulo;

    @NotBlank @Length(min = 10)
    private String mensagem;
}
