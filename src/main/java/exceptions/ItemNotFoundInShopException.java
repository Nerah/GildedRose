package exceptions;

public class ItemNotFoundInShopException extends Exception {

    public ItemNotFoundInShopException(String itemName) {
        System.out.println("We are not selling any item called \"" + itemName + "\"! Please add it to the shop.");
    }

}
