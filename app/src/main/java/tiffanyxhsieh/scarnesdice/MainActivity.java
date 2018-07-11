package tiffanyxhsieh.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int userOverallScore;
    int userTurnScore;
    int computerOverallScore;
    int computerTurnScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button roll = findViewById(R.id.roll);
        final ImageView dice = findViewById(R.id.dice);

        roll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int diceImageResource = getResources().
                        getIdentifier("dice"+Integer.toString(roll()),
                                "drawable", getPackageName());

                dice.setImageResource(diceImageResource);

            }
        });

    }


    public int roll() {
        int r = (int)(Math.random() * 6) + 1;
        return r;

    }


}
