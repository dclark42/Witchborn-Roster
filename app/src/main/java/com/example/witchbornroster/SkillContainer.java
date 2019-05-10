package com.example.witchbornroster;

import android.os.Parcel;
import android.os.Parcelable;

public class SkillContainer implements Parcelable {
    private Skill skill;
    private int skillValue;

    public SkillContainer(Skill skill, int skillValue) {
        this.skill = skill;
        this.skillValue = skillValue;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getSkillValue() {
        return skillValue;
    }

    public void setSkillValue(int skillValue) {
        this.skillValue = skillValue;
    }

    protected SkillContainer(Parcel in) {
        skill = (Skill) in.readValue(Skill.class.getClassLoader());
        skillValue = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(skill);
        dest.writeInt(skillValue);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SkillContainer> CREATOR = new Parcelable.Creator<SkillContainer>() {
        @Override
        public SkillContainer createFromParcel(Parcel in) {
            return new SkillContainer(in);
        }

        @Override
        public SkillContainer[] newArray(int size) {
            return new SkillContainer[size];
        }
    };
}