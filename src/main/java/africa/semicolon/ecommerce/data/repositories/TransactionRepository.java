package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
