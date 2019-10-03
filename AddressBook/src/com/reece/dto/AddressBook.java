package com.reece.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddressBook {

	private String name;
	private List<Contact> contacts;

	public AddressBook(String name) {
		this(name, new ArrayList<Contact>());
	}

	public AddressBook(String name, List<Contact> contacts) {
		this.name = name;
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		if (contacts != null) {
			contacts.add(contact);
		}
	}

	public void removeContact(Contact contact) {
		if (contacts != null) {
			contacts.remove(contact);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public boolean equals(Object obj) {
		if (obj instanceof AddressBook) {
			AddressBook addressBook = (AddressBook) obj;
			return name.equals(addressBook.getName());
		}
		return false;
	}

	public int hashCode() {
		return (name.length());
	}

	public static Set<Contact> getUniqueContacts(List<AddressBook> addressBooks) {
		Set<Contact> commonContacts = new HashSet<Contact>();
		Set<Contact> uniqueContacts = new HashSet<Contact>();
		for (AddressBook addressBook : addressBooks) {
			List<Contact> contacts = addressBook.getContacts();
			List<Contact> allContacts = new ArrayList<Contact>();
			allContacts.addAll(uniqueContacts);
			allContacts.addAll(contacts);
			contacts.retainAll(uniqueContacts);
			commonContacts.addAll(contacts);
			allContacts.removeAll(commonContacts);
			uniqueContacts.clear();
			uniqueContacts.addAll(allContacts);
		}
		return uniqueContacts;
	}
}