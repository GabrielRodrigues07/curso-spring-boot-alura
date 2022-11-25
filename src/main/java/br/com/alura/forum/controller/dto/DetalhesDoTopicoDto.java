package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.StatusTopico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalhesDoTopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private OffsetDateTime dataCriacao;
    private String nomeAutor;
    private StatusTopico status;

    private List<RespostaDto> respostas;

//    public DetalhesDoTopicoDto(Topico topico) {
//        this.id = topico.getId();
//        this.titulo = topico.getTitulo();
//        this.mensagem = topico.getMensagem();
//        this.dataCriacao = topico.getDataCriacao();
//        this.nomeAutor = topico.getAutor().getNome();
//        this.status = topico.getStatus();
//        this.respostas = new ArrayList<>();
//        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
//    }
}
