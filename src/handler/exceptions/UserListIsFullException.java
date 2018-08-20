package handler.exceptions;

public class UserListIsFullException extends Exception
{
	
	//This an exception in case the customer try's to buy more than allowed. it should shown to the
	//cashier.
	public UserListIsFullException()
	{
		System.out.println("Customers list is full. Please try again later.");
	}
}