# Cloud Computing Search Engine Project

# Video Demonstration
https://drive.google.com/file/d/1D1bYHzI2bFiXActT3HNh45AgM0S5Gy43/view?usp=sharing

# Installation

To run the client application, simply clone the GitHub repository to your local machine and build it with Maven.

To run the server application, clone the repository to the GCP computer and compile and run by moving into src/main/java and running

$ javac Server.java && java Server

The directory the program searches for may need modification depending on your setup. It is located in line 86 and can be either a relative or absolute path. Client side variables may also need modification for running on your own GCP cluster, namely the authentication token path, the project ID, and the bucket ID. Further breakdown of the running procedure is in the video demonstration above.
