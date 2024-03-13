package SecondRoll.demo.security.services;

import SecondRoll.demo.models.ERole;
import SecondRoll.demo.models.User;
import SecondRoll.demo.repository.UserRepository;
import SecondRoll.demo.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found."));

        return UserDetailsImpl.build(user);
    }


    // Method for authenticating access for logged in user by id OR person with admin role
    public boolean hasPermission(String userId, HttpServletRequest request) {
        String jwt = jwtUtils.getJwtFromCookie(request);
        String username = jwtUtils.getUsernameFromJwtToken(jwt);
        User user = userRepository.findUserByUsername(username);
        List<String> userRoles = user.getRoles().stream().map(r -> r.getName().name()).toList();
        return Objects.equals(userId, user.getId()) || userRoles.contains(ERole.ROLE_ADMIN.name());
    }
}