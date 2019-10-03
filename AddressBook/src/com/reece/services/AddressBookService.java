package com.reece.services;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.reece.dto.AddressBook;
import com.reece.dto.Contact;

public class AddressBookService {

	private List<AddressBook> addressBooks;

	public AddressBookService() {
		addressBooks = new ArrayList<AddressBook>();
	}

	public void addAddressBook(AddressBook addressbook) {
		if (!addressBooks.contains(addressbook)) {
			addressBooks.add(addressbook);
		}
	}

	public void removeAddressBook(AddressBook addressbook) {
		if (addressBooks.contains(addressbook)) {
			addressBooks.remove(addressbook);
		}
	}

	public void removeExistingContact(List<AddressBook> addressBooks, String contactName) {
		System.out.println("Users should be able to remove existing contact entries \n");
		for (AddressBook addressBook : addressBooks) {
			for (Contact c : addressBook.getContacts()) {
				if (c.getName().equalsIgnoreCase(contactName)) {
					System.out.println(c.getName() + "  " + addressBook.getName());
					addressBook.removeContact(c);
					break;
				}
			}
		}
		System.out.println("");
	}

	public List<AddressBook> getAddressBooks() {
		return addressBooks;
	}

	public void setAddressBooks(List<AddressBook> addressBooks) {
		this.addressBooks = addressBooks;
	}
}
