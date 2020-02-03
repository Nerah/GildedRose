import factory.AbstractItem;
import factory.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsStore {
    // find correct instance for a given string.
    private Map<String, AbstractItem> store;

    public ItemsStore(List<Item> store) throws Exception {
        this.store = new HashMap<>();
        ItemFactory itemFactory = new ItemFactory();

        // store the name of the item (String) with its corresponding instance
        for (Item item : store) {
            this.store.put(item.name, itemFactory.getInstance(item));
        }
    }

    public void updateItem(Item item) {
        store.get(item.name).update();
    }

}
