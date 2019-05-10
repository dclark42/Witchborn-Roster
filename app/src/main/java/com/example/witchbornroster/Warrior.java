package com.example.witchbornroster;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "warrior_table")
public class Warrior {
    @PrimaryKey(autoGenerate = true)
    private int num;
    @ColumnInfo(name = "warrior_title")
    private String name;
    private String type;
    private int typeValue;
    private int level;

    private int baseDefense;
    private int totalDefense;
    private int strength;
    private int agility;
    private int psyche;
    private int rout;
    private int routBonus;
    private int speed;
    private int charge;
    private int chargeBonus;

    private ArrayList<Armor> armorList = new ArrayList<>();
    private int armorBonus;
    private int speedPenalty;
    private int armorValue;

    private WeaponContainer[] weapons = new WeaponContainer[3];

    private ArrayList<SkillContainer> skillList = new ArrayList<>();

    private ItemContainer[] gearList = new ItemContainer[8];

    private int adventures;
    private int routsCaused;
    private int knockouts;
    private int maims;
    private int warWounds;
    private int kills;
    private int shadow;
    private int totalShadow;

    private float warriorValue;

    public Warrior() {
        name = "";
        type = "Warrior";
        typeValue = 0;
        level = 0;
        baseDefense = 5;
        totalDefense = baseDefense;
        strength = 3;
        agility = 3;
        psyche = 3;
        rout = 0;
        routBonus = 0;
        speed = 6;
        chargeBonus = 0;

        armorList = new ArrayList<Armor>();
        armorBonus = 0;
        speedPenalty = 0;
        armorValue = 0;

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
        calcRout();
    }

    public Warrior(String name, String type, int typeValue, int baseDefense, int strength, int agility, int psyche, int routBonus, int speed, int chargeBonus, ArrayList<SkillContainer> skillList) {
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
        calcRout();
    }

