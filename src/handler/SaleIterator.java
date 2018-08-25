package handler;
import java.util.ArrayList;
import handler.exceptions.*;
import model.Sale;
import model.Item;
import externalConnections.*;
import externalConnections.exceptions.ItemNotFoundException;
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
	}
	
	public void addItem(Input input) throws ItemNotFoundException
	{
		for(int i = 0; i < input.getQuantity(); i++)
		{

			try
			{
				if (itemsInStore.getList().isEmpty())
				{
					throw new DbFailureException();
				}

				Item newItem = itemsInStore.getItem(input.getId());
				customersList.add(newItem);
				displayGrabber.addNewPrice(newItem.getPrice());
				itemsInStore.delete(newItem);
			}
			
			catch(DbFailureException dbFailed)
			{
				return;
			}
			
		}
	}
}
