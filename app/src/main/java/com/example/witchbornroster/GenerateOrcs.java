package com.example.witchbornroster;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GenerateOrcs extends AppCompatActivity {
    private List<Warrior> warriors = new ArrayList<Warrior>();
    private String clanName = "Orcs";

    private TextView totalValueTextView;
    private int totalValue;
    private TextView warriorCountTextView;
    private int warriorCount;
    private EditText nameEditText;

    private Button harbingerMinusButton;
    private TextView harbingerCountTextView;
    private Button harbingerPlusButton;
    private int harbingerCount;

    private Button freebooterMinusButton;
    private TextView freebooterCountTextView;
    private Button freebooterPlusButton;
    private int freebooterCount;

    private Button scourgeMinusButton;
    private TextView scourgeCountTextView;
    private Button scourgePlusButton;
    private int scourgeCount;

    private Button reaverMinusButton;
    private TextView reaverCountTextView;
    private Button reaverPlusButton;
    private int reaverCount;

    private Button ogreMinusButton;
    private TextView ogreCountTextView;
    private Button ogrePlusButton;
    private int ogreCount;

    private Button createRosterButton;
    private RosterViewModel rosterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_orcs);

        rosterViewModel = ViewModelProviders.of(this).get(RosterViewModel.class);

        totalValue = WarriorFactory.WARLORD_VALUE;
        warriorCount = 1;

        nameEditText = findViewById(R.id.rosterNameEditText);
        nameEditText.addTextChangedListener(new clanNameListener());

        totalValueTextView = findViewById(R.id.create_point_total);
        totalValueTextView.setText(totalValue + "");
        warriorCountTextView = findViewById(R.id.create_warrior_count);
        warriorCountTextView.setText(warriorCount + "");

        harbingerMinusButton = findViewById(R.id.harbinger_minus);
        harbingerMinusButton.setOnClickListener(new harbingerOnMinusClickListener());
        harbingerCountTextView = findViewById(R.id.harbinger_count);
        harbingerPlusButton = findViewById(R.id.harbinger_plus);
        harbingerPlusButton.setOnClickListener(new harbingerOnPlusClickListener());
        harbingerCount = 0;

        freebooterMinusButton = findViewById(R.id.freebooter_minus);
        freebooterMinusButton.setOnClickListener(new freebooterOnMinusClickListener());
        freebooterCountTextView = findViewById(R.id.freebooter_count);
        freebooterPlusButton = findViewById(R.id.freebooter_plus);
        freebooterPlusButton.setOnClickListener(new freebooterOnPlusClickListener());
        freebooterCount = 0;

        scourgeMinusButton = findViewById(R.id.scourge_minus);
        scourgeMinusButton.setOnClickListener(new scourgeOnMinusClickListener());
        scourgeCountTextView = findViewById(R.id.scourge_count);
        scourgePlusButton = findViewById(R.id.scourge_plus);
        scourgePlusButton.setOnClickListener(new scourgeOnPlusClickListener());
        scourgeCount = 0;

        reaverMinusButton = findViewById(R.id.reaver_minus);
        reaverMinusButton.setOnClickListener(new reaverOnMinusClickListener());
        reaverCountTextView = findViewById(R.id.reaver_count);
        reaverPlusButton = findViewById(R.id.reaver_plus);
        reaverPlusButton.setOnClickListener(new reaverOnPlusClickListener());
        reaverCount = 0;

        ogreMinusButton = findViewById(R.id.ogre_minus);
        ogreMinusButton.setOnClickListener(new ogreOnMinusClickListener());
        ogreCountTextView = findViewById(R.id.ogre_count);
        ogrePlusButton = findViewById(R.id.ogre_plus);
        ogrePlusButton.setOnClickListener(new ogrePlusClickListener());
        ogreCount = 0;

        createRosterButton = findViewById(R.id.orcCreateButton);
        createRosterButton.setOnClickListener(new createClickListener());


    }

    private class clanNameListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            clanName = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void calcValue(){
        int sumValue = WarriorFactory.WARLORD_VALUE;
        sumValue += WarriorFactory.HARBINGER_VALUE * harbingerCount;
        sumValue += WarriorFactory.FREEBOOTER_VALUE * freebooterCount;
        sumValue += WarriorFactory.SCROUGE_VALUE * scourgeCount;
        sumValue += WarriorFactory.REAVER_VALUE * reaverCount;
        sumValue += WarriorFactory.OGRE_VALUE * ogreCount;
        totalValue = sumValue;
        totalValueTextView.setText(totalValue + "");
    }

    private Boolean changeWarriorCount(int i){
        if ((warriorCount + i) > 8){
            return false;
        }

        warriorCount = 1;
        warriorCount += harbingerCount;
        warriorCount += freebooterCount;
        warriorCount += scourgeCount;
        warriorCount += reaverCount;
        warriorCount += ogreCount;

        warriorCountTextView.setText(warriorCount +"");
        calcValue();
        return true;
    }

    private class harbingerOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (harbingerCount > 0){
                harbingerCount--;
                changeWarriorCount(-1);
                harbingerCountTextView.setText(harbingerCount + "");
            }
        }
    }
    private class harbingerOnPlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (harbingerCount < 1) {
                if (changeWarriorCount(1)) {
                    harbingerCount++;
                    changeWarriorCount(1);
                    harbingerCountTextView.setText(harbingerCount + "");
                }
            }
        }
    }

    private class freebooterOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (freebooterCount > 0) {
                freebooterCount--;
                changeWarriorCount(-1);
                freebooterCountTextView.setText(freebooterCount + "");
            }
        }
    }
    private class freebooterOnPlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (freebooterCount < 8) {
                if (changeWarriorCount(1)) {
                    freebooterCount++;
                    changeWarriorCount(1);
                    freebooterCountTextView.setText(freebooterCount + "");
                }
            }
        }
    }

    private class scourgeOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (scourgeCount > 0) {
                scourgeCount--;
                changeWarriorCount(-1);
                scourgeCountTextView.setText(scourgeCount + "");
            }
        }
    }
    private class scourgeOnPlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (scourgeCount < 1) {
                if (changeWarriorCount(1)) {
                    scourgeCount++;
                    changeWarriorCount(1);
                    scourgeCountTextView.setText(scourgeCount + "");
                }
            }
        }
    }

    private class reaverOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (reaverCount > 0) {
                reaverCount--;
                changeWarriorCount(-1);
                reaverCountTextView.setText(reaverCount + "");
            }
        }
    }
    private class reaverOnPlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (reaverCount < 2) {
                if (changeWarriorCount(1)) {
                    reaverCount++;
                    changeWarriorCount(1);
                    reaverCountTextView.setText(reaverCount + "");
                }
            }
        }
    }

    private class ogreOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (ogreCount > 0) {
                ogreCount--;
                changeWarriorCount(-1);
                ogreCountTextView.setText(ogreCount + "");
            }
        }
    }
    private class ogrePlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (ogreCount < 1) {
                if (changeWarriorCount(1)) {
                    ogreCount++;
                    changeWarriorCount(1);
                    ogreCountTextView.setText(ogreCount + "");
                }
            }
        }
    }

    private class createClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(warriorCount != 8){
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"Error: There must be exactly 8 warriors.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            if (totalValue > 90){
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"Error: Total warrior value must be 90 or less.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            warriors.add(WarriorFactory.createWarrior("Warlord"));
            int count = 0;
            warriors.get(count).setNum(count+1);
            count++;

            for (int i = 0; i < harbingerCount; i++){
                warriors.add(WarriorFactory.createWarrior("Harbinger"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            for (int i = 0; i < freebooterCount; i++){
                warriors.add(WarriorFactory.createWarrior("Freebooter"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            for (int i = 0; i < scourgeCount; i++){
                warriors.add(WarriorFactory.createWarrior("Scourge"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            for (int i = 0; i < reaverCount; i++){
                warriors.add(WarriorFactory.createWarrior("Reaver"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            for (int i = 0; i < ogreCount; i++){
                warriors.add(WarriorFactory.createWarrior("Ogre"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            Roster roster = new Roster(warriors, "Orcs", clanName);
            rosterViewModel.insert(roster);
            finish();
        }
    }
}