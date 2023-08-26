/**
 * Class for Items. Items have some parameters like weight or power.
 */
public class Item 
{
    // instance variables
    private int weight;
    private int power;
    private int defence;
    private String name;

    /**
     * Constructor for objects of class Item
     * @param name The name of teh character.
     * @param weight The weight of the item.
     * @param power The power of the character.
     * @param defence The defence of the character.
     */
    public Item(String name, int weight, int power, int defence)
    {
        this.weight = weight;
        this.power = power;
        this.defence = defence;
        this.name = name;
    }   

    /**
     * Accessor.
     * @return  overall weight
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Accessor.
     * @return Returns the string Name of the item.
     */
    public String getName()
    {
        return name;
    }
}
    
/**
 * Subclass Knife of class item.
 */
class Knife extends Item
{
    /**
     * Constructor for objects
     * @param name The name of teh character.
     * @param weight The weight of the item.
     * @param power The power of the character.
     * @param defence The defence of the character.
     */
    public Knife(String name, int weight, int power, int defence) 
    {
        super(name, weight, power, defence); 
    }
}
    
/**
 * Subclass Book of class item.
 */
class Book extends Item
{
    /**
     * Constructor for objects
     * @param name The name of teh character.
     * @param weight The weight of the item.
     * @param power The power of the character.
     * @param defence The defence of the character.*/
    public Book(String name, int weight, int power, int defence)
    {
        super(name, weight, power, defence);
    }
}

/**
 * Subclass UselessBook of class item.
 */
class UselessBook extends Item
{
    /**
     * Constructor for objects
     * @param name The name of teh character.
     * @param weight The weight of the item.
     * @param power The power of the character.
     * @param defence The defence of the character.
     */
    public UselessBook(String name, int weight, int power, int defence)
    {
        super(name, weight, power, defence);
    }
}

/**
 * Subclass Coin of class item.
 */
class Coin extends Item
{
    /**
     * Constructor for objects
     * @param name The name of teh character.
     * @param weight The weight of the item.
     * @param power The power of the character.
     * @param defence The defence of the character.
     */
    public Coin(String name, int weight, int power, int defence)
    {
        super(name, weight, power, defence);
    }
}




