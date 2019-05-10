package com.example.witchbornroster;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "roster_table")
public class Roster {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters(Converters.class)
    private List<Warrior> warriors = new ArrayList<Warrior>();

    private int value;
    private String clanType;

    @ColumnInfo(name="roster_title")
    private String clanName;

    public Roster(int id, List<Warrior> warriors, int value, String clanType, String clanName) {
        this.id = id;
        this.warriors = warriors;
        this.value = value;
        this.clanType = clanType;
        this.clanName = clanName;
    }

    @Ignore
    public Roster(List<Warrior> warriors, String clanType, String clanName) {
        this.warriors = warriors;
        this.clanType = clanType;
        this.clanName = clanName;
        calcValue();
    }

    @Ignore
    public Roster(String clanType, String clanName) {
        this.clanType = clanType;
        this.clanName = clanName;
        value = 0;
        }

    public void addWarrior(Warrior warrior){
        warriors.add(warrior);
        calcValue();
    }

    public void deleteWarrior(Warrior warrior){
        warriors.remove(warrior);
    }

    public void calcValue(){
        value = 0;
        for (Warrior w: warriors
        ) {
            value += w.getWarriorValue();
        }
    }

    public List<Warrior> getWarriors() {
        return warriors;
    }

    public void setWarriors(List<Warrior> warriors) {
        this.warriors = warriors;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        calcValue();
        return value;
    }

    public String getClanType() {
        return clanType;
    }

    public void setClanType(String clanType) {
        this.clanType = clanType;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }
}
