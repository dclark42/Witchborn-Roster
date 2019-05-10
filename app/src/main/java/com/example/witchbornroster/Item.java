package com.example.witchbornroster;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
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

    protected Item(Parcel in) {
        name = in.readString();
        clan = in.readString();
        value = in.readFloat();
        rarity = in.readInt();
        artifact = in.readByte() != 0x00;
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(clan);
        dest.writeFloat(value);
        dest.writeInt(rarity);
        dest.writeByte((byte) (artifact ? 0x01 : 0x00));
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}