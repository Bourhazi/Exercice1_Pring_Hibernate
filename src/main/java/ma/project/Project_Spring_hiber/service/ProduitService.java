package ma.project.Project_Spring_hiber.service;

import ma.project.Project_Spring_hiber.classes.Produit;
import ma.project.Project_Spring_hiber.dao.IDao;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProduitService implements IDao<Produit> {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public boolean create(Produit produit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(produit);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Produit getById(int id) {
        Session session = sessionFactory.openSession();
        Produit produit = session.get(Produit.class, id);
        session.close();
        return produit;
    }

    @Override
    public List<Produit> getAll() {
        Session session = sessionFactory.openSession();
        List<Produit> produits = session.createQuery("from Produit", Produit.class).list();
        session.close();
        return produits;
    }

    public List<Produit> getProduitsByCategorie(int categorieId) {
        Session session = sessionFactory.openSession();
        Query<Produit> query = session.createQuery("FROM Produit WHERE categorie.id = :categorieId", Produit.class);
        query.setParameter("categorieId", categorieId);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }

    public List<Produit> getProduitsCommandesEntreDates(Date dateDebut, Date dateFin) {
        Session session = sessionFactory.openSession();
        Query<Produit> query = session.createQuery("SELECT p FROM Produit p JOIN p.lignesCommande lc WHERE lc.commande.date BETWEEN :dateDebut AND :dateFin", Produit.class);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }

    public List<Produit> getProduitsDansCommande(int commandeId) {
        Session session = sessionFactory.openSession();
        Query<Produit> query = session.createQuery("SELECT p FROM Produit p JOIN p.lignesCommande lc WHERE lc.commande.id = :commandeId", Produit.class);
        query.setParameter("commandeId", commandeId);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }

    public List<Produit> getProduitsAvecPrixSuperieur100() {
        Session session = sessionFactory.openSession();
        Query<Produit> query = session.createQuery("FROM Produit p WHERE p.prix > :prix", Produit.class);
        query.setParameter("prix", 100.0);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }
}
