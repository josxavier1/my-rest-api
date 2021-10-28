package com.jx.restapi.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SalesCustomer {

	private @Id @GeneratedValue Long id;
	@Column(length=50)private String customerName;
	@Column(length=10) private String phone;
	//private List<CustomerPreference> customerPreferenceList;

	SalesCustomer() {}

	public SalesCustomer(String customerName, String phone) {
	    this.customerName = customerName;
	    this.phone = phone;
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		SalesCustomer other = (SalesCustomer) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesCustomer [id=" + id + ", customerName=" + customerName + ", phone=" + phone + "]";
	}

	
}