    public void calcWarriorValue() {
        int total = typeValue;
        total += armorValue;

        for (WeaponContainer wep : weapons) {
            if (wep != null) {
                total += wep.weapon.getValue();
            }
        }
        for (SkillContainer skills : skillList) {
            total += skills.getSkillValue();
        }
        for (ItemContainer item : gearList) {
            if (item != null) {
                total += item.item.getValue();
            }
        }
        warriorValue = total;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void equipWeapon(int slot, Weapon weapon) {
        if (weapon == null) {
            weapons[slot] = null;
        } else {
            if (weapons[slot] == null) {
                weapons[slot] = new WeaponContainer(weapon);
            }
            weapons[slot].equipWeapon(weapon, strength, agility);
        }
        calcWarriorValue();
    }

    public void removeWeapon(int slot) {
        weapons[slot] = null;
        calcWarriorValue();
    }

    public ArrayList<Armor> getArmorList() {
        return armorList;
    }

    public WeaponContainer[] getWeapons() {
        return weapons;
    }

    private void calcArmorTotals() {
        int armorTotalBonus = 0;
        float armorTotalValue = 0;
        int armorTotalSpeedPenalty = 0;
        for (Armor armor : armorList) {
            armorTotalBonus += armor.getDefense();
            armorTotalValue += armor.getValue();
            armorTotalSpeedPenalty += armor.getSpeedPenalty();
        }
        armorBonus = armorTotalBonus;
        armorValue = (int) armorTotalValue;
        speedPenalty = armorTotalSpeedPenalty;
        totalDefense = baseDefense + armorBonus;
    }

    public void setArmorList(ArrayList<Armor> armorList) {
        this.armorList = armorList;
        calcArmorTotals();
    }


    public String getArmorNames() {
        String armorNames = "";
        for (Armor armor : armorList) {
            if (armorList.indexOf(armor) != 0) {
                armorNames += ", ";
            }
            armorNames += armor.getName();
        }
        return armorNames;
    }

    public void equipArmor(Armor armor) {
        armorList.add(armor);
        calcArmorTotals();
        calcWarriorValue();
    }

    public void removeArmor(Armor armor) {
        armorList.remove(armor);
        calcArmorTotals();
        calcWarriorValue();
    }

    public int calcCharge() {
        if (speed == 5) {
            return 2 + chargeBonus;
        } else {
            return speed / 2 + chargeBonus;
        }
    }

    private class WeaponContainer {
        protected Weapon weapon;
        private int totalDamage;
        private int totalChargeDamage;
        private int totalInjury;

        public WeaponContainer(Weapon weapon) {
            this.weapon = weapon;
        }

        public void equipWeapon(Weapon weapon, int strength, int agility) {
            this.weapon = weapon;
            if (weapon.getRange().isEmpty()) {
                totalDamage = weapon.getDamage() + agility;
            } else {
                totalDamage = weapon.getDamage() + strength;
                if (weapon.getChargeDamage() != 0) {
                    totalChargeDamage = weapon.getChargeDamage() + strength;
                }
            }
            totalInjury = weapon.getInjury();
        }

        public void setWeapon(Weapon weapon) {
            this.weapon = weapon;
        }

        public void removeWeapon() {
            weapon = null;
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

    public Weapon getWeapon(int i) {
        if (weaponExists(i)) {
            return weapons[i].getWeapon();
        }
        return null;
    }

    public String getWeaponName(int i) {
        if (weaponExists(i)) {
            return weapons[i].getWeapon().getName();
        }
        return "";
    }

    public String getWeaponAttacks(int i) {
        if (weaponExists(i)) {
            return weapons[i].getWeapon().getAttacks();
        }
        return "";
    }

    public int getWeaponDamage(int i) {
        if (weaponExists(i)) {
            return weapons[i].getTotalDamage();
        }
        return 0;
    }

    public int getWeaponInjury(int i) {
        if (weaponExists(i)) {
            return weapons[i].getTotalInjury();
        }
        return 0;
    }

    public String getWeaponRange(int i) {
        if (weaponExists(i)) {
            return weapons[i].getWeapon().getRange();
        }
        return "";
    }

    public String getWeaponSpecial(int i) {
        if (weaponExists(i)) {
            return weapons[i].getWeapon().getDescription();
        }
        return "";
    }

    public float getWeaponValue(int i) {
        if (weaponExists(i)) {
            return weapons[i].getWeapon().getValue();
        }
        return 0;
    }

    public Boolean weaponExists(int i) {
        if (weapons == null) {
            return false;
        }
        return weapons[i] != null;
    }




    private void calcTotalShadow() {
        int total = 0;
        total += adventures;
        total += routsCaused;
        total += knockouts * 2;
        total += maims * 3;
        total += warWounds * 4;
        total += kills * 5;
        total += shadow;
        totalShadow = total;
        calcLevel();
    }

    public void calcLevel(){
        int shadowCounter = totalShadow;
        int subCounter = 5;
        level = 0;

        shadowCounter -= subCounter;
        while(shadowCounter >= 0) {
            level++;
            subCounter += 5;
            shadowCounter -= subCounter;
        }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(int typeValue) {
        this.typeValue = typeValue;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
        calcArmorTotals();
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getPsyche() {
        return psyche;
    }

    public void setPsyche(int psyche) {
        this.psyche = psyche;
        calcRout();
    }

    private void calcRout() {
        rout = 7 - (psyche + routBonus + level);
    }

    public int getRout() {
        return rout;
    }


    public int getTotalDefense() {
        return totalDefense;
    }

    public int getRoutBonus() {
        return routBonus;
    }

    public void setRoutBonus(int routBonus) {
        this.routBonus = routBonus;
        calcRout();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        charge = calcCharge();
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getChargeBonus() {
        return chargeBonus;
    }

    public void setChargeBonus(int chargeBonus) {
        this.chargeBonus = chargeBonus;
        charge = calcCharge();
    }

    public int getArmorBonus() {
        return armorBonus;
    }

    public void setArmorBonus(int armorBonus) {
        this.armorBonus = armorBonus;
    }

    public int getSpeedPenalty() {
        return speedPenalty;
    }

    public void setSpeedPenalty(int speedPenalty) {
        this.speedPenalty = speedPenalty;
    }

    public float getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(float armorValue) {
        this.armorValue = (int) armorValue;
    }

    public ArrayList<SkillContainer> getSkillList() {
        return skillList;
    }

    public void setSkillList(ArrayList<SkillContainer> skillList) {
        this.skillList = skillList;
        calcWarriorValue();
    }

    public ItemContainer[] getItems() {
        return gearList;
    }

    public void setItemList(ItemContainer[] gearList) {
        this.gearList = gearList;
    }

    public int getTotalShadow() {
        return totalShadow;
    }

    public void setTotalShadow(int totalShadow) {
        this.totalShadow = totalShadow;
    }

    public float getWarriorValue() {
        calcWarriorValue();
        return warriorValue;
    }
}
