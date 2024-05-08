package SecondRoll.demo.controllers;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.Rating;
import SecondRoll.demo.models.User;
import SecondRoll.demo.payload.UpdateUserDTO;
import SecondRoll.demo.payload.WishlistDTO;
import SecondRoll.demo.payload.response.MessageResponse;
import SecondRoll.demo.payload.response.UserProfileResponse;
import SecondRoll.demo.payload.response.UserSearchByIdResponse;
import SecondRoll.demo.payload.response.WishlistResponse;
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

import java.util.ArrayList;
import java.util.List;

// @CrossOrigin(origins = "5173", maxAge = 3600)
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

    // GET a user by ID
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        User user = userRepository.findUserById(userId);

        return ResponseEntity.ok().body(new UserSearchByIdResponse(user.getId(), user.getUsername(), user.getAdress_city()));
    }

    // GET ALL users
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserSearchByIdResponse> foundUsers = new ArrayList<>();
        for (User user : allUsers) {
            UserSearchByIdResponse response = new UserSearchByIdResponse(user.getId(), user.getUsername(), user.getAdress_city());
            foundUsers.add(response);
        }
        return ResponseEntity.ok().body(foundUsers);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all/{adminId}")
    public List<User> getAllUsersAdmin(@PathVariable ("adminId") String adminId, HttpServletRequest request) {
        if (userDetailsService.hasPermission(adminId, request)) {
            return userService.getAllUsers();
        } else {
            return null;
        }
    }

    // PUT - update user
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

    // GET users own wishlist
    @PreAuthorize("hasRole ('USER')")
    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<?> getPersonalWishlist (@PathVariable ("userId") String userId, HttpServletRequest request) {
        if (userDetailsService.hasPermission(userId, request)) {
            User user = userRepository.findUserById(userId);
            List<GameAds> wishlist = user.getWishlist();
            List<WishlistResponse> foundWishlist = new ArrayList<>();
            for (GameAds gameAd : wishlist) {
                WishlistResponse response = new WishlistResponse(gameAd.getId(), gameAd.getTitle(), gameAd.getPrice());
                foundWishlist.add(response);
            }
            return ResponseEntity.ok().body(foundWishlist);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You dont have authority to view this wishlist"));
        }

    }

    // ADD gameAd to a user wishlist using a Data Transfer Object-reference
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/wishlist/{userId}")
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

    // REMOVE gameAd from a user wishlist using a Data Transfer Object-reference
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value = "/wishlist/{userId}")
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
                user.getAdress_zip(), user.getAdress_city(), user.getRatings(), user.getAverageRating()));
    }
}