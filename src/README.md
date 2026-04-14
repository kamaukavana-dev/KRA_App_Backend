# 🚀 KRA NIL Returns Automation API

A backend system built with Spring Boot that integrates with the Kenya Revenue Authority (KRA) API to automate NIL return filing for individual taxpayers.

---

## 📌 Overview

This project provides a RESTful backend service that:

- Authenticates with the KRA API using secure credentials
- Manages access tokens efficiently (caching + expiration handling)
- Submits NIL return filings for individual taxpayers
- Handles structured request/response communication with external services

It demonstrates real-world backend engineering concepts such as API integration, authentication flows, and system design.

---

## 🧠 How It Works (High-Level Flow)

Client Request
↓
Controller Layer
↓
FileNillReturnsService
↓
KraAuthService (Token Management)
↓
KRA API (External System)
↓
Response → Back to Client


---

## 🔐 Authentication Flow

- The system uses Consumer Key & Secret from `application.properties`
- These are encoded using Base64
- A request is sent to KRA to generate an access token

The token is:
- Cached in memory
- Reused until expiration

All API requests use:
Authorization: Bearer <access_token>


---

## 🧩 Features

- ✅ Secure credential handling via configuration
- ✅ OAuth-style token generation and reuse
- ✅ Token expiration management
- ✅ REST API integration with external service (KRA)
- ✅ Structured JSON request building
- ✅ Error handling for API communication

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Web (RestTemplate)
- OkHttp (for authentication requests)
- JSON (org.json)
- Maven

---

## ⚙️ Configuration

Add your credentials in:

```properties
# application.properties

kra.consumer.key=YOUR_CONSUMER_KEY
kra.consumer.secret=YOUR_CONSUMER_SECRET