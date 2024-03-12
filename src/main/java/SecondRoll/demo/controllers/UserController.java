package SecondRoll.demo.controllers;

import SecondRoll.demo.models.Rating;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.UpdateUserDTO;
import SecondRoll.demo.payload.WishlistDTO;
import SecondRoll.demo.payload.response.MessageResponse;
import SecondRoll.demo.payload.response.UserProfileResponse;
import SecondRoll.demo.repository.UserRepository;
import SecondRoll.demo.services.UserService;
import jakarta.validation.Valid;
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET ALL users.
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    // NEW AND "IMPROVED" UPDATE USER - goes through DTO to restrain the info that is ok for user to update.
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO, @PathVariable ("userId") String userId) {
        if (userRepository.existsById((userId))) {

            User updatedUser = userRepository.findUserById(userId);
            updatedUser.setFirstName(updateUserDTO.getFirstName());
            updatedUser.setLastName(updateUserDTO.getLastName());
            updatedUser.setPhoneNumber(updateUserDTO.getPhoneNumber());
            updatedUser.setAdress_street(updateUserDTO.getAdress_street());
            updatedUser.setAdress_zip(updateUserDTO.getAdress_zip());
            updatedUser.setAdress_city(updateUserDTO.getAdress_city());
            userRepository.save(updatedUser);
            return ResponseEntity.ok().body(new MessageResponse("User updated"));

        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("userId could not be found"));
        }
    }

    // HELENA: se kommentar i UserService
    // UPDATE a user. (OLD, REMOVE IF EVERYONE AGREES)
    @PreAuthorize("hasRole('USER')")
    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    // DELETE a user based on ID.
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    // ADD a gameAd to a user wishlist using a Data Transfer Object-reference.
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}/wishlist")
    public ResponseEntity<?> addGameToWishlist (@PathVariable ("userId") String userId, @RequestBody WishlistDTO wishlistDTO){
        userService.addGameToWishlist(userId, wishlistDTO);
        return ResponseEntity.ok().body(new MessageResponse("Game added to wishlist."));
    }

    // REMOVE a gameAd from a user wishlist using a Data Transfer Object-reference.
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value = "/{userId}/wishlist")
    public ResponseEntity<?> removeGameFromWishlist(@PathVariable ("userId") String userId, @RequestBody WishlistDTO wishlistDTO) {
        userService.removeGameFromWishlist(userId, wishlistDTO);
        return ResponseEntity.ok().body(new MessageResponse("Game removed from wishlist."));
    }

    // ADD rating to a user.
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}/rating")
    public ResponseEntity<?> addRatingToUser (@PathVariable String userId, @RequestBody Rating rating) {
        User userWithRating = userService.addRatingToUser(userId, rating);
        return new ResponseEntity<>(userWithRating, HttpStatus.CREATED);
    }

    @GetMapping("/profile/{username}")
    @PreAuthorize("#username == principal.username")
    public ResponseEntity<?> getUserProfile(@PathVariable("username") String username) {
        User user = userRepository.findUserByUsername(username);

        return ResponseEntity.ok().body(new UserProfileResponse(user.getId(), user.getUsername(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getAdress_street(),
                user.getAdress_zip(), user.getAdress_city(), user.getWishlist(), user.getRatings(), user.getAverageRating()));
    }


    // FUNKAR
    /* @GetMapping("/profile/{username}")
    @PreAuthorize("#username == principal.username")
    public Optional<User> getUserProfile(@PathVariable("username") String username) {

        return userRepository.findByUsername(username);
    } */


    // Saving this for the future, putting back the old version of the wishlistController
    /*
    @PutMapping("/{username}/wishlist")
    @PreAuthorize("#username == principal.username")
        public ResponseEntity<?> addGameToWishlist (@PathVariable("username") String username, @RequestBody WishlistDTO wishlistDTO){
            userService.addGameToWishlist(username, wishlistDTO);
        return  ResponseEntity.ok().header(String.valueOf(HttpStatus.CREATED))
                .body(new MessageResponse("Game added to wishlist"));
        } */

    /* @GetMapping("/profile/{username}")
        @PreAuthorize("authentication.principal.username == #username || hasRole('USER')")
        public Optional<User> getUserProfile(@PathVariable("username") String username) {

            return userRepository.findByUsername(username);
    } */

}