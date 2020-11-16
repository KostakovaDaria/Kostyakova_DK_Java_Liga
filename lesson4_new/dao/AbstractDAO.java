package com.philosophy.lesson4.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Абстрактный класс AbstractDAO выступает шаблоном для классов Customer и Order
 * @param <T> - создаваемый класс Customer/Order
 * @param <PK> - Первичный ключ
 */

public abstract class AbstractDAO<T extends Identified<PK>, PK extends Integer> implements GenericDAO<T, PK>{

    /**
     * Возвращает все записи
     * @return SELECT * FROM [TABLE];
     */
    public abstract String getSelectQuery();

    /**
     * Добавляет строку в Базу Данных
     * @return INSERT INTO [TABLE] (COLUMN1..COLUMN_N) VALUES (?, ?, ?..);
     */

    public abstract String getCreateQuery();

    /**
     * Осуществляет запись результата запроса в соответствующий объект
     * @param rs - Результирующее множество
     * @return - Список объектов
     * @throws PersistException
     */

    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    /**
     * Подготовка данных перед записью в Базу Данных
     * @param statement - конечный объект
     * @param object - исходный объект
     * @throws PersistException
     */

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    private DAOFactory<Connection> parentFactory;
    private Connection connection;
    private Set<ManyToOne> relation = new HashSet<ManyToOne>();

    /**
     * Запись данных в Базу Данных с сохранением всех зависимостей
     * @param object - исходные данные
     * @return - Записанный объект
     * @throws PersistException
     */

    @Override
    public T persist(T object) throws PersistException {
        if (object.getId()!=null)
        {
            throw new PersistException("Object is already persist");
        }
        saveDependens(object);
        T persistInstance;

        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count!=1)
            {
                throw new PersistException("On persist modify more then 1 record:" + count);
            }

        }
        catch (Exception e)
        {
            throw new PersistException(e);
        }

        sql = getSelectQuery() + "ORDER BY Id DESC LIMIT 1;";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size()!=1))
            {
                throw new PersistException("Exception on findByKey new persist data");
            }
            persistInstance = list.iterator().next();
        }
        catch (Exception e)
        {
            throw new PersistException(e);
        }

        return persistInstance;
    }

    /**
     * Поиск записей по Первичному ключу
     * @param key - Первичный ключ
     * @return - последнюю запись по Первичному ключу
     * @throws PersistException
     */
    @Override
    public T getByPK(Integer key) throws PersistException {

        List<T> list;
        String sql = getSelectQuery() + " WHERE Id = ?";

            try(PreparedStatement statement = connection.prepareStatement(sql))
            {
                statement.setInt(1, key);
                ResultSet rs = statement.executeQuery();
                list = parseResultSet(rs);
            }
            catch (Exception e)
            {
                throw new PersistException(e);
            }
            if (list == null || list.size() == 0)
            {
                return null;
            }

            if (list.size() > 1)
            {
                throw new PersistException("Received more than one record");
            }
            return list.iterator().next();
    }

    /**
     * Сохранение зависимости между Customer и Order
     * @param owner - объект от которого идет зависимость (главный объект)
     * @throws PersistException
     */

    private void saveDependens(Identified owner) throws PersistException
    {
        for (ManyToOne m : relation)
        {
            try{

                if(m.getDependence(owner)==null)
                {
                    continue;
                }
                if (m.getDependence(owner).getId()==null)
                {
                    Identified depend = m.persistDependence(owner, connection);
                    m.setDependence(owner, depend);
                }
                else
                {

                }
            }
            catch (Exception e)
            {
                throw new PersistException("Exception on save depense on relation " + m + ".", e);
            }

        }

    }

    public AbstractDAO(DAOFactory<Connection> parentFactory, Connection connection)
    {
        this.parentFactory = parentFactory;
        this.connection = connection;
    }

    /**
     * Получить связанный объект по Первичному ключу
     * @param dtoClass - искомый класс
     * @param pk - Первичный ключ
     * @return - связанный объект
     * @throws PersistException
     */

    protected Identified getDependence(Class<? extends Identified> dtoClass, Serializable pk) throws PersistException
    {
        return parentFactory.getDao(connection, dtoClass).getByPK(pk);
    }

    /**
     * Установить отношение между объектами Customer и Order
     * @param ownerClass - класс владелец
     * @param field - поле по которому связываем
     * @return - Хеш-таблица следующего вида: главный -> зависимый
     */

    protected boolean addRelation(Class <? extends Identified> ownerClass, String field)
    {
        try
        { return relation.add(new ManyToOne(ownerClass, parentFactory, field));}
        catch (NoSuchFieldException e) {throw new RuntimeException(e);}

    }
}
