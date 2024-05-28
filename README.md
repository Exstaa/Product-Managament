# Product Register Project

This project is a desktop application built with Java, JavaFX, and JDBC for managing a register of products. The application provides a user-friendly interface to add, update, delete, and view products stored in a database.

## Features

- **Add Product**: Allows users to add new products to the register.
- **Update Product**: Provides functionality to update details of existing products.
- **Delete Product**: Enables users to delete products from the register.
- **View Products**: Displays a list of all products in the register.

## Technologies Used

- **Java**: The core programming language used for the application logic.
- **JavaFX**: Used for building the graphical user interface (GUI).
- **JDBC**: Employed for database connectivity and operations.

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher.
- **JavaFX SDK**: Ensure JavaFX is included if using JDK 11 or higher.
- **Database**: A relational database such as MySQL, PostgreSQL, or SQLite. Ensure the appropriate JDBC driver is included in the project.

## Setup Instructions

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/Exsta/Product-Managament.git
    cd product-register
    ```

2. **Set Up the Database**:
    - Create a database named `product_db`.
    - Execute the SQL script `schema.sql` provided in the `sql` directory to create the necessary tables.
    - Update the database connection details in the `DBUtil.java` file.

3. **Build and Run the Application**:
    - Open the project in your favorite IDE (such as IntelliJ IDEA, Eclipse, or NetBeans).
    - Ensure JavaFX libraries are correctly set up in the project.
    - Run the `Main.java` class to start the application.

## Usage

1. **Add Product**:
    - Click on the "Add Product" button.
    - Fill in the product details in the provided form.
    - Click "Save" to add the product to the database.

2. **Update Product**:
    - Select a product from the list.
    - Click on the "Edit" button.
    - Modify the product details in the form.
    - Click "Save" to update the product information in the database.

3. **Delete Product**:
    - Select a product from the list.
    - Click on the "Delete" button.
    - Confirm the deletion in the dialog that appears.

4. **View Products**:
    - The main screen displays a list of all products.
    - Use the search functionality to filter products by name or category.
