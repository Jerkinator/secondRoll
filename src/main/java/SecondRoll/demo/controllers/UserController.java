package SecondRoll.demo.controllers;

import SecondRoll.demo.models.GameAds;
import SecondRoll.demo.models.User;
import SecondRoll.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    UserService userService;

    // CREATE a User.
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

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

    // POST a gameAd to a user wishlist using ObjectID reference.
    @PostMapping("/{id}/wishlist")
    public ResponseEntity<User> addGameToWishlist(@PathVariable String id, @RequestBody GameAds gameAds) {
        User userWithWishList = userService.addGameToWishlist(id, gameAds);
        return ResponseEntity.ok(userWithWishList);
    }
}