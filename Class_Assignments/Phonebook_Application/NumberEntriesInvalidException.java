package com.phoneBook;

public class NumberEntriesInvalidException extends RuntimeException
{
	public void printMessage() 
	{
		System.out.print("Invalid input format. Must have 6 fields, separated by ', '. Please try again: ");
	}
}
