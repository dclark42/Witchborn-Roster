package com.example.witchbornroster;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RosterDao {
    @Insert
    void insert(Roster... rosters);

    @Update
    void updateRosters(Roster... rosters);

    @Delete
    void deleteRosters(Roster... rosters);

    @Query("DELETE FROM roster_table")
    void deleteAll();

    @Query("SELECT * FROM roster_table WHERE id= :id")
    LiveData<Roster> getRoster(int id);

    @Query("SELECT * FROM roster_table")
    LiveData<List<Roster>> getAllRosters();
}
