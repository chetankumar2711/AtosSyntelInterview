package net.atossyntel.interview.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atossyntel.interview.dao.AccountDao;
import net.atossyntel.interview.exception.BanknameParentException;
import net.atossyntel.interview.exception.ResourceNotFoundException;
import net.atossyntel.interview.model.Account;
import net.atossyntel.interview.model.CrossCustomerTransferRequest;
import net.atossyntel.interview.util.AccountUtil;
import net.atossyntel.interview.util.CommonUtil;

@Service
public class AccountService {

	/**
	 * 
	 */
	@Autowired
	private AccountDao accountDao;

	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	/**
	 * @param customerId
	 * @return
	 */
	public List<Account> getAllAccountsforCustomer(int customerId) {
		LOGGER.debug("Class = " + this.getClass().getName() + "method = getAllAccountsforCustomer " + " customerId = "
				+ customerId);
		return accountDao.getAllAccountsforCustomer(customerId);

	}

	/**
	 * @param customerId
	 * @param id
	 * @return
	 */
	public Account getAccount(int customerId, int id) {
		LOGGER.debug("Class = " + this.getClass().getName() + "method = getAccount " + " customerId = " + customerId
				+ " accountId = " + id);
		return accountDao.getAccount(customerId, id);

	}

	/**
	 * @param account
	 * @return
	 */
	public boolean createAccount(Account account) {
		LOGGER.debug("Class = " + this.getClass().getName() + "method = createAccount " + " customerId = "
				+ account.getCustomerId() + " accountId = " + account.getId());
		return accountDao.createAccount(account);

	}

	/**
	 * @param account
	 * @return
	 */
	public boolean updateAccount(Account account) {
		LOGGER.debug("Class = " + this.getClass().getName() + "method = updateAccount " + " customerId = "
				+ account.getCustomerId() + " accountId = " + account.getId());
		return accountDao.updateAccount(account);

	}

	/**
	 * @param customerId
	 * @param id
	 * @return
	 */
	public boolean deleteAccount(int customerId, int id) {
		LOGGER.debug("Class = " + this.getClass().getName() + "method = deleteAccount " + " customerId = " + customerId
				+ " accountId = " + id);
		return accountDao.deleteAccount(customerId, id);
	}

	/**
	 * @param transferRequest
	 * @return
	 */
	public boolean facilitateTransfer(CrossCustomerTransferRequest transferRequest) {
		LOGGER.debug("Class = " + this.getClass().getName() + "method = facilitateTransfer between accounts "
				+ transferRequest.getPayeeAccountID() + " and " + transferRequest.getReceiverAccountID());

		Account payeeAccount = AccountUtil.getAccountForId(transferRequest.getPayeeAccountID());
		Account receiverAccount = AccountUtil.getAccountForId(transferRequest.getReceiverAccountID());
		if (payeeAccount == null || receiverAccount == null) {
			throw new ResourceNotFoundException("Either of the two accounts given is invalid. Please check again");
		} else if (payeeAccount.getCustomerId() == receiverAccount.getCustomerId()) {
			throw new BanknameParentException(
					"Both accounts belong to same customer, please use transfer balance functionality");
		} else if (CommonUtil.insufficientFund(payeeAccount, transferRequest.getAmount())) {
			throw new BanknameParentException("Insufficient fund");
		} else {
			return accountDao.faciliateTransfer(payeeAccount, receiverAccount, transferRequest.getAmount());
		}
	}

}
