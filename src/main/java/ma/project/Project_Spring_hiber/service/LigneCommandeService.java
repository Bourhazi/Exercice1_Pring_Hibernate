package ma.project.Project_Spring_hiber.service;


import ma.project.Project_Spring_hiber.classes.LigneCommandeProduit;
import ma.project.Project_Spring_hiber.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneCommandeService implements IDao<LigneCommandeProduit> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(LigneCommandeProduit ligneCommande) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ligneCommande);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public LigneCommandeProduit getById(int id) {
        Session session = sessionFactory.openSession();
        LigneCommandeProduit ligneCommande = session.get(LigneCommandeProduit.class, id);
        session.close();
        return ligneCommande;
    }

    @Override
    public List<LigneCommandeProduit> getAll() {
        Session session = sessionFactory.openSession();
        List<LigneCommandeProduit> lignesCommande = session.createQuery("from LigneCommandeProduit", LigneCommandeProduit.class).list();
        session.close();
        return lignesCommande;
    }
}

