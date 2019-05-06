package com.example.witchbornroster;

import java.util.List;

public class Warrior {
    String name;
    String type;
    int typeValue;
    int level;

    int baseDefense;
    int strength;
    int agility;
    int psyche;
    int rout;
    int routBonus;
    int speed;
    int charge;
    int chargeBonus;

    List<Armor> armorList;
    int armorBonus;
    int speedPenalty;
    float armorsValue;

    WeaponContainer[] weapons = new WeaponContainer[3];

    List<SkillContainer> skillList;

    ItemContainer[] gearList = new ItemContainer[8];

    int adventures;
    int routsCaused;
    int knockouts;
    int maims;
    int warWounds;
    int kills;
    int shadow;
    int totalShadow;

    float warriorValue;

    public Warrior() {
        name = "";
        type = "Warrior";
        typeValue = 0;
        level = 0;
        baseDefense = 5;
        strength = 3;
        agility = 3;
        psyche = 3;
        rout = 7 - psyche;
        speed = 6;
        chargeBonus = 0;

        armorBonus = 0;
        speedPenalty = 0;
        armorsValue = 0;

        adventures = 0;
        routsCaused = 0;
        knockouts = 0;
        maims = 0;
        warWounds = 0;
        kills = 0;
        shadow = 0;
        totalShadow = 0;

        warriorValue = typeValue;
        charge = calcCharge();
    }

    public Warrior(String name, String type, int typeValue, int baseDefense, int strength, int agility, int psyche, int routBonus, int speed, int chargeBonus, List<SkillContainer> skillList) {
        this.name = name;
        this.type = type;
        this.typeValue = typeValue;
        this.baseDefense = baseDefense;
        this.strength = strength;
        this.agility = agility;
        this.psyche = psyche;
        this.routBonus = routBonus;
        this.speed = speed;
        this.chargeBonus = chargeBonus;
        this.skillList = skillList;

        level = 0;
        rout = 7 - psyche - routBonus;

        adventures = 0;
        routsCaused = 0;
        knockouts = 0;
        maims = 0;
        warWounds = 0;
        kills = 0;
        shadow = 0;
        totalShadow = 0;

        warriorValue = typeValue;
        charge = calcCharge();
    }

    private void calcWarriorValue(){
        int total = typeValue;
        total += armorsValue;
        for (WeaponContainer wep: weapons){
            total += wep.weapon.getValue();
        }
        for (SkillContainer skills: skillList) {
            total += skills.getSkillValue();
        }
        for (ItemContainer item:gearList) {
            total += item.item.getValue();
        }
        warriorValue = total;
    }

    public void equipWeapon(int slot, Weapon weapon){
        weapons[slot].equipWeapon(weapon);
        calcWarriorValue();
    }

    public void removeWeapon(int slot){
        weapons[slot] = new WeaponContainer();
        calcWarriorValue();
    }

    private void calcArmorTotals(){
        int armorTotalBonus = 0;
        float armorTotalValue = 0;
        int armorTotalSpeedPenalty = 0;
        for (Armor armor:armorList) {
            armorTotalBonus += armor.getDefense();
            armorTotalValue += armor.getValue();
            armorTotalSpeedPenalty += armor.getSpeedPenalty();
        }
        armorBonus = armorTotalBonus;
        armorsValue = armorTotalValue;
        speedPenalty = armorTotalSpeedPenalty;
    }

    public void equipArmor(Armor armor){
        armorList.add(armor);
        calcArmorTotals();;
        calcWarriorValue();
    }

    public void removeArmor(Armor armor){
        armorList.remove(armor);
        calcArmorTotals();
        calcWarriorValue();
    }

    private int calcCharge(){
        if(speed == 5){
            return 2 + chargeBonus;
        } else{
            return speed/2 + chargeBonus;
        }
    }

    private class WeaponContainer{
        protected Weapon weapon;
        private int totalDamage;
        private int totalChargeDamage;
        private int totalInjury;

        public void equipWeapon(Weapon weapon) {
            this.weapon = weapon;
            if(weapon.getRange() != null)
            {
                totalDamage = weapon.getDamage() + agility;
            } else{
                totalDamage = weapon.getDamage() + strength;
                if (weapon.getChargeDamage() != 0){
                    totalChargeDamage = weapon.getChargeDamage() + strength;
                }
            }
            totalInjury = weapon.getInjury();
        }

        public void setTotalDamage(int totalDamage) {
            this.totalDamage = totalDamage;
        }

        public void setTotalChargeDamage(int totalChargeDamage) {
            this.totalChargeDamage = totalChargeDamage;
        }

        public void setTotalInjury(int totalInjury) {
            this.totalInjury = totalInjury;
        }

        public Weapon getWeapon() {
            return weapon;
        }

        public int getTotalDamage() {
            return totalDamage;
        }

        public int getTotalChargeDamage() {
            return totalChargeDamage;
        }

        public int getTotalInjury() {
            return totalInjury;
        }
    }

    private class ItemContainer{
        protected Item item;
        protected int quantity;

        public ItemContainer(Item item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    private void calcTotalShadow(){
        int total = 0;
        total += adventures;
        total += routsCaused;
        total += knockouts*2;
        total += maims*3;
        total += warWounds*4;
        total += kills*5;
        total += shadow;
        totalShadow = total;
    }

    public int getAdventures() {
        return adventures;
    }

    public void setAdventures(int adventures) {
        this.adventures = adventures;
        calcTotalShadow();
    }

    public int getRoutsCaused() {
        return routsCaused;
    }

    public void setRoutsCaused(int routsCaused) {
        this.routsCaused = routsCaused;
        calcTotalShadow();
    }

    public int getKnockouts() {
        return knockouts;
    }

    public void setKnockouts(int knockouts) {
        this.knockouts = knockouts;
        calcTotalShadow();
    }

    public int getMaims() {
        return maims;
    }

    public void setMaims(int maims) {
        this.maims = maims;
        calcTotalShadow();
    }

    public int getWarWounds() {
        return warWounds;
    }

    public void setWarWounds(int warWounds) {
        this.warWounds = warWounds;
        calcTotalShadow();
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
        calcTotalShadow();
    }

    public int getShadow() {
        return shadow;
    }

    public void setShadow(int shadow) {
        this.shadow = shadow;
        calcTotalShadow();
    }
}
