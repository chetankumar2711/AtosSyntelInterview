package net.atossyntel.interview.model;

public class Customer {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	private int id;

	private int zipCode;

	private String lastName;

	private String firstName;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", zipCode=" + zipCode + ", lastName=" + lastName + ", firstName=" + firstName
				+ "]";
	}

	public Customer(int id, int zipCode, String lastName, String firstName) {
		super();
		this.id = id;
		this.zipCode = zipCode;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
