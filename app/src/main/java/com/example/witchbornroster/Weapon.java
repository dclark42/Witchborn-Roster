package com.example.witchbornroster;

public class Weapon extends Item {
    private int hands;
    private String attacks;
    private int damage;
    private int chargeDamage;
    private String damageType;
    private int injury;
    private String range;
    private int siege;

    public Weapon(String name, String clan, float value, int rarity, boolean artifact, String description, int hands, String attacks, int damage, int chargeDamage, String damageType, int injury, String range, int siege) {
        super(name, clan, value, rarity, artifact, description);
        this.hands = hands;
        this.attacks = attacks;
        this.damage = damage;
        this.chargeDamage = chargeDamage;
        this.damageType = damageType;
        this.injury = injury;
        this.range = range;
        this.siege = siege;
    }

    public int getHands() {
        return hands;
    }

    public void setHands(int hands) {
        this.hands = hands;
    }

    public String getAttacks() {
        return attacks;
    }

    public void setAttacks(String attacks) {
        this.attacks = attacks;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getChargeDamage() {
        return chargeDamage;
    }

    public void setChargeDamage(int chargeDamage) {
        this.chargeDamage = chargeDamage;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public int getInjury() {
        return injury;
    }

    public void setInjury(int injury) {
        this.injury = injury;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getSiege() {
        return siege;
    }

    public void setSiege(int siege) {
        this.siege = siege;
    }
}
