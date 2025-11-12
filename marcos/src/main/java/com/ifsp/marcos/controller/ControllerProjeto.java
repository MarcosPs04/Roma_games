package com.ifsp.marcos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ifsp.marcos.model.Usuario;
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
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
    return "cadastro";
    }
    @PostMapping("/cadastroUser")
    public String cadastroUser(@ModelAttribute Usuario usuario) {
        jogoRepository.saveUser(usuario);
        return "redirect:/telaInicio";
    }

    @GetMapping("/listarJogos")
    public String listarJogos(Model model) {
        List<Jogo> jogos = jogoRepository.findAllJogos();
        model.addAttribute("jogos", jogos);
        return "jogos";
    }
    @PostMapping("/jogo/{id}/deletar")
    public String deletarJogo(@PathVariable long id) {
        jogoRepository.deleteJogo(id);
        return "redirect:/listarJogos";
    }
    @GetMapping("DeleteAllJogos")
    public String deleteAllJogos() {
        jogoRepository.deleteAllJogos();
        return "redirect:/listarJogos";
    }
    @GetMapping("/jogo/{id}/editar")
    public String editarJogo(@PathVariable long id, Model model) {
        Jogo jogo = jogoRepository.findById(id);
        model.addAttribute("jogo", jogo);
        return "formularioJogos";
    }


    @GetMapping("/listarUsers")
    public String listarUsers(Model model) {
        List<Usuario> usuarios = jogoRepository.findAllUsers();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
    @PostMapping("/usuario/{id}/deletar")
    public String deletarUser(@PathVariable long id) {
        jogoRepository.deleteUser(id);
        return "redirect:/listarUsers";
    }
    @GetMapping("DeleteAllUsers")
    public String deleteAllUsers() {
        jogoRepository.deleteAllUsers();
        return "redirect:/listarUsers";
    }
    
    @GetMapping("/usuario/{id}/editar")
    public String editarUser(@PathVariable long id, Model model) {
        Usuario usuario = jogoRepository.findUserById(id);
        model.addAttribute("usuario", usuario);
        return "cadastro";
    }

    @GetMapping("/formularioJogo")
    public String formularioJogo(Model model) {
        model.addAttribute("jogo", new Jogo());   
        return "formularioJogos";
    }
    @PostMapping("/cadastroJogo")
    public String cadastroJogo(@ModelAttribute Jogo jogo) {
        jogoRepository.saveJogo(jogo);
        return "redirect:/telaInicio";
    }
    @GetMapping("/pesquisarJogo")
    public String pesquisarJogo(@RequestParam(required = false) String titulo, Model model) {
        List<Jogo> jogos;
        
        if (titulo != null && !titulo.isEmpty()) {
            jogos = jogoRepository.findByTituloContaining(titulo);
        } else {
            jogos = jogoRepository.findAllJogos();
        }

        model.addAttribute("jogos", jogos);
        return "telaInicial";
    }
    @GetMapping("/pesquisarJogoADM")
    public String pesquisarJogoADM(@RequestParam(required = false) String id, Model model) {
        List<Jogo> jogos;
        
        if (id != null && !id.isEmpty()) {
            jogos = jogoRepository.findByIdContaining(id);
        } else {
            jogos = jogoRepository.findAllJogos();
        }

        model.addAttribute("jogos", jogos);
        return "jogos";
    }

    @GetMapping("/pesquisarUser")
    public String pesquisarUser(@RequestParam(required = false) String id, Model model) {
        List<Usuario> usuarios;
        
        if (id != null && !id.isEmpty()) {
            usuarios = jogoRepository.findByUserContaining(id);
        } else {
            usuarios = jogoRepository.findAllUsers();
        }

        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
}