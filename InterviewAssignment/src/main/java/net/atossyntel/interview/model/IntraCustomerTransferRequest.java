package net.atossyntel.interview.model;

public class IntraCustomerTransferRequest {

	private int customerId;

	private AccountType from;

	private AccountType to;

	private double amount;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public AccountType getFrom() {
		return from;
	}

	public void setFrom(AccountType from) {
		this.from = from;
	}

	public AccountType getTo() {
		return to;
	}

	public void setTo(AccountType to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "IntraCustomerTransferRequest [customerId=" + customerId + ", from=" + from + ", to=" + to + ", amount="
				+ amount + "]";
	}

}
