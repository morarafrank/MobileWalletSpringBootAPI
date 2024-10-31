package com.comulynx.wallet.rest.api.controller;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;
import com.comulynx.wallet.rest.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comulynx.wallet.rest.api.AppUtilities;
import com.comulynx.wallet.rest.api.model.Account;
import com.comulynx.wallet.rest.api.model.Customer;
import com.comulynx.wallet.rest.api.repository.AccountRepository;
import com.comulynx.wallet.rest.api.repository.CustomerRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private Gson gson = new Gson();

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountRepository accountRepository;
	@GetMapping("/")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * Fix Customer Login functionality
	 * 
	 * Login
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> customerLogin(@RequestBody String request) {
		try {
			JsonObject response = new JsonObject();
			final JsonObject req = gson.fromJson(request, JsonObject.class);
			String customerId = req.get("customerId").getAsString();
			String customerPIN = req.get("pin").getAsString();

			// PIN
			// NB: We are using plain text password for testing Customer login
			// If customerId doesn't exists throw an error "Customer does not exist"
			// If password do not match throw an error "Invalid credentials"

			// Fetch customer from repository
			Customer customer = customerRepository.findByCustomerId(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer does not exist"));

			// Validating PIN
			if (!customer.getPin().equals(customerPIN)) {
				throw new RuntimeException("Invalid credentials");
			}

			//Customer Name, Customer ID, email and Customer Account
			response.addProperty("customerName", customer.getCustomerName());
			response.addProperty("customerId", customer.getCustomerId());
			response.addProperty("email", customer.getEmail());
			response.addProperty("account", accountRepository.findAccountByCustomerId(customerId)
					.map(Account::getAccountNo).orElse("No account found"));

			return ResponseEntity.status(200).body(HttpStatus.OK);
		} catch (Exception ex) {
			logger.info("Exception {}", AppUtilities.getExceptionStacktrace(ex));
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 *  Add required logic
	 *  
	 *  Create Customer
	 *  
	 * @param customer
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
		try {
			String customerPIN = customer.getPin();
			String email = customer.getEmail();

			// Check if the customer already exists with id or email
			if (customerRepository.findByCustomerId(customer.getCustomerId()).isPresent()) {
				throw new RuntimeException("Customer with ID " + customer.getCustomerId() + " already exists");
			}

			String hashedPIN = hashPIN(customerPIN);

			// set the hashed PIN back to the customer
			customer.setPin(hashedPIN);

			String accountNo = generateAccountNo(customer.getCustomerId());
			Account account = new Account();
			account.setCustomerId(customer.getCustomerId());
			account.setAccountNo(accountNo);
			account.setBalance(0.0);
			accountRepository.save(account);

			return ResponseEntity.ok().body(customerRepository.save(customer));
		} catch (Exception ex) {
			logger.info("Exception {}", AppUtilities.getExceptionStacktrace(ex));
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 *  Add required functionality
	 *  
	 * generate a random but unique Account No (NB: Account No should be unique
	 * in your accounts table)
	 * 
	 */
	private String generateAccountNo(String customerId) {

		// Account No should be unique in the accounts table)
		String accountNo;
		do {
			// Generate a random 10-digit account number
			accountNo = String.format("%010d", new Random().nextInt(1_000_000_000));
		} while (accountRepository.findAccountByAccountNo(accountNo).isPresent());
		return accountNo;
	}


	private String hashPIN(String pin) {
		return new BCryptPasswordEncoder().encode(pin);
	}

}
