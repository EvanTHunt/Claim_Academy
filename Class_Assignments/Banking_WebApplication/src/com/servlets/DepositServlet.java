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
 * Servlet implementation class DepositServlet
 */
@WebServlet("/Deposit")
public class DepositServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositServlet() 
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
		
		// find account selected by user, apply change to the account balance, append to transaction history list, and write account to file
		double accountChange = 0;
		String transType = "";
		if(request.getParameter("transType").equals("deposit"))
		{
			accountChange = Double.parseDouble(request.getParameter("depositAmount"));
			transType = "deposit";
		}
		else if(request.getParameter("transType").equals("withdrawal"))
		{
			accountChange = -Double.parseDouble(request.getParameter("depositAmount"));
			transType = "withdrawal";
		}
		ArrayList<Account> accountList = user.getAccounts();
		for(Account account:accountList)
		{
			if(account.getId().equals(request.getParameter("accountName")))
			{
				double newBalance = account.getBalance() + accountChange;
				account.setBalance(newBalance);
				account.addTransaction(transType + " $" + Math.abs(accountChange) + " $" + newBalance);
				account.writeAccountToFile(user.getEmail());
				break;
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
