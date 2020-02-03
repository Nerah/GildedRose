package exceptions;

public class ItemDoesntExistException extends Exception {

    public ItemDoesntExistException(String itemName) {
        System.out.println("There is no way an item called \"" + itemName + "\" exists! Please add it to the factory.");
    }

}
