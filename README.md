#  Library Management System (Servlet-Based Web Application)

##  Project Overview

The Library Management System is a Java-based web application developed using Servlets, HTML, and Oracle Database. 
This project demonstrates core concepts of Java EE, server-side processing, form handling, database connectivity (JDBC), and basic web application flow.

The application allows users to:

* Add new books to the library database
* Search and view book details using ISBN
* Interact through a simple HTML-based UI

This project follows a **Servlet-based architecture** and is ideal for understanding how backend Java logic integrates with frontend HTML forms and a relational database.
---
##  Technologies Used
### Backend
* Java (JDK 8+)
* Java Servlets
* JDBC (Oracle JDBC Driver)

### Frontend
* HTML5
* Basic CSS (Browser Default Styling)

### Database
* Oracle Database
* SQL for table creation and data manipulation

### Server & Tools
* Apache Tomcat (v8/9/10)
* Eclipse / IntelliJ IDEA
* **Oracle SQL Developer**
---
##  Application Architecture
Client (Browser)
   ↓
HTML Pages (Menu, AddBook, ViewBook)
   ↓
Servlet Controller (MainServlet)
   ↓
Service / DAO Layer
   ↓
Oracle Database
---
##  Application Pages & Flow

###  Main Menu (`Menu.html`)

* Acts as the entry point of the application
* Provides navigation links:

  * Add Book
  * View Book

Output:
<img width="474" height="241" alt="Screenshot 2026-02-07 153012" src="https://github.com/user-attachments/assets/b71f21fa-8b58-4dba-8b80-ad19e9c98ed9" />`
---
### 2️⃣ Add Book Page (`AddBook.html`)
This page allows users to add a new book to the library.
Input Fields:
* ISBN Number
* Book Name
* Book Type (Dropdown – e.g., General)
* Author Name (Dropdown)
* Book Cost
Functionality:
* User fills in the book details
* On clicking AddBook, data is sent to the servlet
* Servlet validates inputs and inserts data into the Oracle database

Output:
<img width="516" height="462" alt="Screenshot 2026-02-07 153027" src="https://github.com/user-attachments/assets/f11d8b99-6bd5-4c6a-bacf-2bc35bad7edd" />
---
### 3️⃣ View Book Page (`ViewBook.html`)
* Allows searching for a book using its ISBN code
* User enters ISBN and clicks *Search*

Functionality:
* Servlet fetches book details from the database
* Displays:
  * Book Title
  * Author Name
  * Author Contact
  * Book Price
  * ISBN Number
Output:
For Search:
<img width="488" height="295" alt="Screenshot 2026-02-07 153042" src="https://github.com/user-attachments/assets/d40cb3c1-7825-4a49-9960-341851eab125" />
View Book:
<img width="745" height="178" alt="Screenshot 2026-02-07 153059" src="https://github.com/user-attachments/assets/89a4c607-eea9-4d85-82bb-d8f41f582c5e" />
---
## Servlet Functionality
### `MainServlet`
* Acts as the central controller
* Handles requests from AddBook and ViewBook pages
* Performs:
  * Request parameter extraction
  * Input validation
  * JDBC operations (Insert / Select)
  * Response generation
---
##  Database Design
###  BOOK Table (Sample Structure)

| Column Name | Data Type |
| ----------- | --------- |
| ISBN        | VARCHAR2  |
| BOOK_NAME   | VARCHAR2  |
| BOOK_TYPE   | CHAR      |
| AUTHOR_ID   | NUMBER    |
| COST        | NUMBER    |

###  AUTHOR Table (Sample Structure)

| Column Name | Data Type |
| ----------- | --------- |
| AUTHOR_ID   | NUMBER    |
| AUTHOR_NAME | VARCHAR2  |
| CONTACT     | VARCHAR2  |
---
##  Database Connectivity
* JDBC is used to connect the application with Oracle Database
* Oracle JDBC Driver configured in the project build path
* Connection details are maintained securely in the servlet or DAO layer
---

##  Key Features
* Servlet-based request handling
* Oracle database integration
* ISBN-based book search
* Dropdown-based form inputs
* Clear separation of UI and backend logic
---
##  How to Run the Project
1. Clone or import the project into Eclipse/IntelliJ
2. Configure Apache Tomcat Server
3. Add Oracle JDBC Driver to build path
4. Create required tables in Oracle DB
5. Update DB credentials in servlet/DAO
6. Deploy the project on Tomcat
7. Access via browser:
   [http://localhost:8081/libraryManagement/Menu.html]
---
##  Author
Iniyavan RK
Java & Web Application Developer
---
##  License
This project is created for educational and academic purposes.
---
