package SecondRoll.demo.services;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.Order;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.OrderDTO;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.OrderRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GameAdsRepository gameAdsRepository;
    @Autowired
    private UserRepository userRepository;
/*
    public Order addOrder (String userId, List<String> gameAdIds ) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        List<GameAds> gameAds = new ArrayList<>();
        for (String gameAdsId : gameAdIds) {
            Optional<GameAds> gameAdsOptional = gameAdsRepository.findById(gameAdsId);
            gameAdsOptional.ifPresent(gameAds::add);
        }
        if (gameAds.size() != gameAdIds.size()) {
            throw new RuntimeException("One or more books not found");
        }

        Order order = new Order();
        order.setUser(userOptional.get());
        order.setGameAds(gameAds);

        return orderRepository.save(order);


    }

 */


    //create order preparing to use payload object in controller

    public Order createOrder(OrderDTO orderDTO) {
        Optional<User> userOptional = userRepository.findById(orderDTO.getUserId());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

            List<GameAds> gameAds = new ArrayList<>();
            for (String gameAdId : orderDTO.getGameAdIds()) {
                gameAds.add(gameAdsRepository.findById(gameAdId)
                        .orElseThrow(() -> new RuntimeException("Game ad not found ")));
            }
            if (gameAds.size() != orderDTO.getGameAdIds().size()) {
                throw new RuntimeException("One or more ads not found");
            }

            Order newOrder = new Order();
            newOrder.setUser(userOptional.get());
            newOrder.setGameAds(gameAds);
            newOrder.setGameAds(gameAds);

            return orderRepository.save(newOrder);


    }


   /* public Order addOrder(String userId, List<String> gameAdIds) {
        Optional<User> userOptional = userRepository.findById(userId);
        //making sure that there is a user matching the entered id
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        List<GameAds> gameAds = new ArrayList<>();
        //loops through to see if the entered gamAdIds match database.
        for (String gameAdsId : gameAdIds) {
            Optional<GameAds> gameAdsOptional = gameAdsRepository.findById(gameAdsId);
            gameAdsOptional.ifPresent(gameAds::add);
        }
        //makes sure that there is the same amount of ads as Ids entered
        if (gameAds.size() != gameAdIds.size()) {
            throw new RuntimeException("One or more ads not found");
        }
        Order order = new Order();
        //sets the user to order object
        order.setUser(userOptional.get());
        //sets the gameAds to order object
        order.setGameAds(gameAds);

        return orderRepository.save(order);
    }

    */


        //get all orders from order collection
    public List<Order> getAllOrders () {
        return orderRepository.findAll();
    }


    //find order by specific id
    public Optional<Order> getOrdersById(String id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Order order){
        return orderRepository.save(order);
    }

    //delete specific order by id
    public String deleteOrderById(String id) {
        orderRepository.deleteById(id);
        return "Order successfully deleted!";
    }
}







