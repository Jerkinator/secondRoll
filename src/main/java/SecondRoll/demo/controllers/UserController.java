package SecondRoll.demo.controllers;

import SecondRoll.demo.models.Rating;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.WishlistDTO;
import SecondRoll.demo.payload.response.UserInfoResponse;
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

    // GET ALL users.
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // HELENA: se kommentar i UserService
    // UPDATE a user.
    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    // DELETE a user based on ID.
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    // ADD a gameAd to a user wishlist using a Data Transfer Object-reference.
    @PutMapping("/{userId}/wishlist")
        public ResponseEntity<?> addGameToWishlist (@PathVariable String userId, @RequestBody WishlistDTO wishlistDTO){
            User userWithWishList = userService.addGameToWishlist(userId, wishlistDTO);
            return new ResponseEntity<>(userWithWishList, HttpStatus.CREATED);
        }

    // REMOVE a gameAd to a user wishlist using a Data Transfer Object-reference.
    @DeleteMapping(value = "/{userId}/wishlist")
    public ResponseEntity<?> removeGameFromWishlist(@PathVariable String userId, @RequestBody WishlistDTO wishlistDTO) {
        User userWithWishList = userService.removeGameFromWishlist(userId, wishlistDTO);
        return new ResponseEntity<>(userWithWishList, HttpStatus.CREATED);
    }

    // ADD rating to a user.
    @PutMapping("/{userId}/rating")
    public ResponseEntity<?> addRatingToUser (@PathVariable String userId, @RequestBody Rating rating) {
        User userWithRating = userService.addRatingToUser(userId, rating);
        return new ResponseEntity<>(userWithRating, HttpStatus.CREATED);
    }
    /* @GetMapping ("/profile/{username}")
    public Optional<User> getUserProfile(@PathVariable String username) {
        return userRepository.findByUsername(username);
    } */

    @GetMapping("/profile/{username}")
    @PreAuthorize("#username == principal.username")
    public ResponseEntity<?> getUserProfile(@PathVariable("username") String username) {
        User user = userRepository.findUserByUsername(username);
        return ResponseEntity.ok().body(new UserInfoResponse(user.getId(), user.getUsername()));
    }


    // FUNKAR
    /* @GetMapping("/profile/{username}")
    @PreAuthorize("#username == principal.username")
    public Optional<User> getUserProfile(@PathVariable("username") String username) {

        return userRepository.findByUsername(username);
    } */

    /* @GetMapping("/profile/{username}")
        @PreAuthorize("authentication.principal.username == #username || hasRole('USER')")
        public Optional<User> getUserProfile(@PathVariable("username") String username) {

            return userRepository.findByUsername(username);
    } */


    //FUNKAR EJ
    /* @GetMapping("/profile/{username}")
    public ResponseEntity<Optional<User>> getLoggedInUserInfo (@PathVariable String username) {
        if (username == SecurityContextHolder.getContext().getAuthentication().getPrincipal()) {
            return ResponseEntity.ok((Optional.ofNullable(userRepository.findUserByUsername(username))));
        }
        return (ResponseEntity<Optional<User>>) ResponseEntity.badRequest();
    } */


    // FUNKAR EJ
    /* @GetMapping("/profile/{userId}")
    public ResponseEntity<User> getLoggedInUser (@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);

        Authentication authentication = authenticationManager.authenticate((Authentication) user.get());
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    return new ResponseEntity<User>(HttpStatus.OK);
    } */
}