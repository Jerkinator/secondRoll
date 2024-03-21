package SecondRoll.demo.services;

import SecondRoll.demo.exception.ServiceException;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.Order;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.OrderDTO;
import SecondRoll.demo.payload.OrderGameDetailsDTO;
import SecondRoll.demo.payload.response.BuyerHistoryResponse;
import SecondRoll.demo.payload.response.OrderResponse;
import SecondRoll.demo.payload.response.SellerHistoryResponse;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.OrderRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    // Create order preparing to use payload object in controller.
    public OrderResponse createOrder(OrderDTO orderDTO) {
        Optional<User> buyer = userRepository.findById(orderDTO.getBuyerId());
        if (!buyer.isPresent()) {
            throw new ServiceException("User not found.");
        }

        // Checks if all gameAds in DTO is present in database otherwise throws error.
        List<GameAds> gameAds = new ArrayList<>();
        for (String gameAdId : orderDTO.getGameAdIds()) {
            gameAds.add(gameAdsRepository.findById(String.valueOf(gameAdId))
                    .orElseThrow(() -> new ServiceException("Game ad with id: " + gameAdId + " was not found.")));
        }

        // Loops through list of game ads to set them to not available.
        Optional<User> seller = Optional.of(new User());
        for (GameAds gameAd : gameAds) {
            gameAd.setAvailable(false);
            gameAdsRepository.save(gameAd);

            // Gets seller from game ad to make sure it is set to correct seller.
            seller = userRepository.findById(gameAd.getUser().getId());
            if (!seller.isPresent()) {
                throw new ServiceException("Seller not found.");
            }
        }
        double totalPrice = gameAds.stream()
                .mapToDouble(GameAds::getPrice)
                .sum();

        double shippingTotal  = gameAds.stream()
                .mapToDouble(GameAds::getShippingCost)
                .sum();
        double orderTotal = (totalPrice + shippingTotal);

        // Checking that all passed game ads exists in database.
        if (gameAds.size() != orderDTO.getGameAdIds().size()) {
            throw new ServiceException("One or more game ads not found.");
        }
        // Creates a new list with just gameTitle and price per game using OrderedgamesdetailDTO.
        List<OrderGameDetailsDTO> orderedGames = new ArrayList<>();
        for (GameAds gameAd : gameAds) {
            OrderGameDetailsDTO orderGameDetailsDTO = new OrderGameDetailsDTO();
            orderGameDetailsDTO.setTitle(gameAd.getTitle());
            orderGameDetailsDTO.setPrice(gameAd.getPrice());
            orderedGames.add(orderGameDetailsDTO);
        }

        Order newOrder = new Order();
        newOrder.setBuyer(buyer.get());
        newOrder.setGameAds(gameAds);
        newOrder.setSeller(seller.get());
        orderRepository.save(newOrder);

        // Creates the response based on what gets passed in the orderDTO based on the fields in orderResponse.
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderedDate(newOrder.getOrderedAt());
        orderResponse.setBuyerName(buyer.get().getUsername());
        orderResponse.setSellerName(seller.get().getUsername());
        orderResponse.setOrderedGames(orderedGames);
        orderResponse.setShippingTotal(shippingTotal);
        orderResponse.setGameTotal(totalPrice);
        orderResponse.setOrderTotal(orderTotal);

        return orderResponse;
    }


    // Get all orders from order collection.
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Find order by specific id.
    public Optional<Order> getOrdersById(String id) {
        return Optional.ofNullable(orderRepository.findById(id).orElseThrow(() ->
                new ServiceException("Order with id: " + id + " was not found.")));
    }

    // Delete specific order by id.
    public String deleteOrderById(String id) {
        orderRepository.deleteById(id);
        return "Order successfully deleted!";
    }

    public List<BuyerHistoryResponse> buyerOrderHistory(String buyerId) {
        List<Order> orders = orderRepository.findByBuyerId(buyerId);
        return orders.stream().map(this::convertToBuyerHistoryDTO).collect(Collectors.toList());
    }

    private BuyerHistoryResponse convertToBuyerHistoryDTO(Order order) {
        BuyerHistoryResponse buyerHistoryResponse = new BuyerHistoryResponse();

        List<OrderGameDetailsDTO> gameDetailsList = order.getGameAds().stream()
                // Using the help method to get game details from the gameAd id.
                .map(gameAds -> getGameDetails(gameAds.getId()))
                .collect(Collectors.toList());

        double totalPrice =order.getGameAds().stream()
                .mapToDouble(GameAds::getPrice)
                .sum();

        double shippingTotal  = order.getGameAds().stream()
                .mapToDouble(GameAds::getShippingCost)
                .sum();

        double orderTotal = (totalPrice + shippingTotal);

        buyerHistoryResponse.setBuyer(order.getBuyer().getUsername());
        buyerHistoryResponse.setSeller(order.getSeller().getUsername());
        buyerHistoryResponse.setOrderedGames(gameDetailsList);
        buyerHistoryResponse.setOrderedDate(order.getOrderedAt());
        buyerHistoryResponse.setShippingTotal(shippingTotal);
        buyerHistoryResponse.setGameTotal(totalPrice);
        buyerHistoryResponse.setOrderTotal(orderTotal);

        return buyerHistoryResponse;
    }

    // Identical as buyer history response but with sold.
    public List<SellerHistoryResponse> sellerOrderHistory(String sellerId) {
        List<Order> orders = orderRepository.findBySellerId(sellerId);
        return orders.stream().map(this::convertToSellerHistoryDTO).collect(Collectors.toList());
    }

    private SellerHistoryResponse convertToSellerHistoryDTO(Order order) {
        SellerHistoryResponse sellerHistoryResponse = new SellerHistoryResponse();

        List<OrderGameDetailsDTO> gameDetailsList = order.getGameAds().stream()
                //using the help method to get game details from the gameAd id
                .map(gameAds -> getGameDetails(gameAds.getId()))
                .collect(Collectors.toList());

        double totalPrice =order.getGameAds().stream()
                .mapToDouble(GameAds::getPrice)
                .sum();

        double shippingTotal  = order.getGameAds().stream()
                .mapToDouble(GameAds::getShippingCost)
                .sum();

        double orderTotal = (totalPrice + shippingTotal);

        sellerHistoryResponse.setBuyer(order.getBuyer().getUsername());
        sellerHistoryResponse.setSeller(order.getSeller().getUsername());
        sellerHistoryResponse.setOrderedGames(gameDetailsList);
        sellerHistoryResponse.setOrderedDate(order.getOrderedAt());
        sellerHistoryResponse.setShippingTotal(shippingTotal);
        sellerHistoryResponse.setGameTotal(totalPrice);
        sellerHistoryResponse.setOrderTotal(orderTotal);

        return sellerHistoryResponse;
    }

    // Help method to get title and price from game ads.
    private OrderGameDetailsDTO getGameDetails(String gameId) {

        Optional<GameAds> gameAds = gameAdsRepository.findById(gameId);
        if (gameAds.isPresent()) {
            OrderGameDetailsDTO gameDetailsDTO = new OrderGameDetailsDTO();
            gameDetailsDTO.setTitle(gameAds.get().getTitle());
            gameDetailsDTO.setPrice(gameAds.get().getPrice());

            return gameDetailsDTO;
        } else {
            throw new RuntimeException("Game ads not found");
        }
    }
}