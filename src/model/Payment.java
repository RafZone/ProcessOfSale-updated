package model;
import java.util.ArrayList;

public class Payment 
{
	private double payed;
	private ArrayList listToPayFor;
	private double total = 0.0;
	
	//The price of each item is added in @total
	public Payment (ArrayList listToPayFor)
	{
		this.listToPayFor = listToPayFor;
		Object[] listArray = listToPayFor.toArray();
		for (int i = 0; i < listArray.length; i++)
		{
			Item item = (Item) listArray[i];
			total = total +item.getPrice();
		}
	}
	
	public double getTotal()
	{
		return total;
	}
}