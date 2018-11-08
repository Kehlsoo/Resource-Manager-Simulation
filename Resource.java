package application.main;

import java.util.ArrayList;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * 
 * This is the Resource class, all information about a Resource object is
 * stored in this class.
 * 
 * @author Kehlsey Lewis
 * @version Fall 2018
 * 
 */

public class Resource {
				
		/** x-coordinate the resources' square is located on. **/
		private int x;
		
		/** y-coordinate the resources' square is located on. **/
		private int y;
		
		/** The process that the resource is owned by. **/
		private Process ownedBy;
		
		/** Reports if the resource is available for taking by a process. **/
		private boolean available;
		
		/** The name or the resource. **/
		private String title;
		
		/** List of processes waiting to take resource. **/
		private ArrayList<Process> queue;
		
		/**
		* The constructor method for the Resource object.
	    **/
		public Resource(int xLocation, int yLocation, String name) {
			title = name;
			x = xLocation;
			y = yLocation;
			queue = new ArrayList<Process>();
			available = true;
			
		}
		
		/**
		 * This method returns the name of the resource.
	    **/
		public String getName(){
			return title;
		}
		
		/**
		* This method creates a rectangle to represent the resource
		* and to display in the application.
	    **/
		public WritableImage makeSquare(){
 
			 StackPane sPane = new StackPane();        
			 Rectangle square = new Rectangle(50,50);
		     square.setFill(Color.PINK);
		     sPane.getChildren().add(square);

		     Text txtNum = new Text(title);
		     sPane.getChildren().add(txtNum);
		     SnapshotParameters parameters = new SnapshotParameters();
		     parameters.setFill(Color.TRANSPARENT);
		     return sPane.snapshot(parameters, null);
		}
		
		/**
		* This method returns the x-coordinate that the resources' rectangle
		* is located on.
	    **/		
		public int getX(){
			return x;
		}
		
		/**
		* This method returns the y-coordinate that the resources' rectangle
		* is located on.
	    **/
		public int getY(){
			return y;
		}
		
		/**
		* This method makes the resource available.
	    **/
		public void makeAvailable(){
			available = true;
		}
		
		/**
		* This method gives the rights to this resource to the specified process.
	    **/
		public void resourceTakenBy(Process process){
			available = false;
			ownedBy = process;
		}
		
		/**
		* This method returns true if the resource is available or 
		* false if the resource is not available.
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
		* This method adds the process to the queue.
	    **/
		public void addToQueue(Process process){
			queue.add(process);
		}
		
		/**
		* This method gives this resource to the next process in line.
	    **/
		public void next(){
			if(queue.isEmpty()){
				makeAvailable();
			}
			
			else {
				resourceTakenBy(queue.get(0));
			}
			
		}
		
		/**
		* This method returns the process that owns this resource.
	    **/
		public Process ownedBy(){
			return ownedBy;
		}
		
		
}
