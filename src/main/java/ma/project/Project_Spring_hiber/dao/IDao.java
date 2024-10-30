package ma.project.Project_Spring_hiber.dao;

import java.util.List;

public interface IDao<T> {
    boolean create(T o);
    T getById(int id);
    List<T> getAll();
}
