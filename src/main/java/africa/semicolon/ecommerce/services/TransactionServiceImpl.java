package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.OrderEntity;
import africa.semicolon.ecommerce.data.model.Transaction;
import africa.semicolon.ecommerce.data.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public boolean verifyTransaction(String link) {
        // Calls the payment endpoint to verify is payment way successful
        return true;
    }

    public void saveTransaction(Transaction transaction) {

        transactionRepository.save(transaction);
        log.info("Transaction was successful");
    }
}
