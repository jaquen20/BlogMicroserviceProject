package com.sandeep.Auth.JwtConfig;


import com.sandeep.Auth.Service.CustomUserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    CustomUserService customUserService;

    @Autowired
    JwtHelper jwtHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final  String authorizationHeader=request.getHeader("Authorization");
        String username=null;
        String jwt=null;
        if(authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")){
            jwt=authorizationHeader.substring(7);
            try {
                username=jwtHelper.getUsernameFromToken(jwt);

            }
            catch (IllegalArgumentException e) {
                System.out.println("unable to get token");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Unable to get token");
                return;
            }catch (ExpiredJwtException e){
                System.out.println("jwt token has expired");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Token has expired");
                return;
            }catch(MalformedJwtException e){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Wrong token");
                return;
            }
            }else {
            logger.warn("jwt token does not begin with the bearer String");
        }

        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.customUserService.loadUserByUsername(username);
            if (jwtHelper.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
        }
    }

