Here's a professional and comprehensive `README.md` for your EcoAware Complaint Tracker project:

---

# 🛠️ EcoAware Complaint Tracker

A production-ready, secure complaint tracking system built using **Spring Boot**, **Java 21**, and **PostgreSQL**, designed to empower students and staff to raise and track environmental or facility-related issues within their campus or organization.

---

## 📌 Features

* 🔐 **User Registration & JWT-based Authentication**
* 📝 **Raise, View, Update, Delete Complaints**
* 🧾 **Image Upload Support** (e.g., for proof/evidence)
* 👥 **Role-based Access Control** (User/Admin)
* 🎯 **Admin Panel** for managing complaints & statuses
* 🗃️ **Complaint Categories** like Waste, Water, etc.
* 🕒 **Timestamps** for every complaint
* ✅ **Secure Password Storage** using BCrypt

---

## 🧱 Entity Relationship Diagram (ERD)

```text
+---------+       +------------+        +-------------+
|  User   |<----->|  Complaint |<------>|   Category  |
+---------+       +------------+        +-------------+
| id      |       | id         |        | id          |
| name    |       | title      |        | name        |
| email   |       | description|        +-------------+
| password|       | imagePath  |
| role    |       | status     |
+---------+       | timestamp  |
                  | user_id FK |
                  | category_id FK |
                  +-------------+
```

---

## ⚙️ Tech Stack

| Layer       | Technology               |
| ----------- | ------------------------ |
| Backend     | Spring Boot              |
| Language    | Java 21                  |
| Security    | Spring Security + JWT    |
| Database    | PostgreSQL               |
| ORM         | Spring Data JPA          |
| File Upload | upload link for just now |
| Build Tool  | Maven                    |

---

## 🌐 API Endpoints

### 🔓 Auth

| Endpoint         | Method | Description       |
| ---------------- | ------ | ----------------- |
| `/auth/register` | POST   | Register new user |
| `/auth/login`    | POST   | Authenticate user |

### 👤 User Complaint

| Endpoint           | Method | Description                       |
| ------------------ | ------ | --------------------------------- |
| `/complaints`      | POST   | Create a new complaint            |
| `/complaints`      | GET    | List complaints of logged-in user |
| `/complaints/{id}` | GET    | Get specific complaint details    |
| `/complaints/{id}` | PUT    | Update complaint (if owner)       |
| `/complaints/{id}` | DELETE | Delete complaint (if owner)       |

### 🛠 Admin

| Endpoint                        | Method | Description             |
| ------------------------------- | ------ | ----------------------- |
| `/admin/complaints`             | GET    | Get all complaints      |
| `/admin/complaints/{id}/status` | PUT    | Update complaint status |

### 🗂 Categories

| Endpoint      | Method | Description         |
| ------------- | ------ | ------------------- |
| `/categories` | GET    | List all categories |

---

## 🔐 Security Configuration

* `/auth/**` is publicly accessible
* `/complaints/**` requires `USER` or `ADMIN`
* `/admin/**` requires `ADMIN` role
* JWT used for stateless authentication
* BCryptPasswordEncoder used for password hashing

---

## 🧪 How to Run the Project

### 🔧 Prerequisites

* Java 21
* PostgreSQL (running on default port)
* Maven

### 🛠️ Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/ecoaware-backend.git
   cd ecoaware-backend
   ```

2. **Create PostgreSQL database**

   ```sql
   CREATE DATABASE ecoaware;
   ```

3. **Configure application.properties**

   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/ecoaware
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Run the app**

   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🧑‍💻 Sample JSON Payloads

### 🔸 Register User

```json
{
  "name": "alice",
  "email": "alice@example.com",
  "password": "alice@123",
  "role": "USER"
}
```

### 🔸 Login

```json
{
  "email": "alice@example.com",
  "password": "alice@123"
}
```

### 🔸 Create Complaint

```json
{
  "title": "Leaking tap in hostel",
  "description": "Tap in ground floor is leaking continuously.",
  "imagePath": "uploads/tap.jpg",
  "categoryId": 2
}
```

### 🔸 Admin Status Update

```json
{
  "status": "ACKNOWLEDGED"
}
```

---

## 🚀 Deployment Ready

This project is fully compatible with production deployment:

* ✅ Secure authentication
* ✅ CORS ready
* ✅ DTO-based request handling
* ✅ Proper exception management

---

## 🛠️ Planned Enhancements

* ✅ Frontend (React + Material UI) – In progress
* ⏳ Pagination & sorting
* ⏳ Swagger/OpenAPI docs
* ⏳ Admin user management
* ⏳ Advanced image storage (e.g., AWS S3)

---

this is ai generated readme file for ecoaware complaint tracker project