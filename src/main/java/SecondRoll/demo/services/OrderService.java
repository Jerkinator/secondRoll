package SecondRoll.demo.services;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.Order;
import SecondRoll.demo.models.User;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.OrderRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GameAdsRepository gameAdsRepository;
    @Autowired
    private UserRepository userRepository;

    //POST
    //Create order with user Id and gameAd Id
    public Order addOrder(String userId, String gameAdsId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }
        Optional<GameAds> gameAdsOptional = gameAdsRepository.findById(gameAdsId);
        if (!gameAdsOptional.isPresent()) {
            throw new RuntimeException("Game ad not found");
        }

        Order order = new Order();
        order.setUser(userOptional.get());
        order.setGameAds(gameAdsOptional.get());

        return orderRepository.save(order);
    }
}
