package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//	Optional<List<Transaction>> findTransactionsByCustomerId(String customerId);

	// paged version of the above method with 100 transactions.
	@Query("SELECT t FROM Transaction t WHERE t.customerId = :customerId ORDER BY t.transactionDate DESC")
	Page<List<Transaction>> findTransactionsByCustomerId(@Param("customerId") String customerId, Pageable pageable);

	Optional<List<Transaction>> findTransactionsByTransactionId(String transactionId);

	Optional<List<Transaction>> findTransactionsByCustomerIdOrTransactionId(String transactionId, String customerId);

	// Fetch the last 5 transactions using Named Parameters
	@Query("SELECT t FROM Transaction t WHERE t.customerId = :customerId AND t.accountNo = :accountNo ORDER BY t.transactionDate DESC")
	Optional<List<Transaction>> getMiniStatementUsingCustomerIdAndAccountNo(
			@Param("customerId") String customerId,
			@Param("accountNo") String accountNo,
			Pageable pageable);
}
