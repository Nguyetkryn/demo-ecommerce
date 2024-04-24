package com.example.demoecommerce.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {
    private static  final Logger log = LoggerFactory.getLogger(JwtUtils.class);
    // secret key dùng cho việc mã hoá dữ liệu để chuyển về thành 1 chuỗi sử dụng SHA256
    private  static String secretKey = "ChiNguyetThatDangYeu@123";
    // thời gian hết hạn của token,  đang set là bao nhiêu đấy em k nhớ
    private  static  Long dueTime = 6000000L;

    // Chuyển hoá username nhận vào thành một chuỗi token
    public String generateTokenByUsername(String username){
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + dueTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserByToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException exception) {
            log.error("Invalid JWT signature :{}", exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.error("Invalid JWT malformed :{}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            log.error("JWT token is expired :{}", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("JWT token is unsupported :{} ", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error("JWT claims is not empty :{}", exception.getMessage());
        }
        return false;
    }


}
