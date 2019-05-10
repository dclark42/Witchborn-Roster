package com.example.witchbornroster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class EXPActivity extends AppCompatActivity {
    private Context context;

    private Button adMinusButton;
    private TextView adCountTextview;
    private Button adPlusButton;

    private Button routMinusButton;
    private TextView routCountTextview;
    private Button routPlusButton;

    private Button koMinusButton;
    private TextView koCountTextview;
    private Button koPlusButton;

    private Button maimMinusButton;
    private TextView maimCountTextview;
    private Button maimPlusButton;

    private Button wwMinusButton;
    private TextView wwCountTextview;
    private Button wwPlusButton;

    private Button killMinusButton;
    private TextView killCountTextview;
    private Button killPlusButton;

    private Button shadowMinusButton;
    private TextView shadowCountTextview;
    private Button shadowPlusButton;

    private TextView totalShadowTextview;
    private TextView levelTextview;

    private Button cancelButton;
    private Button submitButton;

    private int[] adventures;
    private int[] routs;
    private int[] kos;
    private int[] maims;
    private int[] wws;
    private int[] kills;
    private int[] shadow;
    private int totalShadow;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp);

        context = getApplicationContext();

        int[] expValues = getIntent().getIntArrayExtra("expValues");

        level = 0;

        adventures = new int[]{expValues[0]};
        routs = new int[]{expValues[1]};
        kos = new int[]{expValues[2]};
        maims = new int[]{expValues[3]};
        wws = new int[]{expValues[4]};
        kills = new int[]{expValues[5]};
        shadow = new int[]{expValues[6]};



        cancelButton = findViewById(R.id.exp_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton = findViewById(R.id.exp_submit_button);
        submitButton.setOnClickListener(new SubmitButtonClick());

        adMinusButton = findViewById(R.id.ad_minus_button);
        adCountTextview = findViewById(R.id.ad_count_textview);
        adPlusButton = findViewById(R.id.ad_plus_textview);

        routMinusButton = findViewById(R.id.rout_minus_button);
        routCountTextview = findViewById(R.id.rout_count_textview);
        routPlusButton = findViewById(R.id.rout_plus_textview);

        koMinusButton = findViewById(R.id.ko_minus_button);
        koCountTextview = findViewById(R.id.ko_count_textview);
        koPlusButton = findViewById(R.id.ko_plus_textview);

        maimMinusButton = findViewById(R.id.maim_minus_button);
        maimCountTextview = findViewById(R.id.maim_count_textview);
        maimPlusButton = findViewById(R.id.maim_plus_textview);

        wwMinusButton = findViewById(R.id.ww_minus_button);
        wwCountTextview = findViewById(R.id.ww_count_textview);
        wwPlusButton = findViewById(R.id.ww_plus_textview);

        killMinusButton = findViewById(R.id.kill_minus_button);
        killCountTextview = findViewById(R.id.kill_count_textview);
        killPlusButton = findViewById(R.id.kill_plus_textview);

        shadowMinusButton = findViewById(R.id.shadow_minus_button);
        shadowCountTextview = findViewById(R.id.shadow_count_textview);
        shadowPlusButton = findViewById(R.id.shadow_plus_textview);

        totalShadowTextview = findViewById(R.id.total_shadow_textview);
        levelTextview = findViewById(R.id.level_textview);

        adCountTextview.setText(adventures[0] + "");
        routCountTextview.setText(routs[0] + "");
        koCountTextview.setText(kos[0]+"");
        maimCountTextview.setText(maims[0]+"");
        wwCountTextview.setText(wws[0]+"");
        killCountTextview.setText(kills[0]+"");
        shadowCountTextview.setText(shadow[0]+"");

        calcTotalShadow();

        adMinusButton.setOnClickListener(new minusOnClickListener(adCountTextview, adventures));
        routMinusButton.setOnClickListener(new minusOnClickListener(routCountTextview, routs));
        koMinusButton.setOnClickListener(new minusOnClickListener(koCountTextview, kos));
        maimMinusButton.setOnClickListener(new minusOnClickListener(maimCountTextview, maims));
        wwMinusButton.setOnClickListener(new minusOnClickListener(wwCountTextview, wws));
        killMinusButton.setOnClickListener(new minusOnClickListener(killCountTextview, kills));
        shadowMinusButton.setOnClickListener(new minusOnClickListener(shadowCountTextview, shadow));

        adPlusButton.setOnClickListener(new plusButtonClickListener(adCountTextview, adventures));
        routPlusButton.setOnClickListener(new plusButtonClickListener(routCountTextview, routs));
        koPlusButton.setOnClickListener(new plusButtonClickListener(koCountTextview, kos));
        maimPlusButton.setOnClickListener(new plusButtonClickListener(maimCountTextview, maims));
        wwPlusButton.setOnClickListener(new plusButtonClickListener(wwCountTextview, wws));
        killPlusButton.setOnClickListener(new plusButtonClickListener(killCountTextview, kills));
        shadowPlusButton.setOnClickListener(new plusButtonClickListener(shadowCountTextview, shadow));

    }

    private void calcTotalShadow(){
        totalShadow = adventures[0] + routs[0] + kos[0]*2 + maims[0] * 3 + wws[0] * 4 + kills[0] * 5 + shadow[0];
        calcLevel();
        totalShadowTextview.setText(totalShadow+"");
        levelTextview.setText(level + "");
    }

    private class minusOnClickListener implements View.OnClickListener {
        private TextView countTextview;
        int[] i;
        public minusOnClickListener(TextView countTextview, int[] i) {
            this.countTextview = countTextview;
            this.i = i;
        }

        @Override
        public void onClick(View v) {
            i[0]--;
            countTextview.setText(i[0] + "");
            calcTotalShadow();
            totalShadowTextview.setText(totalShadow + "");
        }
    }

    private class plusButtonClickListener implements View.OnClickListener{
        private TextView countTextview;
        int[] i;
        public plusButtonClickListener(TextView countTextview, int[] i) {
            this.countTextview = countTextview;
            this.i = i;
        }

        @Override
        public void onClick(View v) {
            i[0]++;
            countTextview.setText(i[0]+ "");
            calcTotalShadow();
            totalShadowTextview.setText(totalShadow + "");
        }
    }

    private class SubmitButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();
            int[] expArray = {adventures[0], routs[0], kos[0], maims[0], wws[0], kills[0], shadow[0]};
            returnIntent.putExtra("expValues", expArray);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

    public void calcLevel(){
        int shadowCounter = totalShadow;
        int subCounter = 5;
        level = 0;

        shadowCounter -= subCounter;
        while(shadowCounter >= 0) {
            level++;
            subCounter += 5;
            shadowCounter -= subCounter;
        }
    }

}
