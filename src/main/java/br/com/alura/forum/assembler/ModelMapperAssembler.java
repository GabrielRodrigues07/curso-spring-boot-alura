package br.com.alura.forum.assembler;

import br.com.alura.forum.controller.dto.TopicoDto;
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

    public TopicoDto converter(Topico topico) {
        return modelMapper.map(topico, TopicoDto.class);
    }
}
