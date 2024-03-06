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

    // Frågor:
    // ska man som användare inte kunna se annonser överhuvudtaget om man inte har registrerat ett konto?
    // oftast kan man ju se alla annonser så att man lockas att vilja köpa och då behöver skapa ett konto

    // det känns inte bra att ALLA users kan hämta all data om ALLA users eftersom ni satt auth med
    // role USER och ADMIN på samtliga /api/users/** routes... en user bör endast kunna hämta sin egen information
    // samt kunna uppdatera den. En admin däremot ska väl kunna hämta all info om alla users samt all info om en
    // specifik user

    // detsamma gäller orders, en user ska inte kunna hämta alla users ordrar utan endast ha tillgång till just sina
    // egna orders.

    // titta över det här kanske hittar ni fler scenarion ni behöver tänka på? ändra authorization mitt tips är auth på
    // metodnivå istället. Vissa kan säker ligga kvar kanske men gå igenom varje end point för att se vilken auth som krävs.

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/auth/signup").permitAll()
                                .requestMatchers("/api/auth/login").permitAll()
                                .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/orders/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/gameAds/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/test/admin").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}