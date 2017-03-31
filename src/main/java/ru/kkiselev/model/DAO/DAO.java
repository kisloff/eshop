package ru.kkiselev.model.DAO;

import java.util.List;

/**
 * Created by kv on 04.01.17.
 */
public interface DAO <T>{
    List<T> getAll ();
    void addRow(T instance);
    void deleteRow(T instance);
}
