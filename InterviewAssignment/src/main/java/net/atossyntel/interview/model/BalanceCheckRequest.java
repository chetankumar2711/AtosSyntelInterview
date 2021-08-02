package net.atossyntel.interview.model;

public class BalanceCheckRequest {

	private int customerId;

	private AccountType accountType;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "BalanceCheckRequest [customerId=" + customerId + ", accountType=" + accountType + "]";
	}

}
