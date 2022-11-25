package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Resposta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespostaDto {

    private Long id;
    private String mensagem;
    private OffsetDateTime dataCriacao;
    private String nomeAutor;

//    public RespostaDto(Resposta resposta) {
//        this.id = resposta.getId();
//        this.mensagem = resposta.getMensagem();
//        this.dataCriacao = resposta.getDataCriacao();
//        this.nomeAutor = resposta.getAutor().getNome();
//    }
}
