package com.example.witchbornroster;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class RostersListViewModel extends AndroidViewModel {
    private RosterRepository repository;
    private LiveData<List<Roster>> rosters;

    public RostersListViewModel(@NonNull Application application) {
        super(application);
        repository = new RosterRepository(application);
        rosters = repository.getAllRosters();
    }



    public LiveData<List<Roster>> getAllRosters(){
        return rosters;
    }

    public void initialize(){
        repository.initialize();
    }
}
