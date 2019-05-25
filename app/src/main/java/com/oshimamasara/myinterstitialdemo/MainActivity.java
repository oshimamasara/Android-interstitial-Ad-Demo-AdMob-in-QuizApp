package com.oshimamasara.myinterstitialdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_one, btn_two, btn_three;
    TextView tv_question;

    private Question question = new Question();

    private String answer;
    private int questionLength = question.questions.length;
    private InterstitialAd mInterstitialAd;

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "★onCreate");
        random = new Random();

        btn_one = (Button)findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = (Button)findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = (Button)findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);

        tv_question = (TextView)findViewById(R.id.tv_question);

        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());  //広告読み込み

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d("TAG", "★onAdLoaded()");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("TAG", "★onAdFailedToLoad()");
            }

            @Override
            public void onAdOpened() {
                Log.d("TAG", "★onAdOpened()");
                Toast.makeText(MainActivity.this, "広告が表示されます", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAdClicked() {
                Log.d("TAG", "★onAdClicked()");
                Toast.makeText(MainActivity.this, "広告ページを開きます", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Log.d("TAG", "★onAdLeftApplication()");
            }

            @Override
            public void onAdClosed() {
                Log.d("TAG", "★onAdClosed()");
                Toast.makeText(MainActivity.this, "広告ページ閉じます", Toast.LENGTH_SHORT).show();
            }
        });

        NextQuestion(random.nextInt(questionLength));
        Log.d("TAG", "★QUIZ START");
    }

    @Override
    public void onClick(View v) {
        Log.d("TAG", "★onClick()");
        switch (v.getId()){
            case R.id.btn_one:
                if(btn_one.getText() == answer){
                    Toast.makeText(MainActivity.this, "正解\uD83D\uDE0A", Toast.LENGTH_LONG).show();
                    NextQuestion(random.nextInt(questionLength));
                }else{
                    GameOver();
                }
                break;

            case R.id.btn_two:
                if(btn_two.getText() == answer){
                    Toast.makeText(MainActivity.this, "正解\uD83D\uDE0A", Toast.LENGTH_LONG).show();
                    NextQuestion(random.nextInt(questionLength));
                }else{
                    GameOver();
                }
                break;

            case R.id.btn_three:
                if(btn_three.getText() == answer){
                    Toast.makeText(MainActivity.this, "正解\uD83D\uDE0A", Toast.LENGTH_LONG).show();
                    NextQuestion(random.nextInt(questionLength));
                }else{
                    GameOver();
                }
                break;
        }
    }

    private void GameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("不正解\uD83D\uDE2D")
                .setCancelable(false)
                .setPositiveButton("もう一回やる", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        // インタースティシャル広告　表示
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                        }
                    }
                })
                .setNegativeButton("アプリを閉じる", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
        alertDialogBuilder.show();

    }

    private void NextQuestion(int num){
        tv_question.setText(question.getQuestion(num));
        btn_one.setText(question.getchoice1(num));
        btn_two.setText(question.getchoice2(num));
        btn_three.setText(question.getchoice3(num));
        answer = question.getCorrectAnswer(num);
    }

}
