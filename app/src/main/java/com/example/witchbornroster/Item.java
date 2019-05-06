package com.example.witchbornroster;

public class Item {
    private String name;
    private String clan;
    private float value;
    private int rarity;
    private boolean artifact;
    private String description;

    public Item(String name, String clan, float value, int rarity, boolean artifact, String description) {
        this.name = name;
        this.clan = clan;
        this.value = value;
        this.rarity = rarity;
        this.artifact = artifact;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public boolean isArtifact() {
        return artifact;
    }

    public void setArtifact(boolean artifact) {
        this.artifact = artifact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
