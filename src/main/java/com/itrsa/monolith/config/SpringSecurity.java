package com.itrsa.monolith.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()

//TODO
// to setup user profiles and security further analysis must be done
// out of scope at current development stage

// right now we'll prevent erasing data

                .antMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/products/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/api/products/").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/categories/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/api/categories/").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/booking/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/booking/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/api/booking/").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/employee/**").hasRole("ADMIN")
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        var passwordEncoder = passwordEncoder().encode("password");
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder)
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(SecurityProperties properties,
                                                                 ObjectProvider<PasswordEncoder> passwordEncoder) {
        var passwordEncodered = passwordEncoder().encode("password");
        SecurityProperties.User user = properties.getUser();
        List<String> roles = Arrays.asList("ADMIN");
        return new InMemoryUserDetailsManager(
                User.withUsername(user.getName()).password(passwordEncodered)
                        .roles(StringUtils.toStringArray(roles)).build());
    }
}

