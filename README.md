# CSNetworksAssignment: CSC4021Z Assignment : October 13, 2023

Note: The code is also available on GitHub: https://github.com/ChiefMonk/CSNetworksAssignment

Authors
1. Shaylin Chetty - CHTSHA042
2. Chipo Hamayobe - HMYCHI001
3. Orefile Morule - MRLORE001
4. Enock Shezi - SHZENO001

Compile:
This is a Maven project. Just needs JDK >= 15. Depending on the IDE, VSCode auto compiles the project. We also used NetBeans, 
which has lots of compilation and debuging options. Depending on the IDE used, this should output either 
CSNetworksAssignment-1.0.1-jar-with-dependencies.jar or just CSNetworksAssignment.jar. Always use the one with a bigger .jar size.

Running:
This is a secure chat system. We have created the Output folder with the compiled CSNetworksAssignment-1.0.1-jar-with-dependencies.jar.
The Output folder has the following folder:
1. server: this has a copy of the .jar for running the chat server, which also acts as the CA. It also has the server public and private keys
2. client_1: this has a copy of the .jar for running client 1 of the chat
3. client_2: this has a copy of the .jar for running client 2 of the chat
4. client_3: this has a copy of the .jar for running client 3 of the chat

In the Output folder we have included the following .bat files for easier running of the server and 3 clients
1. run_server: click this first to run the chat server and CA (runs on port 4026)
2. run_client_1: click this to run the chat client 1
3. run_client_2: click this to run the chat client 2
4. run_client_3: click this to run the chat client 3

OR If in the IDE: 
 - To run the server, run the project in debug mode and select uct.cs.networks.ui.ChatServer. 
 - To run the client, run the ChatClient.java class from the IDE. 

If you compile your own .jar, please copy to the Output/server and Output/client_1 to 3. Also edit the .bat files to reflect the correct .jar file

# Starting a Session
- Server
This will open a new GUI for the server output (a log of all interactions - this is a summary of key messages. 
A full sequence of interactions and processes are shown in the command line).

- Clients = PLEASE, make sure you run atleast 3 clients, in their own folders so the private/public keys do NOT conflict
This will open a GUI and prompt the user for some personal details used to register the user with the system and create public and private keys.
The chat screen should then load and be populated with the active users currently in the system. 

Users can then chat using the dropdown to select a message type and load an image and enter text in the appropriate regions. 
All messages shared by the server are shown (in encrypted form) in the lower half of the screen whilst messages intended for the current 
user are shown in the top half of the screen along with any incoming images. 