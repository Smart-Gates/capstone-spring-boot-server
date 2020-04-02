# capstone-spring-boot
This project is the back-end server that provides APIs for the capstone-host and the capstone-android application. It provides the authentication for users and the connection with the MySQL database storage.
## Running the Project
This project can be run on a local environment using the following tools: Spring Tool Suite 4, MySQL workbench(with a local MSQL server).  
### Setup
Several file need to be added to the project
/src/main/resources/application.properties needs to be added that will fill follow the format of the /src/main/resources/application.properties.dev file but the secret keys will need to be added. The MySQL credentiations, jwt secret, and the weather API information should be added by the user. This project also uses firebase so an admin firebase configuration file will need to be created and stored under /src/main/resources/google.
## Firebase
This project uses firebase cloud messaging as a way to send messages from the Host device to the Android application users. An Android app was registered with Firebase to provide this functionality. A private Firebase Admin SDK key was generated and stored in this project. To implement notifications a new table with the Users Id was matched to the Firebase token so that the Server can send these push notifications to these specified users. To update these tables, the Android Application sends their google play services token to the Server so that they can be identified and messages can be sent to them. 
