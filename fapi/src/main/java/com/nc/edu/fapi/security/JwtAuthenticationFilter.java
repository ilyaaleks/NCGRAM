package com.nc.edu.fapi.security;

import com.nc.edu.fapi.service.MyUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//This class checks for the authorization header and authenticates the JWT token and sets the authentication in the context.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(SecurityJwtConstants.HEADER_STRING);
        String userName = null;
        String authToken = null;
        if (header != null && header.startsWith(SecurityJwtConstants.TOKEN_PREFIX)) {
            authToken = header.replace(SecurityJwtConstants.TOKEN_PREFIX, "");
            try {
                userName = tokenProvider.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("An error occurred during getting username from token.", e);
            } catch (ExpiredJwtException e) {
                logger.warn("The token is expired and not valid anymore.", e);
            } catch (SignatureException e) {
                logger.error("Authentication failed. Username and password not valid.", e);
            }
        } else {

        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            if (tokenProvider.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("Authenticated user " + userName + ", setting security context.");
                SecurityContextHolder.getContext().setAuthentication(authentication);//logout поместить туда нул
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
