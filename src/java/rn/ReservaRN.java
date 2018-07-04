/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import entidade.Reserva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author Bruna
 */
public class ReservaRN {

    public ReservaRN() {
    }
     public Reserva inserir(Reserva reserva) {

        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        manager.persist(reserva);
        manager.getTransaction().commit();

        manager.close();

        return (reserva);

    }

    public Reserva buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Reserva reserva = manager.find(Reserva.class, id);

        manager.close();
        return reserva;
    }

    public Reserva atualizar(Reserva reserva) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        reserva = manager.merge(reserva);
        manager.getTransaction().commit();

        manager.close();
        return (reserva);
    }

    public Reserva deletar(Long id) {

        EntityManager manager = JPAUtil.createManager();
        Reserva reserva = manager.find(Reserva.class, id);

        manager.getTransaction().begin();
        manager.remove(reserva);
        manager.getTransaction().commit();

        manager.close();
        return (reserva);

    }

    public List<Reserva> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select r from Reserva r");
        List<Reserva> listaReserva = query.getResultList();
        return listaReserva;
    }
}
