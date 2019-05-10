package com.example.witchbornroster;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemContainer implements Parcelable {
    protected Item item;
    protected int quantity;
    protected float itemsValue;

    public ItemContainer() {
        quantity = 1;
        itemsValue = 0;
    }

    public ItemContainer(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        itemsValue = quantity * item.getValue();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        itemsValue = quantity * item.getValue();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        itemsValue = quantity * item.getValue();
    }

    public float getItemsValue() {
        return itemsValue;
    }


    @Override
    public String toString() {
        return quantity + " " + item.getName() + " " + (item.getValue() * quantity);
    }

    protected ItemContainer(Parcel in) {
        item = (Item) in.readValue(Item.class.getClassLoader());
        quantity = in.readInt();
        itemsValue = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(item);
        dest.writeInt(quantity);
        dest.writeFloat(itemsValue);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ItemContainer> CREATOR = new Parcelable.Creator<ItemContainer>() {
        @Override
        public ItemContainer createFromParcel(Parcel in) {
            return new ItemContainer(in);
        }

        @Override
        public ItemContainer[] newArray(int size) {
            return new ItemContainer[size];
        }
    };
}