package com.phoneBook;

import java.util.Scanner;

public class PhoneBookApplication {
	
	public static void main(String[] args) 
	{
		//Initialize application by creating instance of the PhoneBook class
		PhoneBook phoneBook = new PhoneBook();
		
		//Instantiate scanner
		Scanner sc = new Scanner(System.in);
		
		String status = "1";
		
		while(status.equals("1"))
		{
			//User select operation from menu	
			System.out.println("----------------------------------------------------------------\n");
			System.out.print("Select operation to perform:\n"
					+ "\n"
					+ "1 - Add new entry\n"
					+ "2 - Search phonebook by first name\n"
					+ "3 - Search phonebook by last name\n"
					+ "4 - Search phonebook by full name\n"
					+ "5 - Search phonebook by phone number\n"
					+ "6 - Search phonebook by city\n"
					+ "7 - Search phonebook by state\n"
					+ "8 - Delete record\n"
					+ "9 - Update record\n"
					+ "10 - Display all records\n\n"
					+ "Enter selection here: ");
			int operation = sc.nextInt();
			System.out.println("");
			
			//Use switch to run selected operation
			boolean exception = true;
			String input1 = "";
			String input2 = "";
			switch(operation)
			{
			case 1:
				System.out.println("----------------------------------------------------------------\n");
				System.out.print("Enter full information of new entry: ");
				sc = new Scanner(System.in);
				//Rerun scanner and "addPerson" method call if FormatInvalidException is thrown.
				do {
					try {
						input1 = sc.nextLine();
						phoneBook.addPerson(input1);
						exception = false;
					} catch (NumberEntriesInvalidException e) {
						e.printMessage();
					} catch (PhoneNumberInvalidException e) {
						e.printMessage();
					}
				} while(exception == true);
				break;
			case 2:
				System.out.println("----------------------------------------------------------------\n");
				System.out.print("Enter first name to search by: ");
				sc = new Scanner(System.in);
				input1 = sc.nextLine();
				phoneBook.searchFirstName(input1);
				break;
			case 3:
				System.out.println("----------------------------------------------------------------\n");
				System.out.print("Enter last name to search by: ");
				sc = new Scanner(System.in);
				input1 = sc.nextLine();
				phoneBook.searchLastName(input1);
				break;
			case 4:
				System.out.println("----------------------------------------------------------------\n");
				System.out.print("Enter full name to search by: ");
				sc = new Scanner(System.in);
				input1 = sc.nextLine();
				phoneBook.searchFullName(input1);
				break;
			case 5:
				System.out.println("----------------------------------------------------------------\n");
				System.out.print("Enter phone number to search by: ");
				sc = new Scanner(System.in);
				input1 = sc.nextLine();
				phoneBook.searchPhoneNum(input1);
				break;
			case 6:
				System.out.println("----------------------------------------------------------------\n");
				System.out.print("Enter city to search by: ");
				sc = new Scanner(System.in);
				input1 = sc.nextLine();
				phoneBook.searchCity(input1);
				break;
			case 7:
				System.out.println("----------------------------------------------------------------\n");
				System.out.print("Enter state to search by: ");
				sc = new Scanner(System.in);
				input1 = sc.nextLine();
				phoneBook.searchState(input1);
				break;
			case 8:
				System.out.println("----------------------------------------------------------------\n");
				System.out.print("Enter phone number of record to be deleted: ");
				sc = new Scanner(System.in);
				input1 = sc.nextLine();
				phoneBook.deleteRecord(input1);
				break;
			case 9:
				System.out.println("----------------------------------------------------------------\n");
				System.out.print("Enter phone number of record to be updated: ");
				sc = new Scanner(System.in);
				input1 = sc.nextLine();
				if(!phoneBook.isPhoneNumPresent(input1))
				{
					System.out.println("Phone number is not present in phonebook. Cannot update.\n");
					break;
				}
				System.out.print("Enter full updated record: ");
				//Rerun scanner and "updateRecord" method call if FormatInvalidException is thrown.
				do {
					try {
						input2 = sc.nextLine();
						phoneBook.updateRecord(input2, input1);
						exception = false;
					} catch (NumberEntriesInvalidException e) {
						e.printMessage();
					} catch (PhoneNumberInvalidException e) {
						e.printMessage();
					}
				} while(exception == true);
				break;
			case 10:
				System.out.println("----------------------------------------------------------------\n");
				phoneBook.printAll();
				break;
			default:
				System.out.println("Invalid selection. Enter number 1 through 9.");
				break;
			}
			
			//Take user input to either continue or discontinue operation of program
			System.out.println("----------------------------------------------------------------\n");
			System.out.print("Would you like to perform another operation?\n"
					+ "1 - Yes\n"
					+ "Any other key - No\n\n"
					+ "Enter selection: ");
			status = sc.nextLine();
		}
		
		sc.close();
	}

}
