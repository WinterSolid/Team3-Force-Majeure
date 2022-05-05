package com.team3.forcemajeure.util;

import com.team3.forcemajeure.util.Inventory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class InventoryTest {
    Inventory testInventory;
    ArrayList<String> testInventoryShould = new ArrayList<>();
    ArrayList<String> testInventoryShouldNewItem = new ArrayList<>();

    @Before
    public void setUp(){
        testInventory = new Inventory("map");
        testInventoryShould.add("map");
        testInventoryShouldNewItem.add("map");
        testInventoryShouldNewItem.add("newItem");
    }

    @Test
    public void getInventory() {
        assertEquals(testInventoryShould,testInventory.getInventory());
    }

    @Test
    public void appendInventory() {
        testInventory.appendInventory("newItem");
        assertEquals(testInventoryShouldNewItem, testInventory.getInventory());

    }

    @Test
    public void removeItemInventory() {
        testInventory.removeItemInventory("newItem");
        assertEquals(testInventoryShould, testInventory.getInventory());
    }
}