package SecondRoll.demo.services;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.Order;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.OrderDTO;
import SecondRoll.demo.payload.response.BuyerHistoryResponse;
import SecondRoll.demo.payload.response.SellerHistoryResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.OrderRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<User> buyer = userRepository.findById(orderDTO.getBuyerId());
        if (!buyer.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        //checks if all gameAds in DTO is present in database otherwise throws error
        List<GameAds> gameAds = new ArrayList<>();
        for (String gameAdId : orderDTO.getGameAdIds()) {
            gameAds.add(gameAdsRepository.findById(String.valueOf(gameAdId))
                    .orElseThrow(() -> new IllegalArgumentException("Game ad not found ")));
        }
        //Loops through list of game ads to set them to not available
        Optional<User> seller = Optional.of(new User());
        for (GameAds gameAd : gameAds) {
            gameAd.setAvailable(false);
            gameAdsRepository.save(gameAd);

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
        newOrder.setBuyer(buyer.get());
        newOrder.setGameAds(gameAds);
        newOrder.setSeller(seller.get());
        orderRepository.save(newOrder);


        return orderRepository.save(newOrder);

    }


    // get orders for specific user WIP

    public List<Order> getAllOrdersByUsername(String username) {
        List<Order> userOrders = new ArrayList<>();

        List<Order> sellerList = orderRepository
                .findAll()
                .stream()
                .filter(order -> Objects.equals(order.getSeller().getUsername(), username))
                .toList();

        List<Order> buyerList = orderRepository
                .findAll()
                .stream()
                .filter(order -> Objects.equals(order.getBuyer().getUsername(), username))
                .toList();

        userOrders.addAll(sellerList);
        userOrders.addAll(buyerList);

        return userOrders;
    }



        //get all orders from order collection
    public List<Order> getAllOrders () {
        return orderRepository.findAll();
    }


    //find order by specific id
    public Optional<Order> getOrdersById(String id) {
        return orderRepository.findById(id);
    }

    //delete specific order by id
    public String deleteOrderById(String id) {
        orderRepository.deleteById(id);
        return "Order successfully deleted!";
    }

    public List<BuyerHistoryResponse> buyerOrderHistory(String buyerId){
        List<Order> orders = orderRepository.findByBuyerId(buyerId);
        return orders.stream().map(this::convertToBuyerHistoryDTO).collect(Collectors.toList());

    }

    private BuyerHistoryResponse convertToBuyerHistoryDTO (Order order) {
        BuyerHistoryResponse buyerHistoryResponse = new BuyerHistoryResponse();
        buyerHistoryResponse.setBuyerId(order.getBuyer().getUsername());

        buyerHistoryResponse.setOrderedGameIds(order.getGameAds().stream().map(GameAds::getTitle).collect(Collectors.toList()));
        buyerHistoryResponse.setOrderedDate(order.getOrderedAt());

        return buyerHistoryResponse;
    }

    public List<SellerHistoryResponse> sellerOrderHistory(String sellerId) {
        List<Order> orders = orderRepository.findBySellerId(sellerId);

        return orders.stream().map(this::convertToSellerHistoryDTO).collect(Collectors.toList());
    }

    private SellerHistoryResponse convertToSellerHistoryDTO (Order order) {
        SellerHistoryResponse sellerHistoryResponse = new SellerHistoryResponse();
        sellerHistoryResponse.setSellerId(order.getSeller().getUsername());

        sellerHistoryResponse.setSoldGameIds(order.getGameAds().stream().map(GameAds::getTitle).collect(Collectors.toList()));
        sellerHistoryResponse.setSaleDate(order.getOrderedAt());

        return sellerHistoryResponse;
    }

}
