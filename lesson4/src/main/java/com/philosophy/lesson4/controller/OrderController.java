package com.philosophy.lesson4.controller;

import com.philosophy.lesson4.dao.PersistException;
import com.philosophy.lesson4.domain.OrderDTO;
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
     *    "Name":"{Наименование Заказа}",
     *    "Price":{Стоимость Заказа},
     *
     * "customer":
     *
     * {
     *        "Name_Customer":"{Имя клиента}", если клиент не существует в БД генерируется исключение
     *        "Email_Address":"{Адрес}"
     *  }
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
    public  ResponseEntity createOrder(@RequestBody OrderDTO order) throws PersistException {
       orderService.CreateOrder(order);
       return new ResponseEntity(order.getId(), HttpStatus.OK);
    }
}