\# 🍽️ SUVAIO - Food Delivery Web Application



A full-stack \*\*Food Delivery Web Application\*\* developed using \*\*Java, JSP, Servlets, JDBC, and MySQL\*\*. The application provides separate modules for \*\*Users, Restaurant Administrators, Delivery Agents, and System Administrators\*\*, enabling complete food ordering and delivery management through a modern web interface.



\---



\## 📖 Project Overview



SUVAIO is designed to simulate a real-world online food delivery platform. It allows users to browse restaurants, order food, track deliveries, and view nutritional information, while restaurant administrators manage menus and orders. Delivery agents handle assigned deliveries, and system administrators oversee the platform.



The project follows the \*\*MVC (Model-View-Controller)\*\* architecture using JSP, Servlets, JDBC, and MySQL.



\---



\## ✨ Features



\### 👤 User Module



\- User Registration \& Login

\- Browse Restaurants

\- Search Food Items

\- View Restaurant Menus

\- Add/Remove Items from Cart

\- Quantity Management

\- Nutrition Summary

\- Checkout

\- Order History

\- Track Orders

\- User Profile Management



\---



\### 🍴 Restaurant Admin Module



\- Secure Restaurant Login

\- Restaurant Dashboard

\- Menu Management

\- Add Food Items

\- Edit Food Items

\- Remove Food Items

\- View Incoming Orders

\- Revenue Dashboard

\- Restaurant Analytics



\---



\### 🚚 Delivery Agent Module



\- Delivery Agent Login

\- Assigned Orders

\- Picked-up Orders

\- Out-for-Delivery Orders

\- Delivery History

\- Delivery Analytics



\---



\### 🛠️ System Admin Module



\- Admin Dashboard

\- Manage Users

\- Manage Restaurants

\- Manage Delivery Agents

\- Monitor Orders

\- Platform Analytics



\---



\## 🛠️ Technology Stack



\### Backend

\- Java

\- JSP (JavaServer Pages)

\- Servlets

\- JDBC



\### Frontend

\- HTML5

\- CSS3

\- JavaScript



\### Database

\- MySQL



\### Server

\- Apache Tomcat 10



\### Development Tools

\- Eclipse IDE

\- MySQL Workbench

\- Git

\- GitHub



\---



\## 🏗️ Software Architecture



The project follows the \*\*MVC (Model-View-Controller)\*\* architecture.



\### Model

\- Java Beans

\- Entity Classes



\### View

\- JSP Pages

\- HTML

\- CSS

\- JavaScript



\### Controller

\- Java Servlets



\### Database Layer

\- DAO (Data Access Object)

\- DAO Implementation

\- JDBC Connectivity



\---



\## 📁 Project Structure



```



SUVAIO-Food-Delivery-System/

│

├── src/

│   └── main/

│       ├── java/

│       │   ├── controller/

│       │   ├── dao/

│       │   ├── daoimpl/

│       │   ├── model/

│       │   ├── service/

│       │   └── util/

│       │

│       └── webapp/

│           ├── css/

│           ├── js/

│           ├── images/

│           ├── WEB-INF/

│           ├── META-INF/

│           └── \*.jsp

│

├── database/

│   └── suvaio.sql

│

├── README.md

│

└── .gitignore



```



\---



\## 💾 Database



The application uses \*\*MySQL\*\* as the backend database.



Main tables include:



\- User

\- Restaurant

\- Menu

\- Cart

\- Order

\- OrderItem

\- DeliveryAgent



\---



\## 🔐 User Roles



\### 👤 Customer



\- Register/Login

\- Browse restaurants

\- Add food to cart

\- Checkout

\- Track orders

\- View order history



\---



\### 🍴 Restaurant Admin



\- Manage menu

\- Accept orders

\- Update order status

\- View revenue

\- View analytics



\---



\### 🚚 Delivery Agent



\- Login

\- Accept assigned orders

\- Update delivery status

\- View delivery history



\---



\### 🛠️ System Admin



\- Manage restaurants

\- Manage delivery agents

\- Manage users

\- View platform analytics

\- Monitor all orders



\---



\## 🚀 Installation \& Setup



\### Prerequisites



Before running the project, make sure you have installed:



\- Java JDK 17 or later

\- Eclipse IDE

\- Apache Tomcat 10

\- MySQL Server

\- MySQL Workbench

\- Git



\---



\## ⚙️ Database Setup



1\. Open MySQL Workbench.

2\. Create a new database named:



```sql

suvaio

```



3\. Import the SQL file located in the `database` folder.



4\. Update your database credentials in the JDBC connection class if required.



\---



\## ▶️ Running the Project



1\. Clone the repository.



```bash

git clone https://github.com/your-username/SUVAIO-Food-Delivery-System.git

```



2\. Import the project into Eclipse.



3\. Configure Apache Tomcat.



4\. Add the project to the server.



5\. Start Tomcat.



6\. Open your browser.



```

http://localhost:8080/SUVAIO/

```



\---



\## 📸 Application Screenshots



Add screenshots to the `screenshots` folder and update the paths below.



\### Landing Page



!\[Landing Page](screenshots/landing-page.png)



\---



\### User Home



!\[Home](screenshots/home.png)



\---



\### Cart



!\[Cart](screenshots/cart.png)



\---



\### Restaurant Dashboard



!\[Restaurant Dashboard](screenshots/restaurant-dashboard.png)



\---



\### Admin Dashboard



!\[Admin Dashboard](screenshots/admin-dashboard.png)



\---



\### Delivery Dashboard



!\[Delivery Dashboard](screenshots/delivery-dashboard.png)



\---



\### Analytics Dashboard



!\[Analytics](screenshots/analytics.png)



\---



\## 🎥 Demo Video



A complete walkthrough of the application demonstrating:



\- User Module

\- Restaurant Module

\- Delivery Agent Module

\- Admin Module

\- Order Processing

\- Analytics Dashboard



> \*(Add your YouTube or Google Drive video link here after uploading the demo.)\*



\---



\## 🔮 Future Enhancements



\- Online Payment Gateway Integration

\- Real-Time Order Tracking using Maps

\- Push Notifications

\- Email Notifications

\- Restaurant Ratings \& Reviews

\- AI-based Food Recommendations

\- Spring Boot Migration

\- Hibernate Integration

\- REST APIs

\- Docker Deployment

\- Cloud Deployment (AWS/Azure)



\---



\## 👨‍💻 Developer



\*\*Shivaani S\*\*



Information Science Engineering



Java Full Stack Developer | Cybersecurity Enthusiast



GitHub:

https://github.com/hackiller007-vk



LinkedIn:

https://www.linkedin.com/in/vishnu-k04/



\---



\## 🤝 Contributing



Contributions, suggestions, and improvements are welcome.



Feel free to fork this repository and create a pull request.



\---



\## 📄 License



This project is developed for educational and portfolio purposes.



\---



\# ⭐ If you found this project useful, please consider giving it a Star!

