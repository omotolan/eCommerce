package africa.semicolon.ecommerce.email;

import africa.semicolon.ecommerce.dto.responses.MailResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.concurrent.CompletableFuture;

public interface MailService {
    CompletableFuture<MailResponse> sendSimpleMail(MessageRequest messageRequest) throws UnirestException;
    CompletableFuture<MailResponse> sendHtmlMail(MessageRequest messageRequest) throws UnirestException;

}
