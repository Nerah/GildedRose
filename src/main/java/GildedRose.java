import exceptions.ItemNotFoundInShopException;
import factory.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GildedRose {
    private List<Item> items = null;
    private ItemsStore itemsStore;

    public GildedRose() throws Exception {
        initListItems();
        itemsStore = new ItemsStore(items);
    }

    public void initListItems() {
        items = new ArrayList<>();
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Water", 10, 30));
    }

    public void updateItems() {
        for (Item item : items) {
            itemsStore.updateItem(item);
        }
    }

    public Item get(String key) throws Exception {
        Optional<Item> optional = items.stream().filter(item -> item.getName().equals(key)).findFirst();
        if(optional.isPresent()) {
            return optional.get();
        }
        else {
            throw new ItemNotFoundInShopException(key);
        }
    }

}