package SecondRoll.demo.controllers;

import SecondRoll.demo.models.Order;
import SecondRoll.demo.payload.OrderDTO;
import SecondRoll.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping
    //Sending in OrderDTO object as a request
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        //Getting user id and game ad id and creates the order in database
        Order newOrder = orderService.createOrder(orderDTO);
        //this is where what shows in postman is set.
        //only responds with the message Order created
        //return new ResponseEntity(new MessageResponse("Order created"), HttpStatus.CREATED);
        //This line returns whole order object
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }


    //GET
    //retrieving all orders from order collection
    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

   //GET
    //retrieve specific order based on id
   @GetMapping("/{id}")
   public ResponseEntity<Order> getOrdersById(@PathVariable String id) {
       Optional<Order> order = orderService.getOrdersById(id);
       return order.map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
   }


    //DELETE borrowedBooks by id
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteOrderById(@PathVariable String id) {
        return orderService.deleteOrderById(id);
    }


}
