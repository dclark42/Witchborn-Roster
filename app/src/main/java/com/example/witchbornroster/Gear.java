package com.example.witchbornroster;

import android.os.Parcel;
import android.os.Parcelable;

public class Gear extends Item implements Parcelable {
    private boolean consumable;

    public Gear(String name, String clan, float value, int rarity, boolean artifact, String description, boolean consumable) {
        super(name, clan, value, rarity, artifact, description);
        this.consumable = consumable;
    }

    protected Gear(Parcel in) {
        super(in);
        consumable = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte((byte) (consumable ? 0x01 : 0x00));
    }

    public boolean isConsumable() {
        return consumable;
    }

    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Gear> CREATOR = new Parcelable.Creator<Gear>() {
        @Override
        public Gear createFromParcel(Parcel in) {
            return new Gear(in);
        }

        @Override
        public Gear[] newArray(int size) {
            return new Gear[size];
        }
    };
}