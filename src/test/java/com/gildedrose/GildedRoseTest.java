package com.gildedrose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {
    
    @Test
    public void updateQuality() {
        // Given
        Item[] items = new Item[] {};
        GildedRose gildedRose = new GildedRose(items);   

        // When
        gildedRose.updateQuality();

        // Then
        assertThat(gildedRose.items).isEmpty();
    }

}
