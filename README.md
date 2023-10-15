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
 In the IDE: 
 - To run the server, run the project in debug mode and select uct.cs.networks.ui.ChatServer. 
 - To run the client, run the uct.cs.networks.ui.ChatClient.java class from the IDE. Run atleast 2 clients

# Starting a Session
- Server
This will open a new GUI for the server output (a log of all interactions - this is a summary of key messages. 
A full sequence of interactions and processes are shown in the command line).

- Clients 
This will open a GUI and prompt the user for some personal details used to register the user with the system and create public and private keys.
The chat screen should then load and be populated with the active users currently in the system. 

Users can then chat using the dropdown to select a message type and load an image and enter text in the appropriate regions. 
All messages shared by the server are shown (in encrypted form) in the lower half of the screen whilst messages intended for the current 
user are shown in the top half of the screen along with any incoming images. 