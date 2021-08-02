package net.atossyntel.interview.model;

public class CrossCustomerTransferRequest {

	private int payeeAccountID;

	private int receiverAccountId;

	private double amount;

	@Override
	public String toString() {
		return "InterCustomerTransferRequest [payeeCustomerID=" + payeeAccountID + ", receiverCustomerID="
				+ receiverAccountId + ", amount=" + amount + "]";
	}

	public int getPayeeAccountID() {
		return payeeAccountID;
	}

	public void setPayeeAccountID(int payeeCustomerID) {
		this.payeeAccountID = payeeCustomerID;
	}

	public int getReceiverAccountID() {
		return receiverAccountId;
	}

	public void setReceiverAccountID(int receiverCustomerID) {
		this.receiverAccountId = receiverCustomerID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
