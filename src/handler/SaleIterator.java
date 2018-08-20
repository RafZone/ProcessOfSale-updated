package handler;
import java.util.ArrayList;
import model.Sale;
import model.Item;
import externalConnections.*;
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
	
	public void addItem(Input input)
	{
		for(int i = 0; i < input.getQuantity(); i++)
		{

			Item newItem = itemsInStore.getItem(input.getId());
			customersList.add(newItem);
			displayGrabber.addNewPrice(newItem.getPrice());
			itemsInStore.delete(newItem);
			
		}
	}
}
