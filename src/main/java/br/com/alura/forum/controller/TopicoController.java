package br.com.alura.forum.controller;

import br.com.alura.forum.assembler.ModelMapperAssembler;
import br.com.alura.forum.controller.dto.AtualizacaoTopicoDtoRecebido;
import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.dto.TopicoDtoRecebido;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoDtoRecebido topico, UriComponentsBuilder builder) {
        Topico topicoSalvo = topicoRepository.save(topico.converter(cursoRepository));
        URI uri = builder.path("/topicos/{id}").buildAndExpand(topicoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.toDto(topicoSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok().body(modelMapper.toDetalheDto(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoDtoRecebido topicoDtoRecebido) {
        Topico topico = topicoRepository.getReferenceById(id);
        modelMapper.atualizar(topico, topicoDtoRecebido);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
