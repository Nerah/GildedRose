import static org.junit.Assert.*;

import org.junit.Test;


public class GildedRoseTest {

	@Test
	public void sulfurasShouldKeepSameQuality() throws Exception {
		GildedRose inn = new GildedRose();
		Item sulfuras = inn.get("Sulfuras, Hand of Ragnaros");

		int originQuality = sulfuras.quality;

		for (int i = 0; i < 100; i++) {
			inn.updateQuality();
			assertEquals(originQuality, sulfuras.quality);
		}
	}

	@Test
	public void dexterityVestQualityShouldDecrementUntilZero() throws Exception {
		GildedRose inn = new GildedRose();
		Item dexterityVest = inn.get("+5 Dexterity Vest");

		int oldQuality;
		boolean hasNoQuality = false;

		for (int i = 0; i < 100; i++) {
			oldQuality = dexterityVest.quality;
			inn.updateQuality();

			if(dexterityVest.quality > 0 && dexterityVest.sellIn >= 0) {
				assertEquals(dexterityVest.quality, oldQuality - 1);
			}
			else if(dexterityVest.quality >= 0 && !hasNoQuality) {
				assertEquals(dexterityVest.quality, oldQuality - 2);
			}
			else {
				assertEquals(dexterityVest.quality, oldQuality);
			}

			hasNoQuality = dexterityVest.quality == 0;
		}
	}

}