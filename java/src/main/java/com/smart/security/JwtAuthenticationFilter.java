package com.smart.security;

import static com.smart.util.Constants.HEADER_STRING;
import static com.smart.util.Constants.TOKEN_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.smart.model.User;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX,"");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
            	LOGGER.error("an error occured during getting username from token");
            } catch (ExpiredJwtException e) {
            	LOGGER.error("the token is expired and not valid anymore");
            } catch(SignatureException e){
            	LOGGER.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
        	LOGGER.error("couldn't find bearer string, will ignore the header");
        }
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
	                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
	                //UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
	                // System.out.println("authenticated user " + username + ", setting security context");
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	                AuthConfig.currentUser.set((User)userDetails);
	            }
        	
            
            
        }

        chain.doFilter(req, res);
    }
}