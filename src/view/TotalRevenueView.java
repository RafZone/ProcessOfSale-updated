package view;
import externalConnections.Subject;
import model.Observer;

public class TotalRevenueView implements Observer
{
	private double total;
	private Subject displayGrabber;
	
	public TotalRevenueView(Subject displayGrabber)
	{
		this.displayGrabber = displayGrabber;
		displayGrabber.register(this);
	}
	
	public void update(double total)
	{
		this.total = total;
		System.out.println("The current total is : " + total);
	}
}