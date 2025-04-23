This project is still in development and remains unfinished, but it will continue to improve over time. It is a **fully functional banking system** that enables users to **manage finances, conduct transactions, submit complaints, calculate and apply for loans.**  
**Technologies used in this project:**
- Spring Boot  
- Spring Data  
- Spring Security  
- Spring MVC  
- JPA & Hibernate  
- MySQL  
- SQL  
- HTML & CSS  
- Thymeleaf  
- Bootstrap  
- Swagger  

**Project Development Scope**  
The project encompasses the **full development cycle**, including:  
- **Backend:** A server-side system has been built to handle requests, ensure functionality, and integrate with databases. The project includes **database operations** to store information about transactions, users, complaints, and other aspects of banking activity.  
- **Frontend:** A user interface has been designed with a focus on **usability and intuitiveness**.  

**API Endpoints (Postman Testing Available)**  
Some HTTP requests in this project can be tested using **Postman**. *(Note: Not all HTTP requests from the web application are included here.)*  

**MyControllerPostman:**  
Base URL: `http://localhost:8080/api/`  
- `getUserBankAdmin` – Finds all users and their transactions: (GET) `http://localhost:8080/api/getUserBankAdmin`  
- `getUserBankIdAdmin` – Finds a user by ID: (GET) `http://localhost:8080/api/getBankIdAdmin/{id}`  
- `getMyBalanceCard` – Returns card balance information by user ID: (GET) `http://localhost:8080/api/getMyBalance/{id}`  
- `transferMoney` – Performs a transaction: (PUT) `http://localhost:8080/api/transferMoney/{senderId}/{receiverBankCard}/{amount}/{commentTransaction}` *(Not functional in Postman, only in the web app)*  
- `depositMoney` – Simulates an ATM deposit to the specified card: (PUT) `http://localhost:8080/api/depositMoney/{id}/{amount}`  
- `withdrawMoney` – Simulates an ATM withdrawal from the specified card: (PUT) `http://localhost:8080/api/withdrawMoney/{id}/{amount}`  
- `getTransactionHistory` – Retrieves the transaction history by user ID: (GET) `http://localhost:8080/api/getTransactionHistory/{id}`  

**MyControllerBank:**  
Base URL: `http://localhost:8080/`  
- `welcome` – Opens the main page with options to **register or log in**, as well as explore banking services: `http://localhost:8080/welcome`  
- `login` – Opens the login page *(redirects to `/myProfile`)*: `http://localhost:8080/login`  
- `register` – Opens the registration page: `http://localhost:8080/register`  
- `myProfile` – User’s personal dashboard.  
- `transferInfo` – Conducts a transaction to a specified card for the chosen amount.  
- `calculateCredit` – Loan calculator.  
- `getCreditCalculateMoney` – Performs the loan application function.  
- `supportMessage` – Submits a complaint.  
- `logout` – exits the personal cabinet.

**Future Enhancements**  
Planned improvements for the project include:  
- Adding the **ability to transfer funds to a savings account.**  
- Implementing a **cashback feature.**  
- Creating a **real-time currency exchange rate display.**  
- Improving **error handling**.  
- Integrating **CI/CD testing into the project.**

**Find Out More**
You can visit my **LinkedIn** profile to see how the project looks and explore additional details.
- www.linkedin.com/in/teimur-khalilov-3b3038333
