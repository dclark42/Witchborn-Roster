package com.example.witchbornroster;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RosterActivity extends AppCompatActivity {
    private RecyclerView warriorRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private WarriorListAdapter warriorListAdapter;
    private RosterViewModel rosterViewModel;
    private Roster roster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);
        rosterViewModel = ViewModelProviders.of(this).get(RosterViewModel.class);

        int id = getIntent().getIntExtra("id", 0);
        if (id != 0){
            rosterViewModel.setRoster(id);
            roster = rosterViewModel.getRosters().getValue();
            rosterViewModel.getRosters().observe(this, new Observer<Roster>() {
                @Override
                public void onChanged(@Nullable Roster roster) {
                    RosterActivity.this.roster = roster;
                    updateUI();
                }
            });
        }

        warriorRecyclerView = findViewById(R.id.warriorRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        warriorListAdapter = new WarriorListAdapter(this);
        warriorRecyclerView.setAdapter(warriorListAdapter);
        warriorRecyclerView.setLayoutManager(layoutManager);

        warriorListAdapter.setOnItemClickListener(new WarriorListAdapter.RecyclerViewClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Intent warriorIntent = new Intent(RosterActivity.this, WarriorEditor.class);
                warriorIntent.putExtra("roster_id", roster.getId());
                warriorIntent.putExtra("warrior_id", position);
                startActivity(warriorIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        roster.calcValue();
    }

    private void updateUI(){
        if (roster != null){
            warriorListAdapter.setWarriors(roster.getWarriors());
        }
    }
    
}
