package com.example.witchbornroster;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class RosterRepository {
    private RosterDao rosterDao;
    private LiveData<List<Roster>> rosters;

    public RosterRepository(Application application) {
        RosterDatabase rosterDatabase = RosterDatabase.getDatabase(application);
        rosterDao = rosterDatabase.rosterDao();
        rosters = rosterDao.getAllRosters();
    }

    LiveData<List<Roster>> getAllRosters(){
        return rosters;
    }

    LiveData<Roster> getRoster(int id){
        return rosterDao.getRoster(id);
    }

    void deleteRoster(Roster roster){
        DeleteAsync deleteAsync = new DeleteAsync(rosterDao);
        deleteAsync.execute(roster);
    }

    void updateRoster(Roster roster){
        UpdateAsync updateAsync = new UpdateAsync(rosterDao);
        updateAsync.execute(roster);
    }

    void insertRoster(Roster roster){
        InsertAsync insertAsync = new InsertAsync(rosterDao);
        insertAsync.execute(roster);
    }

    void initialize(){
        PopulateDb populateDb = new PopulateDb(rosterDao);
        populateDb.execute();
    }

    private class InsertAsync extends AsyncTask<Roster, Void, Void>{
        private final RosterDao dao;

        public InsertAsync(RosterDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Roster... rosters) {
            dao.insert(rosters[0]);
            return null;
        }
    }

    private class DeleteAsync extends AsyncTask<Roster, Void, Void>{
        private final RosterDao dao;

        public DeleteAsync(RosterDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Roster... rosters) {
            dao.deleteRosters(rosters[0]);
            return null;
        }
    }

    private class UpdateAsync extends AsyncTask<Roster, Void, Void>{
        private final RosterDao dao;

        public UpdateAsync(RosterDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Roster... rosters) {
            dao.updateRosters(rosters[0]);
            return null;
        }
    }

    private static class PopulateDb extends AsyncTask<Void, Void, Void>{
        private final RosterDao dao;

        public PopulateDb(RosterDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();

            //Testing Stuff
            List<Warrior> orcs = new ArrayList<Warrior>();
            orcs.add(WarriorFactory.createWarrior("Warlord"));
            orcs.add(WarriorFactory.createWarrior("Harbinger"));
            orcs.add(WarriorFactory.createWarrior("Freebooter"));
            orcs.add(WarriorFactory.createWarrior("Freebooter"));
            orcs.add(WarriorFactory.createWarrior("Scourge"));
            orcs.add(WarriorFactory.createWarrior("Ogre"));
            orcs.add(WarriorFactory.createWarrior("Reaver"));
            orcs.add(WarriorFactory.createWarrior("Freebooter"));
            Roster orcRoster = new Roster(orcs, "Orcs", "Test Orc");

            dao.insert(orcRoster);

            List<Warrior> dwarves = new ArrayList<Warrior>();
            dwarves.add(WarriorFactory.createWarrior("Jarl"));
            dwarves.add(WarriorFactory.createWarrior("Karl"));
            dwarves.add(WarriorFactory.createWarrior("Karl"));
            dwarves.add(WarriorFactory.createWarrior("Binder"));
            dwarves.add(WarriorFactory.createWarrior("Ax of the Jarl"));
            dwarves.add(WarriorFactory.createWarrior("Dragon Hunter"));
            dwarves.add(WarriorFactory.createWarrior("Bolt Hunter"));
            dwarves.add(WarriorFactory.createWarrior("Karl"));
            Roster dwarfRoster = new Roster(dwarves, "Dwarfs", "Test Dwarves");

            dao.insert(dwarfRoster);

            /*
            List<Roster> testRoster = new ArrayList<Roster>();
            testRoster.add(orcRoster);
            testRoster.add(dwarfRoster);
            rosterListAdapter.setWarClans(testRoster);
            */

            return null;
        }
    }

}
