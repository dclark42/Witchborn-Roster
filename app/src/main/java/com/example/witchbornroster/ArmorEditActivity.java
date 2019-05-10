package com.example.witchbornroster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArmorEditActivity extends AppCompatActivity {
    private Button cancelButton;
    private Button submitButton;
    private Context context;
    private LayoutInflater inflater;

    private TableLayout tableLayout;

    private ArrayList<Armor> armors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armor_edit);

        context = getApplicationContext();

        armors = getIntent().getParcelableArrayListExtra("armors");

        cancelButton = findViewById(R.id.armor_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton = findViewById(R.id.armor_submit_button);
        submitButton.setOnClickListener(new SubmitButtonClick());

        tableLayout = findViewById(R.id.armor_tablelayout_editor);

        inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (Armor armor : armors) {
            addArmorRow(armor);
        }

        FloatingActionButton fab = findViewById(R.id.addArmorButton);
        fab.setOnClickListener(new addArmorClickListener());

    }

    private void addArmorRow(final Armor armor){
        final TableRow row = (TableRow) inflater.inflate(R.layout.armor_table_row,null);
        ((TextView)row.findViewById(R.id.armor_editor_name)).setText(armor.getName());
        ((TextView)row.findViewById(R.id.armor_editor_bonus)).setText(armor.getDefense() + "");
        ((TextView)row.findViewById(R.id.armor_speed_penalty_editor)).setText(armor.getSpeedPenalty() + "");
        ((TextView)row.findViewById(R.id.armor_cost_editor)).setText(armor.getValue() + "");

        row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(ArmorEditActivity.this);
                builder.setTitle("Delete this armor?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        armors.remove(armor);
                        tableLayout.removeView(row);
                        dialog.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });

        tableLayout.addView(row);
    }

    private class SubmitButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();
            returnIntent.putParcelableArrayListExtra("armors", armors);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

    private class addArmorClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Dialog addArmorDialog = new Dialog(ArmorEditActivity.this);
            addArmorDialog.setContentView(R.layout.add_armor_table);

            ViewGroup layout = addArmorDialog.findViewById(R.id.armor_tablelayout_editor);

            LayoutInflater inflater = LayoutInflater.from(context);

            for (int i = 0; i < MainActivity.armorsDB.size(); i++) {
                final Armor armor = MainActivity.armorsDB.get(i);
                View view = inflater.inflate(R.layout.armor_table_row, null);

                TextView armorName = view.findViewById(R.id.armor_editor_name);
                TextView armorDefense = view.findViewById(R.id.armor_editor_bonus);
                TextView armorSpeedPenalty = view.findViewById(R.id.armor_speed_penalty_editor);
                TextView armorCost = view.findViewById(R.id.armor_cost_editor);

                armorName.setText(armor.getName());
                armorDefense.setText(armor.getDefense() + "");
                armorSpeedPenalty.setText(armor.getSpeedPenalty() + "");
                armorCost.setText((int)armor.getValue() + "");

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        armors.add(armor);
                        addArmorRow(armor);
                        addArmorDialog.dismiss();
                    }
                });
                layout.addView(view);
            }

            addArmorDialog.show();
        }
    }
}
