package com.example.authenticationBack.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;
    public String getToken(UserDetails user){
        return getToken(new HashMap<String,Object>(),user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user){
        Set<String> listOfRoles = getRolesSetFromUserDetails(user);
        extraClaims.put("roles",listOfRoles);
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*24*60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Set<String> getRolesSetFromUserDetails(UserDetails user){
        Set<String> listOfRoles = new HashSet<>();
        for(GrantedAuthority grantedAuthority : user.getAuthorities()){
            listOfRoles.add(grantedAuthority.getAuthority());
        }
        return listOfRoles;
    }


    public String getUserFromToken(String token) {
        return getClaim(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token,UserDetails user) {
        final String username= getUserFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }


    private Claims getClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token){
        return getClaim(token,Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
