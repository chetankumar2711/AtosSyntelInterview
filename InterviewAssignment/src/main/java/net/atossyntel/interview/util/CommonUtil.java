package net.atossyntel.interview.util;

import net.atossyntel.interview.model.Account;
import net.atossyntel.interview.model.AccountType;
import net.atossyntel.interview.model.Customer;

public class CommonUtil {

	/**
	 * mark method as synchronized for simplicity could have used synchronized
	 * blocks for lesser lock time
	 * 
	 * This s being used for both inter/cross and intra transfers
	 * 
	 * @param payeeAccount
	 * @param receiverAccount
	 * @param amount
	 * @return
	 */
	public static synchronized boolean updateBothAccounts(Account payeeAccount, Account receiverAccount,
			double amount) {
		// TODO Auto-generated method stub
		payeeAccount.deduct(amount);
		receiverAccount.deposit(amount);
		return true;
	}

	public static boolean insufficientFund(Account payeeAccount, double amount) {
		// TODO Auto-generated method stub
		return payeeAccount.getBalance() < amount;
	}

	public static boolean isExistingCustomerAccountType(Account account) {
		// TODO Auto-generated method stub
		return CustomerUtil.getAllAccountsForCustomer(account.getCustomerId()).stream()
				.anyMatch(acc -> acc.getAccountType() == account.getAccountType());
	}

	public static boolean doesAccountBelongToCustomer(Account account) {
		// TODO Auto-generated method stub
		System.out.println("Cutosmer ID" + account.getCustomerId());
		System.out.println("acc ID" + account.getId());
		return CustomerUtil.getAllAccountsForCustomer(account.getCustomerId()).stream()
				.anyMatch(acc -> acc.getId() == account.getId());
	}

	public static boolean doesAccountBelongToCustomer(int customerId, int id) {
		// TODO Auto-generated method stub
		Account account = new Account(id, customerId, 0, AccountType.CHECKING);
		return doesAccountBelongToCustomer(account);
	}

}
