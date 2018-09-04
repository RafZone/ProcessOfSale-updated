package handler;
import java.util.ArrayList;
import handler.exceptions.*;
import model.Sale;
import model.Item;
import externalConnections.*;
import externalConnections.exceptions.*;
import externalConnections.exceptions.DbFailureException;
import controller.Controller;

public class SaleIterator 
{
	private DisplayGrabber displayGrabber;
	private Sale sale;
	private ArrayList customersList;
	private ItemsInStore itemsInStore;
	private int maxNumOfItems;
	
	public SaleIterator(Sale sale)
	{
		this.sale = sale;
		maxNumOfItems = sale.getMaxNumOfItems();
		itemsInStore = Controller.getItemsInStore();
		customersList = sale.getCustomersList();
		displayGrabber = Controller.getDisplayGrabber();
		displayGrabber.resetPrice();
	}
	
	public void addItem(Input input) throws ItemNotFoundException, DbFailureException
	{
		if (itemsInStore.getList().isEmpty())
		{
			throw new DbFailureException();
		}
		for(int i = 0; i < input.getQuantity(); i++)
		{

			Item newItem = itemsInStore.getItem(input.getId());
			customersList.add(newItem);
			displayGrabber.addNewPrice(newItem.getPrice());
			itemsInStore.delete(newItem);
		}
	}
	
	public void cancel()
	{
		Object [] customersListArray = customersList.toArray();
		for (int i = 0; i < customersListArray.length; i++)
		{
			Item item = (Item) customersListArray[i];
			itemsInStore.add(item);
			customersList.remove(item);
		}
	}
}
