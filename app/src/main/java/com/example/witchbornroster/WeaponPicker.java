package com.example.witchbornroster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class WeaponPicker extends AppCompatActivity {
    private Button cancelButton;
    private Button deleteButton;
    private Context context;
    LayoutInflater inflater;

    TableLayout tableLayout;

    Weapon weapon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_picker);

        context = getApplicationContext();

        weapon = getIntent().getParcelableExtra("weapon");

        cancelButton = findViewById(R.id.weapon_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteButton = findViewById(R.id.delete_weapon_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weapon = null;
                Intent returnIntent = new Intent();
                returnIntent.putExtra("weapon", weapon);
                returnIntent.putExtra("slot", getIntent().getIntExtra("slot", 10));
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        tableLayout = findViewById(R.id.weapon_picker_table);
        inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (Weapon wep : MainActivity.weaponsDB){
            addWeaponRow(wep);
        }
    }

    private void addWeaponRow(Weapon wep) {
        final Weapon wepSave = wep;
        final LinearLayout row = (LinearLayout) inflater.inflate(R.layout.weapon_list_row,null);
        ((TextView)row.findViewById(R.id.weapon_name)).setText(wep.getName());
        ((TextView)row.findViewById(R.id.weapon_attacks)).setText(wep.getAttacks());
        ((TextView)row.findViewById(R.id.weapon_damage)).setText(wep.getDamage() + "");
        ((TextView)row.findViewById(R.id.weapon_injury)).setText(wep.getInjury() + "");
        ((TextView)row.findViewById(R.id.weapon_range)).setText(wep.getRange());
        ((TextView)row.findViewById(R.id.weapon_special)).setText(wep.getDescription());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("weapon", wepSave);
                returnIntent.putExtra("slot", getIntent().getIntExtra("slot", 10));
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        tableLayout.addView(row);
    }
}
