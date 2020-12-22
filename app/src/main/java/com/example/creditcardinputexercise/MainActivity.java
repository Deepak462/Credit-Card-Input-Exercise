package com.example.creditcardinputexercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class MainActivity extends AppCompatActivity {
    CardForm cardForm;
    Button buy;
    AlertDialog.Builder aB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardForm=findViewById(R.id.cardForm);
        buy=findViewById(R.id.btnBuy);
        cardForm.cardRequired(true);
        cardForm.expirationRequired(true);
        cardForm.cvvRequired(true);
        cardForm.cardholderName(CardForm.FIELD_REQUIRED);
        cardForm.setup(MainActivity.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()){
                    aB=new AlertDialog.Builder(MainActivity.this);
                    aB.setMessage("Payment Successful");
                    aB.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(MainActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alertDialog=aB.create();
                    alertDialog.show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please Complete the form", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}