package ma.enset.gestionproduit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("USER", "ADMIN").build()
                );
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
       /* return http.formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(ar -> ar.requestMatchers("/index/**").hasRole("USER"))
                .authorizeHttpRequests(ar -> ar.requestMatchers("/saveProduct/**", "/delete/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
                .build(); */

        return http.formLogin(f -> f.loginPage("/login").permitAll())
                .csrf(Customizer.withDefaults())
              //  .authorizeHttpRequests(ar -> ar.requestMatchers("/user/**").hasRole("USER"))
               // .authorizeHttpRequests(ar -> ar.requestMatchers("/admin/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar.requestMatchers("/public/**", "/webjars/**").permitAll())
                .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
                .exceptionHandling(ex -> ex.accessDeniedPage("/notAuthorized"))
                .build();
    }
}
