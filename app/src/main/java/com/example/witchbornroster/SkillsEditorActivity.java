package com.example.witchbornroster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class SkillsEditorActivity extends AppCompatActivity {

    private Button cancelButton;
    private Button submitButton;
    private Context context;
    private LayoutInflater inflater;
    FloatingActionButton fab;

    LinearLayout linearLayout;

    private ArrayList<SkillContainer> skills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_editor);

        skills = getIntent().getParcelableArrayListExtra("skills");

        context = getApplicationContext();

        cancelButton = findViewById(R.id.skill_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton = findViewById(R.id.skill_submit_button);
        submitButton.setOnClickListener(new SubmitButtonClick());

        linearLayout = findViewById(R.id.skills_editor_layout);

        for (SkillContainer skillContainer : skills) {
            addSkillRow(skillContainer);
        }

        fab = findViewById(R.id.addSkillButton);
        fab.setOnClickListener(new addSkillClickListener());
    }

    private class addSkillClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(SkillsEditorActivity.this);

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });



            LinearLayout linearLayoutParent = new LinearLayout(context);
            linearLayoutParent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayoutParent.setOrientation(LinearLayout.VERTICAL);

            ScrollView scrollView = new ScrollView(context);
            scrollView.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setWeightSum(1);

            final Dialog dialog = builder.create();

            LayoutInflater inflater = LayoutInflater.from(context);
            for (int i = 0; i < MainActivity.gearsDB.size(); i++) {
                final Skill skill = MainActivity.skillsDB.get(i);

                View view = inflater.inflate(R.layout.skill_view_row, null);

                TextView skillCategoryListItem = view.findViewById(R.id.skill_category_row_textview);
                skillCategoryListItem.setText(skill.getCategory());

                TextView skillTypeListItem = view.findViewById(R.id.skill_type_row_textview);
                skillTypeListItem.setText(skill.getType());

                TextView skillNameListItem = view.findViewById(R.id.skill_name_row_textview);
                skillNameListItem.setText(skill.getName());

                TextView skillPrereqListItem = view.findViewById(R.id.skil_prereq_row_textview);
                skillPrereqListItem.setText(skill.getPrerequisite());


                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SkillContainer skillContainer = new SkillContainer(skill, skill.getSkillValue());
                        skills.add(skillContainer);
                        addSkillRow(skillContainer);
                        dialog.dismiss();
                    }
                });
                linearLayout.addView(view);
            }

            scrollView.addView(linearLayout);
            linearLayoutParent.addView(scrollView);

            CheckBox checkBox = new CheckBox(context);
            checkBox.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            checkBox.setText("Free");
            linearLayoutParent.addView(checkBox);

            ((AlertDialog) dialog).setView(linearLayoutParent);
            dialog.show();
        }
    }

    private void addSkillRow(final SkillContainer skillContainer) {
        final TextView skillView = new TextView(context);

        skillView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        skillView.setPadding(0, convertDpToPx(10), 0, convertDpToPx(10));
        skillView.setBackgroundResource(R.drawable.rectangle_thin_border);
        skillView.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
        skillView.setText(skillContainer.getSkill().getName());

        skillView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(SkillsEditorActivity.this);
                builder.setTitle("Delete this skill?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        skills.remove(skillContainer);
                        linearLayout.removeView(skillView);
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        linearLayout.addView(skillView);
    }

    private class SubmitButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();
            returnIntent.putParcelableArrayListExtra("skills", skills);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }
    public int convertDpToPx(int dp){
        float density = context.getResources().getDisplayMetrics().density;
        int px = (int)(dp * density);
        return px;
    }
}
