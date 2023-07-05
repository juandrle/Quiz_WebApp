package de.hsrm.mi.web.projekt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

/*     @Bean
    UserDetailsService userDetailsService() {
        UserBuilder userbuilder = User.withDefaultPasswordEncoder();

        UserDetails user1 = userbuilder.username("joghurta").password("1234")
                .roles("USER", "CHEF").build();

        UserDetails user2 = userbuilder.username("joendhard").password("4321")
                .roles("USER").build();
        return new InMemoryUserDetailsManager(user1, user2);
    } */
    @Order(1)
    @Bean
    SecurityFilterChain filterChainAPI(HttpSecurity http) throws Exception {
        http.securityMatchers(s -> s.requestMatchers("/api/**", "/stompbroker"))
        .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
        .csrf(csrf -> csrf.disable())
        .httpBasic(withDefaults())
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        return http.build();
    }

    @Order(2)
    @Bean
    SecurityFilterChain filterChainApp(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(toH2Console()).permitAll()
                .requestMatchers(HttpMethod.GET, "/kategorie", "/frage", "/quiz", "/registrieren").permitAll()
                .requestMatchers(HttpMethod.POST, "/registrieren").anonymous()
                .requestMatchers("/*/0", "/*/*/del").hasRole("CHEF")
                .anyRequest()
                .authenticated())
                .formLogin(withDefaults()).formLogin(in -> in.defaultSuccessUrl("/frage", true))
                .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()))
                .headers(hdrs -> hdrs.frameOptions().sameOrigin())
                .logout(withDefaults()).logout(out -> out.logoutSuccessUrl("/frage"));

        return http.build();

    }


}
