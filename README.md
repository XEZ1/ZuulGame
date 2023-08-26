# World of Zuul Adventure Game

## Introduction

Welcome to the World of Zuul Adventure Game! In this project, I have developed an interactive text-based adventure game using Java. The game is designed to provide players with an immersive experience as they navigate through different rooms, interact with items, characters, and solve challenges to achieve the ultimate goal.

# Motivation

The purpose behind creating this game was to explore the concepts of object-oriented programming in Java. I wanted to challenge myself by designing an engaging and dynamic game environment while applying the principles of good software design and code organisation.

# Getting Started

To run the game on your local machine, follow these steps:

1. Clone this repository to your computer using the command: 
```
git clone https://github.com/XEZ1/ZullGame.git.
```
2. Execute the following code:
```
Javac Main.java
Java Main
```

# Game Features

**Interactive Rooms**: Players can navigate between different rooms, each with its own description and exits.

**Item Interaction**: Players can pick up items, carry them, and use them to overcome challenges.

**Character Interaction**: Meet characters within the game, engage in conversations, and complete quests.

**Winning Condition**: The game has a clear winning condition that players achieve by completing specific tasks.

**Additional Commands**: I've added several extra commands beyond the base game functionality for enhanced gameplay.

# Design and Implementation

I carefully designed the game's structure using object-oriented principles. The main classes include:

- **Game**: Manages the game loop, player actions, and interactions.
- **Room**: Represents each room in the game, containing its description, exits, items, and characters.
- **Item**: Defines the properties of items that players can interact with.
- **Character**: Models characters with whom players can engage in dialogues and quests.
- **CommandParser**: Parses player input to execute commands and navigate the game world.

# Challenges and Extensions

While implementing the core game functionality, I also took on additional challenges:

- **Three-Word Commands**: Extended the parser to recognise three-word commands, enhancing user interaction.

- **Random Transporter Room**: Created a magic transporter room that randomly teleports the player to different locations.

---

Note: The code and implementation in this project may not adhere to best practices, and it's recommended to approach it with an understanding of its context as a learning exercise from my early programming journey.
This project was developed in year one of Computer Science degree at King's College London