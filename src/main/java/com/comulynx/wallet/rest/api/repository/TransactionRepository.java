package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Transaction;

//@Repository
//public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//
//	Optional<List<Transaction>> findTransactionsByCustomerId(String customerId);
//
//	Optional<List<Transaction>> findTransactionsByTransactionId(String transactionId);
//
//	Optional<List<Transaction>> findTransactionsByCustomerIdOrTransactionId(String transactionId, String customerId);
//
//	// TODO : Change below Query to return the last 5 transactions
//	// TODO : Change below Query to use Named Parameters instead of indexed
//	// parameters
//	// TODO : Change below function to return Optional<List<Transaction>>
//	@Query("SELECT t FROM Transaction t WHERE t.customerId =?1 AND  t.accountNo =?2")
//	List<Transaction> getMiniStatementUsingCustomerIdAndAccountNo(String customer_id, String account_no);
//
//}

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Optional<List<Transaction>> findTransactionsByCustomerId(String customerId);

	Optional<List<Transaction>> findTransactionsByTransactionId(String transactionId);

	Optional<List<Transaction>> findTransactionsByCustomerIdOrTransactionId(String transactionId, String customerId);

	// TODO : Change below Query to return the last 5 transactions
	// TODO : Change below Query to use Named Parameters instead of indexed
	// parameters
	// TODO : Change below function to return Optional<List<Transaction>>

	// Fetch the last 5 transactions using Named Parameters
	@Query("SELECT t FROM Transaction t WHERE t.customerId = :customerId AND t.accountNo = :accountNo ORDER BY t.transactionDate DESC")
	Optional<List<Transaction>> getMiniStatementUsingCustomerIdAndAccountNo(@Param("customerId") String customerId, @Param("accountNo") String accountNo, Pageable pageable);
}

