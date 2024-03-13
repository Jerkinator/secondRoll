package SecondRoll.demo.security;

import SecondRoll.demo.security.jwt.AuthTokenFilter;
import SecondRoll.demo.security.jwt.AuthenticationEntryJwt;
import SecondRoll.demo.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationEntryJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // titta över det här kanske hittar ni fler scenarion ni behöver tänka på? ändra authorization mitt tips är auth på
    // metodnivå istället. Vissa kan säker ligga kvar kanske men gå igenom varje end point för att se vilken auth som krävs.

    // obs obs, ändra framöver så att inte admin kan lägga in orders

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/auth/signup").permitAll()
                                .requestMatchers("/api/auth/login").permitAll()
                                .requestMatchers("/api/gameAds/all").permitAll()
                                //.requestMatchers("/api/users/**").hasRole( "ADMIN")
                                .requestMatchers("api/users/{userId}/wishlist").hasRole("USER")
                                .requestMatchers("/api/users/{userId}/update").hasRole("USER")
                                .requestMatchers("/api/gameAds/sortbydate/**").permitAll()
                                .requestMatchers("/api/gameAds/sortbyprice/**").permitAll()
                                .requestMatchers("/api/gameAds").hasRole("USER")
                                .requestMatchers("/api/users/all").hasRole("ADMIN")
                                .requestMatchers("/api/users/profile/{username}").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/orders").hasRole("USER")
                                .requestMatchers("/api/orders/buyerhistory/{buyerId}").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/orders/sellerhistory/{sellerId}").hasAnyRole("USER", "ADMIN")
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}