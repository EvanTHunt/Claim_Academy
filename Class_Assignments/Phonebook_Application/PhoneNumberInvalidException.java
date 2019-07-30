package com.phoneBook;

public class PhoneNumberInvalidException extends RuntimeException
{
	public void printMessage() 
	{
		System.out.print("Invalid phone number format. Phone number must be ten numeric digits. Please try again: ");
	}
}
