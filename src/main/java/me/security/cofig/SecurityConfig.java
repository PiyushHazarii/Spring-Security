package me.security.cofig;

import me.security.filter.JWTAuthFilter;
import me.security.service.CustomeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // it tells spring boot that we are going to tweek our authentication filter
// and we are going to enable the wensecurity config inside this class
//it turns on Spring Security for web requests and prepare the security filter system
public class SecurityConfig {

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // we use this when we use the jwt authentication and do not use cookie authentication
                // then use this csrf disable
                // another reason is that stateless .sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // then csrf has no meaning over this
                // when to use csrf form login session cookies...
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/authenticate")
                        .permitAll().anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

//                .httpBasic(withDefaults()); // this will remove the default username password authentication authentication
        return http.build();
    }

    // in this we have to tell spring that use this class as a user details service
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomeUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        // in authentication manager we have to register our custom userdetails service
        DaoAuthenticationProvider daoAuthenticationProvider =    new DaoAuthenticationProvider(userDetailsService);

        // dao authentication service mein hum apna customn class patak rahe hai taaki wo authenticate kr ske
        // we have craeted custom dao authentication provider
        // and this guy to use custom user details service and ask to use bycrypt password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);

    }
}
