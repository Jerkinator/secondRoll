package SecondRoll.demo.controllers;

import SecondRoll.demo.models.Order;
import SecondRoll.demo.payload.OrderDTO;
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

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO request) {
        Order order = orderService.addOrder(request.getUserId(), request.getGameAdIds());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /*
    //POST
    // Creates the order by using the payload object OrderDTO in controller
    @PostMapping
    //Sending in OrderDTO object as a request
    public ResponseEntity<Order> orders(@RequestBody OrderDTO request) {
        //Getting user id and game ad id and creates the order in database
        Order order = orderService.addOrder(request.getUserId(), request.getGameAdIds());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

     */








    /*
    //GET
    //retrieving all orders from order collection
    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

   //GET
    //retrieve specific order based on id
   @GetMapping(value ="/{id}")
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
*/

}
