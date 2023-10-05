package org.example.repository;

import java.util.List;

public interface Repository <T>{
    boolean add(T item);
    List<T> getAll();
    T get(int id);
    boolean update(T item);
    boolean remove(T item);
    boolean remove(int id);
}