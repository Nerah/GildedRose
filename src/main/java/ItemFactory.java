import exceptions.ItemDoesntExistException;
import factory.*;
import utils.MapOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ItemFactory {
    Map<String, Class<? extends AbstractItem>> recognizableItems;

    public ItemFactory() {
        initMapRecognizableItems();
    }

    private void initMapRecognizableItems() {
        this.recognizableItems = new HashMap<>();

        recognizableItems.put("Sulfuras, Hand of Ragnaros", LegendaryItem.class);
        recognizableItems.put("Dexterity Vest", OrdinaryItem.class);
        recognizableItems.put("Elixir of the Mongoose", OrdinaryItem.class);
        recognizableItems.put("Aged Brie", AgedBrie.class);
        recognizableItems.put("Backstage passes", BackstagePasses.class);
        recognizableItems.put("Conjured", ConjuredItem.class);
    }

    // return the instance corresponding to the given item.
    public AbstractItem getInstance(Item item) throws Exception {
        // deal with stuff like conjured items, that has "conjured" in their name, as an example.
        Optional<String> similarName = MapOperations.findFirstCorrespondingKey(recognizableItems, item.name::contains);

        if(similarName.isPresent()) {
            return recognizableItems
                    .get(similarName.get())
                    .getConstructor(Item.class)
                    .newInstance(item);
        } else throw new ItemDoesntExistException(item.name);
    }

}
