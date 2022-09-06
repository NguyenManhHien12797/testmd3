package com.example.demotest.dao;

import java.sql.SQLException;
import java.util.List;

public interface IManagerDAO<E> {
    List<E> selectAll() throws SQLException, ClassNotFoundException;

    void create(E e);

    E select(int idg_num);

    void update(int id, E e);

    void delete(int id) throws SQLException;

    void update(E o);

    List<E> search(String search);
}
