package com.phoneBook;

import java.util.Arrays;

public class PhoneBook 
{
	//Class variables
	public Person[] phoneDir = new Person[0];
	
	//Constructors
	public PhoneBook()
	{
	}
	
	//Class methods
	//Public methods
	//***************************************************************************************//
	public void addPerson(String input) throws NumberEntriesInvalidException, PhoneNumberInvalidException
	{
		String[] parsedInput = input.split(", ");
		if(parsedInput.length != 6)
		{
			throw new NumberEntriesInvalidException();
		}
		else if(!isPhoneNumValid(parsedInput[5]))
		{
			throw new PhoneNumberInvalidException();
		}
		else if(isPhoneNumPresent(parsedInput[5])) 
		{
			System.out.println("Phone number already exists in phonebook. Cannot add.\n");
		}
		else
		{
			Person newPerson = new Person(input);
			phoneDir = addPersonToArray(phoneDir, newPerson);
			System.out.println("Entry added. Length of directory is: " + phoneDir.length + "\n");
		}
	}
	
	//***************************************************************************************//
	public void searchFirstName(String firstNameSearch)
	{
		Person[] result = new Person[0];
		for(int i = 0; i < phoneDir.length; i++)
		{
			String[] parsedName = phoneDir[i].getFullName().split(" ");
			if(parsedName[0].equals(firstNameSearch))
			{
				result = addPersonToArray(result, phoneDir[i]);
			}
		}
		if(result.length == 0)
		{
			System.out.println("There are no entries with a first name of: " + firstNameSearch + "\n");
		}
		else
		{
			this.printSearchResults(result);
		}
	}
	
	//***************************************************************************************//
	public void searchLastName(String lastNameSearch)
	{
		Person[] result = new Person[0];
		for(int i = 0; i < phoneDir.length; i++)
		{
			String[] parsedName = phoneDir[i].getFullName().split(" ");
			if(parsedName[parsedName.length - 1].equals(lastNameSearch))
			{
				result = addPersonToArray(result, phoneDir[i]);
			}
		}		
		if(result.length == 0)
		{
			System.out.println("There are no entries with a last name of: " + lastNameSearch);
		}
		else
		{
			this.printSearchResults(result);
		}
	}
	
	//***************************************************************************************//
	public void searchFullName(String fullNameSearch)
	{
		Person[] result = new Person[0];
		for(int i = 0; i < phoneDir.length; i++)
		{
			if(phoneDir[i].getFullName().equals(fullNameSearch))
			{
				result = addPersonToArray(result, phoneDir[i]);
			}
		}		
		if(result.length == 0)
		{
			System.out.println("There are no entries with a full name of: " + fullNameSearch);
		}
		else
		{
			this.printSearchResults(result);
		}
	}
		
	//***************************************************************************************//
	public void searchPhoneNum(String phoneNumSearch)
	{
		Person[] result = new Person[0];
		for(int i = 0; i < phoneDir.length; i++)
		{
			if(phoneDir[i].getPhoneNum().equals(phoneNumSearch))
			{
				result = addPersonToArray(result, phoneDir[i]);
			}
		}		
		if(result.length == 0)
		{
			System.out.println("There are no entries with a phone number of: " + phoneNumSearch);
		}
		else
		{
			this.printSearchResults(result);
		}
	}
	
	//***************************************************************************************//
	public void searchCity(String citySearch)
	{
		Person[] result = new Person[0];
		for(int i = 0; i < phoneDir.length; i++)
		{
			if(phoneDir[i].getCity().equals(citySearch))
			{
				result = addPersonToArray(result, phoneDir[i]);
			}
		}		
		if(result.length == 0)
		{
			System.out.println("There are no entries with a city of: " + citySearch);
		}
		else
		{
			this.printSearchResults(result);
		}
	}
	
	//***************************************************************************************//
	public void searchState(String stateSearch)
	{
		Person[] result = new Person[0];
		for(int i = 0; i < phoneDir.length; i++)
		{
			if(phoneDir[i].getState().equals(stateSearch))
			{
				result = addPersonToArray(result, phoneDir[i]);
			}
		}		
		if(result.length == 0)
		{
			System.out.println("There are no entries with a state of: " + stateSearch);
		}
		else
		{
			this.printSearchResults(result);
		}
	}
	
	//***************************************************************************************//
	public void deleteRecord(String phoneNum)
	{
		if(isPhoneNumPresent(phoneNum))
		{
			Person[] temp = new Person[0];
			for(int i = 0; i < phoneDir.length; i++)
			{
				if(!phoneDir[i].getPhoneNum().equals(phoneNum))
				{
					temp = addPersonToArray(temp, phoneDir[i]);
				}
			}
			this.phoneDir = temp;
			System.out.println("Record deleted. Length of directory is: " + phoneDir.length + "\n");
		}
		else
		{
			System.out.println("Inputted record is not in directory. Cannot delete\n");
		}
	}
	
	//***************************************************************************************//
	public void updateRecord (String update, String phoneNum) throws NumberEntriesInvalidException, PhoneNumberInvalidException
	{
		System.out.println("");
		if(update.split(", ").length != 6)
		{
			throw new NumberEntriesInvalidException();
		}
		else if(!isPhoneNumValid(update.split(", ")[5]))
		{
			throw new PhoneNumberInvalidException();
		}
		for(int i = 0; i < phoneDir.length; i++)
		{
			if(phoneDir[i].getPhoneNum().equals(phoneNum))
			{
				String[] parseInput = update.split(", ");
				phoneDir[i].setFullName(parseInput[0]);
				phoneDir[i].setStreet(parseInput[1]);
				phoneDir[i].setCity(parseInput[2]);
				phoneDir[i].setState(parseInput[3]);
				phoneDir[i].setZip(parseInput[4]);
				phoneDir[i].setPhoneNum(parseInput[5]);
			}
		}
		System.out.println("Record updated\n");

	}
	
	//***************************************************************************************//
	public void printAll()
	{
		printSearchResults(phoneDir);
	}
	
	
	//***************************************************************************************//
	public boolean isPhoneNumPresent(String phoneNum)
	{
		for(int i = 0; i < phoneDir.length; i++)
		{
			if(phoneDir[i].getPhoneNum().equals(phoneNum))
			{
				return true;
			}
		}
		return false;
	}
	
	//Private methods
	//***************************************************************************************//
	private void printSearchResults(Person[] result)
	{
		Arrays.sort(result, (Person a, Person b) -> a.getLastName().compareTo(b.getLastName()));
		System.out.println("");
		for(int i = 0; i < result.length; i++)
		{
			result[i].toString();
		}
		System.out.println("Number of results returned: " + result.length + "\n");
	}
	
	//***************************************************************************************//
	private Person[] addPersonToArray(Person[] personArray, Person person)
	{
		Person[] temp = new Person[personArray.length + 1];
		for(int i = 0; i < personArray.length; i++)
		{
			temp[i] = personArray[i];
		}
		temp[personArray.length] = person;
		return temp;
	}
	
	//***************************************************************************************//
	private boolean isPhoneNumValid(String phoneNum)
	{
		if(phoneNum.length() != 10)
		{
			return false;
		}
		for(int i = 0; i < phoneNum.length(); i++)
		{
			if(phoneNum.charAt(i) < 48 || phoneNum.charAt(i) > 57)
			{
				return false;
			}
		}
		return true;
		
	}

}
