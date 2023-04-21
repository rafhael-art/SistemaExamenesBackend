/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.examenes.security;

import com.sistema.examenes.impl.UserDetailsSeriviceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author rafhael
 */
@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsSeriviceImpl userService;

    @Autowired
    private jwtUtils jwtUtils;

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        
        if (requestTokenHeader !=  null && requestTokenHeader.startsWith("Bearer ")) {
          jwtToken = requestTokenHeader.substring(7);
          
            try {
                username = this.jwtUtils.extractUsername(jwtToken);
            } catch (ExpiredJwtException expiredJwtException) {
                System.out.println("El token a expirado");
            } catch (Exception ex){
                ex.printStackTrace();
            }
        } else{
            System.out.println("Token Invalido, No Empieza con Bearer string");
        }
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() ==  null) {
            UserDetails userDetails = this.userService.loadUserByUsername(username);
            if (this.jwtUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{
                System.out.println("Token no es Válido");
            }
            
            filterChain.doFilter(request, response);
        }
    }

}
