package africa.semicolon.ecommerce.dto.requests;

import africa.semicolon.ecommerce.data.model.ConfirmationToken;
import africa.semicolon.ecommerce.data.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Builder
@Setter
@Getter
public class ConfirmationRequest {
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    private User user;


    public static ConfirmationRequest packDto(ConfirmationToken confirmationToken){
        return ConfirmationRequest.builder()
                .confirmedAt(confirmationToken.getConfirmedAt())
                .createdAt(confirmationToken.getCreatedAt())
                .token(confirmationToken.getToken())
                .user(confirmationToken.getUser())
                .expiresAt(confirmationToken.getExpiresAt())

                .build();
    }
}
