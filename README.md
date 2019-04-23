# Group 16 Service Requester Software Project 

**LABORA - GROUP 16 - SOFWARE PROJECT**
Below is the break downs of our service requester and contractor applications

______________________________________

Getting Started - Dependencies:
You will need to download Android Studio, and either download an emulator or use an android device to run the application(s)

______________________________________

**Contrator:**
Contractor consisted of 5 activities

| **CLASS FILE** | **DESCRIPTION** |
| --- | --- |
| Register Activity | The RegisterActivity file enables contractors to create an account within the app through the login page.  | 
| Login Activity | The LoginActivity file enables the Login page within our Android Studio app to load and function correctly. The file allows contractors to be able to create an account and login. | 
| Menu Activity | The MenuActivity file allows the contractor to go online and pick up jobs. Once they go online, they can receive jobs which will come up on the screen and they can choose to accept or decline.| 
| Map Activity | The MapActivity file displays a map with the location of the customer. Once the contractor accepts the job it will take them to the map and they can follow the route to the destination of the customer. |
| Done Activity | Within the DoneActivity file it consists of a message coming up on the screen saying “You have accepted the job” with a big green tick. |


_______________________________________

**Service Requester:**
Service Requester consistsS of 6 ativities

| **CLASS FILE** | **DESCRIPTION** |
| --- | --- |  
| LoginActivity | The LoginActivity is the first page when the application is opened. It has our logo, as well as 2 input fields (username and password) and a submit button (login). This allows already registered service requesters to login. It incorporates Firebase’s ‘FirebaseAuth’ object. |
| RegisterActivity | If the service requester does not have an account, this registration area will allow them to make one. This is the first section of registration, so it asks for a username, and password. Firebase’s ‘FirebaseAuth’ is also incorporated, therefore it will display a toast if the credentials are taken. |
| RegisterActivity2 | If successful after ‘RegisterActivity’, the user will be taken to this page (activity). This is the second part of the registration process. Users will be required to enter their name, address, phone number etc. Once this stage is completed, an account has been registered, and they can now view their personal profile. |
| ProfileActivity | The profile page opens up when a service requester user has successfully logged in or registered an account. It displays an avatar, as well as menu options. The two options include ‘services’ (request for services) and ‘logout’ (logout of then account). The buttons are enlarged, in order to meet accessibility and usability requirements.  |
| Services | If the service requester user selects ‘services’, they will be redirected to this activity. There is a scroller, in order to search for the service, they want. Then there are 3 fields: Post Code, Description and Keywords. Then there is a request button, which is used to submit the request to the relevant contractors’ maps. |
| Summary | This is the final page displayed when a user requests a service. A tick symbol and confirmation message will appear. It confirms the request has been submitted. |


