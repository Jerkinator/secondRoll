package SecondRoll.demo.controllers;

import SecondRoll.demo.models.Rating;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.WishlistDTO;
import SecondRoll.demo.payload.response.MessageResponse;
import SecondRoll.demo.payload.response.UserProfileResponse;
import SecondRoll.demo.repository.UserRepository;
import SecondRoll.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;

    // GET a user by ID.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // GET logged in user profile
    @GetMapping("/profile/{username}")
    @PreAuthorize("#username == principal.username")
    public ResponseEntity<?> getUserProfile(@PathVariable("username") String username) {
        User user = userRepository.findUserByUsername(username);

        return ResponseEntity.ok().body(new UserProfileResponse(user.getId(), user.getUsername(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getAdress_street(),
                user.getAdress_zip(), user.getAdress_city(), user.getWishlist(), user.getRatings(), user.getAverageRating()));
    }

    // GET ALL users.
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // HELENA: se kommentar i UserService
    // UPDATE a user.
    @PutMapping("/{username}/update")
    @PreAuthorize("#username == principal.username")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    // DELETE a user based on ID.
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    // ADD a gameAd to a user wishlist using a Data Transfer Object-reference.
    @PutMapping("/{username}/wishlist")
    @PreAuthorize("#username == principal.username")
        public ResponseEntity<?> addGameToWishlist (@PathVariable("username") String username, @RequestBody WishlistDTO wishlistDTO){
            userService.addGameToWishlist(username, wishlistDTO);
        return  ResponseEntity.ok().header(String.valueOf(HttpStatus.CREATED))
                .body(new MessageResponse("Game added to wishlist"));
        }

    // REMOVE a gameAd to a user wishlist using a Data Transfer Object-reference.
    @DeleteMapping(value = "/{username}/wishlist")
    @PreAuthorize("#username == principal.username")
    public ResponseEntity<?> removeGameFromWishlist(@PathVariable("username") String username, @RequestBody WishlistDTO wishlistDTO) {
        userService.removeGameFromWishlist(username, wishlistDTO);
        return ResponseEntity.ok().header(String.valueOf(HttpStatus.I_AM_A_TEAPOT))
                .body(new MessageResponse("Game removed from wishlist"));
    }

    // ADD rating to a user.
    @PutMapping("/{userId}/rating")
    public ResponseEntity<?> addRatingToUser (@PathVariable String userId, @RequestBody Rating rating) {
        User userWithRating = userService.addRatingToUser(userId, rating);
        return new ResponseEntity<>(userWithRating, HttpStatus.CREATED);
    }


    // FUNKAR
    /* @GetMapping("/profile/{username}")
    @PreAuthorize("#username == principal.username")
    public Optional<User> getUserProfile(@PathVariable("username") String username) {

        return userRepository.findByUsername(username);
    } */

}

