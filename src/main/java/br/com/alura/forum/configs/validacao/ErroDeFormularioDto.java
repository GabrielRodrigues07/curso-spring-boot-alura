package br.com.alura.forum.configs.validacao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroDeFormularioDto {

    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;

    List<Campo> campos;

    @Getter
    @AllArgsConstructor
    public static class Campo {
        private String nome;
        private String mensagem;
    }
}
