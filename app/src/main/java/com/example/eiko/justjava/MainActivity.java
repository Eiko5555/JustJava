package com.example.eiko.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TextView text = new TextView(this);
        //text.setText("Do you need Coffee???");
        setContentView(R.layout.java_main);
    }

    public void increment(View view) {
        if (quantity == 100){
            Toast.makeText(this,"Can not be over 100.",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        displayQuantity(quantity);
    }  //adding number when its clicked

    public void decrement(View view) {
        if (quantity < 1){
            Toast.makeText(this,"Can not be under 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);
    } // decrease number when its clicked

    public void submitOrder(View view) {
        EditText nametext = (EditText)findViewById(R.id.name);
        String addName= nametext.getText().toString();
        CheckBox addcream = (CheckBox)findViewById(R.id.checkbox);
        boolean yesCream = addcream.isChecked();
        CheckBox addChoco = (CheckBox)findViewById(R.id.checkboxChocolate);
        boolean yesChoco = addChoco.isChecked();
        int price = calculatePrice(yesCream, yesChoco);
        String priceMessage = createOrderSummery(price,yesCream, yesChoco,addName);
        //displayMesage(priceMessage);
        //TextView showprice = (TextView)findViewById(R.id.price_textview);
        //showprice.setText(price);
        Intent sendorderemail = new Intent(Intent.ACTION_SENDTO);
        sendorderemail.setData(Uri.parse("mailto: justjava@gmail.com"));
        sendorderemail.putExtra(Intent.EXTRA_SUBJECT,"JustJava order for"+addName);
        sendorderemail.putExtra(Intent.EXTRA_TEXT,priceMessage);
        //sendorderemail.setType("plain/text");
        //sendorderemail.setType("message/rfc822");
        startActivity(Intent.createChooser(sendorderemail,""));
//        if (sendorderemail.resolveActivity(getPackageManager()) != null) {
//            startActivity(sendorderemail);
//        }
        //displayMesage(priceMessage);
    }

    private int calculatePrice(boolean addcream, boolean addchoco){
        int coffeePrice = 5;
        if (addcream){
            coffeePrice += 1;
        }
        if (addchoco){
            coffeePrice += 2;
        }
        return quantity * coffeePrice;
    }

        private String createOrderSummery(int price, boolean cream,boolean choco, String name) {
        String priceMessage = getString(R.string.order_name, name) +
                "\nAdd Whipped Cream: "+ cream +
                "\nAdd Chocolate: " + choco +
                "\nQuantity: " + quantity +
                "\nTotal $" + price + " , please." +
                "\n"+getString(R.string.thankyou);
        return priceMessage;
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    } //show and update quantity when + - button clicked

    private void displayMesage(String message) {
        TextView orderSummaryTextview = (TextView) findViewById(R.id.order_summary_textview);
        orderSummaryTextview.setText(message);
    } //text display

}
