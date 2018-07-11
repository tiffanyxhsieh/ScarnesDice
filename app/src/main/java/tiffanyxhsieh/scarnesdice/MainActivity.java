package tiffanyxhsieh.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int ZERO = 0;
    private static int userOverallScore = ZERO;
    private static int computerOverallScore = ZERO;
    private static int turnScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button roll = findViewById(R.id.roll);
        final Button hold = findViewById(R.id.hold);
        final Button reset = findViewById(R.id.reset);
        final ImageView dice = findViewById(R.id.dice);
        final TextView userOverallScoreText = findViewById(R.id.userOverallScore);
        final TextView computerOverallScoreText = findViewById(R.id.computerOverallScore);
        final TextView turnScoreText = findViewById(R.id.turnScoreText);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rollValue = roll();
                Log.e("**role", Integer.toString(rollValue));
                String diceImage = "dice"+Integer.toString(rollValue);
                Log.e("dice image", diceImage);


                int diceImageResource = getResources().
                        getIdentifier(diceImage,
                                "drawable", getPackageName());



                dice.setImageResource(diceImageResource);


                if (rollValue != 1) {
                    turnScore += rollValue;

                    Log.e("Turn score: " , Integer.toString(turnScore));
                    turnScoreText.setText("Your turn score: " + Integer.toString(turnScore));

                }

                Log.e("***score", "turnScore: " + turnScore +"\noverallScore: "+userOverallScore);
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOverallScore += turnScore;
                userOverallScoreText.setText(Integer.toString(userOverallScore));

                turnScore = 0;
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userOverallScore = ZERO;
                computerOverallScore = ZERO;
                turnScore = ZERO;

                userOverallScoreText.setText(Integer.toString(userOverallScore));
                computerOverallScoreText.setText(Integer.toString(computerOverallScore));
                turnScoreText.setText(Integer.toString(turnScore));


            }
        });


    }

    public int roll() {
        int r = (int) (Math.random() * 6) + 1;
        return r;

    }
}
