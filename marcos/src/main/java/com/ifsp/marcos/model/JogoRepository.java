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
    public List<Jogo> findAllJogos() {
        String sql = "SELECT * FROM jogo";
        Query q = em.createNativeQuery(sql, Jogo.class);
        List<Jogo> jogos = q.getResultList();
        return jogos;
    }
    @Transactional
    public List<Usuario> findAllUsers() {
        String sql = "SELECT * FROM usuario";
        Query q = em.createNativeQuery(sql, Usuario.class);
        List<Usuario> usuarios = q.getResultList();
        return usuarios;
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
    public void saveUser(Usuario usuario) {
        String sql = "INSERT INTO usuario (email, telefone, senha, data_nascimento) VALUES (:email, :telefone, :senha, :data_nascimento)";

        Query query = em.createNativeQuery(sql);
        query.setParameter("email", usuario.getEmail());
        query.setParameter("telefone", usuario.getTelefone());
        query.setParameter("senha", usuario.getSenha());
        query.setParameter("data_nascimento", usuario.getDataNascimento());
        query.executeUpdate();
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

}