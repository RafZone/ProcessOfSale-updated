package model;
import java.util.ArrayList;

public class Sale 
{
	private int maxNumOfItems = 10;
	private ArrayList customersList;
	
	public Sale()
	{
		customersList = new ArrayList(maxNumOfItems);
	}
	
	public ArrayList getCustomersList()
	{
		return customersList;
	}
	
	public int getMaxNumOfItems()
	{
		return maxNumOfItems;
	}
}
