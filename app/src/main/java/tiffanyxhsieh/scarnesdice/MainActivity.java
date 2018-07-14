package tiffanyxhsieh.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

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
    Timer timer = new Timer();

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

                computerTurn(true);


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

    private void roll(char player) {
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
            //A "1" was rolled, end turn, set turnScore to 0
            turnScore = 0;
            turnScoreText.setText(Integer.toString(turnScore));
            if (player == 'h') {
                //if it was the human player's turn, start the computer's turn
                Toast.makeText(getApplicationContext(),"1 Rolled. End of Turn", Toast.LENGTH_LONG).show();

                computerTurn(true);
            } else if (player == 'c') {
                computerTurn(false);
            }



        }


    }

    private void computerTurn(boolean continueTurn) {
        //disable buttons so human player can't play
        rollButton.setEnabled(false);
        holdButton.setEnabled(false);

        Toast.makeText(getApplicationContext(),"Computer Turn", Toast.LENGTH_LONG).show();

        //computer keeps rolling as long as it doesn't roll a "1", and Math.random < .2
        double r = Math.random();
        while (continueTurn) {
            roll('c');
            if (r < .1) {
                continueTurn = false;
                turnScoreText.setText("Computer Hold");
            }

        }

        //update computer overall score and enable buttons for human player's turn
        computerOverallScore += turnScore;
        computerOverallScoreText.setText(Integer.toString(computerOverallScore));
        holdButton.setEnabled(true);
        rollButton.setEnabled(true);

    }

}
