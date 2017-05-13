package info.allentang.placeknockoff;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button00, button01, button02, button10, button11, button12, button20, button21, button22;
    FirebaseDatabase colorDatabase = FirebaseDatabase.getInstance();
    DatabaseReference zeroRef = colorDatabase.getReference("Coordinates/0");
    DatabaseReference oneRef = colorDatabase.getReference("Coordinates/1");
    DatabaseReference twoRef = colorDatabase.getReference("Coordinates/2");
    private static int MAX_INDEX = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button00 = (Button) findViewById(R.id.button00);
        button01 = (Button) findViewById(R.id.button01);
        button02 = (Button) findViewById(R.id.button02);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button20 = (Button) findViewById(R.id.button20);
        button21 = (Button) findViewById(R.id.button21);
        button22 = (Button) findViewById(R.id.button22);

        HashMap<String, Button> buttonMap = new HashMap<>();
        buttonMap.put("button00", button00);
        buttonMap.put("button01", button01);
        buttonMap.put("button02", button02);
        buttonMap.put("button10", button10);
        buttonMap.put("button11", button11);
        buttonMap.put("button12", button12);
        buttonMap.put("button20", button20);
        buttonMap.put("button21", button21);
        buttonMap.put("button22", button22);


        for(int x = 0; x <= MAX_INDEX; x++){
            for(int y = 0; y <= MAX_INDEX; y++){
                initButtonColor(x,y, buttonMap);
            }
        }


        button00.setOnClickListener(this);
        button01.setOnClickListener(this);
        button02.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button20.setOnClickListener(this);
        button21.setOnClickListener(this);
        button22.setOnClickListener(this);

        Toast.makeText(getApplicationContext(), "Click a button to randomly change the color", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        int buttonColor = generateColor();
        switch(id){
            case R.id.button00:
                zeroRef.child("0").setValue(Integer.toString(buttonColor));
                v.setBackgroundColor(buttonColor);
                break;
            case R.id.button01:
                zeroRef.child("1").setValue(Integer.toString(buttonColor));
                v.setBackgroundColor(buttonColor);
                break;
            case R.id.button02:
                zeroRef.child("2").setValue(Integer.toString(buttonColor));
                v.setBackgroundColor(buttonColor);
                break;
            case R.id.button10:
                oneRef.child("0").setValue(Integer.toString(buttonColor));
                v.setBackgroundColor(buttonColor);
                break;
            case R.id.button11:
                oneRef.child("1").setValue(Integer.toString(buttonColor));
                v.setBackgroundColor(buttonColor);
                break;
            case R.id.button12:
                oneRef.child("2").setValue(Integer.toString(buttonColor));
                v.setBackgroundColor(buttonColor);
                break;
            case R.id.button20:
                twoRef.child("0").setValue(Integer.toString(buttonColor));
                v.setBackgroundColor(buttonColor);
                break;
            case R.id.button21:
                twoRef.child("1").setValue(Integer.toString(buttonColor));
                v.setBackgroundColor(buttonColor);
                break;
            case R.id.button22:
                twoRef.child("2").setValue(Integer.toString(buttonColor));
                v.setBackgroundColor(buttonColor);
                break;
            default:
                return;
        }
    }

    public void initButtonColor(int x, int y, final HashMap<String, Button> buttonHashMap){
        final String xCoord = Integer.toString(x);
        final String yCoord = Integer.toString(y);
        DatabaseReference xRef = colorDatabase.getReference("Coordinates/" + xCoord);
        xRef.child(yCoord).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                buttonHashMap.get("button" + xCoord + yCoord).setBackgroundColor(Integer.parseInt(dataSnapshot.getValue(String.class)));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static int generateColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }
}
