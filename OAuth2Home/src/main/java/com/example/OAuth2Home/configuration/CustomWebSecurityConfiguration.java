package com.example.OAuth2Home.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebSecurity
@Configuration
public class CustomWebSecurityConfiguration {


    @Autowired
    private HttpSecurity httpSecurity;


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
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
}
