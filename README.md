# ⚽ ACE X TWO (1X2) Application 

**ACE X TWO** is a RESTful API designed to manage sports matches and their associated betting odds. It provides a comprehensive set of features for creating, updating, retrieving, and deleting match data while enforcing constraints on match uniqueness and valid odd values.

---

## 🚀 Key Features

### Match Management
- **Create, Update, Retrieve, and Delete Matches**: Full CRUD functionality for managing matches.
- **Match Constraints**:
  - **Sport Types**: Supports `1` (Football) and `2` (Basketball).
  - **Unique Teams**: Teams must be unique within a match.
  - **Valid Match Date**: Match dates cannot be in the past.
  - **Unique Match Definition**: A match is uniquely identified by `Sport`, `Match Date`, and either `Team A` or `Team B`.

### Match Odds Management
- **Specifiers**: Supports `1`, `X`, and `2` as valid specifiers.
- **Unique Odds**: Ensures unique odds per match.
- **Positive Decimal Values**: Odd rates must be positive decimal numbers.

---

## 📌 Technology Stack

- **JDK 21**: The latest Java Development Kit for modern Java development.
- **Spring Boot 3.4.2**: A powerful framework for building Java-based web applications.
- **Apache Tomcat 10.1.13 (Embedded)**: A lightweight and efficient servlet container.
- **PostgreSQL 17**: A reliable and feature-rich open-source relational database.

---

## 🛠️ Prerequisites & Setup

### 1️⃣ Install Java 21
- **Version**: JDK 21
- **Download**: [Oracle JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- **Installation Guide**: [How to Install Java](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html)

### 2️⃣ Install IntelliJ IDEA
- **Version**: Community Edition 2024.1.1
- **Download**: [IntelliJ IDEA](https://www.jetbrains.com/idea/download/other.html)

### 3️⃣ Install PostgreSQL 17
- **Version**: PostgreSQL 17.2
- **Download**: [PostgreSQL Official](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
- **Installation Guide**: [W3Schools Guide](https://www.w3schools.com/postgresql/postgresql_install.php)

---

## 📂 Database Setup

1. **Launch pgAdmin 4 v8**:
   - Open pgAdmin 4 v8 from your applications or desktop.

2. **Connect to PostgreSQL Server**:
   - In the **Browser** panel, expand **Servers**.
   - Right-click on **PostgreSQL 17** and select **Connect Server**.
   - Enter the password you set during PostgreSQL installation.

3. **Create a New Database**:
   - Right-click on **Databases** in the **Browser** panel.
   - Select **Create > Database**.
   - In the **Create Database** dialog:
     - **Database**: `suite_of_sports_db`
     - Click **Save** to create the database.

4. **Execute the SQL Script**:
   - Expand the newly created `suite_of_sports_db` database in the **Browser** panel.
   - Right-click on `suite_of_sports_db` and select **Query Tool**.
   - Open the SQL script located at `...\acextwo\acextwo.sql`.
   - Execute the script to set up the **Ace X Two (1X2)** schema in your PostgreSQL database.

## 🏃 Running the Application

Follow these steps to run the ACE X TWO application:

1. **Open IntelliJ IDEA**:
   - Launch IntelliJ IDEA and import the project.

2. **Set Up PostgreSQL**:
   - Ensure the PostgreSQL database is running and properly configured.
   - Verify that the database schema and tables are created using the provided SQL script.

3. **Configure `application.properties`**:
   - Update the `application.properties` file with your PostgreSQL connection details:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:<PORT>/suite_of_sports_db
     spring.datasource.username=postgres
     spring.datasource.password=<YOUR_PASSWORD>
     ```

4. **Run the Application**:
   - Locate and run the `AcextwoApplication.java` file to start the Spring Boot application.

Once the application is running, you can access the API endpoints and interact with the system.

---

## 🔍 Interactive API Documentation with Swagger UI

The application provides **Swagger UI** for interactive API documentation and testing. You can access it by navigating to the following URL in your browser: `http://localhost:<PORT>/swagger-ui/index.html`
- Replace `<PORT>` with the port number your application is running on (e.g., `8080`).
- Swagger UI allows you to:
  - Explore all available API endpoints.
  - Test endpoints with real requests and responses.
  - View detailed documentation for each endpoint, including request/response formats and constraints.

## 🔍 Alternative API Interaction with Postman

For those who prefer using **Postman** for API interaction, an exported Postman collection is provided. This collection contains all the necessary API endpoints and configurations for testing the **Ace X Two (1X2)** application.

### Steps to Use the Postman Collection:

1. **Locate the Collection**:
   - The Postman collection file is located at:
     ```
     ...\acextwo\AceXTwo.postman_collection.json
     ```

2. **Import the Collection into Postman**:
   - Open **Postman**.
   - Click on the **Import** button in the top-left corner.
   - Select the `AceXTwo.postman_collection.json` file from the specified location.
   - Click **Import** to add the collection to your Postman workspace.

3. **Configure the Environment**:
   - Ensure your Spring Boot application is running.
   - Set the base URL in Postman to match your application's server address (e.g., `http://localhost:8080`).

4. **Interact with the API**:
   - Use the imported collection to:
     - Test all available API endpoints.
     - Send requests and view responses.
     - Validate the functionality of the **Ace X Two (1X2)** application.

This Postman collection provides a convenient and powerful way to explore and test the API without using Swagger UI.

