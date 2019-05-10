package com.example.witchbornroster;

import android.os.Parcel;
import android.os.Parcelable;

public class Skill implements Parcelable {
    public static final String BASIC_SKILL = "Basic";
    public static final String EXPERT_SKILL = "Expert";
    public static final String MASTER_SKILL = "Master";
    public static final String TRAIT = "Trait";

    private String category;
    private String type;
    private String name;
    private String prerequisite;
    private String description;
    private String restrictions;

    public Skill(String category, String type, String name, String prerequisite, String description, String restrictions) {
        this.category = category;
        this.type = type;
        this.name = name;
        this.prerequisite = prerequisite;
        this.description = description;
        this.restrictions = restrictions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    protected Skill(Parcel in) {
        category = in.readString();
        type = in.readString();
        name = in.readString();
        prerequisite = in.readString();
        description = in.readString();
        restrictions = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(prerequisite);
        dest.writeString(description);
        dest.writeString(restrictions);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Skill> CREATOR = new Parcelable.Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };

    public int getSkillValue(){
        switch (type){
            case BASIC_SKILL:
                return 2;
            case EXPERT_SKILL:
                return 3;
            case MASTER_SKILL:
                return 5;
            default: return 0;
        }
    }
}