package com.example.witchbornroster;

import android.os.Parcel;
import android.os.Parcelable;

public class Weapon extends Item implements Parcelable {
    private int hands;
    private String attacks;
    private int damage;
    private int chargeDamage;
    private String damageType;
    private int injury;
    private String range;
    private String siege;


    public Weapon(String name, String clan, float value, int rarity, boolean artifact, String description, int hands, String attacks, int damage, int chargeDamage, String damageType, int injury, String range, String siege) {
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

    public String getSiege() {
        return siege;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    }

    protected Weapon(Parcel in) {
        super(in);
        hands = in.readInt();
        attacks = in.readString();
        damage = in.readInt();
        chargeDamage = in.readInt();
        damageType = in.readString();
        injury = in.readInt();
        range = in.readString();
        siege = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(hands);
        dest.writeString(attacks);
        dest.writeInt(damage);
        dest.writeInt(chargeDamage);
        dest.writeString(damageType);
        dest.writeInt(injury);
        dest.writeString(range);
        dest.writeString(siege);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Weapon> CREATOR = new Parcelable.Creator<Weapon>() {
        @Override
        public Weapon createFromParcel(Parcel in) {
            return new Weapon(in);
        }

        @Override
        public Weapon[] newArray(int size) {
            return new Weapon[size];
        }
    };
}