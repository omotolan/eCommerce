package africa.semicolon.ecommerce.util;

import africa.semicolon.ecommerce.services.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class DeleteExpiredTokenScheduler {

    private final TokenService tokenService;

    @Async
    @Scheduled(cron = "0 0 */23 * * *") //sec,min,hour,day, month day_of_the_week
    public void scheduledDelete() {
        tokenService.deleteUsedToken();
        log.info("expired token(s) cleared");

    }

}
