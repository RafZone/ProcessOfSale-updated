package handler.exceptions;

public class DbFailureException extends Exception
{
	//This is a message for the cahier to worn about the error
	public DbFailureException ()
	{
		System.out.println("The database is crashed. We have contacted the developer and we will get back"
				+ " as soon as we can. please be patient");
	}
}
