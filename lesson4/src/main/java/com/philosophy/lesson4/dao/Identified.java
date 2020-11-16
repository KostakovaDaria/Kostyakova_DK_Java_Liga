package com.philosophy.lesson4.dao;

import java.io.Serializable;

/**
 * Возвращает Первичный Ключ
 * @param <PK>
 */

public interface Identified <PK extends Serializable> {

    PK getId();
}
