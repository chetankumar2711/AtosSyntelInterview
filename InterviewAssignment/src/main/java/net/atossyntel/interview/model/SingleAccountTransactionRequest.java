package net.atossyntel.interview.model;

public class SingleAccountTransactionRequest {

	private int customerId;

	private AccountType accountType;

	private TransactionType txnType;

	private double amount;

	@Override
	public String toString() {
		return "DepositWithdrawalRequest [customerId=" + customerId + ", accountType=" + accountType + ", txnType="
				+ txnType + ", amount=" + amount + "]";
	}

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

	public TransactionType getTxnType() {
		return txnType;
	}

	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
