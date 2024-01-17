# Social-Media-Management-Application

## Overview
Social-Media-Management-Application is a JavaFX GUI application built on the Model-View-Controller (MVC) architecture. This application allows multiple users to create profiles, log in, and manage social media posts. It provides basic functionalities for all users and advanced functionalities for VIP users.

## Basic Functionalities
1. **User Management:**
   - Each user can create a profile with a unique username, password, first name, and last name.
   - Users can log in using their credentials.
   - After login, users are greeted with a dashboard displaying a welcome message containing their full name.

2. **Social Media Post Management:**
   - Users can edit their profiles, modifying first name, last name, username, and password.
   - Users can add social media posts to their collection with details like ID, content, author, #likes, #shares, and date-time.
   - Validation ensures correct post format (e.g., unique ID, numeric #likes).
   - Users can retrieve a post based on its ID.
   - Users can remove a post from their collection.
   - Users can retrieve the top N posts with the most likes, specifying the value of N.
   - Users can export a post to a CSV file, choosing a folder and specifying the filename.

## VIP Functionalities
1. **Upgrade to VIP:**
   - Non-VIP users have the option to upgrade to VIP.

2. **Advanced Functionalities for VIP Users:**
   - **Data Visualization:**
      - VIP users can draw a pie chart illustrating the distribution of #shares.
      - Posts are categorized into three groups based on #shares: 0-99, 100-999, and 1000+.

   - **Bulk Import:**
      - VIP users can import multiple social media posts from a CSV file (e.g., posts.csv).
      - Exceptions during import are captured, handling cases where the CSV file format is incorrect.

## Database
The application utilizes SQLite for data storage.

## Testing
The application is tested using JUnit. The test cases cover various scenarios to ensure the robustness and reliability of the implemented functionalities.

## Disclaimer
This project is a simplified version for educational purposes and does not aim to replicate any real-world system or service.

## How to Run
1. Ensure you have Java and JavaFX installed on your machine.
2. Clone the repository.
3. Compile and run the `Main.java` file.

Feel free to explore and enhance the functionalities in future updates!
