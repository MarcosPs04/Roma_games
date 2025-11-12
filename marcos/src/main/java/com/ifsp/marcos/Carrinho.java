package com.ifsp.marcos;

import java.util.Random;

public class Carrinho {
    
    private int id;
    private String nome;
    private double valor;
    private int quantidade;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Carrinho(String nome, double valor, int quantidade) {
        Random random = new Random();
        this.id = random.nextInt(1000);
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

}
