package application.main;

import java.util.ArrayList;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * 
 * This is the Process class, all information about a Process object is
 * stored in this class.
 * 
 * @author Kehlsey Lewis
 * @version Fall 2018
 * 
 */
public class Process {
	
	/** Holds the list of resources this process owns. **/
	private ArrayList<Resource> resourcesOwned = new ArrayList<Resource>();
	
	/** Holds the list of resources the process is waiting for. **/
	private ArrayList<Resource> inQueFor = new ArrayList<Resource>();
	
	/** x-coordinate the process' circle is located on. **/
	private int x;
	
	/** y-coordinate the process' circle is located on. **/
	private int y;
	
	/** the name of the process. **/
	private String title;
	
	/** allows process to take resource **/
	private boolean available = true;
	
	
	/**
	* The constructor method to create a Process object.
    **/
	public Process(int xlocation, int ylocation, String name) {
		title = name;
		this.y = ylocation;
		this.x = xlocation;

	}
	
	/**
	 * This method returns the name of the process.
    **/
	public String getName(){
		return title;
	}
	
	/**
	* Process takes the given resource and adds to resourcesOwned array.
    **/
	public void takeResource (Resource r){
		resourcesOwned.add(r);
	}
	
	/**
	* This method will release the resource from the process' owned list.
    **/
	public void releaseResource (Resource r){
		resourcesOwned.remove(r);
	}
	
	/**
	* This method creates a circle to represent the process and to display in the application.
    **/
	public WritableImage makeCircle(){
		
		 StackPane sPane = new StackPane();        
	     Circle c = new Circle(40);
	     c.setFill(Color.LAVENDER);
	     sPane.getChildren().add(c);

	     Text txtNum = new Text(title);
	     sPane.getChildren().add(txtNum);
	     SnapshotParameters parameters = new SnapshotParameters();
	     parameters.setFill(Color.TRANSPARENT);
	     return sPane.snapshot(parameters, null);
	}
	
	/**
	* This method returns the x-coordinate that the process' circle
	* is located on.
    **/
	public int getX(){
		return x;
	}
	
	/**
	* This method returns the y-coordinate that the process' circle
	* is located on.
    **/
	public int getY(){
		return y;
	}
	
	/**
	* This method adds the resource to the list of resources the process is waiting for.
    **/
	public void inLineFor(Resource resource){
		inQueFor.add(resource);
	}
	
	/**
	* This method returns the list of resources the process owns.
    **/
	public ArrayList<Resource> getResourcesOwned(){
		return resourcesOwned;
	}
	
	/**
	* This method returns the list of resources the process is waiting for.
    **/
	public ArrayList<Resource> WaitingFor(){
		return inQueFor;
	}
	
	/**
	* This method returns true if the process is available or 
	* false if the process is not available.
    **/
	public boolean isAvailable(){
		if (available == true){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	* This method returns true if the process is available or 
	* false if the process is not available.
    **/
	public void makeAvailable(boolean flag){
		if (flag == true){
			available = true;
		}
		else {
			available = false;
		}
	}
}
