package ma.project.Project_Spring_hiber.service;


import ma.project.Project_Spring_hiber.classes.Commande;
import ma.project.Project_Spring_hiber.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService implements IDao<Commande> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Commande commande) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(commande);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Commande getById(int id) {
        Session session = sessionFactory.openSession();
        Commande commande = session.get(Commande.class, id);
        session.close();
        return commande;
    }

    @Override
    public List<Commande> getAll() {
        Session session = sessionFactory.openSession();
        List<Commande> commandes = session.createQuery("from Commande", Commande.class).list();
        session.close();
        return commandes;
    }
}

