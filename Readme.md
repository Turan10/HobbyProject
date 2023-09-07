# FindAFriend

## Business Idea

FindAFriend is a platform that connects people from all over the country, allowing them to share their passions, and connect
over shared interests,

### Key Features:

- **User Profiles**: Users can create profiles to showcase their hobbies and interests.
- **Address Book**: Save your address and phone number, making it easier for friends and other users to find you.
- **User Profiles** Users can find each other based on their hobbies and interests.
- **Hobbies**: Users can add multiple hobbies to their profile, allowing them to connect with people upon the same interests on
  our platform.

## Domain Model
FindAFriend is build around the focus of connecting people with similar interests. The domain model below shows the key entities and relationships in our system.
The person entity is the core of our system, and is connected to other entities such as hobbies, phone, and address. 
We have created a simple and scalable domain model that can be easily extended to support additional features in the future.

![Domain.png](src%2Fmain%2Fresources%2FDomain.png)![img.png](Domain.png)

## EER Diagram



## Work Process

For the work process of this project we had some main application to manage and help us reach our goal. We used Github for repository management, and Discord for communication and work assignments.
We agreed upon working in the school together from 10 am until 2 pm, and then work from home afterwards. This allowed us to work together and help each other with problems, and then work individually on our own tasks afterwards.

For the project burden we managed to help each other throughout the project, but we all where assigned specific user stories: 

- **[Turan]**: Created a method for us to populate the database by using a .csv file. This allowed us to quickly populate the database with data, and test our queries. 
Turan also worked on the user stories  US-3, US-5, US-8, and US-9.
- 
- **[Sitthichai]**: Created method to retrieve all the information about a person. Also worked on relationships between the different entities and helped refactoring the DAO classes 

- **[Vivek]**: Created the, populate Hobby method which gets all the hobbies from the provided .csv file, the populate type which gets all the types from the hobbies so we can create hobbies using them. Tested the methods in DAOs and entities concerning Hobby, Type, (Populate), and person.

- **[Metin]**:

---

## User Stories

- **[US-1]**: As a user I want to get all the information about a person
- **[US-2]**: As a user I want to get all phone numbers from a given person
- **[US-3]**: As a user I want to get all persons with a given hobby
- **[US-4]**: As a user I want to get the number of people with a given hobby
- **[US-5]**: As a user I want to get a list all hobbies + a count of how many are interested in each hobby
- **[US-6]**: As a user I want to get all persons living in a given city (i.e. 2800 Lyngby)
- **[US-7]**: As a user I want to get a list of all postcodes and city names in Denmark
- **[US-8]**: As a user I want to get all the information about a person (address, hobbies etc.) given a phone number
- **[US-9]**: As a user I want to be able to do CRUD operations on all JPA entities unless it wouldn't make sense for a given entity.

## Project requirements
**[R-1]**:
- JPA
- JPQL
- Maven
- JDK 17^
- JUnit 5
- Docker
- PostgresSQL
- pgAdmin
- Lombok
- **[R-2]**: The project must contain a meaningful EER-diagram (use pgAdmin to create the diagram)
- **[R-3]**: The project must be documented in a README.md file(*)
-  **[R-4]**: The project must contain meaningful unit tests. (70 - 80 % of the methods must be tested (DAO, Entity ...))
-  **[R-5]**: JPA annotations must be used for mapping domain classes
-  **[R-6]**: JPQL must be used for all CRUD operations
-  **[R-7]**: JPA annotations must include minimum once an @Enumerated, @PrePersist and @PreUpdate.
-  **[R-8]**: The phone number as to follow the Danish rules for phone numbers (e.g. +45 12345678)
-  **[R-9]**: If you use a Date property in an entity, it must be a java.time.LocalDate, java.time.Date or java.time.LocalDateTime and not just a String
-  **[R-10 ]**: DAO classes should follow the Singleton Pattern
