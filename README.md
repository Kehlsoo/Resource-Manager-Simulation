# Resource-Manager-Simulation
This simulation keeps tracks of system state and utilizes a deadlock detection protocol along with showing how the operating system works as a resource manager.

Given complete knowledge of the current state of the system, it should be possible for a resource manager to conduct an evaluation of the system.  Obviously, a request for a resource that is currently being used cannot be granted, and the requesting process must be delayed.  However, sometimes a process is not merely delayed, but deadlocked; and the resource manager should be able to detect this problem and either produce diagnostic output or take corrective measures.  

The data.txt file has the following format:
* Processes:  an integer representing the total number of processes in the system
* Resources:  an integer representing the total number of resource classes in the system.  There is one instance of each resource type.
* Resource request: the name of a process and the name of the resource it is requesting
* Resource release: the name of a process and the name of the resource it is releasing

The first two values (processes, resources) is used to initialize the system.  The remaining lines in the input file consist of resource requests/releases that represent the activity of the various processes in the system over time.  At each resource request, the program displays the activity taken as a result of the request (e.g. "P2 now owns R3"), and output the result of a deadlock detection check (e.g. "System is deadlocked: processes P1, P2").


Screenshots:

![image](https://user-images.githubusercontent.com/35376384/48173587-62005a80-e2d2-11e8-8bb3-aa6bb6e0e591.png)

![image](https://user-images.githubusercontent.com/35376384/48173624-8a885480-e2d2-11e8-9e33-d69cc97bf791.png)

![image](https://user-images.githubusercontent.com/35376384/48173667-b4417b80-e2d2-11e8-9a31-c4f3a8c71f45.png)
