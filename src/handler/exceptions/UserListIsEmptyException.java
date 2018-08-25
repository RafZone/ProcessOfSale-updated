package handler.exceptions;

public class UserListIsEmptyException extends Exception
{
	
	//This an exception in case the customer try's to buy more than allowed. it should shown to the
	//cashier.
	public UserListIsEmptyException()
	{
		System.out.println("Customers list is Empty. Please try again later.");
	}
}