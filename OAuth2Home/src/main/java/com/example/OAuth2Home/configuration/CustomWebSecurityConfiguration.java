package com.example.OAuth2Home.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@ConfigurationProperties
public class CustomWebSecurityConfiguration {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.type}")
    private String type;

    @Value("${spring.datasource.driverClassName}")
    private String driverClass;

    @Bean
    public DataSource dataSource() {
        if (type.equals("MYSQL")) {
            return DataSourceBuilder
                    .create()
                    .username(username)
                    .password(password)
                    .url(url)
                    .driverClassName(driverClass)
                    .build();
        }

        return DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(url)
                .driverClassName(driverClass)
                .build();
    }


    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers("/garage/entry", "/kitchen/entry", "/laundry/entry", "/rooms/entry").authenticated()
                        .requestMatchers("/living_room/entry").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsManager userDetailsService(DataSource dataSource) {


     /*   UserDetails kitchen = User.builder().roles("kitchenUser").username("kitchenUser").password("{noop}12345Kitchen").build();
        UserDetails garage = User.builder().roles("garageUser").username("garageUser").password("{noop}12345@Garage").build();
        UserDetails laundry = User.builder().roles("laundryUser").username("laundryUser").password("{bcrypt}$2y$10$lGAi1qCuGPNmSRmCRTm7DObmidTeNL9Vq0TLberYyYLsSbmK4ZQFq").build();//12345laundry --bctryptcode
        UserDetails living = User.builder().roles("hallUser").username("hallUser").password("{SHA-256}858e1f305443116c22100e06fdd49cc11679362587dcca2a4e01bf2e1162777d").build();//12345hall--encodehashhexdecimal
        UserDetails rooms = User.builder().roles("roomsUser").username("roomsUser").password("{ldap}03F51CE2bdnsbuyweui2").build(); //12345rooms -- ldap Keygenerator randomkey using as password
        return new InMemoryUserDetailsManager(kitchen, garage, laundry, living, rooms);*/

        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return  new HaveIBeenPwnedRestApiPasswordChecker();
    }

    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
