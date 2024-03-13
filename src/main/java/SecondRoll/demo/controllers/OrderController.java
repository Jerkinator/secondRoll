package SecondRoll.demo.controllers;

import SecondRoll.demo.models.Order;
import SecondRoll.demo.payload.OrderDTO;
import SecondRoll.demo.payload.response.BuyerHistoryResponse;
import SecondRoll.demo.payload.response.OrderResponse;
import SecondRoll.demo.payload.response.SellerHistoryResponse;
import SecondRoll.demo.repository.UserRepository;
import SecondRoll.demo.security.services.UserDetailsServiceImpl;
import SecondRoll.demo.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsServiceImpl userDetailsService;


    //Sending in OrderDTO object as a request
    @PostMapping()
    /*public ResponseEntity<GameAdResponse> createGameAd(@Valid @RequestBody CreateGameDTO createGameDTO) {
        GameAds gameAd = gameAdsService.createGameAd(createGameDTO);
        User user = gameAd.getUser();
        return ResponseEntity.ok().body(new GameAdResponse(user.getUsername(), gameAd.getTitle(),
                gameAd.getDescription(), gameAd.getPrice(), gameAd.getShippingCost(), gameAd.getGameCreator(),
                gameAd.getGamePlayTime(), gameAd.getGameRecommendedAge(), gameAd.getGamePlayers(),gameAd.gameGenres,
                gameAd.getPhotoURL(), gameAd.getCreated_at(), gameAd.getUpdated_at()));

     */

    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderDTO orderDTO) {
        //Getting user id and game ad id and creates the order in database
        OrderResponse newOrder = orderService.createOrder(orderDTO);
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
        if (userDetailsService.hasPermission(buyerId, request)) {
            List<BuyerHistoryResponse> orders = orderService.buyerOrderHistory(buyerId);
            return ResponseEntity.ok(orders);
        } else {
            throw new RuntimeException("Not authorized");
        }
    }

    @GetMapping("/sellerhistory/{sellerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<SellerHistoryResponse>> sellerOrderHistory(@PathVariable String sellerId, HttpServletRequest request) {
        if (userDetailsService.hasPermission(sellerId, request)) {
            List<SellerHistoryResponse> orders = orderService.sellerOrderHistory(sellerId);
            return ResponseEntity.ok(orders);
        } else {
            throw new RuntimeException("Not authorized");
        }
    }
}