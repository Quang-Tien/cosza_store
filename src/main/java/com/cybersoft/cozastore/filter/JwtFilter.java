package com.cybersoft.cozastore.filter;


import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {
    /*
        nhan dc token truyen tren header
        giai ma token -> thanh cong thi hop le, -> tao chung thuc cho di vao link

     */
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = request.getHeader("Authorization");
            Claims claims = jwtHelper.decodeToken(token.replace("Bearer ", ""));
            if(claims != null) {
                SecurityContext context = SecurityContextHolder.getContext();
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                context.setAuthentication(user);
            }
        }
        catch (Exception e) {

        }
        filterChain.doFilter(request, response);
    }
}
