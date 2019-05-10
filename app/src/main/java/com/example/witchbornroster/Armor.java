package com.example.witchbornroster;

import android.os.Parcel;
import android.os.Parcelable;

public class Armor extends Item implements Parcelable {
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

    protected Armor(Parcel in) {
        super(in);
        defense = in.readInt();
        speedPenalty = in.readInt();
        bulk = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(defense);
        dest.writeInt(speedPenalty);
        dest.writeString(bulk);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Armor> CREATOR = new Parcelable.Creator<Armor>() {
        @Override
        public Armor createFromParcel(Parcel in) {
            return new Armor(in);
        }

        @Override
        public Armor[] newArray(int size) {
            return new Armor[size];
        }
    };
}