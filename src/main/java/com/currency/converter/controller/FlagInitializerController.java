package com.currency.converter.controller;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class FlagInitializerController {

    private Map<String, Image> flags = new HashMap<>();

    public FlagInitializerController() {
        initFlags();
    }

    void initFlags() {
        flags.put("EUR", new Image(getClass().getResourceAsStream("/css/img/european-union.png")));
        flags.put("CAD", new Image(getClass().getResourceAsStream("/css/img/canada.png")));
        flags.put("COP", new Image(getClass().getResourceAsStream("/css/img/colombia.png")));
        flags.put("USD", new Image(getClass().getResourceAsStream("/css/img/united-states.png")));
        flags.put("MXN", new Image(getClass().getResourceAsStream("/css/img/mxflag.png")));
    }

    public Map<String, Image> getFlags() {
        return flags;
    }
}