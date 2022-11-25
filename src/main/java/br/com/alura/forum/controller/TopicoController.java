package br.com.alura.forum.controller;

import br.com.alura.forum.assembler.ModelMapperAssembler;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.dto.TopicoDtoRecebido;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@AllArgsConstructor
public class TopicoController {

    private ModelMapperAssembler modelMapper;

    private TopicoRepository topicoRepository;

    private CursoRepository cursoRepository;

//    @GetMapping
//    public Page<TopicoDto> listar(Pageable pageable) {
//        return topicoRepository.findAll(pageable).map(topico -> modelMapper.converter(topico));
//    }

    @GetMapping
    public ResponseEntity<List<TopicoDto>> listar(String nomeCurso) {
        if (nomeCurso == null) {
            return ResponseEntity.ok().body(modelMapper.toCollectionDto(topicoRepository.findAll()));
        }
        else {
            return ResponseEntity.ok().body(modelMapper.toCollectionDto(topicoRepository.findByCursoNome(nomeCurso)));
        }
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoDtoRecebido topico, UriComponentsBuilder builder) {
        Topico topicoSalvo = topicoRepository.save(topico.converter(cursoRepository));
        URI uri = builder.path("/topicos/{id}").buildAndExpand(topicoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.toDto(topicoSalvo));
    }
}
