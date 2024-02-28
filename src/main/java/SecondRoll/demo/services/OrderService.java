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



    //create order preparing to use payload object in controller
    public Order createOrder(OrderDTO orderDTO) {
        Optional<User> userOptional = userRepository.findById(orderDTO.getBuyerId());
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        //checks if all gameAds in DTO is present in database otherwise throws error
        List<GameAds> gameAds = new ArrayList<>();
            for (String gameAdId : orderDTO.getGameAdIds()) {
                gameAds.add(gameAdsRepository.findById(gameAdId)
                        .orElseThrow(() -> new IllegalArgumentException("Game ad not found ")));
            }
            //Loops through list of game ads to set them to not available
            Optional <User> seller = Optional.of(new User());
            for (GameAds gameAd : gameAds) {
                gameAd.setAvailable(false);

               //Gets seller from game ad to make sure it is set to correct seller
               seller = userRepository.findById(gameAd.getUser().getId());
                if (!seller.isPresent()) {
                    throw new IllegalArgumentException("Seller not found");
                }
            }

            //checking that all passed game ads exists in database
            if (gameAds.size() != orderDTO.getGameAdIds().size()) {
                throw new IllegalArgumentException("One or more ads not found");
            }

            Order newOrder = new Order();
            newOrder.setBuyer(userOptional.get());
            newOrder.setGameAds(gameAds);
            newOrder.setSeller(seller.get());

            return orderRepository.save(newOrder);


    }


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







