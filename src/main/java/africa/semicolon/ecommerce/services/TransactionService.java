package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.OrderEntity;
import africa.semicolon.ecommerce.data.model.Transaction;

public interface TransactionService {
    boolean verifyTransaction(String link);
    void saveTransaction(Transaction transaction);
}
