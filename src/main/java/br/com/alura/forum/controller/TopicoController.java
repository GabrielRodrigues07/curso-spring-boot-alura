package br.com.alura.forum.controller;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @GetMapping
    @ResponseBody
    public List<Topico> listar() {
        Topico topico = new Topico("Duvida", "Duvida com Spring", new Curso("Spring", "Programação"));

        Topico topico1 = new Topico();
        topico1.setTitulo("Duvida");
        topico1.setMensagem("Duvida com Spring");
        topico1.setCurso(new Curso("Spring", "Programação"));
        return Arrays.asList(topico, topico, topico1);
    }
}
