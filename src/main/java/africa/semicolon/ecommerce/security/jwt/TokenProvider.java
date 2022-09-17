package africa.semicolon.ecommerce.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface TokenProvider {
    String getUsernameFromJWTToken(String token);
    Date getExpirationDateFromJWTToken(String token);
    <T> T getClaimFromJWTToken(String token, Function<Claims, T> claimsResolver);
    Header<?> getHeaderFromJWTToken(String token);
    Claims getAllClaimsFromJWTToken(String token);
    Boolean isJWTTokenExpired(String token);
    String generateJWTToken(Authentication authentication);

    String generateTokenForVerification(String id);

    Boolean validateJWTToken(String token, UserDetails userDetails);
    UsernamePasswordAuthenticationToken getAuthenticationToken(final String token, final Authentication existingAuth, final UserDetails userDetails);
}
