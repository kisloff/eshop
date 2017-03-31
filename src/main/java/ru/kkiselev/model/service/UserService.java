package ru.kkiselev.model.service;

/**
 * Created by kv on 20.01.17.
 */
public interface UserService<User> {

    void addUser(User instance);

    User getUserByEmail(String email);

    boolean isRegistered(User instance);

    boolean isAdminUser(User user);

}
