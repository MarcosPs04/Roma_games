package com.ifsp.marcos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerProduto {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @GetMapping("/listProdutos")
    public String listProdutos(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "produtos.html";
    }

    @GetMapping("/produto/{id}")
    public String show(@PathVariable long id, Model model) {
        Produto produto = produtoRepository.findById(id);
        model.addAttribute("produto", produto);
        return "show";
    }

    @GetMapping("/formularioProduto")
    public String criarProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "formularioProduto.html";       
    }

    @PostMapping("/criarProduto")
    public String criarProduto(
        @RequestParam String nome,
        @RequestParam String descricao,
        @RequestParam double preco
    ){
        produtoRepository.save(new Produto(nome, descricao, preco));
        return "redirect:/listProdutos";
    }

    @GetMapping("/produto/{id}/editar")
    public String editarProduto(@PathVariable long id, Model model) {
        Produto produto = produtoRepository.findById(id);
        model.addAttribute("produto", produto);
        return "formularioProduto.html";
    }

    @PostMapping("/produto/{id}/atualizar")
    public String atualizarProduto(
        @PathVariable long id,
        @RequestParam String nome,
        @RequestParam String descricao,
        @RequestParam double preco
    ) {
        Produto produto = new Produto(nome, descricao, preco);
        produto.setId(id);
        produtoRepository.update(produto);
        return "redirect:/listProdutos";
    }

    @PostMapping("/produto/{id}/deletar")
    public String deletarProduto(@PathVariable long id) {
        produtoRepository.delete(id);
        return "redirect:/listProdutos";
    }
}
