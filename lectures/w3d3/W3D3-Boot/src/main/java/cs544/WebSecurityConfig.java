package cs544;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("pass"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.debug(true)
                .ignoring()
                .requestMatchers("/js/**", "/css/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/login", "/logout")
                        .permitAll()
                        // .requestMatchers("/cars/**", "/addCar")
                        // .permitAll()
                        .requestMatchers(HttpMethod.GET, "/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cars").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/cars/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cars/addCar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cars/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cars/update/**").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.GET, "/cars/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cars/delete/**").hasRole("ADMIN")
                        // .requestMatchers("/**").permitAll())
                        .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults()).logout(Customizer.withDefaults());

        return http.build();
    }

}
