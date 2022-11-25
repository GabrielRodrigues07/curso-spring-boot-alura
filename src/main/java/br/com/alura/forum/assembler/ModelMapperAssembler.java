package br.com.alura.forum.assembler;

import br.com.alura.forum.controller.dto.AtualizacaoTopicoDtoRecebido;
import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.RespostaDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Resposta;
import br.com.alura.forum.model.Topico;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ModelMapperAssembler {

    private ModelMapper modelMapper;

    public List<TopicoDto> toCollectionDto(List<Topico> topicos) {
        return topicos.stream().map(topico -> modelMapper.map(topico, TopicoDto.class)).collect(Collectors.toList());
    }

    public TopicoDto toDto(Topico topico) {
        return modelMapper.map(topico, TopicoDto.class);
    }

    public DetalhesDoTopicoDto toDetalheDto(Topico topico) {
        return modelMapper.map(topico, DetalhesDoTopicoDto.class);
    }

    public List<RespostaDto> toRespostaDto(List<Resposta> respostas) {
        return respostas.stream().map(resposta -> modelMapper.map(resposta, RespostaDto.class)).collect(Collectors.toList());
    }

    public void atualizar(Topico topico, AtualizacaoTopicoDtoRecebido topicoDtoRecebido) {
        modelMapper.map(topicoDtoRecebido, topico);
    }
}
