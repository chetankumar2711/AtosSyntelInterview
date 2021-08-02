package net.atossyntel.interview.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import net.atossyntel.interview.exception.ResourceNotFoundException;
import net.atossyntel.interview.model.Account;
import net.atossyntel.interview.model.Customer;
import net.atossyntel.interview.model.IntraCustomerTransferRequest;
import net.atossyntel.interview.model.TransactionType;
import net.atossyntel.interview.util.AccountUtil;
import net.atossyntel.interview.util.CommonUtil;
import net.atossyntel.interview.util.CustomerUtil;

@Repository
public class CustomerDao {

	/**
	 * 
	 */
	private static List<Customer> customerList = new ArrayList<Customer>();

	/**
	 * @return
	 */
	public static List<Customer> getCustomerList() {
		return customerList;
	}

	/**
	 * @param customerList
	 */
	public static void setCustomerList(List<Customer> customerList) {
		CustomerDao.customerList = customerList;
	}

	/**
	 * 
	 */
	static int idCounter = 0;

	static {
		customerList.add(new Customer(++idCounter, 20171, "Oaks", "Simon"));
		customerList.add(new Customer(++idCounter, 20171, "Verma", "Rahul"));
	}

	/**
	 * @return
	 */
	/**
	 * @return
	 */
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerList;
	}

	/**
	 * @param id
	 * @return
	 */
	/**
	 * @param id
	 * @return
	 */
	public Customer getCustomer(int id) {
		System.out.println("id: " + id);
		// TODO Auto-generated method stub
		List<Customer> singleCustomer = customerList.stream().filter(c -> c.getId() == id).collect(Collectors.toList());
		if (singleCustomer.size() > 0) {
			return singleCustomer.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @param customer
	 * @return
	 */
	public boolean addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if (CustomerUtil.isExistingCustomer(customer)) {
			return false;
		} else {
			if (customer.getId() == 0) {
				customer.setId(++idCounter);
			}
			customerList.add(customer);
			return true;
		}
	}

	/**
	 * @param customer
	 * @return
	 */
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if (!CustomerUtil.isExistingCustomer(customer)) {
			return false;
		} else {
			for (Customer c : customerList) {
				if (c.getId() == customer.getId()) {
					c.setZipCode(customer.getZipCode());
					c.setLastName(customer.getLastName());
					c.setFirstName(customer.getFirstName());
					break;
				}
			}
			return true;
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		if (CustomerUtil.isExistingCustomer(id)) {
			return customerList.remove(CustomerUtil.getCustomerforId(id));
		} else {
			return false;
		}
	}

	/**
	 * @param from
	 * @param to
	 * @param amount
	 * @return
	 */
	public boolean transferBalance(Account from, Account to, double amount) {
		return CommonUtil.updateBothAccounts(from, to, amount);
	}

	/**
	 * @param account
	 * @param amount
	 * @param txnType
	 * @return
	 */
	public boolean updateAccountBalance(Account account, double amount, TransactionType txnType) {
		if (txnType.equals(TransactionType.DEPOSIT))
			account.setBalance(account.getBalance() + amount);
		if (txnType.equals(TransactionType.WITHDRAWAL))
			account.setBalance(account.getBalance() - amount);
		return true;
	}

	/**
	 * @param customerAccount
	 * @return
	 */
	public Double getAccountBalance(Account customerAccount) {
		// TODO Auto-generated method stub
		return customerAccount.getBalance();
	}

}
