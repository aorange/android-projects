package com.example.allentang.password;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Password generator App with the two buttons and input fields
 * @author Allen Tang
 */
public class MainActivity extends AppCompatActivity {


    EditText passwordLen;
    EditText amountOfNumbers;
    EditText amountOfSymbols;
    TextView outputPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordLen = (EditText)findViewById(R.id.lengthinput);
        amountOfNumbers = (EditText)findViewById(R.id.passwordnums);
        amountOfSymbols = (EditText)findViewById(R.id.passwordsymbol);
        outputPass = (TextView)findViewById(R.id.output);


        //functionality for the password generator button with error processing
        final Button refreshButton = (Button)findViewById(R.id.refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordLen == null || amountOfNumbers == null || amountOfSymbols == null ||
                        passwordLen.getText().equals("") || amountOfNumbers.getText().equals("") ||
                        amountOfSymbols.getText().equals("")){
                    Toast.makeText(getApplicationContext(), "Unfilled fields", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(amountOfNumbers.getText().toString()) +
                        Integer.parseInt(amountOfSymbols.getText().toString()) >
                        Integer.parseInt(passwordLen.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Password fields are illogical", Toast.LENGTH_SHORT).show();
                }
                else{

                      String password = PasswordGenerator.createPass(
                              Integer.parseInt(passwordLen.getText().toString()),
                              Integer.parseInt(amountOfNumbers.getText().toString()),
                              Integer.parseInt(amountOfSymbols.getText().toString()));
                      outputPass.setText(password);
                }
            }
        });

        //functionality to copy password to clipboard
        final Button copyToClipboard = (Button) findViewById(R.id.clipboard);
        copyToClipboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(outputPass == null || outputPass.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"Nothing to copy to clipboard", Toast.LENGTH_SHORT).show();
                }
                else{
                    ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData pass = ClipData.newPlainText("Generated password", outputPass.getText());
                    clipboard.setPrimaryClip(pass);
                }
            }
        });
    }
}
