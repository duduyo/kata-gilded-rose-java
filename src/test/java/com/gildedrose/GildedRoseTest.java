package com.gildedrose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
    //        new Item("Conjured Mana Cake", 3, 6)
    };

    Item[] legacyItems = new Item[] { new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
//            new Item("Conjured Mana Cake", 3, 6)
    };

    @Test
    public void initItems() {
        // Given
        GildedRose gildedRose = new GildedRose(items);

        // Then
        assertThat(gildedRose.items).extracting("name").containsExactly("+5 Dexterity Vest", "Aged Brie",
                "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert", "Backstage passes to a TAFKAL80ETC concert",
                "Backstage passes to a TAFKAL80ETC concert");
        assertThat(gildedRose.items).extracting("sellIn").containsExactly(10, 2, 5, 0, -1, 15, 10, 5);

        assertThat(gildedRose.items).extracting("quality").containsExactly(20, 0, 7, 80, 80, 20, 49, 49);

    }

    @Test
    public void updateQualityWithItemsAfterOneDay() {
        // Given
        GildedRose gildedRose = new GildedRose(items);

        // When
        gildedRose.updateQuality();

        // Then
        assertThat(gildedRose.items).extracting("name").containsExactly("+5 Dexterity Vest", "Aged Brie",
                "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert", "Backstage passes to a TAFKAL80ETC concert",
                "Backstage passes to a TAFKAL80ETC concert");

        assertThat(gildedRose.items).extracting("sellIn").containsExactly(9, 1, 4, 0, -1, 14, 9, 4);

        assertThat(gildedRose.items).extracting("quality").containsExactly(19, 1, 6, 80, 80, 21, 50, 50);
    }

    @Test
    public void updateQualityWithItemsAfter100Days() {
        // Given
        GildedRose gildedRose = new GildedRose(items);

        // When
        for (int day = 0; day < 100; day++) {
            gildedRose.updateQuality();
        }

        // Then
        assertThat(gildedRose.items).extracting("sellIn").containsExactly(-90, -98, -95, 0, -1, -85, -90, -95);

        assertThat(gildedRose.items).extracting("quality").containsExactly(0, 50, 0, 80, 80, 0, 0, 0);
    }

    @Test
    public void compareResultWithLegacyDuring100Days() {
        // Given
        GildedRose gildedRose = new GildedRose(items);
        LegacyGildedRose legacyGuildedRose = new LegacyGildedRose(legacyItems);

        // When
        for (int day = 0; day < 100; day++) {
            gildedRose.updateQuality();
            legacyGuildedRose.updateQuality();
            assertThat(gildedRose.items).isEqualTo(legacyGuildedRose.items);
        }

        // Then

    }


}
