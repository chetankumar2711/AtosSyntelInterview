package net.atossyntel.interview.model;

public class Account {

	@Override
	public String toString() {
		return "Account [id=" + id + ", customerId=" + customerId + ", balance=" + balance + ", accountType="
				+ accountType + "]";
	}

	private int id;

	private int customerId;

	private double balance;

	private AccountType accountType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public void deduct(double amount) {
		this.balance -= amount;
	}

	public void deposit(double amount) {
		this.balance += amount;
	}

	public Account(int id, int customerId, double balance, AccountType accountType) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.balance = balance;
		this.accountType = accountType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (customerId != other.customerId)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
