package view;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import controller.Controller;
import externalConnections.exceptions.ItemNotFoundException;
import handler.Input;
import model.Item;

public class View 
{
	private Controller controller;
	private Scanner in;
	
	public View ()
	{
		controller = new Controller();
		in = new Scanner(System.in);
		System.out.println("Welcome to my Store.");
		while(true)
		{
			System.out.println("If you would like to start a new sale press Enter."
					+ "press any key to shutdown");
			String user = in.nextLine();
			if(user.isEmpty())
			{
				startSale();
				if (!controller.getCustomersList().isEmpty() && paid())
				{
					System.out.println("Thank you for your purchase. Here is your receit");
					printList(controller.getCustomersList());
				}
				else
				{
					controller.cancel();
					System.out.println("Your purchase is canceled. Thank you for your visit");
				}
			}
			else
			{
				System.out.println("Bye Bye");
				break;
			}
		}
	}
	
	private void startSale()
	{
		controller.startSale();
		System.out.println("Here is a list of available items");
		printList(controller.getItemsInStore().getList());
		System.out.println("Enter the identifier of an Item or press enter to exit.");
		Input userInput = new Input();
		while(inputSet(userInput))
		{
			//This is a very important section because if we do not use the conditional
			//statement and we have a false input, the program would crash
			try {
				if (controller.validate(userInput))
				{
					System.out.println("The following Item(s) has been added x" + userInput.getQuantity());
					printItem(controller.getItem(userInput));
					controller.addItem(userInput);
				}
				else
				{
					System.out.println("The identifier could not be found.");
				}
			} catch (ItemNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("The Item is not Found.");
			}
		}
	}
	
	private boolean inputSet(Input myInput)
	{
		Scanner in = new Scanner(System.in);
		while (true)
		{
			String input = in.nextLine();
			Scanner scan = new Scanner(input).useDelimiter("[^0-9]+");
			if (input.isEmpty())
			{
				return false;
			}
			else
			{
				try
				{
					myInput.setId(scan.nextInt());
					if (scan.hasNextInt())
					{
						int q = scan.nextInt();
						if (q > 0)
							myInput.setQuantity(q);
						else
							myInput.setQuantity(1); //need to change this to an exception because if we insert 0 then 
													//the quantity is changed to 1 and instead we should throw invalid
													//quantity exception
					}
					else
						myInput.setQuantity(1);
					return true;
				}
				
				catch(NoSuchElementException e)
				{
					System.out.println("Please write a number");
				}
			}
		}
	}
	
	private boolean paid()
	{
		controller.Payment();
		System.out.println("Please insert amount paid to confirm sale or press enter to cancel");
		Scanner input = new Scanner (in.nextLine());
		if (input.hasNextInt())
		{
			double amount= input.nextInt();
			if (amount >= controller.getTotal())
			{
			System.out.println("The amount payed is: " + amount);
			System.out.println("Change:              " + (amount-controller.getTotal()));
			return true;
			}
		}
		return false;
	}
	
	private void printItem(Item item)
	{
		//System.out.println("The following Item has been added to the list");
		System.out.print(item.getName() + "\t");
		System.out.print(item.getIdentifier() + "\t");
		System.out.print(item.getPrice() + "\t");
	}
	
	public void printList(ArrayList list)
	{
		if (list.size()==0)
		{
			System.out.println("list is empty");
		}
		
		else
		{
			System.out.println("Name\t" + "Id\t" + "Price\t" + "Quantity");
			ArrayList copy = new ArrayList();
			Object[] original = list.toArray();
			for (int i = 0; i < list.size();i++)
			{
				Item item = (Item) original[i];
				if (!copy.contains(item))
				{
					printItem(item);
					int j = item.getOccurences(list);
					System.out.print(j);
					System.out.println();
					copy.add(item);
				}
			}
			
			System.out.println("-------------------------");
		}
	}
}
