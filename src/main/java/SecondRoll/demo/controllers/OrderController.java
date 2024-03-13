package SecondRoll.demo.controllers;

import SecondRoll.demo.models.ERole;
import SecondRoll.demo.models.Order;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.OrderDTO;
import SecondRoll.demo.payload.response.BuyerHistoryResponse;
import SecondRoll.demo.payload.response.SellerHistoryResponse;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> getOrdersById(@PathVariable String id) {
        Optional<Order> order = orderService.getOrdersById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //DELETE order by id
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteOrderById(@PathVariable String id) {
        return orderService.deleteOrderById(id);
    }

    //GET buyer history for bought games
    @GetMapping("/buyerhistory/{buyerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<BuyerHistoryResponse>> buyerOrderHistory(@PathVariable String buyerId, HttpServletRequest request) {
        if (hasPermission(buyerId, request)) {
            List<BuyerHistoryResponse> orders = orderService.buyerOrderHistory(buyerId);
            return ResponseEntity.ok(orders);
        } else {
            throw new RuntimeException("Not authorized");
        }
    }

    @GetMapping("/sellerhistory/{sellerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<SellerHistoryResponse>> sellerOrderHistory(@PathVariable String sellerId, HttpServletRequest request) {
        if (hasPermission(sellerId, request)) {
            List<SellerHistoryResponse> orders = orderService.sellerOrderHistory(sellerId);
            return ResponseEntity.ok(orders);
        } else {
            throw new RuntimeException("Not authorized");
        }
    }

    // where do we put this?
    // Method for authenticating access for logged in user by id OR person with admin role
    private boolean hasPermission(String userId, HttpServletRequest request) {
        String jwt = jwtUtils.getJwtFromCookie(request);
        String username = jwtUtils.getUsernameFromJwtToken(jwt);
        User user = userRepository.findUserByUsername(username);
        List<String> userRoles = user.getRoles().stream().map(r -> r.getName().name()).toList();
        return Objects.equals(userId, user.getId()) || userRoles.contains(ERole.ROLE_ADMIN.name());
    }
}