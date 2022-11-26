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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@AllArgsConstructor
public class TopicoController {

    private ModelMapperAssembler modelMapper;

    private TopicoRepository topicoRepository;

    private CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDto> listar(@RequestParam(required = false) String nomeCurso, @PageableDefault(size = 5, sort = "titulo", direction = Sort.Direction.DESC) Pageable pageable) {
        if (nomeCurso == null) {
            return topicoRepository.findAll(pageable).map(topico -> modelMapper.toDto(topico));
        }
        return topicoRepository.findByCursoNome(nomeCurso, pageable).map(topico -> modelMapper.toDto(topico));
    }

//    @GetMapping
//    public ResponseEntity<Page> listar(@RequestParam(required = false) String nomeCurso,
//                                             @RequestParam int pagina,
//                                             @RequestParam int qtd) {
//
//        Pageable paginacao = PageRequest.of(pagina, qtd);
//
//
//        if (nomeCurso == null) {
//            return ResponseEntity.ok().body(topicoRepository.findAll(paginacao).map(topicos -> modelMapper.toDto(topicos)));
//        } else {
//            return ResponseEntity.ok().body(topicoRepository.findByCursoNome(nomeCurso, paginacao).map(topico -> modelMapper.toDto(topico)));
//        }
//    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoDtoRecebido topico, UriComponentsBuilder builder) {
        Topico topicoSalvo = topicoRepository.save(topico.converter(cursoRepository));
        URI uri = builder.path("/topicos/{id}").buildAndExpand(topicoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.toDto(topicoSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        return optionalTopico.map(topico -> ResponseEntity.ok().body(modelMapper.toDetalheDto(topico))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoDtoRecebido topicoDtoRecebido) {

        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            modelMapper.atualizar(optionalTopico.get(), topicoDtoRecebido);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            topicoRepository.deleteById(optionalTopico.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
