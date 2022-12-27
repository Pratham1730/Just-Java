package com.example.justjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 1;

    public void increment(View View){
        {
            if(quantity == 30)
            {
                Toast.makeText(this,"You can't have more than 30 cup",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View View){
        if(quantity == 1)
        {
            Toast.makeText(this,"You can't have less than 1 cup",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    private int priceOfCoffees(){
        return quantity * 10;
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void submitOrder(View View){
        int price = priceOfCoffees();
        int priceOfWhippedCream = whippedCream();
        int priceOfChocolate = chocolate();
        String name = nameInput();
        String orderSummary =   createOrderSummary(price, priceOfWhippedCream,priceOfChocolate,name);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*") ;
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order");
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private int whippedCream(){
        CheckBox whippedCream = (CheckBox) findViewById(R.id.check_box_WhippedCream);
        boolean hasWhippedCream = whippedCream.isChecked();
        if(hasWhippedCream)
        {
            return quantity * 2;
        }
        return 0;
    }

    private int chocolate(){
        CheckBox chocolate = (CheckBox) findViewById(R.id.check_box_chocolate);
        boolean hasChocolate = chocolate.isChecked();
        if(hasChocolate)
        {
            return quantity * 4;
        }
        return 0;
    }

    private String createOrderSummary(int price, int priceOfWhippedCream , int priceOfChocolate ,String name){
        int total = price + priceOfWhippedCream + priceOfChocolate;
        return "NAME: " + name + "\n" + "PRICE: " + "Rs." + total + "\n" + getString(R.string.THANKYOU);
    }

    private String nameInput(){
        EditText nameEditText = (EditText) findViewById(R.id.name_input);
        Editable nameField = nameEditText.getText();
        String name = nameField.toString();
        if(name.isEmpty())
        {
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show();
            return "NO NAME";
        }
        return name;
    }

}