package com.ifsp.marcos.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jogo")

public class Jogo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "titulo")
        private String titulo;

        @Column(name = "plataforma")
        private String plataforma;

        @Column(name = "anoLancamento")
        private LocalDate anoLancamento;
        
        @Column(name = "historia")
        private String historia;
        
        @Column(name = "preco")
        private int preco;
        
        @Column(name = "requisitosSys")
        private String requisitosSys;

        public Jogo(String titulo, String plataforma, LocalDate anoLancamento, String historia, int preco, String requisitosSys) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.anoLancamento = anoLancamento;
        this.historia = historia;
        this.preco = preco;
        this.requisitosSys = requisitosSys;
        }
        public Jogo() {
        }

        public String getTitulo() {
            return titulo;
        }
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
        public String getPlataforma() {
            return plataforma;
        }
        public void setPlataforma(String plataforma) {
            this.plataforma = plataforma;
        }
        public LocalDate getAnoLancamento() {
            return anoLancamento;
        }
        public void setAnoLancamento(LocalDate anoLancamento) {
            this.anoLancamento = anoLancamento;
        }
        public String getHistoria() {
            return historia;
        }
        public void setHistoria(String historia) {
            this.historia = historia;
        }
        public int getPreco() {
            return preco;
        }
        public void setPreco(int preco) {
            this.preco = preco;
        }
        public String getRequisitosSys() {
            return requisitosSys;
        }
        public void setRequisitosSys(String requisitosSys) {
            this.requisitosSys = requisitosSys;
        }
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

    
}
