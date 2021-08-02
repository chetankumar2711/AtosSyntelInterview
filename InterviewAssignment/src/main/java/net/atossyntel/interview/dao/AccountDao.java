package net.atossyntel.interview.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import net.atossyntel.interview.model.Account;
import net.atossyntel.interview.model.AccountType;
import net.atossyntel.interview.util.AccountUtil;
import net.atossyntel.interview.util.CommonUtil;

@Repository
public class AccountDao {

	/**
	 * 
	 */
	static int idCounter = 0;

	/**
	 * 
	 */
	private static List<Account> accountList = new ArrayList<Account>();

	/**
	 * @return
	 */
	public static List<Account> getAccountList() {
		return accountList;
	}

	/**
	 * @param accountList
	 */
	public static void setAccountList(List<Account> accountList) {
		AccountDao.accountList = accountList;
	}

	static {
		accountList.add(new Account(++idCounter, 1, 20000, AccountType.CHECKING));
		accountList.add(new Account(++idCounter, 1, 3000, AccountType.SAVING));

		accountList.add(new Account(++idCounter, 2, 15000, AccountType.CHECKING));
	}

	/**
	 * @param customerId
	 * @return
	 */
	public List<Account> getAllAccountsforCustomer(int customerId) {
		// TODO Auto-generated method stub
		return accountList.stream().filter(acc -> acc.getCustomerId() == customerId).collect(Collectors.toList());
	}

	/**
	 * @param customerId
	 * @param id
	 * @return
	 */
	public Account getAccount(int customerId, int id) {
		// TODO Auto-generated method stub
		List<Account> singleAccount = accountList.stream()
				.filter(acc -> acc.getId() == id && acc.getCustomerId() == customerId).collect(Collectors.toList());
		if (singleAccount.size() > 0) {
			return singleAccount.get(0);
		} else {
			return null;
		}

	}

	/**
	 * @param account
	 * @return
	 */
	public boolean createAccount(Account account) {
		// TODO Auto-generated method stub
		if (AccountUtil.isAccountIDExisting(account.getId()) || CommonUtil.isExistingCustomerAccountType(account)) {
			return false;
		} else {
			if (account.getId() == 0) {
				account.setId(++idCounter);
			}
			accountList.add(account);
			System.out.println(accountList);
			return true;
		}
	}

	/**
	 * @param account
	 * @return
	 */
	public boolean updateAccount(Account account) {
		if (AccountUtil.isAccountIDExisting(account.getId()) && CommonUtil.doesAccountBelongToCustomer(account)) {
			for (Account acc : accountList) {
				if (acc.getId() == account.getId() && acc.getCustomerId() == account.getCustomerId()) {
					acc.setAccountType(account.getAccountType());
					acc.setBalance(account.getBalance());
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param customerId
	 * @param id
	 * @return
	 */
	public boolean deleteAccount(int customerId, int id) {
		if (AccountUtil.isAccountIDExisting(id) && CommonUtil.doesAccountBelongToCustomer(customerId, id)) {
			return accountList.remove(AccountUtil.getAccountForId(id));
		} else {
			return false;
		}
	}

	/**
	 * @param payeeAccount
	 * @param receiverAccount
	 * @param amount
	 * @return
	 */
	public boolean faciliateTransfer(Account payeeAccount, Account receiverAccount, double amount) {
		return CommonUtil.updateBothAccounts(payeeAccount, receiverAccount, amount);
	}

	/**
	 * @param payeeAccountId
	 * @return
	 */
	private Account getAccountForId(int payeeAccountId) {
		// TODO Auto-generated method stub
		Account account = null;
		for (Account acc : accountList) {
			if (acc.getId() == payeeAccountId) {
				account = acc;
				break;
			}
		}
		return account;
	}

}
