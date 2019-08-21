package com.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;

public class User 
{

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	ArrayList<Account> accounts = new ArrayList<>(0);
	private static final String path = "/Users/evan/Documents/streams/";
	
	public User()
	{
	}
	
	//*********************************************************************************************//
	public void writeUserToFile()
	{
		String fileName = path + this.email + ".txt";
		try 
		{
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		bw.write(this.formatData());
		bw.close();
		}catch(IOException e)
		{
			System.out.println("ERROR writing to file");
		}
	}
	
	//*********************************************************************************************//
	public static User readFile(String email)
	{
		String fileName = path + email + ".txt";
		User user = new User();
		Scanner scanner = new Scanner(System.in);
		try 
		{
			// read user profile
			scanner = new Scanner(new File(fileName));
			String line = scanner.nextLine();
			String[] parsedLine = line.split(", ");
			
			// populate user object with user information from file
			user.setFirstName(parsedLine[0]);
			user.setLastName(parsedLine[1]);
			user.setEmail(parsedLine[2]);
			user.setPassword(parsedLine[3]);
		}
		catch(FileNotFoundException e)
		{
			System.out.print("ERROR reading file: " + fileName);
		}
		scanner.close();
		return user;
	}
	
	//*********************************************************************************************//
	public String formatData()
	{
		String basicInfo = this.firstName + ", " + this.lastName + ", " + this.email + ", " + this.password;
		
		String accountNames = "";
		for(Account account:this.accounts)
		{
			accountNames += ", " + account.getId();
		}
		
		return basicInfo + accountNames;
	}
	
	//*********************************************************************************************//
	public boolean isAccountPresent(String id)
	{
		if(this.accounts.size() == 0)
		{
			return false;
		}
		else
		{
			for(Account account:this.accounts)
			{
				if(account.getId().equals(id))
				{
					return true;
				}
			}
			return false;
		}
	}
	
	//*********************************************************************************************//
	public void addAccount(Account account)
	{
		this.accounts.add(account);
	}
	
	//*********************************************************************************************//
	public Account getAccount(String accountName)
	{
		for(Account account:accounts)
		{
			if(account.getId().equals(accountName))
			{
				return account;
			}
		}
		return null;
	}
	
	//*********************************************************************************************//
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public static String getPath() {
		return path;
	}
	
}
