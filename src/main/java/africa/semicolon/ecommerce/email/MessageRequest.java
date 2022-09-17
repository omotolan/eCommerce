package africa.semicolon.ecommerce.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MessageRequest {
    @NotNull
    @NotBlank
    private String firstName;
    private String receiver;
    private String body;
    private String subject;
    private String sender;
}
