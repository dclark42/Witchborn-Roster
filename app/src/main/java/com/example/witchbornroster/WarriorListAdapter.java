package com.example.witchbornroster;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class WarriorListAdapter extends RecyclerView.Adapter<WarriorListAdapter.WarriorHolder> {

    private RecyclerViewClickListener listener;

    public void setOnItemClickListener(RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public interface RecyclerViewClickListener {
        void onItemClick(View view, int position);
    }

    class WarriorHolder extends RecyclerView.ViewHolder{

        private final TextView warriorNumTextView;
        private final TextView warriorNameTextView;

        private final TextView warriorTypeTextView;
        private final TextView levelTextView;

        private final TextView defenseTextView;
        private final TextView defenseSpecialTextView;
        private final TextView strengthTextView;
        private final TextView strengthSpecialTextView;
        private final TextView agilityTextView;
        private final TextView agilitySpecialTextView;
        private final TextView psycheTextView;
        private final TextView psycheSpecialTextView;
        private final TextView routTextView;
        private final TextView speedTextView;
        private final TextView speedSpecialTextView;
        private final TextView chargeTextView;

        private final TextView armorNameTextView;
        private final TextView armorBonusTextView;
        private final TextView speedPenaltyTextView;
        private final TextView armorCostTextView;

        private final TextView weapon1NameTextView;
        private final TextView weapon1AttacksTextView;
        private final TextView weapon1DamageTextView;
        private final TextView weapon1InjuryTextView;
        private final TextView weapon1RangeTextView;
        private final TextView weapon1SpecialTextView;
        private final TextView weapon1CostTextView;
        private final TextView weapon2NameTextView;
        private final TextView weapon2AttacksTextView;
        private final TextView weapon2DamageTextView;
        private final TextView weapon2InjuryTextView;
        private final TextView weapon2RangeTextView;
        private final TextView weapon2SpecialTextView;
        private final TextView weapon2CostTextView;
        private final TextView weapon3NameTextView;
        private final TextView weapon3AttacksTextView;
        private final TextView weapon3DamageTextView;
        private final TextView weapon3InjuryTextView;
        private final TextView weapon3RangeTextView;
        private final TextView weapon3SpecialTextView;
        private final TextView weapon3CostTextView;

        private final FlexboxLayout skillsBox;
        private final LinearLayout gearLinearLayout;

        private final TextView adventureTextView;
        private final TextView koTextView;
        private final TextView maimTextView;
        private final TextView warWoundTextView;
        private final TextView killTextView;
        private final TextView shadowTextView;
        private final TextView totalShadowTextView;
        private final TextView totalValueTextView;


        public WarriorHolder(@NonNull final View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                    return false;
                }
            });

            this.warriorNumTextView = itemView.findViewById(R.id.warrior_num);
            this.warriorNameTextView = itemView.findViewById(R.id.warrior_name);

            this.warriorTypeTextView = itemView.findViewById(R.id.warrior_type);
            this.levelTextView = itemView.findViewById(R.id.level_textview);
            this.defenseTextView = itemView.findViewById(R.id.defense_score);
            this.defenseSpecialTextView = itemView.findViewById(R.id.defense_special);
            this.strengthTextView = itemView.findViewById(R.id.strength_score);
            this.strengthSpecialTextView = itemView.findViewById(R.id.strength_special);
            this.agilityTextView = itemView.findViewById(R.id.agility_score);
            this.agilitySpecialTextView = itemView.findViewById(R.id.agility_special);
            this.psycheTextView = itemView.findViewById(R.id.psyche_score);
            this.psycheSpecialTextView = itemView.findViewById(R.id.psyche_special);
            this.routTextView = itemView.findViewById(R.id.rout);
            this.speedTextView = itemView.findViewById(R.id.speed_score);
            this.speedSpecialTextView = itemView.findViewById(R.id.speed_special);
            this.chargeTextView = itemView.findViewById(R.id.charge);

            this.armorNameTextView = itemView.findViewById(R.id.armor_name);
            this.armorBonusTextView = itemView.findViewById(R.id.armor_bonus);
            this.speedPenaltyTextView = itemView.findViewById(R.id.speed_penalty);
            this.armorCostTextView = itemView.findViewById(R.id.armor_cost);
            this.weapon1NameTextView = itemView.findViewById(R.id.weapon1_name);
            this.weapon1AttacksTextView = itemView.findViewById(R.id.weapon1_attacks);
            this.weapon1DamageTextView = itemView.findViewById(R.id.weapon1_damage);
            this.weapon1InjuryTextView = itemView.findViewById(R.id.weapon1_injury);
            this.weapon1RangeTextView = itemView.findViewById(R.id.weapon1_range);
            this.weapon1SpecialTextView = itemView.findViewById(R.id.weapon1_special);
            this.weapon1CostTextView = itemView.findViewById(R.id.weapon1_cost);
            this.weapon2NameTextView = itemView.findViewById(R.id.weapon2_name);
            this.weapon2AttacksTextView = itemView.findViewById(R.id.weapon2_attacks);
            this.weapon2DamageTextView = itemView.findViewById(R.id.weapon2_damage);
            this.weapon2InjuryTextView = itemView.findViewById(R.id.weapon2_injury);
            this.weapon2RangeTextView = itemView.findViewById(R.id.weapon2_range);
            this.weapon2SpecialTextView = itemView.findViewById(R.id.weapon2_special);
            this.weapon2CostTextView = itemView.findViewById(R.id.weapon2_cost);
            this.weapon3NameTextView = itemView.findViewById(R.id.weapon3_name);
            this.weapon3AttacksTextView = itemView.findViewById(R.id.weapon3_attacks);
            this.weapon3DamageTextView = itemView.findViewById(R.id.weapon3_damage);
            this.weapon3InjuryTextView = itemView.findViewById(R.id.weapon3_injury);
            this.weapon3RangeTextView = itemView.findViewById(R.id.weapon3_range);
            this.weapon3SpecialTextView = itemView.findViewById(R.id.weapon3_special);
            this.weapon3CostTextView = itemView.findViewById(R.id.weapon3_cost);

            skillsBox = itemView.findViewById(R.id.skills_box);
            gearLinearLayout = itemView.findViewById(R.id.gear_box);

            this.adventureTextView = itemView.findViewById(R.id.adventure_count);
            this.koTextView = itemView.findViewById(R.id.ko_count);
            this.maimTextView = itemView.findViewById(R.id.maim_count);
            this.warWoundTextView = itemView.findViewById(R.id.war_wound_count);
            this.killTextView = itemView.findViewById(R.id.kill_count);
            this.shadowTextView = itemView.findViewById(R.id.shadow);
            this.totalShadowTextView = itemView.findViewById(R.id.total_shadow);
            this.totalValueTextView = itemView.findViewById(R.id.total_value_textview);
        }

    }

    private final LayoutInflater inflater;
    private List<Warrior> warriors;
    private Context context;

    public WarriorListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WarriorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.warrior_list, viewGroup, false);
        return new WarriorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WarriorHolder warriorHolder, int i) {
        if (warriors != null){
            Warrior warrior = warriors.get(i);

            warriorHolder.warriorNumTextView.setText(warrior.getNum() + "");
            warriorHolder.warriorNameTextView.setText(warrior.getName());
            warriorHolder.warriorTypeTextView.setText(warrior.getType());
            warriorHolder.levelTextView.setText(warrior.getLevel() + "");
            warriorHolder.defenseTextView.setText(warrior.getTotalDefense() + "");
            warriorHolder.defenseSpecialTextView.setText("");
            warriorHolder.strengthTextView.setText(warrior.getStrength() + "");
            warriorHolder.strengthSpecialTextView.setText("");
            warriorHolder.agilityTextView.setText(warrior.getAgility() + "");
            warriorHolder.agilitySpecialTextView.setText("");
            warriorHolder.psycheTextView.setText(warrior.getPsyche() + "");
            warriorHolder.psycheSpecialTextView.setText("");
            warriorHolder.routTextView.setText(warrior.getRout() + "");
            warriorHolder.speedTextView.setText(warrior.getSpeed() + "");
            warriorHolder.speedSpecialTextView.setText("");
            warriorHolder.chargeTextView.setText(warrior.getCharge() + "");

            warriorHolder.armorNameTextView.setText(warrior.getArmorNames());
            warriorHolder.armorBonusTextView.setText(warrior.getArmorBonus() + "");
            warriorHolder.speedPenaltyTextView.setText(warrior.getSpeedPenalty() + "");
            warriorHolder.armorCostTextView.setText(warrior.getArmorValue() + "");

            if (warrior.weaponExists(0)){
                warriorHolder.weapon1NameTextView.setText(warrior.getWeaponName(0));
                warriorHolder.weapon1AttacksTextView.setText(warrior.getWeaponAttacks(0));
                warriorHolder.weapon1DamageTextView.setText(warrior.getWeaponDamage(0) + "");
                warriorHolder.weapon1InjuryTextView.setText(warrior.getWeaponInjury(0) + "");
                warriorHolder.weapon1RangeTextView.setText(warrior.getWeaponRange(0));
                warriorHolder.weapon1SpecialTextView.setText(warrior.getWeaponSpecial(0));
                warriorHolder.weapon1CostTextView.setText(warrior.getWeaponValue(0) + "");
            }
            if (warrior.weaponExists(1)){
                warriorHolder.weapon2NameTextView.setText(warrior.getWeaponName(1));
                warriorHolder.weapon2AttacksTextView.setText(warrior.getWeaponAttacks(1));
                warriorHolder.weapon2DamageTextView.setText(warrior.getWeaponDamage(1) + "");
                warriorHolder.weapon2InjuryTextView.setText(warrior.getWeaponInjury(1) + "");
                warriorHolder.weapon2RangeTextView.setText(warrior.getWeaponRange(1));
                warriorHolder.weapon2SpecialTextView.setText(warrior.getWeaponSpecial(1));
                warriorHolder.weapon2CostTextView.setText(warrior.getWeaponValue(1) + "");
            }
            if (warrior.weaponExists(2)){
                warriorHolder.weapon3NameTextView.setText(warrior.getWeaponName(2));
                warriorHolder.weapon3AttacksTextView.setText(warrior.getWeaponAttacks(2));
                warriorHolder.weapon3DamageTextView.setText(warrior.getWeaponDamage(2) + "");
                warriorHolder.weapon3InjuryTextView.setText(warrior.getWeaponInjury(2) + "");
                warriorHolder.weapon3RangeTextView.setText(warrior.getWeaponRange(2));
                warriorHolder.weapon3SpecialTextView.setText(warrior.getWeaponSpecial(2));
                warriorHolder.weapon3CostTextView.setText(warrior.getWeaponValue(2) + "");
            }

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            warriorHolder.skillsBox.removeAllViewsInLayout();
            for (SkillContainer skillContainer: warrior.getSkillList()) {
                final TextView skillTextView = new TextView(context);
                skillTextView.setLayoutParams(layoutParams);
                skillTextView.setPadding(0, 0, convertDpToPx(10), convertDpToPx(5));
                skillTextView.setText(skillContainer.getSkill().getName());
                warriorHolder.skillsBox.addView(skillTextView);
            }

            warriorHolder.gearLinearLayout.removeAllViewsInLayout();
            for (ItemContainer itemContainer : warrior.getItems()) {
                final TextView itemTextView = new TextView(context);
                itemTextView.setBackgroundResource(R.drawable.rectangle_thin_border);
                itemTextView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                if (itemContainer != null){
                    itemTextView.setText(itemContainer.toString());
                } else{
                    itemTextView.setText("");
                }
                warriorHolder.gearLinearLayout.addView(itemTextView);
            }

            warriorHolder.adventureTextView.setText(warrior.getAdventures() + "");
            warriorHolder.koTextView.setText(warrior.getKnockouts() + "");
            warriorHolder.maimTextView.setText(warrior.getMaims() + "");
            warriorHolder.warWoundTextView.setText(warrior.getWarWounds() + "");
            warriorHolder.killTextView.setText(warrior.getKills() + "");
            warriorHolder.shadowTextView.setText(warrior.getShadow() + "");
            warriorHolder.totalShadowTextView.setText(warrior.getTotalShadow() + "");
            warriorHolder.totalValueTextView.setText(warrior.getWarriorValue() + "");
        }
    }

    public int convertDpToPx(int dp){
        float density = context.getResources().getDisplayMetrics().density;
        int px = (int)(dp * density);
        return px;
    }



    @Override
    public int getItemCount() {
        if (warriors != null){
            return warriors.size();
        }
        return 0;
    }

    public void setWarriors(List<Warrior> warriors) {
        this.warriors = warriors;
        notifyDataSetChanged();
    }


}
