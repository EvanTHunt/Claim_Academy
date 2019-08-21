package com.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Account 
{
	
	private String id;
	private double balance;
	private ArrayList<String> transHist = new ArrayList<>(0);
	
	public Account()
	{
	}

	//*********************************************************************************************//
	public void writeAccountToFile(String email)
	{
		String fileName = User.getPath() + email + "_" + this.id + ".txt";
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
		public static Account readFile(String email, String accountName)
		{
			String fileName = User.getPath() + email + "_" + accountName + ".txt";
			Account account = new Account();
			Scanner scanner = new Scanner(System.in);
			try 
			{
				// read account
				scanner = new Scanner(new File(fileName));
				String line = scanner.nextLine();
				String[] parsedLine = line.split(", ");
				
				// populate account object with account information from file
				if(line.length() == 2)
				{
					account.setId(parsedLine[0]);
					account.setBalance(Double.parseDouble(parsedLine[1]));
				}
				else
				{
					account.setId(parsedLine[0]);
					account.setBalance(Double.parseDouble(parsedLine[1]));
					for(int i = 2;i < parsedLine.length; i++)
					{
						account.addTransaction(parsedLine[i]);
					}
				}
			}
			catch(FileNotFoundException e)
			{
				System.out.print("ERROR reading file: " + fileName);
			}
			scanner.close();
			return account;
		}
		
	//*********************************************************************************************//
	public String formatData()
	{
		String basicInfo = this.id + ", " + this.balance;
		
		String transHist = "";
		for(String trans:this.transHist)
		{
			transHist += ", " + trans;
		}
		
		return basicInfo + transHist;
	}
	
	//*********************************************************************************************//
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public ArrayList<String> getTransHist() {
		return transHist;
	}

	public void setTransHist(ArrayList<String> transHist) {
		this.transHist = transHist;
	}
	
	public void addTransaction(String trans)
	{
		this.transHist.add(trans);
	}
	
}
