package vn.edu.fpt.jpos.repositories.entities;

import java.util.List;

public interface IDao<T, V extends Throwable> {

    List<T> gets() throws V;

    T get(String id) throws V;

    T post(T item) throws V;

    T put(T item) throws V;

    T delete(T item) throws V;
}
