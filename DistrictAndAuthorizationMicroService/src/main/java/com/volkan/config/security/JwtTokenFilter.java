package com.volkan.config.security;

import com.volkan.exception.DistrictAndAuthorizationManagerException;
import com.volkan.exception.EErrorType;
import com.volkan.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String userHeader = request.getHeader("Authorization");
        if (userHeader!=null && userHeader.startsWith("Bearer ")) {
            String token = userHeader.substring(7);
            Optional<String> userRole = jwtTokenManager.getRoleFromToken(token);
            if (userRole.isPresent()) { //jwtTokenManager.validateToken(token)
                UserDetails userDetails = jwtUserDetails.loadUserByUserRole(userRole.get());
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                throw new DistrictAndAuthorizationManagerException(EErrorType.INVALID_TOKEN);
            }
        }
        filterChain.doFilter(request,response);
    }
}
