package com.weihua.race.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.weihua.race.common.enums.JWTResultEnums;
import com.weihua.race.common.exception.JWTException;
import com.weihua.race.domain.bean.UserTokenInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class JWTUtil {

    private static final long EXPIRE_TIME = 60 * 60 * 24 * 30;

    private static final String SECRET = "SHIRO+JWT";


    /**
     * <p>
     * 根据用户的[username,roleId,companyId,groupId]生成JWTToken
     * </p>
     *
     * @param info 用户权限相关信息类 {@link UserTokenInfo}
     * @return JWTToken
     * @date 2023-04-18 10:37
     */
    public static String createToken(UserTokenInfo info) {
        // 过期时间
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.SECOND, (int) EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 创建JwtToken对象
        String token = "";
        token = JWT.create()
                // 用户名
                .withSubject(info.getUsername())
                // 发布时间
                .withIssuedAt(new Date())
                // 过期时间
                .withExpiresAt(ca.getTime())
                // 自定义随机Claim
                .withClaim("username", info.getUsername())
                .withClaim("roleId", info.getRoleId())
                .sign(algorithm);

        return token;
    }

    /**
     * <p>
     * 在token中获取到username信息
     * </p>
     *
     * @param token JWTToken
     * @return java.lang.String 用户名
     * @date 2022/5/7 15:49 <br>
     * @author liulingyu <br>
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (NullPointerException | JWTDecodeException e) {
            throw new JWTException(JWTResultEnums.TOKEN_DECODE_FAILED, e.getMessage());
        } catch (Exception e) {
            log.error("error in jwt getUsername: ", e);
            throw new JWTException(JWTResultEnums.TOKEN_DECODE_FAILED, e.getMessage());
        }
    }

    /**
     * <p>
     * 校验 token 是否正确
     * </p>
     *
     * @param token    密钥
     * @param username 用户名
     * @return boolean 是否正确
     * @date 2022/5/7 15:51 <br>
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

}
