package net.atossyntel.interview.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.atossyntel.interview.dao.AccountDao;
import net.atossyntel.interview.dao.CustomerDao;
import net.atossyntel.interview.model.Account;
import net.atossyntel.interview.model.AccountType;
import net.atossyntel.interview.model.Customer;

public class CustomerUtil {

	public static List<Account> getAllAccountsForCustomer(int customerID) {
		// TODO Auto-generated method stub
		return AccountDao.getAccountList().stream().peek(System.out::println)
				.filter(acc -> acc.getCustomerId() == customerID).collect(Collectors.toCollection(ArrayList::new));
	}

	public static Account getSpecificAccountForCustomer(int customerID, AccountType accountType) {
		// TODO Auto-generated method stub
		Account account = null;
		for (Account acc : AccountDao.getAccountList()) {
			if (acc.getCustomerId() == customerID && acc.getAccountType() == accountType) {
				account = acc;
				break;
			}
		}
		return account;
	}

	public static Customer getCustomerforId(int id) {
		return CustomerDao.getCustomerList().stream().filter(acc -> acc.getId() == id).findFirst().get();
	}

	public static boolean isExistingCustomer(Customer customer) {
		return CustomerDao.getCustomerList().stream().anyMatch(c -> c.getId() == customer.getId());
	}

	public static boolean isExistingCustomer(int customerId) {
		return CustomerDao.getCustomerList().stream().anyMatch(c -> c.getId() == customerId);
	}

}
