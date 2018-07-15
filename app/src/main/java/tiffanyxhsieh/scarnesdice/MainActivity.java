package tiffanyxhsieh.scarnesdice;

import android.os.CountDownTimer;
import android.os.Handler;
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
    private TextView computerMove;
    private TextView winnerText;
    char winner = 'n';



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
        computerMove = findViewById(R.id.computerMove);
        winnerText = findViewById(R.id.winnerText);
        winnerText.setText("");


        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computerMove.setText("Human Move");
                if (roll('h') == 0) {
                    computerTurn();
                }
            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computerMove.setText("");
                userOverallScore += turnScore;
                userOverallScoreText.setText(Integer.toString(userOverallScore));

                if (userOverallScore >= 100) {
                    winner = 'h';
                    winnerText.setText("Human Player is the winner!");
                    rollButton.setClickable(false);
                    holdButton.setClickable(false);
                }

                turnScore = 0;
                turnScoreText.setText("Turn Score: "+ turnScore);

                computerTurn();


            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            resetScores();

            }
        });


    }

    private void resetScores() {
        userOverallScore = ZERO;
        computerOverallScore = ZERO;
        turnScore = ZERO;

        userOverallScoreText.setText(Integer.toString(userOverallScore));
        computerOverallScoreText.setText(Integer.toString(computerOverallScore));
        turnScoreText.setText(Integer.toString(turnScore));
    }

    private int roll(char player) {
        int r = (int) (Math.random() * 6) + 1;

        int rollValue = r;
        String diceImage = "dice"+Integer.toString(rollValue);


        int diceImageResource = getResources().
                getIdentifier(diceImage,
                        "drawable", getPackageName());

        dice.setImageResource(diceImageResource);


        if (rollValue != 1) {
            //NOT "1", was rolled, add this value to the turnScore
            turnScore += rollValue;

            Log.e("actual turn score: ", Integer.toString(turnScore));
            turnScoreText.setText("Turn score: " + Integer.toString(turnScore));
            return rollValue;

        } else {
            //A "1" was rolled, end turn, set turnScore to 0
            turnScore = 0;
            turnScoreText.setText("Turn Score: " + Integer.toString(turnScore));

            if (player =='h') {
                computerTurn();
            }

        }

        return rollValue;


    }

    //TODO: FIX TIMER....thread isn't right
    private void computerTurn() {
        //disable buttons so human player can't play
        rollButton.setClickable(false);
        holdButton.setClickable(false);

        Timer t = new Timer();

        //computer keeps rolling as long as it doesn't roll a "1", and Math.random >=.4

        int numRolls = 0;
        int rollVal = roll('c');
        boolean continueTurn = true;

        while(rollVal != 0 && continueTurn){
            numRolls++;
            Log.e("comp rolls", Integer.toString(numRolls));
            double r = Math.random();

            if (r < .4) {
                continueTurn = false;
            } else {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        roll('c');
                    }
                }, 2000);


            }


        }


        computerOverallScore += turnScore;
        Log.e("computerScore : ", Integer.toString(computerOverallScore));
        computerOverallScoreText.setText(Integer.toString(computerOverallScore));

        //Computer wins...freeze current game
        if (computerOverallScore >= 100) {
            winner = 'c';
            rollButton.setClickable(false);
            holdButton.setClickable(false);
            winnerText.setText("Computer wins!");
        }
        //computer stops rolling
        //update computer overall score and enable buttons for human player's turn

        turnScore = 0;
        turnScoreText.setText("Turn Score: " + Integer.toString(turnScore));

        holdButton.setClickable(true);
        rollButton.setClickable(true);

    }

}
