package com.ifsp.marcos.model;

import java.util.List;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class JogoRepository {
    
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Jogo jogo) {
        String sql = "INSERT INTO jogo (titulo, plataforma, ano_lancamento, historia, preco, requisitos_sys) VALUES (:titulo, :plataforma, :ano_lancamento, :historia, :preco, :requisitos_sys)";

        Query query = em.createNativeQuery(sql);
        query.setParameter("titulo", jogo.getTitulo());
        query.setParameter("plataforma", jogo.getPlataforma());
        query.setParameter("ano_lancamento", jogo.getAnoLancamento());
        query.setParameter("historia", jogo.getHistoria());
        query.setParameter("preco", jogo.getPreco());
        query.setParameter("requisitos_sys", jogo.getRequisitosSys());
        query.executeUpdate();
    }

    @Transactional
    public List<Jogo> findAll() {
        String sql = "SELECT * FROM jogo";
        Query q = em.createNativeQuery(sql, Jogo.class);
        List<Jogo> jogos = q.getResultList();
        return jogos;
    }

    @Transactional
    public Jogo findById(long id) {
        String sql = "SELECT * FROM jogo WHERE id = :id";
        Query q = em.createNativeQuery(sql, Jogo.class);
        q.setParameter("id", id);
        Jogo jogo = (Jogo) q.getSingleResult();
        return jogo;
    }

    @Transactional
    public List<Jogo> findByTituloContaining(String termo) {
        String sql = "SELECT * FROM jogo WHERE LOWER(titulo) LIKE :termo";
        Query q = em.createNativeQuery(sql, Jogo.class);
        q.setParameter("termo", "%" + termo.toLowerCase() + "%");
        List<Jogo> jogos = q.getResultList();
        return jogos;
    }
}