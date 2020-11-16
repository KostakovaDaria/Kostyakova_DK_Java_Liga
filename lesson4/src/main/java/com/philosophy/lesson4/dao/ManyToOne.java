package com.philosophy.lesson4.dao;

import java.lang.reflect.Field;
import java.sql.Connection;

/**
 * Реализуется модель многие ко одному
 * @param <Owner> - класс на который ссылается зависимый объект
 * @param <Dependence> - класс зависимого объекта
 */

public class ManyToOne<Owner extends Identified, Dependence extends Identified>{

    private DAOFactory<Connection> factory;
    private Field field;
    private String name;
    private int bash;

    public ManyToOne(Class<Owner> ownerClass, DAOFactory<Connection> factory, String field) throws NoSuchFieldException
    {
        this.factory = factory;
        this.field = ownerClass.getDeclaredField(field);
        this.field.setAccessible(true);

        name = ownerClass.getSimpleName() + "to" + this.field.getType().getSimpleName();
        bash = ownerClass.hashCode() & field.hashCode();
    }

    public Dependence getDependence(Owner owner) throws IllegalAccessException
    {
        return (Dependence) field.get(owner);
    }

    public void setDependence(Owner owner, Dependence dependence) throws IllegalAccessException
    {
        field.set(owner, dependence);
    }

    public Identified persistDependence(Owner owner, Connection connection) throws IllegalAccessException, PersistException {
        return factory.getDao(connection, field.getType()).persist(getDependence(owner));
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int hashCode(){
        return bash;
    }

}
