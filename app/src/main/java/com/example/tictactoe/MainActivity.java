package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button playAgain ;
    TextView score ;
    GridLayout gridLayout;

    private static final String TAG = "MainActivity";
    // 0 : o --- 1 : x ----- 2:empty
    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2,};
    int [][] winingPositions = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         playAgain = findViewById(R.id.button);
         score = findViewById(R.id.textView);
         gridLayout = findViewById(R.id.gridLayout);

    }

    public void ClickOn(View view) {
        ImageView counter = (ImageView) view;
        int tagCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tagCounter]==2 && gameActive ) {

            Log.d(TAG, "gameState="+gameState[tagCounter]);
            gameState[tagCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            for (int[] winningPosition : winingPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[0]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 0) {
                        winner = "o";
                    } else {
                        winner = "x";
                    }
                    score.setText( winner + "  has won !");
                    playAgain.setVisibility(View.VISIBLE);
                    score.setVisibility(View.VISIBLE);
                    gameActive = false;
                }
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

        }
    }

    public void retry(View view) {
        score.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        for(int i = 0; i < gridLayout.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i = 0; i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        activePlayer = 0;
        gameActive = true;
    }
}