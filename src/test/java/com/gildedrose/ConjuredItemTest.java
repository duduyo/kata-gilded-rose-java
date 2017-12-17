package com.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ConjuredItemTest {

	@Test
	public void conjuredItemDegradesQualityTwiceAsNormal() throws Exception {
		// Given
		Item[] items = new Item[] { new Item("Conjured Mana Cake", 3, 6) };
		GildedRose gildedRose = new GildedRose(items);

		// When
		gildedRose.updateQuality();
		// Then
		assertThat(gildedRose.items).extracting("quality").containsExactly(4);
	}

}