package com.icaetano.beerprint.security.config.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class TokenProvider {
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private final Key key;
    private final JwtParser jwtParser;

    // TODO: Please replace the following secret with your own.
    private static final String BASE_64_SECRET = "RjKP2Lae6Q/o4YN0BVh7/QdwUYp0LxfujuqE+tt46EB7yVR37ln9uLm/oanDpjVnXTvhdaLunPFMLzuM6JYaJV/ASAfA1RJxMfnEadsIvjr+PyVDIEP5qfw9et8Sw7WYpOoMcAOyTXHLcKh8jP8XkMJXnRNH1DpQRoYx/rbChRU+iU3FbpJQIryrf6gvseOIuofxgfy6lnTkK/8yIJy4axYvmI1NbcHeJdpS9A==";

    public TokenProvider() {
        byte[] keyBytes = Decoders.BASE64.decode(BASE_64_SECRET);

        key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(final Authentication authentication, final boolean rememberMe) {
        final String authorities = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        final long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + 1000 * 2592000L);
        } else {
            validity = new Date(now + 1000 * 36000L);
        }

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(final String token) {
        final Claims claims = jwtParser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("auth").toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(final String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);

            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
        }

        return false;
    }
}