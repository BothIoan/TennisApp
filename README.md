# TennisApp

## Description
   An application used by tennis club that organizes matches. Every match is between 2 players. A match is played in ‘best of 5’ format (first player that gets 3 sets, wins). This means that each match is composed of a maximum of 5 sets. For each set, the first player to reach minimum 6 games by a margin of 2. 

## Layered architecture
   The main ideea for this project was to get used with loose coupling by having a 3 layered architecture. Thus, I took it to the extreme, and the three layers comunicate through some intermediate classes, sending and recieving only Integers (or arrays of Integers) and Strings (or arrays of Strings).
 * DB-App communication is realised with the help of Hibernate.
 * The GUI is made using JavaFX
 
## Functionalities
   * Two types of users
   * Crud on multiple elements of the app
   * Implemented in the bussines layer a system that keeps track of the score by the rules of tennis. This system is tested to some extent through unit tests. It won't allow scores that don't follow the rules, to be loaded (from the DB, or from an update forced by the Admin user).
