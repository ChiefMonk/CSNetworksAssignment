# CSNetworksAssignment
CSC4021Z Assignment

# Starting a Session
Load the codebase into an IDE (recommended: VS Code). This should auto compile a Java project (as well as a Maven project). To run the server, run the project in debug mode and select uct.cs.networks.ui.ChatServer. This will open a new GUI for the server output (a log of all interactions - this is a summary of key messages. A full sequence of interactions and processes are shown in the command line). To run the client, run the ChatClient.java class from the IDE. This will open a GUI and prompt the user for some personal details used to register the user with the system and create public and private keys. The chat screen should then load and be populated with 5 users currently in the system. 

Users can then chat using the dropdown to select a message type and load an image and enter text in the appropriate regions. All messages shared by the server are shown (in encrypted form) in the lower half of the screen whilst messages intended for the current user are shown in the top half of the screen along with any incoming images. 