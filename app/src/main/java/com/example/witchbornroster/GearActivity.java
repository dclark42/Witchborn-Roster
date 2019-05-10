package com.example.witchbornroster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GearActivity extends AppCompatActivity {
    private Button cancelButton;
    private Button submitButton;
    private Context context;

    private ItemContainer[] items = new ItemContainer[8];

    private EditText gearQuantity1;
    private TextView gearName1;
    private TextView gearValue1;
    private EditText gearQuantity2;
    private TextView gearName2;
    private TextView gearValue2;
    private EditText gearQuantity3;
    private TextView gearName3;
    private TextView gearValue3;
    private EditText gearQuantity4;
    private TextView gearName4;
    private TextView gearValue4;
    private EditText gearQuantity5;
    private TextView gearName5;
    private TextView gearValue5;
    private EditText gearQuantity6;
    private TextView gearName6;
    private TextView gearValue6;
    private EditText gearQuantity7;
    private TextView gearName7;
    private TextView gearValue7;
    private EditText gearQuantity8;
    private TextView gearName8;
    private TextView gearValue8;

    private LinearLayout slot1;
    private LinearLayout slot2;
    private LinearLayout slot3;
    private LinearLayout slot4;
    private LinearLayout slot5;
    private LinearLayout slot6;
    private LinearLayout slot7;
    private LinearLayout slot8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gear);

        context = getApplicationContext();

        ArrayList<ItemContainer> itemArrayList;
        itemArrayList = getIntent().getParcelableArrayListExtra("items");
        if (itemArrayList != null) {
            items = itemArrayList.toArray(items);
        }

        cancelButton = findViewById(R.id.gear_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton = findViewById(R.id.gear_submit_button);
        submitButton.setOnClickListener(new SubmitButtonClick());
        gearQuantity1 = findViewById(R.id.quantity_gear_slot1);
        if (items[0] != null) {
            gearQuantity1.setText(items[0].getQuantity() + "");
        }
        gearQuantity1.addTextChangedListener(new quantityChangedTextListener(0));
        gearName1 = findViewById(R.id.name_gear_slot1);
        gearValue1 = findViewById(R.id.cost_gear_slot1);
        gearQuantity2 = findViewById(R.id.quantity_gear_slot2);
        if (items[1] != null) {
            gearQuantity2.setText(items[1].getQuantity() + "");
        }
        gearQuantity2.addTextChangedListener(new quantityChangedTextListener(1));
        gearName2 = findViewById(R.id.name_gear_slot2);
        gearValue2 = findViewById(R.id.cost_gear_slot2);
        gearQuantity3 = findViewById(R.id.quantity_gear_slot3);
        if (items[2] != null) {
            gearQuantity3.setText(items[2].getQuantity() + "");
        }
        gearQuantity3.addTextChangedListener(new quantityChangedTextListener(2));
        gearName3 = findViewById(R.id.name_gear_slot3);
        gearValue3 = findViewById(R.id.cost_gear_slot3);
        gearQuantity4 = findViewById(R.id.quantity_gear_slot4);
        if (items[3] != null) {
            gearQuantity4.setText(items[3].getQuantity() + "");
        }
        gearQuantity4.addTextChangedListener(new quantityChangedTextListener(3));
        gearName4 = findViewById(R.id.name_gear_slot4);
        gearValue4 = findViewById(R.id.cost_gear_slot4);
        gearQuantity5 = findViewById(R.id.quantity_gear_slot5);
        if (items[4] != null) {
            gearQuantity5.setText(items[4].getQuantity() + "");
        }
        gearQuantity5.addTextChangedListener(new quantityChangedTextListener(4));
        gearName5 = findViewById(R.id.name_gear_slot5);
        gearValue5 = findViewById(R.id.cost_gear_slot5);
        gearQuantity6 = findViewById(R.id.quantity_gear_slot6);
        if (items[5] != null) {
            gearQuantity6.setText(items[5].getQuantity() + "");
        }
        gearQuantity6.addTextChangedListener(new quantityChangedTextListener(5));
        gearName6 = findViewById(R.id.name_gear_slot6);
        gearValue6 = findViewById(R.id.cost_gear_slot6);
        gearQuantity7 = findViewById(R.id.quantity_gear_slot7);
        if (items[6] != null) {
            gearQuantity7.setText(items[6].getQuantity() + "");
        }
        gearQuantity7.addTextChangedListener(new quantityChangedTextListener(6));
        gearName7 = findViewById(R.id.name_gear_slot7);
        gearValue7 = findViewById(R.id.cost_gear_slot7);
        gearQuantity8 = findViewById(R.id.quantity_gear_slot8);
        if (items[7] != null) {
            gearQuantity8.setText(items[7].getQuantity() + "");
        }
        gearQuantity8.addTextChangedListener(new quantityChangedTextListener(7));
        gearName8 = findViewById(R.id.name_gear_slot8);
        gearValue8 = findViewById(R.id.cost_gear_slot8);

        slot1 = findViewById(R.id.gear_slot1);
        slot2 = findViewById(R.id.gear_slot2);
        slot3 = findViewById(R.id.gear_slot3);
        slot4 = findViewById(R.id.gear_slot4);
        slot5 = findViewById(R.id.gear_slot5);
        slot6 = findViewById(R.id.gear_slot6);
        slot7 = findViewById(R.id.gear_slot7);
        slot8 = findViewById(R.id.gear_slot8);

        slot1.setOnClickListener(new GearSlotOnClickListener(gearQuantity1, gearName1, gearValue1, 0));
        slot2.setOnClickListener(new GearSlotOnClickListener(gearQuantity2, gearName2, gearValue2, 1));
        slot3.setOnClickListener(new GearSlotOnClickListener(gearQuantity3, gearName3, gearValue3, 2));
        slot4.setOnClickListener(new GearSlotOnClickListener(gearQuantity4, gearName4, gearValue4, 3));
        slot5.setOnClickListener(new GearSlotOnClickListener(gearQuantity5, gearName5, gearValue5, 4));
        slot6.setOnClickListener(new GearSlotOnClickListener(gearQuantity6, gearName6, gearValue6, 5));
        slot7.setOnClickListener(new GearSlotOnClickListener(gearQuantity7, gearName7, gearValue7, 6));
        slot8.setOnClickListener(new GearSlotOnClickListener(gearQuantity8, gearName8, gearValue8, 7));

        populateNewItemList();

    }

    public void populateNewItemList() {
        if (items[0] != null) {
            gearName1.setText(items[0].getItem().getName());
            gearValue1.setText(items[0].getItemsValue() + "");
        }
        if (items[1] != null) {
            gearName2.setText(items[1].getItem().getName());
            gearValue2.setText(items[1].getItemsValue() + "");
        }
        if (items[2] != null) {
            gearName3.setText(items[2].getItem().getName());
            gearValue3.setText(items[2].getItemsValue() + "");
        }
        if (items[3] != null) {
            gearName4.setText(items[3].getItem().getName());
            gearValue4.setText(items[3].getItemsValue() + "");
        }
        if (items[4] != null) {
            gearName5.setText(items[4].getItem().getName());
            gearValue5.setText(items[4].getItemsValue() + "");
        }
        if (items[5] != null) {
            gearName6.setText(items[5].getItem().getName());
            gearValue6.setText(items[5].getItemsValue() + "");
        }
        if (items[6] != null) {
            gearName7.setText(items[6].getItem().getName());
            gearValue7.setText(items[6].getItemsValue() + "");
        }
        if (items[7] != null) {
            gearName8.setText(items[7].getItem().getName());
            gearValue8.setText(items[7].getItemsValue() + "");
        }
    }

    public void populateItemList() {
        if (items[0] != null) {
            gearName1.setText(items[0].getItem().getName());
            gearValue1.setText(items[0].getItemsValue() + "");
            slot1.setOnClickListener(new GearSlotOnClickListener(gearQuantity1, gearName1, gearValue1, 0));
        }
        if (items[1] != null) {
            gearName2.setText(items[1].getItem().getName());
            gearValue2.setText(items[1].getItemsValue() + "");
            slot2.setOnClickListener(new GearSlotOnClickListener(gearQuantity2, gearName2, gearValue2, 1));
        }
        if (items[2] != null) {
            gearName3.setText(items[2].getItem().getName());
            gearValue3.setText(items[2].getItemsValue() + "");
            slot3.setOnClickListener(new GearSlotOnClickListener(gearQuantity3, gearName3, gearValue3, 2));
        }
        if (items[3] != null) {
            gearName4.setText(items[3].getItem().getName());
            gearValue4.setText(items[3].getItemsValue() + "");
            slot4.setOnClickListener(new GearSlotOnClickListener(gearQuantity4, gearName4, gearValue4, 3));
        }
        if (items[4] != null) {
            gearName5.setText(items[4].getItem().getName());
            gearValue5.setText(items[4].getItemsValue() + "");
            slot5.setOnClickListener(new GearSlotOnClickListener(gearQuantity5, gearName5, gearValue5, 4));
        }
        if (items[5] != null) {
            gearName6.setText(items[5].getItem().getName());
            gearValue6.setText(items[5].getItemsValue() + "");
            slot6.setOnClickListener(new GearSlotOnClickListener(gearQuantity6, gearName6, gearValue6, 5));
        }
        if (items[6] != null) {
            gearName7.setText(items[6].getItem().getName());
            gearValue7.setText(items[6].getItemsValue() + "");
            slot7.setOnClickListener(new GearSlotOnClickListener(gearQuantity7, gearName7, gearValue7, 6));
        }
        if (items[7] != null) {
            gearName8.setText(items[7].getItem().getName());
            gearValue8.setText(items[7].getItemsValue() + "");
            slot8.setOnClickListener(new GearSlotOnClickListener(gearQuantity8, gearName8, gearValue8, 7));
        }
    }

    private class quantityChangedTextListener implements TextWatcher {
        int i;

        public quantityChangedTextListener(int i) {
            this.i = i;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() != 0){
                items[i].setQuantity(Integer.parseInt(s.toString()));
                populateItemList();
            }

        }
    }

    private class SubmitButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();
            ArrayList<ItemContainer> itemArrayList;
            itemArrayList = new ArrayList<>(Arrays.asList(items));
            returnIntent.putParcelableArrayListExtra("items", itemArrayList);
            setResult(RESULT_OK, returnIntent);
            finish();

        }
    }

    private class GearSlotOnClickListener implements View.OnClickListener {
        TextView gearQuantity;
        TextView gearName;
        TextView gearValue;
        int slot;

        public GearSlotOnClickListener(TextView gearQuantity, TextView gearName, TextView gearValue, int slot) {
            this.gearQuantity = gearQuantity;
            this.gearName = gearName;
            this.gearValue = gearValue;
            this.slot = slot;
        }

        @Override
        public void onClick(View v) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(GearActivity.this);

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setNeutralButton("Delete Item", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    items[slot] = null;
                    gearQuantity.setText("");
                    gearName.setText("");
                    gearName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    gearValue.setText("");
                    dialog.dismiss();
                }
            });

            final Dialog dialog = builder.create();
            ScrollView scrollView = new ScrollView(context);
            scrollView.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            LayoutInflater inflater = LayoutInflater.from(context);
            for (int i = 0; i < MainActivity.gearsDB.size(); i++) {
                final Gear gear = MainActivity.gearsDB.get(i);

                View view = inflater.inflate(R.layout.gear_list_row, null);
                TextView gearClanListItem = view.findViewById(R.id.gear_clan_picker);
                gearClanListItem.setText(gear.getClan());

                TextView gearNameListItem = view.findViewById(R.id.gear_name_picker);
                StringBuilder gearNameStringBuilder = new StringBuilder();
                gearNameStringBuilder.append(gear.getName());
                if (gear.isArtifact()) {
                    gearNameStringBuilder.append("*");
                }
                gearNameListItem.setText(gearNameStringBuilder.toString());
                if (gear.isConsumable()) {
                    gearNameListItem.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_drop_black_18dp, 0, 0, 0);
                } else {
                    gearNameListItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }

                TextView gearRarityListItem = view.findViewById(R.id.gear_rarity_picker);
                if (gear.getRarity() > 0) {
                    gearRarityListItem.setText("Rare" + gear.getRarity());
                } else {
                    gearRarityListItem.setText("");
                }

                TextView gearCostListItem = view.findViewById(R.id.gear_cost_picker);
                gearCostListItem.setText(gear.getValue() + "");

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (items[slot] == null) {
                            items[slot] = new ItemContainer();
                        }
                        items[slot].setItem(gear);
                        items[slot].setQuantity(1);
                        populateItemList();
                        dialog.dismiss();


                    }
                });
                linearLayout.addView(view);
            }
            scrollView.addView(linearLayout);
            ((AlertDialog) dialog).setView(scrollView);
            dialog.show();

        }
    }
}
