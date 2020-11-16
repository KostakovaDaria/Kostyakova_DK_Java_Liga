package com.philosophy.lesson4.dao;

public interface DAOFactory<Context> {

    interface DAOCreater<Context>
    {
        GenericDAO create(Context context);
    }

    /**
     * Получаем хранилище объектов
     * @return
     * @throws PersistException
     */

    Context getContext() throws PersistException;

    GenericDAO getDao(Context context, Class dtoClass) throws PersistException;
}
