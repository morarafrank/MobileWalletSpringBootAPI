package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Customer;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

	@Modifying
	@Transactional
	@Query("DELETE FROM Customer c WHERE c.customerId = :customerId")
	int deleteCustomerByCustomerId(@Param("customerId") String customerId);

	@Modifying
	@Transactional
	@Query("UPDATE Customer c SET c.firstName = :firstName WHERE c.customerId = :customerId")
	int updateCustomerByCustomerId(@Param("firstName") String firstName, @Param("customerId") String customerId);

	@Query("SELECT c FROM Customer c WHERE c.email LIKE %gmail%")
	List<Customer> findAllCustomersWhoseEmailContainsGmail();
}

