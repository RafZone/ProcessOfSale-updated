package externalConnections;
import model.Observer;;

public interface Subject 
{
	public void register(Observer display);
	public void unregister(Observer display);
	public void notifyObserver();
	public void addNewPrice(double newPrice);
}