package com.example.witchbornroster;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

public class RosterViewModel extends AndroidViewModel {
    private RosterRepository repository;
    private LiveData<Roster> roster;

    public RosterViewModel(@NonNull Application application) {
        super(application);
        repository = new RosterRepository(application);
    }

    public LiveData<Roster> getRosters() {
        return roster;
    }

    public void setRoster(int id) {
        this.roster = repository.getRoster(id);
    }

    public void insert(Roster roster){
        repository.insertRoster(roster);
    }

    public void update(Roster roster){
        repository.updateRoster(roster);
    }

    public void delete(Roster roster){
        repository.deleteRoster(roster);
    }


}
