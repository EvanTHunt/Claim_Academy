package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.Account;
import com.user.User;

/**
 * Servlet implementation class TransferServlet
 */
@WebServlet("/Transfer")
public class TransferServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// get session and retrieve user object
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		
		// find both accounts selected by user, apply change to the account balances, append to transaction history lists, and write account to file
		ArrayList<Account> accountList = user.getAccounts();
		Double transferAmount = Double.parseDouble(request.getParameter("transferAmount"));
		for(Account account:accountList)
		{
			if(account.getId().equals(request.getParameter("accountFrom")))
			{
				double newBalance = account.getBalance() - transferAmount;
				account.setBalance(newBalance);
				account.addTransaction("transferFrom" + " $" + transferAmount + " $" + newBalance);
				account.writeAccountToFile(user.getEmail());
			}
			else if(account.getId().equals(request.getParameter("accountTo")))
			{
				double newBalance = account.getBalance() + transferAmount;
				account.setBalance(newBalance);
				account.addTransaction("transferTo" + " $" + transferAmount + " $" + newBalance);
				account.writeAccountToFile(user.getEmail());
			}
		}
		
		// assign updated account list back into user object and set user object to session
		user.setAccounts(accountList);
		session.setAttribute("user", user);
		
		// send back to home page
		RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
		rs.forward(request,response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
