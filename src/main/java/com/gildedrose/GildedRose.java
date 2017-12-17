package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			updateItem(items[i]);
		}
	}

	private void updateItem(Item item) {
		if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
			return;
		}
		item.sellIn = item.sellIn - 1;

		if (item.name.equals("Aged Brie")) {
			updateQualityForAgedBrieItems(item);
		} else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
			updateQualityForBackstageItems(item);
		} else if (item.name.equals("Conjured Mana Cake")) {
			updateQualityForConjuredItems(item);
		} else {
			updateQualityForNormalItems(item);
		}
	}

	private void updateQualityForAgedBrieItems(Item item) {
		increaseQualityIfLessThan50(item);
		if (item.sellIn < 0) {
			increaseQualityIfLessThan50(item);
		}
	}

	private void updateQualityForBackstageItems(Item item) {
		increaseQualityIfLessThan50(item);
		if (item.sellIn < 10) {
			increaseQualityIfLessThan50(item);
		}
		if (item.sellIn < 5) {
			increaseQualityIfLessThan50(item);
		}
		if (item.sellIn < 0) {
			item.quality = 0;
		}
	}

	private void updateQualityForNormalItems(Item item) {
		decreaseQualityIfGreaterThan0(item);
		if (item.sellIn < 0) {
			decreaseQualityIfGreaterThan0(item);

		}
	}

	private void updateQualityForConjuredItems(Item item) {
		decreaseQualityIfGreaterThan0(item);
		decreaseQualityIfGreaterThan0(item);
	}

	private void decreaseQualityIfGreaterThan0(Item item) {
		if (item.quality > 0) {
			item.quality = item.quality - 1;
		}
	}

	private void increaseQualityIfLessThan50(Item item) {
		if (item.quality < 50) {
			item.quality = item.quality + 1;
		}
	}
}