package com.example.witchbornroster;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RosterListAdapter.RosterListAdapterOnClickHandler, RosterListAdapter.RosterListAdapterOnLongClickHandler {
    private RecyclerView rosterRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RosterListAdapter rosterListAdapter;
    private RostersListViewModel rostersListViewModel;
    private RosterViewModel rosterViewModel;

    public static List<Weapon> weaponsDB;
    public static List<Armor> armorsDB;
    public static List<Gear> gearsDB;
    public static List<Skill> skillsDB;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        weaponsDB = new ArrayList<>();
        armorsDB = new ArrayList<>();
        gearsDB = new ArrayList<>();
        skillsDB = new ArrayList<>();
        generateDB();

        rostersListViewModel = ViewModelProviders.of(this).get(RostersListViewModel.class);
        rostersListViewModel.getAllRosters().observe(this, new Observer<List<Roster>>() {
            @Override
            public void onChanged(@Nullable List<Roster> rosters) {
                rosterListAdapter.setWarClans(rosters);
            }
        });


        rosterRecyclerView = findViewById(R.id.rosterRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        rosterListAdapter = new RosterListAdapter(this, this, this);
        rosterRecyclerView.setAdapter(rosterListAdapter);
        rosterRecyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.addRosterButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] clans = {"Orcs", "Dwarves"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pick a clan");
                builder.setItems(clans, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                        switch (which){
                            case 0: intent = new Intent(MainActivity.this, GenerateOrcs.class);
                                break;
                            case 1: intent = new Intent(MainActivity.this, GenerateDwarves.class);
                                break;
                                default: intent = new Intent(MainActivity.this, GenerateOrcs.class);
                        }
                        MainActivity.this.startActivity(intent);
                    }
                });
                builder.show();
            }
        });

        //rostersListViewModel.initialize();

    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @Override
    public void onLongClick(Roster roster) {
        Log.d(TAG, "Long Click Working");
        final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Delete this roster?");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rosterViewModel = ViewModelProviders.of(MainActivity.this).get(RosterViewModel.class);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onClick(Roster roster) {
        Intent rosterIntent = new Intent(this, RosterActivity.class);
        rosterIntent.putExtra("id", roster.getId());
        startActivity(rosterIntent);
    }

    public String loadJSON(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("witchborn_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
    public void generateDB() {
        try{
            JSONObject jsonDb = new JSONObject(loadJSON(getApplicationContext()));
            JSONArray weaponsArray = jsonDb.getJSONArray("Weapons");
            JSONArray armorArray = jsonDb.getJSONArray("Armor");
            JSONArray gearArray = jsonDb.getJSONArray("Gear");
            JSONArray skillsArray = jsonDb.getJSONArray("Skills");

            for(int i = 0; i < weaponsArray.length(); i++){
                JSONObject weaponObj = weaponsArray.getJSONObject(i);

                String name = weaponObj.getString("Name");
                String clan = weaponObj.getString("Clan");
                float value = weaponObj.getLong("Value");
                int rarity = weaponObj.getInt("Rarity");
                boolean artifact = weaponObj.getBoolean("Artifact");
                String description = weaponObj.getString("Description");
                int hands = weaponObj.getInt("Hands");
                String attacks = weaponObj.getString("Attacks");
                int damage = weaponObj.getInt("Damage");
                int chargeDamage = weaponObj.getInt("Charge Damage");
                String damageType = weaponObj.getString("Damage Type");
                int injury = weaponObj.getInt("Injury");
                String range = weaponObj.getString("Range");
                String siege = weaponObj.getString("Siege");

                weaponsDB.add(new Weapon(name, clan, value, rarity, artifact, description, hands, attacks, damage, chargeDamage, damageType, injury, range, siege));
            }

            for(int i = 0; i < armorArray.length(); i++){
                JSONObject armorObj = armorArray.getJSONObject(i);

                String name = armorObj.getString("Name");
                String clan = armorObj.getString("Clan");
                float value = armorObj.getLong("Value");
                int rarity = armorObj.getInt("Rarity");
                boolean artifact = armorObj.getBoolean("Artifact");
                String description = armorObj.getString("Description");
                int defense = armorObj.getInt("Defense");
                int speedPenalty = armorObj.getInt("Speed Penalty");
                String bulk = armorObj.getString("Bulk");

                armorsDB.add(new Armor(name, clan, value, rarity, artifact, description, defense, speedPenalty, bulk));
            }

            for(int i = 0; i < gearArray.length(); i++){
                JSONObject gearObj = gearArray.getJSONObject(i);

                String name = gearObj.getString("Name");
                String clan = gearObj.getString("Clan");
                float value = gearObj.getLong("Value");
                int rarity = gearObj.getInt("Rarity");
                boolean artifact = gearObj.getBoolean("Artifact");
                String description = gearObj.getString("Description");
                boolean consumable = gearObj.getBoolean("Consumable");

                gearsDB.add(new Gear(name, clan, value, rarity, artifact, description, consumable));
            }

            for(int i = 0; i < skillsArray.length(); i++){
                JSONObject skillObj = skillsArray.getJSONObject(i);

                String category = skillObj.getString("Category");
                String type = skillObj.getString("Type");
                String name = skillObj.getString("Name");
                String prerequisite = skillObj.getString("Prerequisite");
                String description = skillObj.getString("Restrictions");
                String restrictions = skillObj.getString("Description");

                skillsDB.add(new Skill(category, type, name, prerequisite, description, restrictions));
            }
            
        } catch (JSONException e){
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }


    }
}
