package africa.semicolon.ecommerce.events;

import africa.semicolon.ecommerce.dto.responses.MailResponse;
import africa.semicolon.ecommerce.email.MailService;
import africa.semicolon.ecommerce.email.MessageRequest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Component("mailgun_sender")
@Slf4j
@AllArgsConstructor
public class SendMessageEventListener {

    // @Qualifier("mailgun_sender")
    private final MailService mailService;
    private final TemplateEngine templateEngine;
    private final Environment environment;

    @EventListener
    public void handleSendMessageEvent(SendMessageEvent event) throws UnirestException, ExecutionException, InterruptedException {
        MessageRequest messageRequest = (MessageRequest) event.getSource();

//        String verificationLink = messageRequest.getDomainUrl() + "api/v1/auth/verify/" + messageRequest.getVerificationToken();

        log.info("Message request --> {}", messageRequest);

        Context context = new Context();
        context.setVariable("user_name", messageRequest.getFirstName().toUpperCase());
        context.setVariable("verification_token", messageRequest.getBody());
        if (Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
            log.info("Message Event -> {}", event.getSource());
            messageRequest.setBody(templateEngine.process("registration_verification_mail.html", context));
            MailResponse mailResponse = mailService.sendHtmlMail(messageRequest).get();
            log.info("Mail Response --> {}", mailResponse);

        } else {
//            messageRequest.setBody(verificationLink);
            MailResponse mailResponse = mailService.sendSimpleMail((MessageRequest) event.getSource()).get();
            log.info("Mail Response --> {}", mailResponse);

        }
    }

}
