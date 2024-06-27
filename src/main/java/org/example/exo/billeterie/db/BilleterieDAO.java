package org.example.exo.billeterie.db;

import org.example.exo.billeterie.db.model.Client;
import org.example.exo.billeterie.db.model.Event;
import org.example.exo.billeterie.db.model.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

// TODO make search by id
// TODO public List<Ticket> listTickets(Event event){return null;}


public class BilleterieDAO {
    private EntityManager em;

    public BilleterieDAO(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    public <T> void create(T item){
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }

    public List<Client> getAllClients(){
        return em.createQuery("from Client" , Client.class).getResultList();
    }

    public List<Event> getAllEvent(){
        return em.createQuery("from Event" , Event.class).getResultList();
    }

    public List<Ticket> getAllTickets(){
        return em.createQuery("from Ticket" , Ticket.class).getResultList();
    }

    public List<Ticket> listTicketsFromEvent(long eventId){
        Event event = em.find(Event.class, eventId);
        return event.getTickets();
    }

    public Client getClientByID(long id){
        return em.find(Client.class, id);
    }

    public Event getEventByID(long id){
        return em.find(Event.class, id);
    }

    public Ticket getTicketByID(long id){
        return em.find(Ticket.class, id);
    }

    public <T> void update(T item){
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    public <T> void delete(T item){
        em.getTransaction().begin();
        em.remove(item);
        em.getTransaction().commit();
    }

    public EntityManager getEm() {
        return em;
    }
}
