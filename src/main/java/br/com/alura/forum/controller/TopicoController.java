package br.com.alura.forum.controller;

import br.com.alura.forum.assembler.ModelMapperAssembler;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@AllArgsConstructor
public class TopicoController {

    private ModelMapperAssembler modelMapper;

    private TopicoRepository topicoRepository;

//    @GetMapping
//    public Page<TopicoDto> listar(Pageable pageable) {
//        return topicoRepository.findAll(pageable).map(topico -> modelMapper.converter(topico));
//    }

    @GetMapping
    public List<TopicoDto> listar(String nomeCurso) {
        if (nomeCurso == null) {
            return modelMapper.toCollectionDto(topicoRepository.findAll());
        }
        else {
            return modelMapper.toCollectionDto(topicoRepository.findByCursoNome(nomeCurso));
        }
    }
}
