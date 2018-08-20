package controller;

import java.util.ArrayList;
import externalConnections.*;
import handler.*;
import model.*;
import view.TotalRevenueView;

public class Controller 
{
	private static ItemsInStore itemsInStore;
	private static DisplayGrabber displayGrabber;
	private TotalRevenueView display;
	private SaleIterator saleIterator;
	private Payment payment;
	private Sale sale;
	
	public Controller()
	{
		itemsInStore = new ItemsInStore();
		displayGrabber = new DisplayGrabber();
	}
	
	public void startSale()
	{
		display = new TotalRevenueView(displayGrabber);
		sale = new Sale();
		saleIterator = new SaleIterator(sale);
	}
	
	public void Payment()
	{
		payment = new Payment(sale.getCustomersList());
	}
	
	public boolean validate(Input input)
	{
		return itemsInStore.validate(input.getId(), input.getQuantity());
	}
	
	public Item getItem(Input input)
	{
		Item item;
		item = itemsInStore.getItem(input.getId());
		return item;
	}
	
	public void addItem(Input userInput)
	{
		saleIterator.addItem(userInput);
	}
	
	public static ItemsInStore getItemsInStore()
	{
		return itemsInStore;
	}
	
	public static DisplayGrabber getDisplayGrabber()
	{
		return displayGrabber;
	}
	
	public ArrayList getCustomersList()
	{
		return sale.getCustomersList();
	}
	
	public double getTotal()
	{
		return payment.getTotal();
	}
}
