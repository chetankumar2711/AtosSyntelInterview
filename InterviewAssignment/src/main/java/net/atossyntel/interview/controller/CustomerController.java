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

import net.atossyntel.interview.constants.RestPath;
import net.atossyntel.interview.exception.BanknameParentException;
import net.atossyntel.interview.exception.ResourceAlreadyPresentException;
import net.atossyntel.interview.exception.ResourceNotFoundException;
import net.atossyntel.interview.model.AccountType;
import net.atossyntel.interview.model.BalanceCheckRequest;
import net.atossyntel.interview.model.Customer;
import net.atossyntel.interview.model.IntraCustomerTransferRequest;
import net.atossyntel.interview.model.SingleAccountTransactionRequest;
import net.atossyntel.interview.service.CustomerService;

@RestController
public class CustomerController {

	/**
	 * 
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * @return
	 */
	@GetMapping(path = RestPath.CUSTOMERS)
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();

	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping(path = RestPath.CUSTOMERS + "/{id}")
	public Customer getCustomer(@PathVariable int id) {
		Customer customer = customerService.getCustomer(id);
		if (null == customer) {
			throw new ResourceNotFoundException("Customer with id " + id + " does not exist");
		} else {
			return customer;
		}
	}

	/**
	 * @param customer
	 * @return
	 */
	@PostMapping(path = RestPath.CUSTOMERS)
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity responseEntity = null;
		if (customerService.addCustomer(customer)) {
			try {
				responseEntity = ResponseEntity.created(new URI("/customers/" + customer.getId())).build();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				throw new BanknameParentException("couldn't create ResponseEntity object");
			}
		} else {
			throw new ResourceAlreadyPresentException(
					"Customer with id " + customer.getId() + " can't be added as it is already existing");
		}
		return responseEntity;

	}

	/**
	 * @param customer
	 * @return
	 */
	@PutMapping(path = RestPath.CUSTOMERS)
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {

		ResponseEntity responseEntity = null;
		if (customerService.updateCustomer(customer)) {
			responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new ResourceNotFoundException(
					"Customer with id " + customer.getId() + " can't be updated as it doesn't exist");
		}
		return responseEntity;
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = RestPath.CUSTOMERS + "/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {
		ResponseEntity responseEntity = null;
		if (customerService.deleteCustomer(id)) {
			responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new ResourceNotFoundException("Customer with id " + id + " can't be deleted as it doesn't exist");
		}
		return responseEntity;
	}

	/**
	 * @param transferRequest
	 * @return
	 */
	@PostMapping(path = RestPath.BALANCE_TRANSFER)
	public ResponseEntity<Customer> transferBalance(@RequestBody IntraCustomerTransferRequest transferRequest) {
		ResponseEntity responseEntity = null;
		if (customerService.transferBalance(transferRequest)) {
			responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new BanknameParentException(
					"Balance transfer between these two accounts of yours can't be performed now. Please try again later ");
		}
		return responseEntity;
	}

	/**
	 * @param txnRequest
	 * @return
	 */
	@PostMapping(path = RestPath.DEPOSIT_OR_WITHDRAWAL)
	public ResponseEntity<Customer> handleSingleAccountTxn(SingleAccountTransactionRequest txnRequest) {
		ResponseEntity responseEntity = null;
		if (customerService.handleSingleAccountTransaction(txnRequest)) {
			responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			throw new BanknameParentException(
					"Selected transaction for your account can't be performed now. Please try again later ");
		}
		return responseEntity;
	}

	/**
	 * @param request
	 * @return
	 */
	@GetMapping(path = RestPath.BALANCE_CHECK)
	public Double getCurrentBalanceForSelectedAccount(BalanceCheckRequest request) {
		return customerService.getCurrentBalanceForSelectedAccount(request);
	}

}
