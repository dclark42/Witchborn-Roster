package com.example.witchbornroster;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class WarriorEditor extends AppCompatActivity {
    private String TAG = WarriorEditor.class.getSimpleName();

    private static final int ARMOR_INTENT_RESULT = 10;
    private static final int WEAPON_INTENT_RESULT = 20;
    private static final int GEAR_INTENT_RESULT = 30;
    private static final int SKILL_INTENT_RESULT = 40;
    private static final int EXP_INTENT_RESULT = 50;

    private RosterViewModel rosterViewModel;
    private Roster roster;
    private Warrior warrior;

    private TextView warriorNumTextView;
    private EditText warriorNameTextView;

    private TextView warriorTypeTextView;
    private TextView levelTextView;

    private EditText defenseTextView;
    private EditText defenseSpecialTextView;
    private EditText strengthTextView;
    private TextView strengthSpecialTextView;
    private EditText agilityTextView;
    private TextView agilitySpecialTextView;
    private EditText psycheTextView;
    private TextView routTextView;
    private EditText routBonusTextView;
    private EditText speedTextView;
    private EditText speedSpecialTextView;
    private TextView chargeTextView;
    private EditText chargeBonusEditText;

    private TableLayout armorTableLayout;
    private TextView armorNameTextView;
    private TextView armorBonusTextView;
    private TextView speedPenaltyTextView;
    private TextView armorCostTextView;

    private TableRow weapon1RowLayout;
    private TextView weapon1NameTextView;
    private TextView weapon1AttacksTextView;
    private TextView weapon1DamageTextView;
    private TextView weapon1InjuryTextView;
    private TextView weapon1RangeTextView;
    private TextView weapon1SpecialTextView;
    private TextView weapon1CostTextView;
    private TableRow weapon2RowLayout;
    private TextView weapon2NameTextView;
    private TextView weapon2AttacksTextView;
    private TextView weapon2DamageTextView;
    private TextView weapon2InjuryTextView;
    private TextView weapon2RangeTextView;
    private TextView weapon2SpecialTextView;
    private TextView weapon2CostTextView;
    private TableRow weapon3RowLayout;
    private TextView weapon3NameTextView;
    private TextView weapon3AttacksTextView;
    private TextView weapon3DamageTextView;
    private TextView weapon3InjuryTextView;
    private TextView weapon3RangeTextView;
    private TextView weapon3SpecialTextView;
    private TextView weapon3CostTextView;

    private FlexboxLayout skillsBox;
    private LinearLayout gearLinearLayout;

    private TableLayout expTableLayout;
    private TextView adventureTextView;
    private TextView koTextView;
    private TextView maimTextView;
    private TextView warWoundTextView;
    private TextView killTextView;
    private TextView shadowTextView;
    private TextView totalShadowTextView;
    private TextView totalValueTextView;

    private Button cancelButton;
    private Button submitButton;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_warrior);

        context = getApplicationContext();

        rosterViewModel = ViewModelProviders.of(this).get(RosterViewModel.class);
        int id = getIntent().getIntExtra("roster_id", 0);
        if (id != 0) {
            final int warriorPosition = getIntent().getIntExtra("warrior_id", 0);
            rosterViewModel.setRoster(id);
            roster = rosterViewModel.getRosters().getValue();
            rosterViewModel.getRosters().observe(this, new Observer<Roster>() {
                @Override
                public void onChanged(@Nullable Roster roster) {
                    WarriorEditor.this.roster = roster;
                    updateUI(warriorPosition);
                }
            });

        }

        warriorNumTextView = findViewById(R.id.edit_warrior_num);
        warriorNameTextView = findViewById(R.id.edit_warrior_name);
        warriorTypeTextView = findViewById(R.id.edit_warrior_type);
        levelTextView = findViewById(R.id.edit_level_textview);
        defenseTextView = findViewById(R.id.edit_defense_score);
        defenseSpecialTextView = findViewById(R.id.edit_defense_special);
        strengthTextView = findViewById(R.id.edit_strength_score);
        strengthSpecialTextView = findViewById(R.id.edit_strength_special);
        agilityTextView = findViewById(R.id.edit_agility_score);
        agilitySpecialTextView = findViewById(R.id.edit_agility_special);
        psycheTextView = findViewById(R.id.edit_psyche_score);
        routTextView = findViewById(R.id.edit_rout);
        routBonusTextView = findViewById(R.id.edit_rout_bonus);
        speedTextView = findViewById(R.id.edit_speed_score);
        speedSpecialTextView = findViewById(R.id.edit_speed_special);
        chargeTextView = findViewById(R.id.edit_charge);
        chargeBonusEditText = findViewById(R.id.edit_charge_bonus);

        armorTableLayout = findViewById(R.id.edit_armor_tablelayout);
        armorTableLayout.setOnClickListener(new ArmorTableClickListener());
        armorNameTextView = findViewById(R.id.edit_armor_name);
        armorBonusTextView = findViewById(R.id.edit_armor_bonus);
        speedPenaltyTextView = findViewById(R.id.edit_speed_penalty);
        armorCostTextView = findViewById(R.id.edit_armor_cost);

        weapon1RowLayout = findViewById(R.id.editor_weapon_row1);
        weapon1NameTextView = findViewById(R.id.edit_weapon1_name);
        weapon1AttacksTextView = findViewById(R.id.edit_weapon1_attacks);
        weapon1DamageTextView = findViewById(R.id.edit_weapon1_damage);
        weapon1InjuryTextView = findViewById(R.id.edit_weapon1_injury);
        weapon1RangeTextView = findViewById(R.id.edit_weapon1_range);
        weapon1SpecialTextView = findViewById(R.id.edit_weapon1_special);
        weapon1CostTextView = findViewById(R.id.edit_weapon1_cost);
        weapon2RowLayout = findViewById(R.id.editor_weapon_row2);
        weapon2NameTextView = findViewById(R.id.edit_weapon2_name);
        weapon2AttacksTextView = findViewById(R.id.edit_weapon2_attacks);
        weapon2DamageTextView = findViewById(R.id.edit_weapon2_damage);
        weapon2InjuryTextView = findViewById(R.id.edit_weapon2_injury);
        weapon2RangeTextView = findViewById(R.id.edit_weapon2_range);
        weapon2SpecialTextView = findViewById(R.id.edit_weapon2_special);
        weapon2CostTextView = findViewById(R.id.edit_weapon2_cost);
        weapon3RowLayout = findViewById(R.id.editor_weapon_row3);
        weapon3NameTextView = findViewById(R.id.edit_weapon3_name);
        weapon3AttacksTextView = findViewById(R.id.edit_weapon3_attacks);
        weapon3DamageTextView = findViewById(R.id.edit_weapon3_damage);
        weapon3InjuryTextView = findViewById(R.id.edit_weapon3_injury);
        weapon3RangeTextView = findViewById(R.id.edit_weapon3_range);
        weapon3SpecialTextView = findViewById(R.id.edit_weapon3_special);
        weapon3CostTextView = findViewById(R.id.edit_weapon3_cost);


        skillsBox = findViewById(R.id.edit_skills_box);
        skillsBox.setOnClickListener(new SkillsOnClick());
        gearLinearLayout = findViewById(R.id.edit_gear_box);

        expTableLayout = findViewById(R.id.exp_tablelayout);
        expTableLayout.setOnClickListener(new expTableOnClickListener());
        adventureTextView = findViewById(R.id.edit_adventure_count);
        koTextView = findViewById(R.id.edit_ko_count);
        maimTextView = findViewById(R.id.edit_maim_count);
        warWoundTextView = findViewById(R.id.edit_war_wound_count);
        killTextView = findViewById(R.id.edit_kill_count);
        shadowTextView = findViewById(R.id.edit_shadow);
        totalShadowTextView = findViewById(R.id.edit_total_shadow);
        totalValueTextView = findViewById(R.id.edit_total_value_textview);

        cancelButton = findViewById(R.id.edit_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton = findViewById(R.id.edit_submit_button);
        submitButton.setOnClickListener(new SubmitButtonClick());
    }

    private void updateUI(int warriorPosition) {
        if (roster != null) {
            if (warrior == null) {
                warrior = roster.getWarriors().get(warriorPosition);
            }

            warriorNumTextView.setText(warrior.getNum() + "");
            warriorNameTextView.setText(warrior.getName());
            warriorTypeTextView.setText(warrior.getType());
            levelTextView.setText(warrior.getLevel() + "");
            defenseTextView.setText(warrior.getBaseDefense() + "");
            defenseSpecialTextView.setText("");
            strengthTextView.setText(warrior.getStrength() + "");
            strengthSpecialTextView.setText("");
            agilityTextView.setText(warrior.getAgility() + "");
            agilitySpecialTextView.setText("");
            psycheTextView.setText(warrior.getPsyche() + "");
            routTextView.setText(warrior.getRout() + "");
            routBonusTextView.setText(warrior.getRoutBonus() + "");
            speedTextView.setText(warrior.getSpeed() + "");
            speedSpecialTextView.setText("");
            chargeTextView.setText(warrior.getCharge() + "");
            chargeBonusEditText.setText(warrior.getChargeBonus() + "");
            armorNameTextView.setText(warrior.getArmorNames());
            armorBonusTextView.setText(warrior.getArmorBonus() + "");
            speedPenaltyTextView.setText(warrior.getSpeedPenalty() + "");
            armorCostTextView.setText(warrior.getArmorValue() + "");

            weapon1RowLayout.setOnClickListener(new WeaponTableClickListener(0, warrior.getWeapon(0)));
            weapon1NameTextView.setText(warrior.getWeaponName(0));
            weapon1AttacksTextView.setText(warrior.getWeaponAttacks(0));
            weapon1DamageTextView.setText(warrior.getWeaponDamage(0) + "");
            weapon1InjuryTextView.setText(warrior.getWeaponInjury(0) + "");
            weapon1RangeTextView.setText(warrior.getWeaponRange(0));
            weapon1SpecialTextView.setText(warrior.getWeaponSpecial(0));
            weapon1CostTextView.setText(warrior.getWeaponValue(0) + "");

            weapon2RowLayout.setOnClickListener(new WeaponTableClickListener(1, warrior.getWeapon(1)));
            weapon2NameTextView.setText(warrior.getWeaponName(1));
            weapon2AttacksTextView.setText(warrior.getWeaponAttacks(1));
            weapon2DamageTextView.setText(warrior.getWeaponDamage(1) + "");
            weapon2InjuryTextView.setText(warrior.getWeaponInjury(1) + "");
            weapon2RangeTextView.setText(warrior.getWeaponRange(1));
            weapon2SpecialTextView.setText(warrior.getWeaponSpecial(1));
            weapon2CostTextView.setText(warrior.getWeaponValue(1) + "");

            weapon3RowLayout.setOnClickListener(new WeaponTableClickListener(2, warrior.getWeapon(2)));
            weapon3NameTextView.setText(warrior.getWeaponName(2));
            weapon3AttacksTextView.setText(warrior.getWeaponAttacks(2));
            weapon3DamageTextView.setText(warrior.getWeaponDamage(2) + "");
            weapon3InjuryTextView.setText(warrior.getWeaponInjury(2) + "");
            weapon3RangeTextView.setText(warrior.getWeaponRange(2) + "");
            weapon3SpecialTextView.setText(warrior.getWeaponSpecial(2));
            weapon3CostTextView.setText(warrior.getWeaponValue(2) + "");

            populateSkillList();

            populateItemList();

            populateExp();

            gearLinearLayout.setOnClickListener(new gearClickListener());

            warriorNameTextView.addTextChangedListener(new nameListener());
            defenseTextView.addTextChangedListener(new DefenseChangeListener());
            strengthTextView.addTextChangedListener(new StrengthChangeListener());
            agilityTextView.addTextChangedListener(new AgilityChangeListener());
            psycheTextView.addTextChangedListener(new PsycheChangeListener());
            routBonusTextView.addTextChangedListener(new PsycheChangeListener());
            speedTextView.addTextChangedListener(new SpeedChangeListener());
            chargeBonusEditText.addTextChangedListener(new ChargeBonusChangeListener());

        }
    }

    public void populateExp(){
        adventureTextView.setText(warrior.getAdventures() + "");
        koTextView.setText(warrior.getKnockouts() + "");
        maimTextView.setText(warrior.getMaims() + "");
        warWoundTextView.setText(warrior.getWarWounds() + "");
        killTextView.setText(warrior.getKills() + "");
        shadowTextView.setText(warrior.getShadow() + "");
        totalShadowTextView.setText(warrior.getTotalShadow() + "");
        totalValueTextView.setText(warrior.getWarriorValue() + "");
    }

    public void populateSkillList(){
        skillsBox.removeAllViewsInLayout();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (SkillContainer skillContainer : warrior.getSkillList()) {
            TextView skillTextView = new TextView(context);
            skillTextView.setLayoutParams(layoutParams);
            skillTextView.setPadding(0, 0, convertDpToPx(10), convertDpToPx(5));
            skillTextView.setText(skillContainer.getSkill().getName());
            skillsBox.addView(skillTextView);
        }
    }

    public void populateItemList() {
        gearLinearLayout.removeAllViewsInLayout();
        gearLinearLayout.removeAllViewsInLayout();
        for (ItemContainer itemContainer : warrior.getItems()) {
            TextView itemTextView = new TextView(context);
            itemTextView.setBackgroundResource(R.drawable.rectangle_thin_border);
            itemTextView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            if (itemContainer != null) {
                itemTextView.setText(itemContainer.toString());
            } else {
                itemTextView.setText("");
            }
            gearLinearLayout.addView(itemTextView);
        }
    }

    public int convertDpToPx(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        int px = (int) (dp * density);
        return px;
    }

    private class nameListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            warrior.setName(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class SubmitButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            rosterViewModel.update(roster);
            finish();
        }
    }

    private class DefenseChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                warrior.setBaseDefense(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Error: not a number", Toast.LENGTH_SHORT);
                defenseTextView.setText(warrior.getBaseDefense() + "");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class StrengthChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                warrior.setStrength(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Error: not a number", Toast.LENGTH_SHORT);
                strengthTextView.setText(warrior.getStrength() + "");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class AgilityChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                warrior.setAgility(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Error: not a number", Toast.LENGTH_SHORT);
                agilityTextView.setText(warrior.getAgility() + "");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class PsycheChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                warrior.setPsyche(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Error: not a number", Toast.LENGTH_SHORT);
                psycheTextView.setText(warrior.getPsyche() + "");
            }
            routTextView.setText(warrior.getRout() + "");
        }
    }

    private class routBonusChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                warrior.setRoutBonus(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Error: not a number", Toast.LENGTH_SHORT);
                routBonusTextView.setText(warrior.getRoutBonus() + "");
            }
            routTextView.setText(warrior.getRout() + "");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class SpeedChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                warrior.setSpeed(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Error: not a number", Toast.LENGTH_SHORT);
                speedTextView.setText(warrior.getSpeed() + "");
            }
            chargeTextView.setText(warrior.getCharge() + "");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class ChargeBonusChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                warrior.setChargeBonus(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Error: not a number", Toast.LENGTH_SHORT);
                chargeBonusEditText.setText(warrior.getChargeBonus() + "");
            }
            chargeTextView.setText(warrior.getCharge() + "");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class ArmorTableClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent armorIntent = new Intent(WarriorEditor.this, ArmorEditActivity.class);
            armorIntent.putParcelableArrayListExtra("armors", warrior.getArmorList());
            startActivityForResult(armorIntent, ARMOR_INTENT_RESULT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ARMOR_INTENT_RESULT) {
            warrior.setArmorList(data.<Armor>getParcelableArrayListExtra("armors"));
            armorNameTextView.setText(warrior.getArmorNames());
            armorBonusTextView.setText(warrior.getArmorBonus() + "");
            speedPenaltyTextView.setText(warrior.getSpeedPenalty() + "");
            armorCostTextView.setText(warrior.getArmorValue() + "");
        } else if (resultCode == RESULT_OK && requestCode == WEAPON_INTENT_RESULT) {
            int slot = data.getIntExtra("slot", 10);
            if (slot != 10) {
                Weapon wep = data.getParcelableExtra("weapon");
                warrior.equipWeapon(slot, wep);

                switch (slot) {
                    case 0:
                        weapon1NameTextView.setText(warrior.getWeaponName(0));
                        weapon1AttacksTextView.setText(warrior.getWeaponAttacks(0));
                        weapon1DamageTextView.setText(warrior.getWeaponDamage(0) + "");
                        weapon1InjuryTextView.setText(warrior.getWeaponInjury(0) + "");
                        weapon1RangeTextView.setText(warrior.getWeaponRange(0));
                        weapon1SpecialTextView.setText(warrior.getWeaponSpecial(0));
                        weapon1CostTextView.setText(warrior.getWeaponValue(0) + "");
                        break;
                    case 1:
                        weapon2NameTextView.setText(warrior.getWeaponName(1));
                        weapon2AttacksTextView.setText(warrior.getWeaponAttacks(1));
                        weapon2DamageTextView.setText(warrior.getWeaponDamage(1) + "");
                        weapon2InjuryTextView.setText(warrior.getWeaponInjury(1) + "");
                        weapon2RangeTextView.setText(warrior.getWeaponRange(1));
                        weapon2SpecialTextView.setText(warrior.getWeaponSpecial(1));
                        weapon2CostTextView.setText(warrior.getWeaponValue(1) + "");
                        break;
                    case 2:
                        weapon3NameTextView.setText(warrior.getWeaponName(2));
                        weapon3AttacksTextView.setText(warrior.getWeaponAttacks(2));
                        weapon3DamageTextView.setText(warrior.getWeaponDamage(2) + "");
                        weapon3InjuryTextView.setText(warrior.getWeaponInjury(2) + "");
                        weapon3RangeTextView.setText(warrior.getWeaponRange(2) + "");
                        weapon3SpecialTextView.setText(warrior.getWeaponSpecial(2));
                        weapon3CostTextView.setText(warrior.getWeaponValue(2) + "");
                    default:
                        break;
                }
            } else {

            }
        } else if (resultCode == RESULT_OK && requestCode == GEAR_INTENT_RESULT) {
            ArrayList<ItemContainer> itemArrayList;
            itemArrayList = data.getParcelableArrayListExtra("items");
            if (itemArrayList != null) {
                warrior.setItemList(itemArrayList.toArray(warrior.getItems()));
                populateItemList();
            }
        } else if (resultCode == RESULT_OK && requestCode == SKILL_INTENT_RESULT) {
            warrior.setSkillList(data.<SkillContainer>getParcelableArrayListExtra("skills"));
            populateSkillList();
        }else if (resultCode == RESULT_OK && requestCode == EXP_INTENT_RESULT) {
            int[] expValues = data.getIntArrayExtra("expValues");
            warrior.setAdventures(expValues[0]);
            warrior.setRoutsCaused(expValues[1]);
            warrior.setKnockouts(expValues[2]);
            warrior.setMaims(expValues[3]);
            warrior.setWarWounds(expValues[4]);
            warrior.setKills(expValues[5]);
            warrior.setShadow(expValues[6]);
            populateExp();
        }
        warrior.calcWarriorValue();
        totalValueTextView.setText(warrior.getWarriorValue() + "");
    }

    private class WeaponTableClickListener implements View.OnClickListener {
        int slot;
        Weapon weapon;

        @Override
        public void onClick(View v) {
            Intent weaponIntent = new Intent(WarriorEditor.this, WeaponPicker.class);
            weaponIntent.putExtra("weapon", weapon);
            weaponIntent.putExtra("slot", slot);
            Log.d(TAG, "Weapon slot: " + slot);
            startActivityForResult(weaponIntent, WEAPON_INTENT_RESULT);
        }

        public WeaponTableClickListener(int slot, Weapon weapon) {
            this.slot = slot;
            this.weapon = weapon;
        }
    }

    private class gearClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent gearIntent = new Intent(WarriorEditor.this, GearActivity.class);
            ArrayList<ItemContainer> itemArrayList;
            itemArrayList = new ArrayList<>(Arrays.asList(warrior.getItems()));
            gearIntent.putParcelableArrayListExtra("items", itemArrayList);
            startActivityForResult(gearIntent, GEAR_INTENT_RESULT);
        }
    }

    private class SkillsOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent skillIntent = new Intent(WarriorEditor.this, SkillsEditorActivity.class);
            skillIntent.putParcelableArrayListExtra("skills", warrior.getSkillList());
            startActivityForResult(skillIntent, SKILL_INTENT_RESULT);
        }


    }

    private class expTableOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent expIntent = new Intent(WarriorEditor.this, EXPActivity.class);
            int[] expArray = {warrior.getAdventures(), warrior.getRoutsCaused(), warrior.getKnockouts(), warrior.getMaims(), warrior.getWarWounds(), warrior.getKills(), warrior.getShadow()};
            expIntent.putExtra("expValues", expArray);
            startActivityForResult(expIntent, EXP_INTENT_RESULT);

        }
    }
}
