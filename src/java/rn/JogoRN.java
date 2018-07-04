/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import entidade.Jogo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author Bruna
 */
public class JogoRN {
    public JogoRN(){
        
    }
    public Jogo inserir(Jogo jogo) {

        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        manager.persist(jogo);
        manager.getTransaction().commit();

        manager.close();

        return (jogo);

    }

    public Jogo buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Jogo jogo = manager.find(Jogo.class, id);

        manager.close();
        return jogo;
    }

    public Jogo atualizar(Jogo jogo) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        jogo = manager.merge(jogo);
        manager.getTransaction().commit();

        manager.close();

        return (jogo);
    }

    public Jogo deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Jogo jogo = manager.find(Jogo.class, id);

        manager.getTransaction().begin();
        manager.remove(jogo);
        manager.getTransaction().commit();

        manager.close();

        return (jogo);

    }

    public List<Jogo> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select j from Jogo j");
        List<Jogo> listaJogos = query.getResultList();
        return listaJogos;
    }

}
