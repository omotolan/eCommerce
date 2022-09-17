package africa.semicolon.ecommerce.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateToken {
    public static String generateToken(){
        String token = UUID.randomUUID().toString();

        return token.substring(0, 5);

    }

}
