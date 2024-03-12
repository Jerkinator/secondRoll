package SecondRoll.demo.controllers;

import SecondRoll.demo.models.ERole;
import SecondRoll.demo.models.Order;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.OrderDTO;
import SecondRoll.demo.payload.response.BuyerHistoryResponse;
import SecondRoll.demo.repository.UserRepository;
import SecondRoll.demo.security.jwt.JwtUtils;
import SecondRoll.demo.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;


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
    @PreAuthorize("hasRole('ADMIN')")
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


    // HELENA: nej vi håller på med ordrar här, inte böcker eller?
    //DELETE borrowedBooks by id
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteOrderById(@PathVariable String id) {
        return orderService.deleteOrderById(id);
    }

    //GET buyer history for bought games

    @GetMapping("/buyerhistory/{buyerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<BuyerHistoryResponse>> buyerOrderHistory(@PathVariable String buyerId, HttpServletRequest request) {
        String jwt = jwtUtils.getJwtFromCookie(request);
        String username = jwtUtils.getUsernameFromJwtToken(jwt);
        User user = userRepository.findUserByUsername(username);
        List<String> userRoles = user.getRoles().stream().map(r -> r.getName().name()).toList();
        if(Objects.equals(buyerId, user.getId()) || userRoles.contains(ERole.ROLE_ADMIN.name())){
        List<BuyerHistoryResponse> orders = orderService.buyerOrderHistory(buyerId);
        return ResponseEntity.ok(orders);
        } else {
            throw new RuntimeException("NOT AUTHORIZED!");
        }
    }


    /*
    @GetMapping("/sellerhistory/{sellerId}")
    public ResponseEntity<List<SellerHistoryResponse>> sellerOrderHistory(@PathVariable String sellerId) {
        List<SellerHistoryResponse> orders = orderService.sellerOrderHistory(sellerId);
        return ResponseEntity.ok(orders);
    }
*/






     /*
    // Lists all orders for a authenticated(/logged in user
    @GetMapping("/all/{username}")
    @PreAuthorize("hasRole('ADMIN') or #username == principal.username")
    public ResponseEntity<List<Order>> getAllUserOrders(@PathVariable("username") String username) {
        User user = userRepository.findUserByUsername(username);
        List<Order> ordersByUsername = orderService.getAllOrdersByUsername(user.getUsername());
        return ResponseEntity.ok(ordersByUsername);
    }
      */
}