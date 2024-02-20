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

## Technical Aspects

Developed with: Java, JavaFX, SQLite
Design Pattern: MVC (Model-View-Controller)
Singleton Usage: Potentially used for centralized access to global resources or configuration (details unavailable due to confidentiality).
OOP Principles: Embraces object-oriented design principles for modularity, encapsulation, and inheritance (specific principles not disclosed).

## Testing
The application is tested using JUnit. The test cases cover various scenarios to ensure the robustness and reliability of the implemented functionalities.

## Disclaimer
This project is a simplified version for educational purposes and does not aim to replicate any real-world system or service.

## How to Run
1. Ensure you have Java and JavaFX installed on your machine.
2. Clone the repository.
3. Compile and run the `Main.java` file.

Feel free to explore and enhance the functionalities in future updates!

Screenshots:
<img width="620" alt="Screenshot 2567-02-20 at 13 22 37" src="https://github.com/cnkamorn/Social-Media-Management-Application/assets/92679212/ad4d6e10-a2ff-44b9-b19b-8eb9b827c829">
<img width="572" alt="Screenshot 2567-02-20 at 13 22 58" src="https://github.com/cnkamorn/Social-Media-Management-Application/assets/92679212/86a6218f-7aee-4e37-a59b-d2282fc9dad8">
<img width="611" alt="Screenshot 2567-02-20 at 13 23 12" src="https://github.com/cnkamorn/Social-Media-Management-Application/assets/92679212/b9d1c818-53bd-4be6-a863-cf1acd468f07">
<img width="838" alt="Screenshot 2567-02-20 at 13 24 21" src="https://github.com/cnkamorn/Social-Media-Management-Application/assets/92679212/8db8576c-9aab-4961-b46b-8483982ef0a0">
<img width="830" alt="Screenshot 2567-02-20 at 13 24 25" src="https://github.com/cnkamorn/Social-Media-Management-Application/assets/92679212/e12e58e5-5628-4836-9e43-88446bc1695a">
<img width="826" alt="Screenshot 2567-02-20 at 13 26 26" src="https://github.com/cnkamorn/Social-Media-Management-Application/assets/92679212/6fd1334c-47d3-43b4-8bc1-c3ac5a75b7a7">
<img width="821" alt="Screenshot 2567-02-20 at 13 26 33" src="https://github.com/cnkamorn/Social-Media-Management-Application/assets/92679212/4ef7a6e2-4e3d-4194-9eb0-fcd376587986">
<img width="833" alt="Screenshot 2567-02-20 at 13 26 46" src="https://github.com/cnkamorn/Social-Media-Management-Application/assets/92679212/a8c5d589-6e1d-4e54-9a0a-ca88e89dd611">
<img width="619" alt="Screenshot 2567-02-20 at 13 27 05" src="https://github.com/cnkamorn/Social-Media-Management-Application/assets/92679212/69d5e274-7049-401a-9ca9-b92d5e523c7b">

