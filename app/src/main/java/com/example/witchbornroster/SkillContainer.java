package com.example.witchbornroster;

public class SkillContainer {
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
}