package br.com.alura.forum.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter @Setter
public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private OffsetDateTime dataCriacao;

//    public static List<TopicoDto> converter(List<Topico> topicos) {
//        TopicoDto topicoDto = modelMapper.map(topicos, TopicoDto.class);
//        return
//    }
}
