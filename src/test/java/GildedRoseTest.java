import static org.junit.Assert.*;

import factory.AbstractItem;
import factory.BackstagePasses;
import factory.Item;
import org.junit.Test;

public class GildedRoseTest {

	@Test
	public void sulfurasShouldKeepSameQuality() throws Exception {
		GildedRose inn = new GildedRose();
		Item sulfuras = inn.get("Sulfuras, Hand of Ragnaros");

		int originQuality = sulfuras.quality;

		for (int i = 0; i < 100; i++) {
			inn.updateItems();
			assertEquals(originQuality, sulfuras.quality);
		}
	}

	@Test
	public void dexterityVestQualityShouldDecrementUntilZero() throws Exception {
		GildedRose inn = new GildedRose();
		Item dexterityVest = inn.get("+5 Dexterity Vest");

		int oldQuality;

		for (int i = 0; i < 100; i++) {
			oldQuality = dexterityVest.quality;
			inn.updateItems();

			if(!hasReachMinQuality(dexterityVest) && !hasReachSellInBoundary(dexterityVest))
				assertEquals(dexterityVest.quality, oldQuality - 1);
			else if(!hasReachMinQuality(dexterityVest))
				assertTrue(dexterityVest.quality == oldQuality - 2 || hasReachMinQuality(dexterityVest));
			else assertEquals(dexterityVest.quality, AbstractItem.MIN_QUALITY);
		}
	}

	@Test
	public void elixirOfTheMongooseQualityShouldDecrementUntilZero() throws Exception {
		GildedRose inn = new GildedRose();
		Item elixirOfTheMongoose = inn.get("Elixir of the Mongoose");

		int oldQuality;

		for (int i = 0; i < 100; i++) {
			oldQuality = elixirOfTheMongoose.quality;
			inn.updateItems();

			if(!hasReachMinQuality(elixirOfTheMongoose) && !hasReachSellInBoundary(elixirOfTheMongoose))
				assertEquals(elixirOfTheMongoose.quality, oldQuality - 1);
			else if(!hasReachMinQuality(elixirOfTheMongoose))
				assertTrue(elixirOfTheMongoose.quality == oldQuality - 2 || hasReachMinQuality(elixirOfTheMongoose));
			else assertEquals(elixirOfTheMongoose.quality, AbstractItem.MIN_QUALITY);
		}
	}

	@Test
	public void AgedBrieQualityShouldIncrement() throws Exception {
		GildedRose inn = new GildedRose();
		Item agedBrie = inn.get("Aged Brie");

		int oldQuality;

		for (int i = 0; i < 100; i++) {
			oldQuality = agedBrie.quality;
			inn.updateItems();

			if(hasReachMaxQuality(agedBrie))
				assertEquals(agedBrie.quality, AbstractItem.MAX_QUALITY);
			else if(!hasReachSellInBoundary(agedBrie))
				assertEquals(agedBrie.quality, oldQuality + 1);
			else assertEquals(agedBrie.quality, oldQuality + 2);
		}
	}

	@Test
	public void BackstagePassesQualityShouldIncrement() throws Exception {
		GildedRose inn = new GildedRose();
		Item backstagePasses = inn.get("Backstage passes to a TAFKAL80ETC concert");

		int oldQuality;

		for (int i = 0; i < 100; i++) {
			oldQuality = backstagePasses.quality;
			inn.updateItems();

			if(hasReachMaxQuality(backstagePasses))
				assertEquals(backstagePasses.quality, AbstractItem.MAX_QUALITY);
			if(!hasReachCloseDate(backstagePasses))
				assertEquals(backstagePasses.quality, oldQuality + 1);
			else if(!hasReachImminentDate(backstagePasses))
				assertEquals(backstagePasses.quality, oldQuality + 2);
			else if(!hasReachSellInBoundary(backstagePasses))
				assertEquals(backstagePasses.quality, oldQuality + 3);
			else assertEquals(backstagePasses.quality, AbstractItem.MIN_QUALITY);
		}
	}

	@Test
	public void conjuredItemQualityShouldDecrementUntilZero() throws Exception {
		GildedRose inn = new GildedRose();
		Item conjuredWater = inn.get("Conjured Water");

		int oldQuality;

		for (int i = 0; i < 100; i++) {
			oldQuality = conjuredWater.quality;
			inn.updateItems();

			if(!hasReachMinQuality(conjuredWater) && !hasReachSellInBoundary(conjuredWater))
				assertEquals(conjuredWater.quality, oldQuality - 2);
			else if(!hasReachMinQuality(conjuredWater))
				assertTrue(conjuredWater.quality == oldQuality - 4 || hasReachMinQuality(conjuredWater));
			else assertEquals(conjuredWater.quality, AbstractItem.MIN_QUALITY);
		}
	}

	private boolean hasReachMaxQuality(Item item) {
		return item.quality >= AbstractItem.MAX_QUALITY;
	}

	private boolean hasReachMinQuality(Item item) {
		return item.quality <= AbstractItem.MIN_QUALITY;
	}

	private boolean hasReachSellInBoundary(Item item) {
		return item.sellIn < AbstractItem.MIN_SELLIN;
	}

	private boolean hasReachCloseDate(Item item) {
		return item.sellIn < BackstagePasses.CLOSE_DATE;
	}

	private boolean hasReachImminentDate(Item item) {
		return item.sellIn < BackstagePasses.IMMINENT_DATE;
	}

}