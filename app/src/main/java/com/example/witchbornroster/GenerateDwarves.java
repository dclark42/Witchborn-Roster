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

public class GenerateDwarves extends AppCompatActivity {
    private List<Warrior> warriors = new ArrayList<Warrior>();
    private String clanName = "Dwarves";

    private TextView totalValueTextView;
    private int totalValue;
    private TextView warriorCountTextView;
    private int warriorCount;
    private EditText nameEditText;

    private Button binderMinusButton;
    private TextView binderCountTextView;
    private Button binderPlusButton;
    private int binderCount;

    private Button karlMinusButton;
    private TextView karlCountTextView;
    private Button karlPlusButton;
    private int karlCount;

    private Button boltHunterMinusButton;
    private TextView boltHunterCountTextView;
    private Button boltHunterPlusButton;
    private int boltHunterCount;

    private Button axOfJarlMinusButton;
    private TextView axOfJarlCountTextView;
    private Button axOfJarlPlusButton;
    private int axOfJarlCount;

    private Button dragonHunterMinusButton;
    private TextView dragonHunterCountTextView;
    private Button dragonHunterPlusButton;
    private int dragonHunterCount;

    private Button createRosterButton;
    private RosterViewModel rosterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_dwarves);

        rosterViewModel = ViewModelProviders.of(this).get(RosterViewModel.class);

        totalValue = WarriorFactory.JARL_VALUE;
        warriorCount = 1;

        nameEditText = findViewById(R.id.rosterNameEditText);
        nameEditText.addTextChangedListener(new clanNameListener());

        totalValueTextView = findViewById(R.id.create_point_total);
        totalValueTextView.setText(totalValue + "");
        warriorCountTextView = findViewById(R.id.create_warrior_count);
        warriorCountTextView.setText(warriorCount + "");

        binderMinusButton = findViewById(R.id.binder_minus);
        binderMinusButton.setOnClickListener(new binderOnMinusClickListener());
        binderCountTextView = findViewById(R.id.binder_count);
        binderPlusButton = findViewById(R.id.binder_plus);
        binderPlusButton.setOnClickListener(new binderOnPlusClickListener());
        binderCount = 0;

        karlMinusButton = findViewById(R.id.karl_minus);
        karlMinusButton.setOnClickListener(new karlOnMinusClickListener());
        karlCountTextView = findViewById(R.id.karl_count);
        karlPlusButton = findViewById(R.id.karl_plus);
        karlPlusButton.setOnClickListener(new karlOnPlusClickListener());
        karlCount = 0;

        boltHunterMinusButton = findViewById(R.id.boltHunter_minus);
        boltHunterMinusButton.setOnClickListener(new boltHunterOnMinusClickListener());
        boltHunterCountTextView = findViewById(R.id.boltHunter_count);
        boltHunterPlusButton = findViewById(R.id.boltHunter_plus);
        boltHunterPlusButton.setOnClickListener(new boltHunterOnPlusClickListener());
        boltHunterCount = 0;

        axOfJarlMinusButton = findViewById(R.id.axOfJarl_minus);
        axOfJarlMinusButton.setOnClickListener(new axOfJarlOnMinusClickListener());
        axOfJarlCountTextView = findViewById(R.id.axOfJarl_count);
        axOfJarlPlusButton = findViewById(R.id.axOfJarl_plus);
        axOfJarlPlusButton.setOnClickListener(new axOfJarlOnPlusClickListener());
        axOfJarlCount = 0;

        dragonHunterMinusButton = findViewById(R.id.dragonHunter_minus);
        dragonHunterMinusButton.setOnClickListener(new dragonHunterOnMinusClickListener());
        dragonHunterCountTextView = findViewById(R.id.dragonHunter_count);
        dragonHunterPlusButton = findViewById(R.id.dragonHunter_plus);
        dragonHunterPlusButton.setOnClickListener(new dragonHunterPlusClickListener());
        dragonHunterCount = 0;

        createRosterButton = findViewById(R.id.dwarfCreateButton);
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
        int sumValue = WarriorFactory.JARL_VALUE;
        sumValue += WarriorFactory.BINDER_VALUE * binderCount;
        sumValue += WarriorFactory.KARL_VALUE * karlCount;
        sumValue += WarriorFactory.BOLT_HUNTER_VALUE * boltHunterCount;
        sumValue += WarriorFactory.AX_OF_JARL_VALUE * axOfJarlCount;
        sumValue += WarriorFactory.DRAGON_HUNTER_VALUE * dragonHunterCount;
        totalValue = sumValue;
        totalValueTextView.setText(totalValue + "");
    }

    private Boolean changeWarriorCount(int i){
        if ((warriorCount + i) > 8){
            return false;
        }
        warriorCount = 1;
        warriorCount += binderCount;
        warriorCount += karlCount;
        warriorCount += boltHunterCount;
        warriorCount += axOfJarlCount;
        warriorCount += dragonHunterCount;
        warriorCountTextView.setText(warriorCount + "");
        calcValue();
        return true;
    }

    private class binderOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (binderCount > 0){
                binderCount--;
                changeWarriorCount(-1);
                binderCountTextView.setText(binderCount + "");
            }
        }
    }
    private class binderOnPlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (binderCount < 1) {
                if (changeWarriorCount(1)) {
                    binderCount++;
                    changeWarriorCount(1);
                    binderCountTextView.setText(binderCount + "");
                }
            }
        }
    }

    private class karlOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (karlCount > 0) {
                karlCount--;
                changeWarriorCount(-1);
                karlCountTextView.setText(karlCount + "");
            }
        }
    }
    private class karlOnPlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (karlCount < 8) {
                if (changeWarriorCount(1)) {
                    karlCount++;
                    changeWarriorCount(1);
                    karlCountTextView.setText(karlCount + "");
                }
            }
        }
    }

    private class boltHunterOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (boltHunterCount > 0) {
                boltHunterCount--;
                changeWarriorCount(-1);
                binderCountTextView.setText(boltHunterCount + "");
            }
        }
    }
    private class boltHunterOnPlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (boltHunterCount < 2) {
                if (changeWarriorCount(1)) {
                    boltHunterCount++;
                    changeWarriorCount(1);
                    boltHunterCountTextView.setText(boltHunterCount + "");
                }
            }
        }
    }

    private class axOfJarlOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (axOfJarlCount > 0) {
                axOfJarlCount--;
                changeWarriorCount(-1);
                axOfJarlCountTextView.setText(axOfJarlCount + "");
            }
        }
    }
    private class axOfJarlOnPlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (axOfJarlCount < 1) {
                if (changeWarriorCount(1)) {
                    axOfJarlCount++;
                    changeWarriorCount(1);
                    axOfJarlCountTextView.setText(axOfJarlCount + "");
                }
            }
        }
    }

    private class dragonHunterOnMinusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (dragonHunterCount > 0) {
                dragonHunterCount--;
                changeWarriorCount(-1);
                dragonHunterCountTextView.setText(dragonHunterCount + "");
            }
        }
    }
    private class dragonHunterPlusClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (dragonHunterCount < 1) {
                if (changeWarriorCount(1)) {
                    dragonHunterCount++;
                    changeWarriorCount(1);
                    dragonHunterCountTextView.setText(dragonHunterCount + "");
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

            warriors.add(WarriorFactory.createWarrior("Jarl"));
            int count = 0;
            warriors.get(count).setNum(count+1);
            count++;

            for (int i = 0; i < binderCount; i++){
                warriors.add(WarriorFactory.createWarrior("Binder"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            for (int i = 0; i < karlCount; i++){
                warriors.add(WarriorFactory.createWarrior("Karl"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            for (int i = 0; i < boltHunterCount; i++){
                warriors.add(WarriorFactory.createWarrior("Bolt Hunter"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            for (int i = 0; i < axOfJarlCount; i++){
                warriors.add(WarriorFactory.createWarrior("Ax of the Jarl"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            for (int i = 0; i < dragonHunterCount; i++){
                warriors.add(WarriorFactory.createWarrior("Dragon Hunter"));
                warriors.get(count).setNum(count+1);
                count++;
            }
            Roster roster = new Roster(warriors, "Dwarves", clanName);
            rosterViewModel.insert(roster);
            finish();
        }
    }
}
