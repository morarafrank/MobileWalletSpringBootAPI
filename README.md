# MobileWalletSpringBootAPI Solution For Compulynx Interview.

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://shields.io)  
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

## About Project

This Spring Boot project provides a REST API for managing customers, accounts, and transactions in a banking system. The available endpoints facilitate core operations such as retrieving customer and account information, managing transactions, and transferring money.

Key features:
- **Customer API**:
  - `GET /api/v1/customers/` - Retrieve all customers.
  - `POST /api/v1/customers/login` - Customer login functionality.
- **Account API**:
  - `GET /api/v1/accounts/` - Retrieve details of all accounts.
  - `POST /api/v1/accounts/balance` - Check the balance of an account.
- **Transaction API**:
  - `GET /api/v1/transactions/` - Retrieve all transaction records.
  - `POST /api/v1/transactions/last-100-transactions` - Retrieve the last 100 transactions.
  - `POST /api/v1/transactions/send-money` - Transfer money between accounts.

This is an interview solution for the **Compulynx** interview..

---

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Technologies Used](#technologies-used)
- [License](#license)

---

## Prerequisites

- Java 17+
- Maven 3.8+
- A running database instance (e.g., MySQL or H2)

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/username/project-name.git
   cd project-name
   ```

2. Update the `application.properties` file with your database configuration.

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## Usage

To test the APIs, you can use tools like [Postman](https://www.postman.com/) or [cURL](https://curl.se/). The application will be running on `http://localhost:8080`.

Example request for `GET /customers`:
```bash
curl -X GET http://localhost:8080/customers
```

---

## API Endpoints

### Customer API
- **GET /customers** - Retrieve all customer details.
- **POST /customers/login** - Customer login functionality.

### Account API
- **GET /accounts** - Retrieve details of all accounts.
- **POST /accounts/balance** - Check the balance of an account.

### Transaction API
- **GET /transactions** - Retrieve all transaction records.
- **POST /transactions/last-100** - Retrieve the last 100 transactions.
- **POST /transactions/send-money** - Transfer money between accounts.

---

## Technologies Used

- **Spring Boot** - Backend framework.
- **Spring Data JPA** - Database interaction.
- **Hibernate** - ORM for persistence.
- **H2/MySQL** - Database (H2 for testing, MySQL for production).
- **Maven** - Dependency management.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

Contributions are welcome! Feel free to enhance the README.md or any part of the project to improve its functionality or documentation..
