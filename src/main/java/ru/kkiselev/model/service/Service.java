package ru.kkiselev.model.service;

import java.util.List;

/**
 * Created by kv on 04.01.17.
 */
public interface Service<T> {
    List<T> getAll ();
    void addRow(T instance);
    void deleteRow(T instance);
    void updateRow(T instance);
}
