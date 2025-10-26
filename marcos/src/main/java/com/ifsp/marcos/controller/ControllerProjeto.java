package com.ifsp.marcos.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String cadastro() {
    return "cadastro";
    }
    @PostMapping("/cadastro")
    public String cadastroUser(
        @RequestParam String email,
        @RequestParam String telefone,
        @RequestParam String senha,
        @RequestParam String dataNascimento
    ){
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setSenha(senha);
        usuario.setDataNascimento(dataNascimento);
        jogoRepository.saveUser(usuario);
        return "redirect:/telaInicio";

    }
    @GetMapping("/listarJogos")
    public String listarJogos(Model model) {
        List<Jogo> jogos = jogoRepository.findAllJogos();
        model.addAttribute("jogos", jogos);
        return "jogos";
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
    public String editarUsers(@PathVariable long id, Model model) {
        Usuario usuario = jogoRepository.findUserById(id);
        model.addAttribute("usuario", usuario);
        return "cadastro";
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
            jogos = jogoRepository.findAllJogos();
        }

        model.addAttribute("jogos", jogos);
        return "telaInicial";
    }
}