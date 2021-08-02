package net.atossyntel.interview.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.models.Path;
import net.atossyntel.interview.constants.RestPath;
import net.atossyntel.interview.exception.BanknameParentException;
import net.atossyntel.interview.exception.ResourceAlreadyPresentException;
import net.atossyntel.interview.exception.ResourceNotFoundException;
import net.atossyntel.interview.model.Account;
import net.atossyntel.interview.model.CrossCustomerTransferRequest;
import net.atossyntel.interview.model.Customer;
import net.atossyntel.interview.service.AccountService;

@RestController
public class AccountController {

	/**
	 * 
	 */
	@Autowired
	private AccountService accountService;

	/**
	 * @param customerId
	 * @return
	 */
	@GetMapping(path = RestPath.CUSTOMERS + "/{customerId}" + RestPath.ACCOUNTS)
	public List<Account> getAllAccountsforCustomer(@PathVariable int customerId) {
		return accountService.getAllAccountsforCustomer(customerId);
	}

	/**
	 * @param customerId
	 * @param id
	 * @return
	 */
	@GetMapping(path = RestPath.CUSTOMERS + "/{customerId}" + RestPath.ACCOUNTS + "/{id}")
	public Account getAccount(@PathVariable int customerId, @PathVariable int id) {
		Account account = accountService.getAccount(customerId, id);
		// accountService.facilitateTransfer(id, id+2, 1000);
		if (null == account) {
			throw new ResourceNotFoundException(
					"Account with id " + id + " and customerID " + customerId + " does not exist");
		} else {
			return account;
		}

	}

	/**
	 * @param account
	 * @return
	 */
	@PostMapping(path = RestPath.CUSTOMERS + "/{customerId}" + RestPath.ACCOUNTS)
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		ResponseEntity responseEntity = null;
		if (accountService.createAccount(account)) {
			responseEntity = ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			throw new ResourceAlreadyPresentException(
					"Account with id " + account.getId() + " can't be added as either Account ID is not unique or Customer already has this account type");
		}
		return responseEntity;
	}

	/**
	 * @param account
	 * @return
	 */
	@PutMapping(path = RestPath.CUSTOMERS + "/{customerId}" + RestPath.ACCOUNTS)
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
		ResponseEntity responseEntity = null;
		if (accountService.updateAccount(account)) {
			responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new ResourceNotFoundException(
					"Account with id " + account.getId() + " can't be updated as either it doesn't exist or it doesn't belong to the customer");
		}
		return responseEntity;
	}

	/**
	 * @param customerId
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = RestPath.CUSTOMERS + "/{customerId}" + RestPath.ACCOUNTS + "/{id}")
	public ResponseEntity<Account> deleteAccount(@PathVariable int customerId, @PathVariable int id) {
		ResponseEntity responseEntity = null;
		if (accountService.deleteAccount(customerId, id)) {
			responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new ResourceNotFoundException("Account with id " + id + " can't be deleted as either it doesn't exist or it doesn't belong to the customer");
		}
		return responseEntity;
	}

	/**
	 * Even though we are performing the transfer between two customers, still
	 * inherently it is transfer between two accounts therefore keeping this in
	 * AccountController class
	 * 
	 * @param transferRequest
	 * @return
	 */
	@PostMapping(path = RestPath.MONEY_TRANSFER)
	public ResponseEntity<Account> performCrossCustomerTransferRequest(
			@RequestBody CrossCustomerTransferRequest transferRequest) {
		ResponseEntity responseEntity = null;
		if (accountService.facilitateTransfer(transferRequest)) {
			responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new BanknameParentException(
					"Balance transfer between these two accounts can't be performed now. Please try again later ");
		}
		return responseEntity;
	}

}
