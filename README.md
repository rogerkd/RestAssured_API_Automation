# 🚀 RestAssured API Automation Framework

An end-to-end **API Test Automation Framework** built with **Java + RestAssured + TestNG + Extent Reports**, designed for scalable, maintainable, and easily extendable API testing.  

---

## 📌 Features

- ✅ **Java + RestAssured** for API testing  
- ✅ **TestNG** for test execution & parallel runs  
- ✅ **Extent Reports** for rich HTML reporting  
- ✅ **Config-driven** for flexibility (Base URL, tokens, etc.)  
- ✅ **Token Management Utility** for OAuth2 authentication  
- ✅ **Positive & Negative Test Coverage**  
- ✅ **Reusable Request Specifications**  
- ✅ **Easy to integrate with CI/CD pipelines**  

---

## 📂 Project Structure

RestAssuredAPIAutomation
│── src/main/java
│ ├── com.yourorg.base # Base classes, listeners
│ ├── com.yourorg.utils # Token manager, config reader
│── src/test/java
│ ├── com.yourorg.tests # All API test classes
│── testng.xml # TestNG suite config
│── pom.xml # Maven dependencies
│── config.properties # Base URL & API paths
│── ExtentReport.html # Generated report

---

## 🔑 Tech Stack

- **Language:** Java 21  
- **Test Framework:** TestNG  
- **API Testing:** RestAssured  
- **Reporting:** Extent Reports  
- **Build Tool:** Maven  
- **Version Control:** Git + GitHub  

---

## 🧪 Test Cases Covered

**Authentication**
- ✔ Get access token (Positive)
- ✔ Get access token (Negative - invalid credentials)

**Customer Management**
- ✔ Add Customer (Positive)
- ✔ Add Customer (Negative - missing required fields)
- ✔ View Customer by ID (Positive/Negative)
- ✔ View Customer by Mobile (Positive/Negative)
- ✔ View Customer List (Positive)
- ✔ Delete Customer (Positive/Negative)

---

## ⚡ How to Run

1️⃣ **Clone the repo**  
```bash
git clone https://github.com/yourusername/RestAssured_API_Automation.git
cd RestAssured_API_Automation

2️⃣ Install dependencies
mvn clean install

3️⃣ Run tests
mvn test

4️⃣ View report
Open ExtentReport.html in your browser after execution.

📸 Sample Report (Extent)

🤝 Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you’d like to change.

📜 License
This project is licensed under the MIT License - see the LICENSE file for details.

💡 Pro Tip: This framework is production-ready, easy to integrate with Jenkins, and can be extended for multiple environments and microservices.







Ask ChatGPT
