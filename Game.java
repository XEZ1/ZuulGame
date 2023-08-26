import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<String> listOfItems;
    private ArrayList<Item> listOfItemsWePicked;
    private Character character; 
    private String previousDirection;
    private HashMap<String, Room> room; 
    private Random random; 
    private ArrayList<OtherCharacters> characters;
    private ArrayList<Room> roomsArray;
    private HashMap<Room, String[]> exits;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        listOfItemsWePicked = new ArrayList<>(); 
        character = new Character(); 
        room = new HashMap<>();
        characters = new ArrayList<>();
        roomsArray = new ArrayList<>();
        random = new Random();
        exits = new HashMap<>();
        createRoomsAndItems();
    }

    /**
     * Create all the rooms and link their exits together.
     * Create all the items and put them in a different rooms.
     * Also creates some useful array lists and hashmaps to use in the future to modify the game.
     */
    private void createRoomsAndItems()
    {
        Room garden, magicTransporterRoom, dressingRoom, library, roomOfEmptiness, roomOfEvil, armory, royalChamber, theater, treasury;
      
        // create the rooms
        garden = new Room(" in a castle garden");
        magicTransporterRoom = new Room(" in a magic transporter room");
        dressingRoom = new Room(" in a dressing room");
        library = new Room(" in the place of knowledge, in the library");
        roomOfEmptiness = new Room(" in a room of emptiness. It's better not to be here for a long time, otherwise loneliness will eat you up!");
        roomOfEvil = new Room(" in a room where the main monster is");
        armory = new Room(" in an armory room");
        royalChamber = new Room(" in a royal chamber");
        theater = new Room(" in a theater");
        treasury = new Room(" in a treasury room. There are a lot of money daaaaaaaamn");
        
        // initialise room exits
        garden.setExit("north", dressingRoom);
        garden.setExit("south", magicTransporterRoom);
        magicTransporterRoom.setExit("north", garden);
        dressingRoom.setExit("south", garden);
        dressingRoom.setExit("north_west", library);
        library.setExit("south_east", dressingRoom);
        dressingRoom.setExit("north_east", roomOfEmptiness);
        roomOfEmptiness.setExit("north_west", armory);
        roomOfEmptiness.setExit("north_east", roomOfEvil);
        roomOfEmptiness.setExit("south_west", dressingRoom);
        armory.setExit("north_east", theater);
        armory.setExit("south_east", roomOfEmptiness);
        theater.setExit("south_west", armory);
        theater.setExit("north_east", treasury);
        treasury.setExit("south_west", theater);
        treasury.setExit("south", roomOfEvil);
        roomOfEvil.setExit("west", royalChamber);
        royalChamber.setExit("east", roomOfEvil);
        roomOfEvil.setExit("south_west", roomOfEmptiness);
        roomOfEvil.setExit("north", treasury);
        
        // start game outside
        currentRoom = garden;
        
        //create the items
        Item sword = new Knife("magical_sword", 2, 10, 1);
        Item book = new Book("book_of_defence", 1, 2, 10);
        Item uselessBook = new UselessBook("usless_book_of_french", 6, 0, 0);
        Item coin = new Coin("coin", 1, 0, 0);
        
        // set the items in the rooms
        armory.setItem("magical_sword", sword);
        library.setItem("book_of_defence", book);
        library.setItem("useless_book_of_french", uselessBook);
        treasury.setItem("coin", coin);
    
        // creating an array to access elements by their String names
        room.put("royalChamber", royalChamber);
        room.put("magicTransporterRoom", magicTransporterRoom);
        room.put("dressingRoom", dressingRoom);
        room.put("library", library);
        room.put("roomOfEmptiness", roomOfEmptiness);
        room.put("armory", armory);
        room.put("theater", theater);
        room.put("treasury", treasury);
        room.put("roomOfEvil", roomOfEvil);
        room.put("royalChamber", royalChamber);
        
        // create the characters
        OtherCharacters dragon = new Dragon("dragon", 5, 4, true, roomOfEvil);
        // characters.add(dragon);  so it doesn't move
        OtherCharacters jeffery = new Teacher("jeffery", 2, 2, false, library);
        characters.add(jeffery);
        OtherCharacters michael = new Teacher("michael", 1, 2, false, treasury);
        characters.add(michael);
        
        // create an array list with all rooms in it to check the size
        roomsArray.add(garden);
        roomsArray.add(dressingRoom);
        roomsArray.add(library);
        roomsArray.add(roomOfEmptiness);
        roomsArray.add(armory);
        roomsArray.add(theater);
        roomsArray.add(roomOfEvil);
        roomsArray.add(royalChamber);
      
        // put in a hashMap rooms as keys and exits as values of the hashMap 
        exits.put(garden, new String[] {"south", "north"});
        exits.put(magicTransporterRoom, new String[] {"north"});
        exits.put(dressingRoom, new String[] {"south", "north_west", "north_east"});
        exits.put(library, new String[] {"south_east"});
        exits.put(roomOfEmptiness, new String[] {"north_west", "north_east", "south_west"});
        exits.put(armory, new String[] {"north_east", "south_east"});
        exits.put(theater, new String[] {"south_west", "north_east"});
        exits.put(treasury, new String[] {"south_west", "south"});
        exits.put(roomOfEvil, new String[] {"west", "north", "south_west"});
        exits.put(royalChamber, new String[] {"east"});
    }
    
    /**
     * Get the random room to implement the magic transporter room.
     * @return returns a random room.
     */
    private Room getTheRandomRoom() 
    {
        int i = random.nextInt(roomsArray.size());  // 0 - 7
        Room randomRoom = roomsArray.get(i);
        return randomRoom;
    }
    
    /**
     * Gets the overall weight.
     * @param command The command to be processed.
     */
    private void getTheOverallWeight(Command command)
    {
        System.out.println(character.getTheOverallWeight());
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true if the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            wantToQuit = goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("investigate")) {
            investigateTheRoom(command);
        }
        else if (commandWord.equals("pick")) {
            pickTheItem(command);
        }
        else if (commandWord.equals("list_items")) {
            character.listTheItemsWePicked();
        }
        else if (commandWord.equals("get_the_overall_weight")) {
            getTheOverallWeight(command);
        }
        else if (commandWord.equals("back")) {
            goBack(command);
        }
        else if (commandWord.equals("investigate_where_are_the_characters")) {
            investigateWhereAreTheCharacters(command);
        }
        else if (commandWord.equals("give")) {
            giveAnItemToSomeone(command);
        }
    
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message.
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the castle.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * also method checks the direction for "back" buttom.
     * @param command The command to be processed.
     * @return false if the game continues. Otherwise - true.
     */
    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        previousDirection = command.getSecondWord();        
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            moveTheCharacter(getTheRandomRoom());   // make other NPSes move
            magicRoom();                            //checks whether the room is the magic one or not
            return killTheDragon();                 // checks if the current pattern is the win pattern
        }
        return false;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param command The command to be processed.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        return true;  // signal that we want to quit
    }
    
    /**
     * Checks if there are any items in the current room.
     * @param command The command to be processed.
     */
    private void investigateTheRoom(Command command)
    {
        listOfItems = currentRoom.itemsInTheRoom();
        if (listOfItems.isEmpty()) {
            System.out.println("There are no items");
        }
        else {
            System.out.println(listOfItems);
        }
    }
    
    /**
     * Picks the specific item.
     * @param command The command to be processed.
     */
    private void pickTheItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("pick what?");
            return;
        }
        character.putItem(command.getSecondWord(), currentRoom.getItem(command.getSecondWord()));
        listOfItemsWePicked.add(currentRoom.getItem(command.getSecondWord()));
    }
    
    /**
     * Goes back to the previous room.
     * @param command The command to be processed.
     */
    private void goBack(Command command)
    {
        String direction = reversiveDirection(previousDirection);
        
        if (direction != null) {
            previousDirection = direction; 
        
            // Try to leave current room.
            Room previousRoom = currentRoom.getExit(direction);

            if (previousRoom == null) {
                System.out.println("There is no door!");
            }
            else {
                currentRoom = previousRoom;
                System.out.println(currentRoom.getLongDescription());
            }
        }
        
        else {
            System.out.println("Try another direction!");
        }
        
    }
    
    /**
     * This method gives an item you picked to any of picked characters.
     * @param command The command to be processed.
     */
    private void giveAnItemToSomeone(Command command)
    {
        if (command.hasSecondWord()) {
            if (command.hasThirdWord()) {
                String nps = command.getThirdWord();
                String itemToGive = command.getSecondWord();
                for (OtherCharacters character1 : characters) {
                    if (character1.getName().equals(nps)) { 
                        for (Item item : listOfItemsWePicked) {
                            if (item.equals(currentRoom.getItem(itemToGive))) {
                                character1.receiveAnItem(item);
                            }
                        }
                    }
                }
            } 
            else {
                System.out.println("give to whom?");
            }
        }
        else {
            System.out.println("give what?");
        }
        
    }
    
    /**
     * Another command that checks and prints out where are all the characters.
     * @param command The command to be processed.
     */
    private void investigateWhereAreTheCharacters(Command command)
    {
        for (int i = 0; i < 2; i++){ //amountOfCharacters
            System.out.println("the character " + characters.get(i).getName() + " is" + characters.get(i).getRoom().getShortDescription());
        }
    }
    
    /**
     * This method reverses the direction to make it possible
     * to go back and to implement goBack command.
     * @param prevDirection Takes previous direction as a parameter.
     * @return string that is the reversive direction.
     */
    private String reversiveDirection(String prevDirection)
    {
        if (prevDirection == null) {
            System.out.println("You can not go back here.");
            return null;
        }
        else {
            if(prevDirection.equals("north")) {
                String reversiveDirection = "south";
                return reversiveDirection;
            }
            else if(prevDirection.equals("south")) {
                String reversiveDirection = "north";
                return reversiveDirection;
            }
            else if(prevDirection.equals("west")) {
                String reversiveDirection = "east";
                return reversiveDirection;
            }
            else if(prevDirection.equals("east")) {
                String reversiveDirection = "west";
                return reversiveDirection;
            }
            else if(prevDirection.equals("north_west")) {
                String reversiveDirection = "south_east";
                return reversiveDirection;
            }
            else if(prevDirection.equals("south_east")) {
                String reversiveDirection = "north_west";
                return reversiveDirection;
            }
            else if(prevDirection.equals("north_east")) {
                String reversiveDirection = "south_west";
                return reversiveDirection;
            }
            else if(prevDirection.equals("south_west")) {
                String reversiveDirection = "north_east";
                return reversiveDirection;
            }
            else {
                String reversiveDirection = null;
                return reversiveDirection;
            }
        }
    }
    
    /**
     * Checks if the current pattern is the win pattern or not.
     * @return true if the game should be stoped. Otherwise - false.
     */     
    private boolean winPattern() 
    {
        // implement the sword 
        if (currentRoom == room.get("royalChamber")) {
            System.out.println("You saved the princess from the dragon");
            System.out.println("You won");
            return true;
            }
        return false;
    }
    
    /**
     * This methods works when you are in the room of evil, where the dragon is located. 
     * It checks if you have the necessary items to kill the dragon. if you have them, then you are able to skip (kill)
     * the dragon and move to the room where the princess is located. When it is done, player automatically wins the game
     * (it calls the winPattern method).
     * @return returns the winPattern method (or false) that returns true or false to finish or to continue the game.
     */
    private boolean killTheDragon()
    {
        if(currentRoom == room.get("roomOfEvil")) {
            Item item1 = findAnItem("magical_sword");
            Item item2 = findAnItem("book_of_defence");
            if ((item1 != null) && (item2 != null)) {
                System.out.println("You killed the dragon");
                goRoyalChamber();
                return winPattern();
            }
            else {
                System.out.println("dragon hit you. You don't have magic items to kill him");
                System.out.println("run away. Find the needed items, then come back and kill him");
                return false;
            }
        }
        return false;
    }
    
    /**
     * Teleports the player to the royal chamber room if the hero is in the room of evil and if the dragon was skiped (killed).
     */
    private void goRoyalChamber() 
    {
        if (currentRoom == room.get("roomOfEvil")) {
            currentRoom = room.get("royalChamber");
        }
    }
    
    /**
     * Finds an item in array list of items picked.
     * @param itemName String the name of the item.
     * @return returns an item (or null) if it was found in a list of items the player picked.
     */
    private Item findAnItem(String itemName)
    {
        for (Item item : listOfItemsWePicked) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Method that implements the magic transporter room.
     */
    private void magicRoom()
    {
        if (currentRoom == room.get("magicTransporterRoom")) {
            currentRoom = getTheRandomRoom();
            System.out.println("This room transportates you randomly to any other room. Get ready and letsssssssss go. Pafff");
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Method that makes all characters be able to move between rooms.
     * @param room1 - room.
     */
    private void moveTheCharacter(Room room1) 
    {
        for (OtherCharacters character : characters) {
            Room roomWhereTheCharacterIs = character.getRoom();
            String[] getExits = exits.get(roomWhereTheCharacterIs);
            int theAmountOfExits = getExits.length;
            int i = random.nextInt(theAmountOfExits); 
            String exit = getExits[i];
            Room roomWeNeed = roomWhereTheCharacterIs.getExit(exit);
            character.setRoom(roomWeNeed); 
        }
    }
}
