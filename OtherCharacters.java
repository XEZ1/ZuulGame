import java.util.ArrayList;
/**
 * 
 * This class represents the characters in the game,
 * its class and string name.
 * e.g. dragon luv66 or the professor Michael Kolling.
 */
public class OtherCharacters
{
    // instance variables
    private Room currentRoom;
    private int defence;
    private int attack;
    private boolean melee; 
    private String name;
    private ArrayList<Item> inventory;

    /**
     * Constructor for objects of class OtherCharacters.
     * @param name The name of teh character.
     * @param defence The defence of the character.
     * @param attack The power of the attack of the character.
     * @param melee The rype of attack the character has.
     * @param room The room in which the character currently are.
     */
    public OtherCharacters(String name, int defence, int attack, boolean melee, Room room)
    {
        this.defence = defence;
        this.attack = attack;
        this.melee = melee;
        this.name = name;
        currentRoom = room;
        inventory = new ArrayList<>();
    }    
    
    /**
     * Mutator that allows to change the current room.
     * @param room Room of class Room.
     */
    public void setRoom(Room room) 
    {
        currentRoom = room; 
    }
    
    /**
     * Accessor.
     * @return current room
     */
    public Room getRoom()
    {
        return currentRoom;
    }
    
    /**
     * Accessor.
     * @return name of the character.
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Character can receive an item that will be stored in array list "inventory".
     * @param item Item that we want to pass to the character.
     */
    public void receiveAnItem(Item item)
    {
        inventory.add(item);
    }
}

/**
 * Subclass Dragon of class OtherCharacters
 */
class Dragon extends OtherCharacters
{
    /**
     * Constructor for objects.
     * @param name The name of teh character.
     * @param defence The defence of the character.
     * @param attack The power of the attack of the character.
     * @param melee The rype of attack the character has.
     * @param room The room in which the character currently are.
     */
    public Dragon(String name, int defence, int attack, boolean melee, Room room)
    {
        super(name, defence, attack, melee, room);
    }
}

/**
 * Subclass Teacher of class OtherCharacters
 */
class Teacher extends OtherCharacters 
{
    /**
     * Constructor for objects.
     * @param name The name of teh character.
     * @param defence The defence of the character.
     * @param attack The power of the attack of the character.
     * @param melee The rype of attack the character has.
     * @param room The room in which the character currently are.
     */
    public Teacher(String name, int defence, int attack, boolean melee, Room room)
    {
        super(name, defence, attack, melee, room);
    }
}

