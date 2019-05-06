package com.example.witchbornroster;

public class Gear extends Item {
    private boolean consumable;

    public Gear(String name, String clan, float value, int rarity, boolean artifact, String description, boolean consumable) {
        super(name, clan, value, rarity, artifact, description);
        this.consumable = consumable;
    }
}
