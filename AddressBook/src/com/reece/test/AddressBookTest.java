package com.reece.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.reece.dto.AddressBook;
import com.reece.dto.Contact;
import com.reece.services.AddressBookService;

public class AddressBookTest {
	private Contact ct1, ct2, ct3, ct4, ct5, ct6;
	private AddressBookService addressBookService;

	@Before
	public void setUp() {
		addressBookService = new AddressBookService();
		addNewContacts();
		addingNewContacts();
		removeExistingContact();
		showAddressBook();
		showMultipleAddressBooks();
		uniqueContactsFromAddressBooks();
	}

	private void showAddressBook() {
		AddressBook personalAddressBook = new AddressBook("PersonalAddressBook");
		personalAddressBook.addContact(ct1);
		personalAddressBook.addContact(ct2);
		System.out.println("Users should be able to print all contacts in an address book "
				+ personalAddressBook.getName() + "\n");
		System.out.println(personalAddressBook.getContacts());
		System.out.println("");
	}

	private void showMultipleAddressBooks() {
		addAddressBook();
		List<AddressBook> addressBooks = addressBookService.getAddressBooks();
		printAddressBooks(addressBooks);
	}

	private void removeExistingContact() {
		AddressBook personalAddressBook = new AddressBook("PersonalAddressBook");
		AddressBook officialAddressBook = new AddressBook("OfficialAddressBook");
		personalAddressBook.addContact(ct1);
		personalAddressBook.addContact(ct2);
		personalAddressBook.addContact(ct3);
		officialAddressBook.addContact(ct2);
		officialAddressBook.addContact(ct4);
		officialAddressBook.addContact(ct3);
		addressBookService.addAddressBook(personalAddressBook);
		addressBookService.addAddressBook(officialAddressBook);
		List<AddressBook> addressBooks = addressBookService.getAddressBooks();
		addressBookService.removeExistingContact(addressBooks, ct2.getName());
	}

	public static void main(String[] args) {
		AddressBookTest addressBookTest = new AddressBookTest();
		addressBookTest.setUp();
	}

	@Test
	public void uniqueContactsFromAddressBooks() {
		System.out.println("Users should be able to print a unique set of all contacts across multiple address books");
		addTwoAddressBooks();
		List<AddressBook> addressBooks = addressBookService.getAddressBooks();
		printInput(addressBooks);
		Set<Contact> uniqueContacts = AddressBook.getUniqueContacts(addressBooks);
		printOutput(addressBooks, uniqueContacts);
		Set<Contact> expected = new HashSet<Contact>(Arrays.asList(ct1, ct4));
		assertTrue(uniqueContacts.equals(expected));
	}

	private void addingNewContacts() {
		System.out.println("Users should be able to add new contact entries \n");
		ct6 = new Contact("Vipraja", "0426337234");
		AddressBook personalAddressBook = new AddressBook("PersonalAddressBook");
		personalAddressBook.addContact(ct6);
		System.out.println(personalAddressBook.getContacts().toString());
		System.out.println("");
	}

	private void addNewContacts() {
		ct1 = new Contact("Lakshmi", "0470504141");
		ct2 = new Contact("Sunil", "0396789456");
		ct3 = new Contact("Vidish", "0467826789");
		ct4 = new Contact("Disha", "0278902146");
		ct5 = new Contact("Kishore", "0428356788");
	}

	private void addAddressBook() {
		AddressBook personalAddressBook = new AddressBook("PersonalAddressBook");
		personalAddressBook.addContact(ct1);
		personalAddressBook.addContact(ct2);
		personalAddressBook.addContact(ct3);
		addressBookService.addAddressBook(personalAddressBook);
	}

	private void addTwoAddressBooks() {
		AddressBook personalAddressBook = new AddressBook("PersonalAddressBook");
		AddressBook officialAddressBook = new AddressBook("OfficialAddressBook");
		personalAddressBook.addContact(ct1);
		personalAddressBook.addContact(ct2);
		personalAddressBook.addContact(ct3);
		officialAddressBook.addContact(ct2);
		officialAddressBook.addContact(ct4);
		officialAddressBook.addContact(ct3);
		addressBookService.addAddressBook(personalAddressBook);
		addressBookService.addAddressBook(officialAddressBook);
	}

	private void printAddressBooks(List<AddressBook> addressBooks) {
		for (AddressBook addressBook : addressBooks) {
			System.out.println("Contacts from multiple AddressBooks: " + addressBook.getName());
			for (Contact c : addressBook.getContacts()) {
				System.out.println(c.toString());
			}
			System.out.println("");
		}
	}

	private void printInput(List<AddressBook> addressBooks) {
		for (AddressBook addressBook : addressBooks) {
			System.out.println("Address Book: " + addressBook.getName());
			for (Contact c : addressBook.getContacts()) {
				System.out.println(c.getName());
			}
			System.out.println("");

		}
	}

	private void printOutput(List<AddressBook> addressBooks, Set<Contact> uniqueContacts) {
		System.out.print("Unique set of contacts from multiple Address Books: ");
		String names = "";
		for (AddressBook addressBook : addressBooks) {
			names += addressBook.getName() + ", ";
		}
		if (names.length() > 0) {
			System.out.println(names.substring(0, names.lastIndexOf(",")));
		}
		for (Contact c : uniqueContacts) {
			System.out.println(c.getName());
		}
		System.out.println("\n");
	}

}
