//package com.sandeep.profile_data.JWT;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JWTRequestFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//        if (token !=null && token.startsWith("Bearer ")){
//            token=token.substring(7);
//            if (jwtUtil.validateToken(token)){
//                String username= jwtUtil.getUsernameFromToken(token);
//                request.setAttribute("username", username);
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
//}
