package SecondRoll.demo.controllers;

import SecondRoll.demo.payload.OrderDTO;
import SecondRoll.demo.models.Order;
import SecondRoll.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    //POST
    // Creates the order by using the payload object OrderDTO in controller
    @PostMapping
    //Sending in OrderDTO as a request
    public ResponseEntity<Order> orders(@RequestBody OrderDTO request) {
        //Getting user id and game ad id
        Order order = orderService.addOrder(request.getUserId(), request.getGameAdIds());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
