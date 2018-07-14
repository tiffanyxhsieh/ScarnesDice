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
    private Button rollButton;
    private Button holdButton;
    private Button resetButton;
    private ImageView dice;
    private TextView userOverallScoreText;
    private TextView computerOverallScoreText;
    private TextView turnScoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollButton = findViewById(R.id.roll);
        holdButton = findViewById(R.id.hold);
        resetButton = findViewById(R.id.reset);
        dice = findViewById(R.id.dice);
        userOverallScoreText = findViewById(R.id.userOverallScore);
        computerOverallScoreText = findViewById(R.id.computerOverallScore);
        turnScoreText = findViewById(R.id.turnScoreText);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll('h');
            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOverallScore += turnScore;
                userOverallScoreText.setText(Integer.toString(userOverallScore));

                turnScore = 0;
                turnScoreText.setText("Turn Score: "+ turnScore);

                computerTurn();


            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
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

    private int roll(char player) {
        int r = (int) (Math.random() * 6) + 1;

        int rollValue = r;
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
            turnScoreText.setText("Turn score: " + Integer.toString(turnScore));

        } else {
            turnScore = 0;
            if (player == 'h') {
                computerTurn();
            }


        }

        Log.e("***score", "turnScore: " + turnScore +"\noverallScore: "+userOverallScore);
        return turnScore;

    }

    private void computerTurn() {
        rollButton.setEnabled(false);
        holdButton.setEnabled(false);

        boolean continueTurn = true;
        double r = Math.random();
        while (continueTurn) {
            int scoreAdded = roll('c');
            if (!(scoreAdded != 0 && r > .5)) {
                continueTurn = false;
            }

        }

        turnScoreText.setText(Integer.toString(turnScore));
        holdButton.setEnabled(true);
        rollButton.setEnabled(true);

    }

}
