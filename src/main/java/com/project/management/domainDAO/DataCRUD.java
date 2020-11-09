package com.project.management.domainDAO;


import java.sql.SQLException;
import java.util.List;

public abstract class DataCRUD<T> {
    public abstract void create(T t) throws SQLException;



    public abstract void update(T t) throws SQLException;

    public abstract void delete(T t) throws SQLException;



}

