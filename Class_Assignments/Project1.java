package Week2;

import java.util.Scanner;

public class Project1 {

	public static void main(String[] args) 
	{
		//Prompt user to input change amount between 1 and 99 cents
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter change amount between 1 cent and 99 cents: ");
		int cents = sc.nextInt();
		sc.close();
		
		//Call changeMaker method with user given value
		changeMaker(cents);	
	}

	public static void changeMaker(int cents)
	{
		/** This method takes a user input between 1 and 99 cents. The input is an integer.
		 * The method uses the division operator to calculate the number of coins that are
		 * required to make that total amount. The order of code here is critical, as it 
		 * divides out the larger coins from the total first. It divides out quarters first, 
		 * then dimes, then nickels, and the remainder is pennies. The method also uses a
		 * 'temp' variable that stores the current remainder of coins after each successive
		 * division step. This 'temp' variable is calculated using the modulus operator and 
		 * the value of the coin that was just divided out of the total.
		*/
		
		//declare 'temp' variable, which tracks the current remainder of change due
		int temp = cents;
		
		//Calculate number of quarters. Remainder of division will be truncated
		int quarters = temp/25;
		
		//Calculate remainder of change owed after maximum number of quarters returned
		temp %= 25;
		
		//Calculate number of dimes. Remainder of division will be truncated
		int dimes = temp/10;
		
		//Calculate remainder of change owed after maximum number of dimes returned
		temp %= 10;
		
		//Calculate number of nickels. Remainder of division will be truncated
		int nickels = temp/5;
				
		//Calculate number of pennies.
		int pennies = temp%5;
		
		//Print out number of each coin to return to user, as long as value is not zero.
		if(quarters != 0)
		{
		System.out.println("Number of Quarters given: " + quarters);
		}
		
		if(dimes != 0)
		{
		System.out.println("Number of Dimes given: " + dimes);
		}
		
		if(nickels != 0)
		{
		System.out.println("Number of Nickels given: " + nickels);
		}
		
		if(pennies != 0)
		{
		System.out.println("Number of Pennies given: " + pennies);
		}
	}
	
}