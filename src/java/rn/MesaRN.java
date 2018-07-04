/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import entidade.Mesa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author Bruna
 */
public class MesaRN {
    public MesaRN(){
        
    }
        public Mesa inserir(Mesa mesa) {

        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        manager.persist(mesa);
        manager.getTransaction().commit();

        manager.close();

        return (mesa);

    }

    public Mesa buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Mesa mesa = manager.find(Mesa.class, id);

        manager.close();
        return mesa;
    }

    public Mesa atualizar(Mesa mesa) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        mesa = manager.merge(mesa);
        manager.getTransaction().commit();

        manager.close();
        return (mesa);
    }

    public Mesa deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Mesa mesa = manager.find(Mesa.class, id);

        manager.getTransaction().begin();
        manager.remove(mesa);
        manager.getTransaction().commit();

        manager.close();
        return (mesa);

    }

    public List<Mesa> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select m from Mesa m");
        List<Mesa> listaMesa = query.getResultList();
        return listaMesa;
    }
}
