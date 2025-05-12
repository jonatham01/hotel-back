package manager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import manager.entity.User;
import manager.repository.JwtTokenRepository;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import io.jsonwebtoken.io.Decoders;
import org.springframework.util.StringUtils;


@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final JwtTokenRepository tokenRepository;

    public SecretKey getSecretKey() {
        byte[] bytes = Decoders.BASE64.decode("2k4n2k42k4b24jb213l1n3knk1n31kn3k13k131k3b13kb");
        return Keys.hmacShaKeyFor(bytes);
    }

    public String createToken(User user, Map<String, Object> claims) {

            Date now = new Date(System.currentTimeMillis());
            Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 24);
            return Jwts.builder()
                    .header()
                    .type("jwt")
                    .and()
                    .subject(user.getUsername())
                    .issuedAt(now)
                    .expiration(expiration)
                    .claims(claims)
                    .signWith(getSecretKey())
                    .compact();

    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    public String extractJwtFromRequest(HttpServletRequest req) {
        String header=  req.getHeader("Authorization");
        //System.out.println(header);
        //if(StringUtils.hasText(header)|| !header.startsWith("Bearer "))return null;
        if(header ==null) return null;
        return header.substring(7).trim();
    }

    public Date getExpirationDate(String token) {
        return parseToken(token).getExpiration();
    }

}
