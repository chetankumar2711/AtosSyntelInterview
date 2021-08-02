package net.atossyntel.interview.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atossyntel.interview.dao.CustomerDao;
import net.atossyntel.interview.exception.BanknameParentException;
import net.atossyntel.interview.exception.ResourceNotFoundException;
import net.atossyntel.interview.model.Account;
import net.atossyntel.interview.model.BalanceCheckRequest;
import net.atossyntel.interview.model.Customer;
import net.atossyntel.interview.model.IntraCustomerTransferRequest;
import net.atossyntel.interview.model.SingleAccountTransactionRequest;
import net.atossyntel.interview.model.TransactionType;
import net.atossyntel.interview.util.CommonUtil;
import net.atossyntel.interview.util.CustomerUtil;

@Service
public class CustomerService {

	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	/**
	 * 
	 */
	@Autowired
	private CustomerDao customerDao;

	/**
	 * @return
	 */
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	/**
	 * @param id
	 * @return
	 */
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		LOGGER.debug("Class = " + this.getClass().getName() + "method = getCustomer " + " customerId = " + id);
		return customerDao.getCustomer(id);
	}

	/**
	 * @param customer
	 * @return
	 */
	public boolean addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		LOGGER.debug(
				"Class = " + this.getClass().getName() + "method = addCustomer " + " customerId = " + customer.getId());
		return customerDao.addCustomer(customer);
	}

	/**
	 * @param customer
	 * @return
	 */
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		LOGGER.debug(
				"Class = " + this.getClass().getName() + "method = addCustomer " + " customerId = " + customer.getId());
		return customerDao.updateCustomer(customer);
	}

	/**
	 * @param id
	 * @return
	 */
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		LOGGER.debug("Class = " + this.getClass().getName() + "method = addCustomer " + " customerId = " + id);
		return customerDao.deleteCustomer(id);

	}

	/**
	 * @param transferRequest
	 * @return
	 */
	public boolean transferBalance(IntraCustomerTransferRequest transferRequest) {
		// TODO Auto-generated method stub
		LOGGER.debug("Class = " + this.getClass().getName() + "method = IntraCustomerTransferRequest "
				+ " customerId = " + transferRequest.getCustomerId());
		if (CustomerUtil.isExistingCustomer(transferRequest.getCustomerId())) {
			Account payeeAccount = CustomerUtil.getSpecificAccountForCustomer(transferRequest.getCustomerId(),
					transferRequest.getFrom());
			Account receiverAccount = CustomerUtil.getSpecificAccountForCustomer(transferRequest.getCustomerId(),
					transferRequest.getTo());
			if (payeeAccount == null || receiverAccount == null) {
				throw new ResourceNotFoundException("Either of the two accounts given is invalid. Please check again");
			} else if (CommonUtil.insufficientFund(payeeAccount, transferRequest.getAmount())) {
				throw new BanknameParentException("Insufficient fund");
			} else {
				return customerDao.transferBalance(payeeAccount, receiverAccount, transferRequest.getAmount());
			}
		} else {
			throw new ResourceNotFoundException(
					"Customer with id " + transferRequest.getCustomerId() + " does not exist");
		}

	}

	/**
	 * @param txnRequest
	 * @return
	 */
	public boolean handleSingleAccountTransaction(SingleAccountTransactionRequest txnRequest) {
		LOGGER.debug("Class = " + this.getClass().getName() + "method = handleSingleAccountTransaction "
				+ " customerId = " + txnRequest.getCustomerId());
		Account customerAccount = CustomerUtil.getSpecificAccountForCustomer(txnRequest.getCustomerId(),
				txnRequest.getAccountType());
		if (customerAccount == null)
			throw new BanknameParentException("Selected Account for the customer doesn't exist. Please check again");
		TransactionType txnType = txnRequest.getTxnType();
		double amount = txnRequest.getAmount();
		if (txnType.equals(TransactionType.WITHDRAWAL) && CommonUtil.insufficientFund(customerAccount, amount)) {
			throw new BanknameParentException("Insufficient fund");
		} else {
			return customerDao.updateAccountBalance(customerAccount, amount, txnType);
		}

	}

	/**
	 * @param request
	 * @return
	 */
	public Double getCurrentBalanceForSelectedAccount(BalanceCheckRequest request) {
		// TODO Auto-generated method stub
		LOGGER.debug("Class = " + this.getClass().getName() + "method = getCurrentBalanceForSelectedAccount "
				+ " customerId = " + request.getCustomerId());
		Account customerAccount = CustomerUtil.getSpecificAccountForCustomer(request.getCustomerId(),
				request.getAccountType());
		if (customerAccount == null)
			throw new BanknameParentException("Selected Account for the customer doesn't exist. Please check again");
		return customerDao.getAccountBalance(customerAccount);

	}

}
