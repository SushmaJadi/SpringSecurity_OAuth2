package com.example.OAuth2Home.configuration;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class CustomWebSecurityConfiguration {



    /*@Bean
        public Filter springSecurityFilterChain(ObjectProvider<HttpSecurity> provider) throws Exception {

            boolean hasFilterChain = !this.securityFilterChains.isEmpty();
            if (!hasFilterChain) {
              return this.webSecurity.addSecurityFilterChainBuilder(() -> {
                    httpSecurity = provider.getObject();
                    httpSecurity.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/garage/entry").permitAll());
                    httpSecurity.formLogin(Customizer.withDefaults());
                    httpSecurity.httpBasic(Customizer.withDefaults());
                    return httpSecurity.build();
                }).build();
            }
            for (SecurityFilterChain securityFilterChain : this.securityFilterChains) {
                this.webSecurity.addSecurityFilterChainBuilder(() -> securityFilterChain);
            }
            for (WebSecurityCustomizer customizer : this.webSecurityCustomizers) {
                customizer.customize(this.webSecurity);
            }
            return this.webSecurity.build();
        }*/
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/garage/entry", "/kitchen/entry", "/laundry/entry").authenticated());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails kitchen = User.builder().roles("kitchenUser").username("kitchenUser").password("{noop}12345Kitchen").build();
        UserDetails garage = User.builder().roles("garageUser").username("garageUser").password("{noop}12345@Garage").build();
        UserDetails laundry = User.builder().roles("laundryUser").username("laundryUser").password("{bcrypt}$2y$10$lGAi1qCuGPNmSRmCRTm7DObmidTeNL9Vq0TLberYyYLsSbmK4ZQFq").build();
        UserDetails living = User.builder().roles("hallUser").username("hallUser").password("12345hall").build();
        UserDetails rooms = User.builder().roles("roomsUser").username("roomsUser").password("12345rooms").build();

        return new InMemoryUserDetailsManager(kitchen, garage, laundry, living, rooms);
    }

    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
