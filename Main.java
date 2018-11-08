package application.main;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * 
 * This is the main class, here the program will be launched placing 
 * the user in the resource manager simulation. Here the program will read
 * and execute the commands given in the data.txt file.
 * 
 * @author Kehlsey Lewis
 * @version Fall 2018
 * 
 */


public class Main extends Application {

	/** Stage for the primary stage which will be the main-window. **/
	private static Stage mainWindow;
		
	/** Holds the current line number. **/
	private int lineCount = 1;
	
	/** Holds the number of processes specified. **/
	private Process[] processesList;
	
	/** Holds the number of resources specified. **/
	private Resource[] resourcesList;
	
	/** Holds the line connections between the processes and resources specified. **/
	private ArrayList<Connection> lines = new ArrayList<Connection>();
	
	/** Holds the display elements. **/
	private Pane root = new Pane();
	
	/** Displays the line being read and displays in application **/
	private Text text = new Text("press next to begin");
	
	/** Displays more information about what is going on with the line read. **/
	private Text info = new Text("more information displayed here");
	
	@Override
	public final void start(final Stage primaryStage) throws IOException {
		
		//setting up display
        Scene scene = new Scene(root, 1100, 800);
        primaryStage.setTitle("Resource Manager Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
        Canvas cvs = new Canvas();
        GraphicsContext gc = cvs.getGraphicsContext2D();         
        cvs.setWidth(1100);
        cvs.setHeight(800);
        cvs.setLayoutX(0);
        cvs.setLayoutY(0);
        root.getChildren().add(cvs);
        
        //for text label
		text.setStyle("-fx-font: 24 arial;");
		text.setX(710);
		text.setY(625);
        root.getChildren().add(text);
        
        //creating the next button
        Button nextButton = new Button("Next");
        nextButton.setLayoutX(500);
        nextButton.setLayoutY(760);
        nextButton.setPrefSize(50, 30);
        
        //button action listener
        EventHandler<ActionEvent> nextButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	lineCount++;
            	try {
					readNextLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        
        nextButton.setOnAction(nextButtonHandler);        
        root.getChildren().add(nextButton);
               
        //process label
        Text processLabel = new Text("Processes \u2193");
        processLabel.setStyle("-fx-font: 24 arial;");
        processLabel.setX(20);
        processLabel.setY(30);
        root.getChildren().add(processLabel);
        
        //getting # of processes
        String processes = readfile(lineCount);
        String numProcessesString = processes.replaceAll("[^0-9]", ""); //using regular expression
        int numProcesses = Integer.parseInt(numProcessesString);
        processesList = new Process[numProcesses+1];
        
        int x = 10;
        int y = 50;
               
        //making processes and displaying in application
        for (int i = 0; i <= numProcesses; i++){
        	processesList[i] = new Process(x,y,"p"+i);
        	gc.drawImage(processesList[i].makeCircle(),x,y);
        	x += 100;		
        }
        
        //resource label
        Text resourceLabel = new Text("Resources \u2191");
        resourceLabel.setStyle("-fx-font: 24 arial;");
        resourceLabel.setX(20);
        resourceLabel.setY(575);
        root.getChildren().add(resourceLabel);
        
        lineCount ++; //moving to next line
        
        //getting num of resources
        String resources = readfile(lineCount);
        String numResourcesString = resources.replaceAll("[^0-9]", ""); //using regular expression
        int numResources = Integer.parseInt(numResourcesString);
        resourcesList = new Resource[numResources+1];
        
        x = 10;
        y = 500;
        
        //making resources
        for (int i = 0 ; i <= numResources; i++){
        	resourcesList[i] = new Resource(x,y,"r"+i);
        	gc.drawImage(resourcesList[i].makeSquare(),x,y);
        	x += 100;	
        	
        }
        
        //for more info box 
        Rectangle infoBox = new Rectangle(380,230);
        infoBox.setX(700);
        infoBox.setY(560);
        infoBox.setFill(Color.TRANSPARENT);
        infoBox.setStroke(Color.BLACK);
        root.getChildren().add(infoBox);
        
        Text infoBoxLabel = new Text("Details:");
        infoBoxLabel.setStyle("-fx-font: 17 arial;");
        infoBoxLabel.setX(710);
        infoBoxLabel.setY(580);
        root.getChildren().add(infoBoxLabel);
        
        
        info.setStyle("-fx-font: 17 arial;");
        info.setX(710);
        info.setY(680);
        root.getChildren().add(info);
        
	}
	
			
	/**
	 * Updates the current scene with the passed scene.
	 */
	public static void setScene(final Scene scene) {
		mainWindow.setScene(scene);
	}

	/**
	* This method is the main method that will launch the application.
       **/
	public static void main(final String[] args) {
		launch(args);
	}
	
	/**
	* This method will read the given line of the file and return a string
	* version of the line.
       **/
	public String readfile(int line) throws IOException{
		
		FileInputStream fs= new FileInputStream("C:/Users/Kehlsey/workspace/ResourceManager/src/application/main/data.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));
		
		String read;
		
		for(int i = 0; i < line-1; ++i){ //reads lines up to requested line
		  br.readLine();
		}
		read = br.readLine();
		
		if (read == null){
			read = "end of file reached";
		}
		
		return read;
	}
	
	/**
	* This method will translate the next line into the given instructions to
	* decide which method to use.
       **/
	public void readNextLine() throws IOException{
		
		String line = readfile(lineCount); //reads the requested line
		
		if (line.compareTo("end of file reached") == 0){ //if end of file
			text.setText("End of file reached");
			info.setText("");
		}
		
		else {

		text.setText("Line read: " + line); //displays the line in the application
		
		String[] arr = line.split(" ");
		
		//using regular expressions to determine process/resource number
		String processNumString = arr[0].replaceAll("[^0-9]", "");
        int processID = Integer.parseInt(processNumString);
        
		String resourceNumString = arr[2].replaceAll("[^0-9]", "");
        int resourceID = Integer.parseInt(resourceNumString);
        
        //decides the appropriate method to use
        if ( arr[1].equals("requests") ) {
        	   request(processID, resourceID);
        	} else {

        	   release(processID, resourceID);
        	}	
	}
	}

	/**
	* This method is used for when a process releases a resource.
    **/
	public void release(int process, int resource){
		
		//process releases resource
		processesList[process].releaseResource(resourcesList[resource]);
		
		//finding connection to remove
		for (Connection i:lines){
			if (i.getProcessConnection().equals(processesList[process]) && i.getResourceConnection().equals(resourcesList[resource])){
				i.remove();
				
				//updates info text
				info.setText(processesList[process].getName() + " releases " + resourcesList[resource].getName() );
			}
		}
		
		//gives resource to next process waiting
		resourcesList[resource].next();
		if (!resourcesList[resource].isAvailable()){ //creates new connection with next in queue
			Connection line = new Connection (resourcesList[resource].ownedBy(),resourcesList[resource]);
			lines.add(line);
			root.getChildren().add(line.makeLine());
			
			//updates info text
			info.setText(processesList[process].getName() + " releases " + resourcesList[resource].getName()
					+ ", resource is now owned by " + resourcesList[resource].ownedBy().getName() );
		}
	}
	
	/**
	* This method is used for when a process requests a resource.
    **/
	public void request(int process, int resource){
		
		//if resource is available, process will take it
		if (resourcesList[resource].isAvailable()){ //checking if resource is available
				resourcesList[resource].resourceTakenBy(processesList[process]); //process claiming resource
				processesList[process].takeResource(resourcesList[resource]);
				
				//creates connection
				Connection line = new Connection (processesList[process],resourcesList[resource]);
				lines.add(line);
				root.getChildren().add(line.makeLine());
				
				//updates the info text
				info.setText(processesList[process].getName() + " now owns " + resourcesList[resource].getName() );
			
		}
		
		//adds to queue for that resource
		else { 
			resourcesList[resource].addToQueue(processesList[process]);
			processesList[process].inLineFor(resourcesList[resource]);
			
			//checks if deadlocked
			if(deadlocked(processesList[process], resourcesList[resource]) == true){
				
				//shows connection that created connection
				Connection line = new Connection (processesList[process],resourcesList[resource]);
				line.setFill(Color.RED);
				lines.add(line);
				root.getChildren().add(line.makeLine());
				
				for (Connection i:lines){
					if (i.getProcessConnection().equals(resourcesList[resource].ownedBy()) && i.getResourceConnection().equals(resourcesList[resource])){
						i.setFill(Color.RED);
					}
				}
				
				//updates text to notify deadlock
				info.setText(processesList[process].getName() + " and " + resourcesList[resource].ownedBy().getName() + " are deadlocked" );			
				}
			
			else {
				
				//updates text that process is waiting
				info.setText(processesList[process].getName() + " is waiting for " + resourcesList[resource].getName());
			}
			}	
	}
	
	/**
	* This method detects deadlock if it is present.
    **/
	public boolean deadlocked(Process process1, Resource resource){
		
		//getting second process in question
		Process process2 = resource.ownedBy();
		
		//getting the list of resources the processes are waiting for
		ArrayList<Resource> p2WaitingFor = new ArrayList<Resource>(process2.WaitingFor());
		ArrayList<Resource> p1WaitingFor = new ArrayList<Resource>(process1.WaitingFor());
		
		//getting the list of resources the processes own
		ArrayList<Resource> p1Owns = new ArrayList<Resource>(process1.getResourcesOwned());
		ArrayList<Resource> p2Owns = new ArrayList<Resource>(process2.getResourcesOwned());
		
		//flag for process1 being blocked
		boolean flag1 = false;
		
		//flag for process2 being blocked
		boolean flag2 = false;
			
		//figuring out if p1 is waiting for a resource p2 owns
		for (Resource r1: p1WaitingFor){
			if (p2Owns.contains(r1)){
				flag1 = true;
			}
		}
		
		//figuring out if p2 is waiting for a resource p1 owns
		for (Resource r2: p2WaitingFor){
			if (p1Owns.contains(r2)){
				flag2 = true;
			}
		}
		
		//both flags true then deadlocked
		if (flag1 && flag2){
			return true;
		}
		
		//otherwise not deadlocked
		else {		
		return false;
		}	
	}
	
}
