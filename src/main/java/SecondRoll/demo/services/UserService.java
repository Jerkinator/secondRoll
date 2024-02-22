package SecondRoll.demo.services;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.Rating;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.GameDTO;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GameAdsRepository gameAdsRepository;

    @Autowired
    GameAdsService gameAdsService;

    // CREATE a user.
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // GET a user by ID.
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // GET all users.
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // UPDATE a user.
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // DELETE a user.
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User successfully deleted!";
    }

    // ADD a gameAd to a user wishlist using a Data Transfer Object-reference.
    public User addGameToWishlist(String userId, GameDTO gameDTO) {
        User user = userRepository.findById(userId).orElseThrow();
        GameAds gameAd = gameAdsRepository.findById(gameDTO.getGameId()).orElseThrow();
        user.getWishlist().add(gameAd);
        return userRepository.save(user);
    }

    // REMOVE a gameAd to a user wishlist using a Data Transfer Object-reference.
    public User removeGameFromWishlist(String userId, GameDTO gameDTO) {
        User user = userRepository.findById(userId).orElseThrow();
        List<GameAds> wishlist = user.getWishlist();
        wishlist.removeIf(gameAd -> gameAd.getId().equals(gameDTO.getGameId()));
        return userRepository.save(user);
    }

    // ADD rating to a user.
    public User addRatingToUser(String userId, Rating rating) {
        User user = userRepository.findById(userId).orElseThrow();
        int amount = rating.getRating();
        ArrayList<Integer> ratings = new ArrayList<Integer>();
        ratings.add(amount);
        int length = user.getRating().size();
        int sum = 0;
        for(int i = 0; i < ratings.size(); i++){
            sum = sum + ratings[i];
        }

        return userRepository.save(user);
    }
}


    //OLD CODE FOR ADDING AND DELETING GAMES TO/FROM WISHLIST. STORED FOR NOW.

    /* public User addGameToWishlist(String userId, String gameId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        GameAds game = gameAdsRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found."));
        user.getWishlist().add(game);
        return userRepository.save(user);
    }

    public User removeGameFromWishlist(String userId, String gameId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        List<GameAds> wishlist = user.getWishlist();
        wishlist.removeIf(gameAd -> gameAd.getId().equals(gameId));
        return userRepository.save(user);
    } */