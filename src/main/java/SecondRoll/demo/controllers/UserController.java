package SecondRoll.demo.controllers;

import SecondRoll.demo.models.Rating;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.UpdateUserDTO;
import SecondRoll.demo.payload.WishlistDTO;
import SecondRoll.demo.payload.response.MessageResponse;
import SecondRoll.demo.payload.response.UserProfileResponse;
import SecondRoll.demo.repository.UserRepository;
import SecondRoll.demo.security.services.UserDetailsServiceImpl;
import SecondRoll.demo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // GET a user by ID.
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET ALL users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // NEW AND "IMPROVED" UPDATE USER - goes through DTO to restrain the info that is ok for user to update
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/{userId}/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO,
                                        @PathVariable ("userId") String userId, HttpServletRequest request) {
        if (userDetailsService.hasPermission(userId, request)) {

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
                    .body(new MessageResponse("You don't have authority to update this user"));
        }
    }

    // DELETE a user based on id
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    // ADD gameAd to a user wishlist using a Data Transfer Object-reference.
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}/wishlist")
    public ResponseEntity<?> addGameToWishlist (@PathVariable ("userId") String userId,
                                                @RequestBody WishlistDTO wishlistDTO, HttpServletRequest request){
        if (userDetailsService.hasPermission(userId, request)) {
            userService.addGameToWishlist(userId, wishlistDTO);
            return ResponseEntity.ok().body(new MessageResponse("Game added to wishlist."));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You don't have authority to add a game to this wishlist"));
        }
    }

    // REMOVE gameAd from a user wishlist using a Data Transfer Object-reference.
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value = "/{userId}/wishlist")
    public ResponseEntity<?> removeGameFromWishlist(@PathVariable ("userId") String userId,
                                                    @RequestBody WishlistDTO wishlistDTO, HttpServletRequest request) {
        if (userDetailsService.hasPermission(userId, request)) {
            userService.removeGameFromWishlist(userId, wishlistDTO);
            return ResponseEntity.ok().body(new MessageResponse("Game removed from wishlist."));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You don't have authority to remove a game from this wishlist"));
        }

    }

    // ADD rating to a user
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}/rating")
    public ResponseEntity<?> addRatingToUser (@PathVariable ("userId") String userId, @RequestBody Rating rating) {
        userService.addRatingToUser(userId, rating);
        return ResponseEntity.ok().body(new MessageResponse("Rating added."));
    }

    @PreAuthorize("hasRole('ADMIN') or #username == principal.username")
    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable("username") String username) {
        User user = userRepository.findUserByUsername(username);

        return ResponseEntity.ok().body(new UserProfileResponse(user.getId(), user.getUsername(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getAdress_street(),
                user.getAdress_zip(), user.getAdress_city(), user.getWishlist(), user.getRatings(), user.getAverageRating()));
    }
}