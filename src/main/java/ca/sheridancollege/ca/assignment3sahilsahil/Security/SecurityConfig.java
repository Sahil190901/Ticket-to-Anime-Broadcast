package ca.sheridancollege.ca.assignment3sahilsahil.Security;

import ca.sheridancollege.ca.assignment3sahilsahil.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    private UserDetailsServiceImpl userDetailsService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http,
                                             PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }


     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests((auth)->
                        auth
//                                .authorizeRequests()
                                .requestMatchers(HttpMethod.GET, "/register").permitAll()

                                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                                .requestMatchers("/user/**").hasRole("GUEST")
                                .requestMatchers(HttpMethod.GET ,"/").permitAll()
                     .requestMatchers(HttpMethod.GET ,"/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.GET ,"/view").hasAnyRole("GUEST" , "VENDER")
                                .requestMatchers(HttpMethod.GET ,"/view1").hasRole("VENDER")
                                .requestMatchers(HttpMethod.GET ,"/edit/**").hasRole("VENDER")
                                .requestMatchers(HttpMethod.GET ,"/delete/**").hasRole("VENDER")
                                .requestMatchers(HttpMethod.GET ,"/add").hasRole("VENDER")
                                .requestMatchers(HttpMethod.GET ,"/Images/**").permitAll()
                                .anyRequest().authenticated())
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login")
                                .failureUrl ("/login?failed")

                                .permitAll())
                .logout((logout) ->
                        logout
                                .deleteCookies("remove")
                                .invalidateHttpSession(true)
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll())
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .accessDeniedPage("/AccessDenied"));

        return http.build();

    }
}
