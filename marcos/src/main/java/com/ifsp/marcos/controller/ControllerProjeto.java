package com.ifsp.marcos.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifsp.marcos.model.Jogo;
import com.ifsp.marcos.model.JogoRepository;

@Controller
public class ControllerProjeto {

    @Autowired
    private JogoRepository jogoRepository;

    @GetMapping("/telaInicio")
    public String telaInicio() {
        return "telaInicial";
    }
    @GetMapping("/login")
    public String login() {
    return "login";
    }
    @GetMapping("/cadastro")
    public String cadastro() {
    return "cadastro";
    }
    @PostMapping("/cadastro")
    public String cadastrarUsuario(
        @RequestParam(required = false) String telefone,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String senha,
        @RequestParam(required = false) String dataNascimento
    ) {
        return "redirect:/login";
    }
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Jogo> jogos = jogoRepository.findAll();
        model.addAttribute("jogos", jogos);
        return "jogos";
    }
    
    @GetMapping("/formularioJogo")
    public String formularioJogo() {   
        return "formularioJogos";
    }

    @PostMapping("/cadastrarJogo")
    public String cadastrarJogo(
        @RequestParam String titulo,
        @RequestParam String plataforma,
        @RequestParam LocalDate anoLancamento,
        @RequestParam String historia,
        @RequestParam int preco,
        @RequestParam String requisitosSys
    ){
        jogoRepository.save(new Jogo(titulo, plataforma, anoLancamento, historia, preco, requisitosSys));
        return "redirect:/listar";
    }
    @GetMapping("/pesquisar")
    public String pesquisar(@RequestParam(required = false) String titulo, Model model) {
        List<Jogo> jogos;
        
        if (titulo != null && !titulo.isEmpty()) {
            jogos = jogoRepository.findByTituloContaining(titulo);
        } else {
            jogos = jogoRepository.findAll();
        }

        model.addAttribute("jogos", jogos);
        return "telaInicial";
    }
}