# Online Banking System (Backend)

This is a **Spring Boot backend project** for an Online Banking System.  
It provides REST APIs for managing customers, accounts, fund transfers, and transaction history.  
The APIs were tested using **Postman**.

---

## Features
- **Customer Management**
  - Customer registration with email notification
  - View pending, accepted, and closing customers
  - Accept or reject customer requests
- **Admin Management**
  - Admin login (basic, to be enhanced with JWT Security)
- **Banking Operations**
  - View balance
  - Transfer money between accounts
  - Generate transaction history
- **Error Handling**
  - Global exception handling with `@RestControllerAdvice`

---

## Tech Stack
- **Spring Boot**
- **Spring Data JPA**
- **MySQL Database**
- **Lombok**
- **Java Mail Sender (for email notifications)**
- **Postman (API Testing)**

---

## Project Structure
- `controller/` → REST Controllers
- `service/` → Business logic
- `dao/` → Database access layer
- `repository/` → Spring Data JPA Repositories
- `entity/` → JPA Entities
- `exception/` → Custom exceptions + global handler
- `config/` → Email configuration

---

## Future Enhancements
- Add **Spring Security + JWT Authentication** for secure login
- Role-based access (Admin vs Customer)
- Add frontend (React) for complete application

---

## How to Run
1. Clone the repository to your local machine and navigate into the project folder
2. Update application.properties with your MySQL credentials and mail configuration.
3. Run the Spring Boot application.
4. Test APIs with Postman.

---

## Note
This project currently focuses on the backend only.
Frontend integration is planned as a future improvement.
