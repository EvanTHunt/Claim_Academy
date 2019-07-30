package com.phoneBook;

public class Person
{
	//Class variables
	private String fullName;
	private Address address;
	private String phoneNum;
	
	//Constructors
	public Person()
	{
	}
	
	public Person(String input)
	{
		String[] parseInput = input.split(", ");
		this.address = new Address(parseInput[1], parseInput[2], parseInput[3], parseInput[4]);
		this.fullName = parseInput[0];
		this.phoneNum = parseInput[5];
	}

	//Class methods
	public String getFullName() 
	{
		return fullName;
	}

	public void setFullName(String fullName) 
	{
		this.fullName = fullName;
	}
	
	public String getFirstName()
	{
		String[] parsedName = fullName.split(" ");
		return parsedName[0];
	}
	
	public String getLastName()
	{
		String[] parsedName = fullName.split(" ");
		return parsedName[parsedName.length - 1];
	}
	
	public String getStreet() 
	{
		return address.getStreet();
	}

	public void setStreet(String street) 
	{
		this.address.setStreet(street);
	}
	
	public String getCity() 
	{
		return address.getCity();
	}

	public void setCity(String city) 
	{
		this.address.setCity(city);
	}
	
	public String getState() 
	{
		return address.getState();
	}

	public void setState(String state) 
	{
		this.address.setState(state);
	}
	
	public String getZip() 
	{
		return address.getZip();
	}

	public void setZip(String zip) 
	{
		this.address.setZip(zip);
	}

	public String getPhoneNum() 
	{
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) 
	{
		this.phoneNum = phoneNum;
	}
	
	@Override
	public String toString()
	{
		String string = "Name: " + fullName + "\n"
				+ "Street: " + address.getStreet() + "\n"
				+ "City: " + address.getCity() + "\n"
				+ "State: " + address.getState() + "\n"
				+ "Zip: " + address.getZip() + "\n"
				+ "Phone number: " + formatPhoneNum() + "\n";
		System.out.println(string);
		return string;
	}
	
	private String formatPhoneNum()
	{
		char[] phoneNumArray = phoneNum.toCharArray();
		String formattedPhoneNum = "(" + phoneNumArray[0] + phoneNumArray[1] + phoneNumArray[2] + ")-" + phoneNumArray[3] + phoneNumArray[4] + phoneNumArray[5] + "-" + phoneNumArray[6] + phoneNumArray[7] + phoneNumArray[8] + phoneNumArray[9];
		return formattedPhoneNum;
	}
	
	
	
//	@Override
//	public int compareTo(Person person) 
//	{
//		return 0;
//	}
//	
//	public static Comparator<Person> PersonNameComparator = new Comparator<Person>() 
//	{
//		public int compare(Person person1, Person person2) 
//		{
//			String[] person1Parsed = person1.getFullName().toUpperCase().split(" ");
//			String[] person2Parsed = person2.getFullName().toUpperCase().split(" ");
//	
//			String personName1 = person1Parsed[person1Parsed.length - 1];
//			String personName2 = person2Parsed[person2Parsed.length - 1];
//			
//			//ascending order
//			return personName1.compareTo(personName2);
//		}
//	};

}
