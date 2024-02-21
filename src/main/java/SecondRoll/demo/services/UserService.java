package SecondRoll.demo.services;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
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

    // POST a gameAd to a user wishlist using ObjectID reference.
    public User addGameToWishlist(String userId, List<String> gameIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        // GameAds game = gameAdsRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found."));

        List<GameAds> userWishlist = new ArrayList<>();

        // IF all gameID's we send into this method, all the ID's will be added to user Wishlist.
        for(String gameId : gameIds) {
            Optional<GameAds> gameAd = gameAdsRepository.findById(gameId);
            gameAd.ifPresent(userWishlist::add);
        }

        if(userWishlist.size() != gameIds.size()){
            throw new RuntimeException("One or more games not found.");
        }

        user.setWishlist(userWishlist);

        //MIGHT overwrite the entire user.
        return userRepository.save(user);
    }

    public User removeGameFromWishlist(String userId, String gameId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        GameAds game = gameAdsRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found."));
        user.getWishlist().remove(game);
        return userRepository.save(user);
    }
}