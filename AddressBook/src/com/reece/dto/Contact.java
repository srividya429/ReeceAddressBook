package com.reece.dto;

import java.util.Comparator;

public class Contact {

	private String name;
	private String contactNumber;

	public Contact(String name, String contactNumber) {
		this.name = name;
		this.contactNumber = contactNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String toString() {
		return name + ", " + contactNumber;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Contact) {
			Contact contact = (Contact) obj;
			return (name.equals(contact.getName()) && contactNumber.equals(contact.getContactNumber()));
		}

		return false;
	}

	public int hashCode() {
		return (name.length() + contactNumber.length());
	}
}

class ContactNameComparator implements Comparator<Contact> {
	public int compare(Contact contact1, Contact contact2) {
		return contact1.getName().compareToIgnoreCase(contact2.getName());
	}
}