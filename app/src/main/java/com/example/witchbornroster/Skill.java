package com.example.witchbornroster;

public class Skill {
    public static final String BASIC_SKILL = "Basic";
    public static final String EXPERT_SKILL = "Expert";
    public static final String MASTER_SKILL = "Master";
    public static final String TRAIT = "Trait";

    String category;
    String type;
    String name;
    String prerequisite;
    String description;
    String restrictions;

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
}
