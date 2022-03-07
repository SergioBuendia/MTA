package sv.gob.mh.dga.mta.seguridad.web;

import java.sql.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sv.gob.mh.dga.mta.common.constants.Constantes;
import sv.gob.mh.dga.mta.seguridad.model.JwtUser;

@Component
public class JwtUtil {
	
	//private final long EXPIRATION_TIME = 21_600_000; // 6 hours
	//private final long EXPIRATION_TIME = 1800000; // 30 minutos
	//private final long EXPIRATION_TIME = 60000; // 1 minuto
	  
	private final String SECRET = "Mta.2021";
	

	/**
	 * Parse Token
	 * 
	 * @param token, Token
	 * @return JwtUser
	 */
    public JwtUser parseToken(String token) {
    	JwtUser jwtUser = null;
    	
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            
            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        } catch (JwtException | ClassCastException e) {

        }
        
        return jwtUser;
    }

    /**
     * Generate Token
     * 
     * @param jwtUser, JwtUser
     * @return String
     */
    public String generateToken(JwtUser jwtUser) {
        Claims claims = Jwts.claims()
        		.setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + Constantes.EXPIRATION_TIME + 10*1000*10))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
    /**
     * Generate Token
     * 
     * @param jwtUser, JwtUser
     * @return String
     */
    public String doExpireToken(JwtUser jwtUser) {
        Claims claims = Jwts.claims()
        		.setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());
        
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
    /**
     * Generate Token Swagger
     * 
     * @param jwtUser, JwtUser 
     * @return String
     */
    public String generateTokenSwagger(JwtUser jwtUser) {
        Claims claims = Jwts.claims()
        		.setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
}
