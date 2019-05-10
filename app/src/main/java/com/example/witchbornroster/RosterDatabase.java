package com.example.witchbornroster;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities={Roster.class}, version=1, exportSchema = false)
public abstract class RosterDatabase extends RoomDatabase {

    public abstract RosterDao rosterDao();
    private static RosterDatabase INSTANCE;

    static RosterDatabase getDatabase(final Context context){
        synchronized (RosterDatabase.class){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RosterDatabase.class, "roster_database").build();
            }
        }
        return INSTANCE;
    }


}
