package com.cybersoft.cozastore.config;

import com.cybersoft.cozastore.filter.JwtFilter;
import com.cybersoft.cozastore.provider.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // đây là filter dùng để custom rules liên quan tới link hoặc cấu hình của security

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CustomAuthenProvider customAuthenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()//Tắt cấu hình liên quan tới tấn công CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// phải hủy bỏ session vì xài jwt để chứng thực
                .and()
                .authorizeHttpRequests() // Quy định lại các rules liên quan tới chứng thực cho link đc gọi
                    .antMatchers("/signin/**", "/signup/**").permitAll()

                    .anyRequest().authenticated() // tat ca cac link con lai phai chung thuc
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // add jwtFilter chay trc authenFilter
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("123456"))
//                .roles("ADMIN", "DELETE")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("123456"))
//                .roles("USER", "SAVE")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }



    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(customUserDetailService)
//                .passwordEncoder(passwordEncoder()) //khai báo chuẩn mã hóa password mà userdetailservice sử dụng
                .authenticationProvider(customAuthenProvider)
                .build();
    }
}
