package com.example.witchbornroster;

public class Armor extends Item {
    private int defense;
    private int speedPenalty;
    private String bulk;

    public Armor(String name, String clan, float value, int rarity, boolean artifact, String description, int defense, int speedPenalty, String bulk) {
        super(name, clan, value, rarity, artifact, description);
        this.defense = defense;
        this.speedPenalty = speedPenalty;
        this.bulk = bulk;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeedPenalty() {
        return speedPenalty;
    }

    public void setSpeedPenalty(int speedPenalty) {
        this.speedPenalty = speedPenalty;
    }

    public String getBulk() {
        return bulk;
    }

    public void setBulk(String bulk) {
        this.bulk = bulk;
    }
}
