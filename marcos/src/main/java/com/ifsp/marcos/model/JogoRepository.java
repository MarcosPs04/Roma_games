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
    public Jogo saveJogo(Jogo jogo) {
        if (jogo.getId() == null) {
            em.persist(jogo);
            return jogo;
        } else {
            Jogo merged = em.merge(jogo);
            return merged;
        }
    }
    @Transactional
    public List<Jogo> findAllJogos() {
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
    @Transactional
    public List<Jogo> findByIdContaining(String termo) {
        String sql = "SELECT * FROM jogo WHERE LOWER(id) LIKE :termo";
        Query q = em.createNativeQuery(sql, Jogo.class);
        q.setParameter("termo", "%" + termo.toLowerCase() + "%");
        List<Jogo> jogo = q.getResultList();
        return jogo;
    }

    @Transactional
    public void deleteJogo(long id) {
        String sql = "DELETE FROM jogo WHERE id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
    @Transactional
    public void deleteAllJogos() {
        String sql = "DELETE FROM jogo";
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }
    
    @Transactional
    public Usuario saveUser(Usuario usuario) {
        if (usuario.getId() == null) {
            em.persist(usuario);
            return usuario;
        } else {
            Usuario merged = em.merge(usuario);
            return merged;
        }
    }
    @Transactional
    public void deleteUser(long id) {
        String sql = "DELETE FROM usuario WHERE id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
    @Transactional
    public void deleteAllUsers() {
        String sql = "DELETE FROM usuario";
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }
    @Transactional
    public Usuario findUserById(long id) {
        String sql = "SELECT * FROM usuario WHERE id = :id";
        Query q = em.createNativeQuery(sql, Usuario.class);
        q.setParameter("id", id);
        Usuario usuario = (Usuario) q.getSingleResult();
        return usuario;
    }
    @Transactional
    public List<Usuario> findAllUsers() {
        String sql = "SELECT * FROM usuario";
        Query q = em.createNativeQuery(sql, Usuario.class);
        List<Usuario> usuarios = q.getResultList();
        return usuarios;
    }
    @Transactional
    public List<Usuario> findByUserContaining(String termo) {
        String sql = "SELECT * FROM usuario WHERE LOWER(id) LIKE :termo";
        Query q = em.createNativeQuery(sql, Usuario.class);
        q.setParameter("termo", "%" + termo.toLowerCase() + "%");
        List<Usuario> usuarios = q.getResultList();
        return usuarios;
    }
}