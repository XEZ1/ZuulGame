import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * Players class that has an inventory and its weight.
 * @author   EzzatAlsalibi
 * @version  29.11.2021
 */
public class Character
{
    // instance variables
    private Room currentRoom;
    private HashMap<String, Item> inventory;
    private int overallWeight;

    /**
     * Constructor for objects of class Character.
     */
    public Character()
    {
        inventory = new HashMap<>();
        this.overallWeight = overallWeight;
    }
    
    /**
     * @param weight Weight of the item.
     */
    public void setTheOverallWeight(int weight)
    {
        this.overallWeight = weight;
    }
    
    /**
     * @return returns the overall weight of the items the player has picked.
     */
    public int getTheOverallWeight()
    {
        return overallWeight;
    }
    
    /**
     * It fills the HashMap "inventory" with an item name (String) and item (Item) itself.
     * Also checks whether we have exceeded the limit or not.
     * @param itemName String name of the item.
     * @param item Item itself.
     */
    public void putItem(String itemName, Item item)
    {
        if (overallWeight <= 5) {
            if (item != null) {
                overallWeight += item.getWeight();
                inventory.put(itemName, item);
                System.out.println("You picked this item");
            }
            else {
                System.out.println("This item is not placed in this room");
            }
        }
        else {
            System.out.println("You reched the weight limit. You can't pick this item");
        }
        
    }
    
    /**
     * Method that lists the items the player already picked.
     * it is used to implement the check command.
     */
    public void listTheItemsWePicked()
    {
        ArrayList<String> listOfItems = new ArrayList<>();
        if (inventory.isEmpty()){
            System.out.println("we don't have any items");
        }
        else {
            System.out.println("Items: ");
            Set<String> keys = inventory.keySet();
            for (String item : keys) {
                listOfItems.add(item);
            }
            System.out.println(listOfItems);
        }
    }
}
