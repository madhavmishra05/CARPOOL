# 🚗 Car Pooling System (Console Application + PostgreSQL)

A console-based ride booking system built using **Java and PostgreSQL**. This application allows users to create accounts, publish rides, search for available rides, book seats, and manage bookings. The system uses **JDBC with transaction management** to ensure safe and consistent database operations.

---

# 📌 Features

* 👤 Create new user accounts
* ❌ Delete user accounts with password verification
* 🚘 Publish rides with available seats, route, and fare
* 🔍 Search rides by source and destination
* 🎟️ Book seats on available rides
* 📄 View all available rides
* 👤 View user profile
* 📚 View booking history
* ❌ Cancel bookings (updates booking status)
* 🔄 Transaction-safe booking using JDBC

---

# 🛠️ Technologies Used

* **Language:** Java (Console-based application)
* **Database:** PostgreSQL
* **Connectivity:** JDBC (Java Database Connectivity)
* **Driver:** postgresql-42.7.9.jar
* **IDE:** IntelliJ IDEA / VS Code (Recommended)

---

# 📁 Project Structure

```
Car-Pooling/
│
├── Main.java
├── RideBookingSystem.java
├── DBConnection.java
│
├── User.java
├── Ride.java
├── Booking.java
│
└── postgresql-42.7.9.jar
```

### File Description

| File                   | Description                                     |
| ---------------------- | ----------------------------------------------- |
| Main.java              | Handles console menu and user input             |
| RideBookingSystem.java | Contains business logic and database operations |
| DBConnection.java      | Handles PostgreSQL database connection          |
| User.java              | User model class                                |
| Ride.java              | Ride model class                                |
| Booking.java           | Booking model class                             |

---

# 🔄 Booking Workflow

1. User creates an account
2. Driver publishes a ride
3. Passenger searches for available rides
4. Passenger books seats
5. System updates seat availability
6. Booking is stored in database

---

# ❌ Cancel Booking Workflow

* Updates booking status to `CANCELLED`
* Booking history remains preserved
* Prevents duplicate cancellations

---

# 🔐 Key Concepts Implemented

* Object-Oriented Programming (OOP)
* JDBC connectivity
* PreparedStatement (SQL injection prevention)
* Transaction management (commit & rollback)
* PostgreSQL integration
* Console-based user interface

---

# 📈 Future Improvements

* Add login system
* Password encryption
* GUI version (JavaFX / Swing)
* Web version using Spring Boot
* REST API integration

---

# 👨‍💻 Author

Developed as a learning project to demonstrate Java backend development using PostgreSQL and JDBC.

---

