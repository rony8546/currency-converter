package com.currency.converter.controller;

import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class FlagInitializerControllerTest {
    private FlagInitializerController controller;

    @BeforeEach
    public void setUp() {
        controller = new FlagInitializerController();
    }

    @Test
    public void testGetFlags() {
        Map<String, Image> flags = controller.getFlags();
        assertNotNull(flags);
        assertEquals(2, flags.size());
        assertNotNull(flags.get("USA"));
        assertNotNull(flags.get("Canada"));
    }

    @Test
    public void testInitFlags() {
        controller.initFlags();
        Map<String, Image> flags = controller.getFlags();
        assertNotNull(flags);
        assertEquals(2, flags.size());
        assertNotNull(flags.get("USA"));
        assertNotNull(flags.get("Canada"));
    }
}