package com.wy.yunoa.utils.JWT;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: wy
 * @CreateTime: 2023-11-08  16:35
 * @Description: TODO
 * @Version: 1.0
 */
public class JwtUtil {
    private final static String SUBJECT = "AUTH-USER";
    public static final int ACCESS_EXPIRE = 60;
    private final static String JWT_ISS = "wy";
    private final static String SECRET = "com.wy.yunoa";
    public static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    // 加密算法
    private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;

    public static String createToken(Long userId,String username) {
        String uuid = UUID.randomUUID().toString();
        Date exprireDate = Date.from(Instant.now().plusSeconds(ACCESS_EXPIRE));
        return Jwts.builder().header()// 设置头部信息header
                .add("typ", "JWT").add("alg", "HS256")
                .and()
                // 设置自定义负载信息payload
                .claim("userId",userId).claim("username", username)
                // 令牌ID
                .id(uuid)
                // 过期日期
                .expiration(exprireDate)
                // 签发时间
                .issuedAt(new Date())
                // 主题
                .subject(SUBJECT)
                // 签发者
                .issuer(JWT_ISS)
                // 签名
                .signWith(KEY,ALGORITHM)
                .compact();
    }

    public static Jws<Claims> parseClaim(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
    }
    public static JwsHeader parseHeader(String token) {
        return parseClaim(token).getHeader();
    }

    public static Claims parsePayload(String token) {
        return parseClaim(token).getPayload();
    }

    public static Long getUserId(String token) {
        Claims payload = parseClaim(token).getPayload();
        return payload.get("userId", Long.class);
    }

    public static String getUsername(String token) {
        return parseClaim(token).getPayload().get("username",String.class);
    }

}

