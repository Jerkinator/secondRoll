package SecondRoll.demo.services;

import SecondRoll.demo.exception.ServiceException;
import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.Rating;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.WishlistDTO;
import SecondRoll.demo.repository.GameAdsRepository;
import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameAdsRepository gameAdsRepository;

    @Autowired
    GameAdsService gameAdsService;

    // GET all users.
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // DELETE a user.
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User successfully deleted!";
    }

    // ADD a gameAd to a user wishlist using a Data Transfer Object-reference.
    public User addGameToWishlist(String userId, WishlistDTO wishlistDTO) {
        User user = userRepository.findById(userId).orElseThrow();
        GameAds gameAd = gameAdsRepository.findById(wishlistDTO.getGameId()).orElseThrow();
        user.getWishlist().add(gameAd);
            return userRepository.save(user);
        }

        // REMOVE a gameAd to a user wishlist using a Data Transfer Object-reference.
        public User removeGameFromWishlist (String userId, WishlistDTO wishlistDTO){
            User user = userRepository.findById(userId).orElseThrow();
            List<GameAds> wishlist = user.getWishlist();
            wishlist.removeIf(gameAd -> gameAd.getId().equals(wishlistDTO.getGameId()));
            return userRepository.save(user);
        }

        // ADD rating to a user.
        /* This method takes an integer value and adds it to the rating object. If the value is between 1-6,
        it then adds the value to the User ratings arraylist. Then it loops through the arraylist and stores all
        combined values into a total sum.
        Then finally it takes the sum and divides it by the total amount of values in the arraylist to get the average
        rating. */
        public User addRatingToUser (String userId, Rating rating){
            User user = userRepository.findById(userId).orElseThrow();

            int number = rating.getRating();

            if (number <= 0 || number > 6) {
                throw new ServiceException("Please select a rating between 1 - 6!");
            } else {
                rating.setRating(number);
            }

            user.getRatings().add(number);

            int sum = 0;
            int lengthOfRatingsList = user.getRatings().size();

            for (int i = 0; i < lengthOfRatingsList; i++) {
                sum += user.getRatings().get(i);
            }

            int averageRating = sum / lengthOfRatingsList;
            user.setAverageRating(averageRating);

            return userRepository.save(user);
        }
    }