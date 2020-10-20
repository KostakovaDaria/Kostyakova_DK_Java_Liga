package com.philosophy.lesson4.dao;

import java.io.Serializable;

public interface GenericDAO <T extends Identified<PK>, PK extends Serializable>{

    T create(T object) throws PersistException;

    T persist(T object) throws PersistException;

    T getByPK(PK key) throws PersistException;

}
