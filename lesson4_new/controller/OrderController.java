package com.philosophy.lesson4.controller;

import com.philosophy.lesson4.dao.PersistException;
import com.philosophy.lesson4.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.philosophy.lesson4.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Метод createOrder принемает JSON следующего вида:
     *
     *{
     *
     *    "Name_Order":"{Наименование Заказа}",
     *    "Price_Order":{Стоимость Заказа},
     *
     * "customer":
     *
     * {
     *        "Id_Customer":"{Id клиента}", если клиент не существует в БД генерируется исключение
     * }
     *
     * }
     *
     * Тестировачные запросы для проверки работы сервиса были сгенерированы в Fiddler
     *
     * @param order
     * @return {id}
     * @throws PersistException
     */

    @RequestMapping(path = "/api/v1/order", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createOrder(@RequestBody Order order) throws PersistException {
        return new ResponseEntity(orderService.CreateOrder(order), HttpStatus.OK);
    }
}