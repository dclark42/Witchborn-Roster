package com.example.witchbornroster;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public String fromWarriorList(List<Warrior> warriors) {
        if (warriors == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Warrior>>() {}.getType();
        String json = gson.toJson(warriors, type);
        return json;
    }

    @TypeConverter
    public List<Warrior> toWarriorList(String warriorString) {
        if (warriorString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Warrior>>() {}.getType();
        List<Warrior> warriors = gson.fromJson(warriorString, type);
        return warriors;
    }

}
