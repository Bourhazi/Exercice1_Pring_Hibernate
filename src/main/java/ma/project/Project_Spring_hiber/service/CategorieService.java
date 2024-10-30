package ma.project.Project_Spring_hiber.service;


import ma.project.Project_Spring_hiber.classes.Categorie;
import ma.project.Project_Spring_hiber.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService implements IDao<Categorie> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Categorie categorie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(categorie);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Categorie getById(int id) {
        Session session = sessionFactory.openSession();
        Categorie categorie = session.get(Categorie.class, id);
        session.close();
        return categorie;
    }

    @Override
    public List<Categorie> getAll() {
        Session session = sessionFactory.openSession();
        List<Categorie> categories = session.createQuery("from Categorie", Categorie.class).list();
        session.close();
        return categories;
    }
}

