package application.main;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * 
 * This is the Connection class, all information about a Connection between a resource and process is
 * stored in this class.
 * 
 * @author Kehlsey Lewis
 * @version Fall 2018
 * 
 */

public class Connection {
	
	/** The process that the line is connected to. **/
	private Process process;
	
	/** The resource that the line is connected to. **/
	private Resource resource;
	
	/** The line for the connection. **/
	private Line line = new Line();
	
	/**
	* The constructor method for the Connection object.
    **/
	public Connection(Process process, Resource resource){
		this.process = process;
		this.resource = resource;
		
	}
	
	/**
	* This method makes a line that connects the process and resource in the application.
    **/
	public Line makeLine(){
		         
		line.setStartX(process.getX()+45);
		line.setStartY(process.getY()+50);
		line.setEndX(resource.getX()+25);
		line.setEndY(resource.getY());
	    line.setFill(Color.BLACK);
		 return line;
	}
	
	/**
	* This method returns the line.
    **/
	public Line getLine(){
		return line;
	}
	
	/**
	* This method returns the resource the line is connected to.
    **/
	public Resource getResourceConnection(){
		return resource;
	}
	
	/**
	* This method returns the process the line is connected to.
    **/
	public Process getProcessConnection(){
		return process;
	}
	
	/**
	* This method makes the line invisible so that the connection is no longer
	* seen in the application.
    **/
	public void remove(){
		line.setVisible(false);
	}

	/**
	* This method changes the line color to red when the connection is for deadlock.
    **/
	public void setFill(Color red) {
		line.setStroke(Color.RED);
		
	}

}
