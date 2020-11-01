package nissan.procurement.utils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;

/*
    Our simple static class that demonstrates how to create and decode JWTs.
 */
public class Authentication {

    // The secret key. This should be in a property file NOT under source
    // control and not hard coded in real life. We're putting it here for
    // simplicity.

    public static String createJWT(String username) {
    	return _createJWT(username, Constant.EXPIRATIONTIME);
    }
    
    public static String createJWT(String username, long ttlMillis) {
    	return _createJWT(username, ttlMillis);
    }
    
    public static boolean isAuthenticated(String jwt) {
    	if (jwt == null || jwt.isEmpty()) {
    		return false;
    	}
    	try {
	    	Claims claims = _decodeJWT(jwt);
	    	String username = claims.getSubject();
	    	return username!=null?true:false;
    	} catch (Exception  e) {
    		return false;
    	}
    	
    }
    private static String _createJWT(String username, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.SECRET_STRING);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(username)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    private static Claims _decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Constant.SECRET_STRING))
                .parseClaimsJws(jwt.replace(Constant.TOKEN_PREFIX, ""))
                .getBody();
        return claims;
    }
}