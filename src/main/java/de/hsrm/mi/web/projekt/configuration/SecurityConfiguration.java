package de.hsrm.mi.web.projekt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpBasicDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserBuilder userbuilder = User.withDefaultPasswordEncoder();

        UserDetails user1 = userbuilder.username("joghurta").password("1234")
                .roles("USER", "CHEF").build();

        UserDetails user2 = userbuilder.username("joendhard").password("4321")
                .roles("USER").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    SecurityFilterChain filterChainApp(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/kategorie", "/frage", "/quiz").permitAll()
                .requestMatchers(HttpMethod.POST, "/registrieren").anonymous()
                .requestMatchers("/*/0", "/*/*/del").hasRole("CHEF")
                .anyRequest()
                .authenticated())
                .formLogin(withDefaults())
                .logout(withDefaults()).logout(out -> out.logoutSuccessUrl("/frage"));

        return http.build();

    }

}