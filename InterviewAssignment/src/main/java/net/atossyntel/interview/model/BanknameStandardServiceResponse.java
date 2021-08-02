package net.atossyntel.interview.model;

public class BanknameStandardServiceResponse<T> {

	private boolean status;

	private String msg;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "BanknameStandardServiceResponse [status=" + status + ", msg=" + msg + "]";
	}

}
