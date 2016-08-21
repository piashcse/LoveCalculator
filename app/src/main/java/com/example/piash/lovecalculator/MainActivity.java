package com.example.piash.lovecalculator;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonclick(View v){

        EditText editTextFirstName = (EditText)findViewById(R.id.editText);
        EditText editTextSecondName = (EditText)findViewById(R.id.editText2);
        TextView textViewResult  = (TextView)findViewById(R.id.textView);

        String name1 = editTextFirstName.getText().toString();
        String name2 = editTextSecondName.getText().toString();

        long  n1,n2,value1,value2;
        float result;



        n1=sum_Of_String(name1);
        n2=sum_Of_String(name2);

        value2=one_Digit( n1 );
        value1=one_Digit( n2 );

        if(value1>value2)
        {

            result=((float)value2/(float)value1)*100;
        }
        else
        {
            result=((float)value1/(float)value2)*100;

        }

        textViewResult.setText("Love Reult : "+(int)result+"%");

        Toast.makeText(getApplicationContext(),"Love Result : "+(int)result, Toast.LENGTH_SHORT).show();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    public long sum_Of_String( String name ){
        long total = 0;
        for( int i = 0; i < name.length(); i++){
            if(name.charAt(i)>= 'a' && name.charAt(i)<= 'z' || name.charAt(i)>= 'A' && name.charAt(i)<= 'Z')
            {


                switch(name.charAt(i))
                {
                    case 'a':
                    case 'A':total++;
                        break;

                    case 'b':
                    case 'B':total+=2;
                        break;

                    case 'c':
                    case 'C':total+=3;
                        break;

                    case 'd':
                    case 'D':total+=4;
                        break;

                    case 'e':
                    case 'E':total+=5;
                        break;

                    case 'f':
                    case 'F':total+=6;
                        break;

                    case 'g':
                    case 'G':total+=7;
                        break;

                    case 'h':
                    case 'H':total+=8;
                        break;

                    case 'i':
                    case 'I':total+=9;
                        break;

                    case 'j':
                    case 'J':total+=10;
                        break;

                    case 'k':
                    case 'K':total+=11;
                        break;

                    case 'l':
                    case 'L':total+=12;
                        break;

                    case 'm':
                    case 'M':total+=13;
                        break;

                    case 'n':
                    case 'N':total+=14;
                        break;

                    case 'o':
                    case 'O':total+=15;
                        break;

                    case 'p':
                    case 'P':total+=16;
                        break;

                    case 'q':
                    case 'Q':total+=17;
                        break;

                    case 'r':
                    case 'R':total+=18;
                        break;

                    case 's':
                    case 'S':total+=19;
                        break;

                    case 't':
                    case 'T':total+=20;
                        break;

                    case 'u':
                    case 'U':total+=21;
                        break;

                    case 'v':
                    case 'V':total+=22;
                        break;

                    case 'w':
                    case 'W':total+=23;
                        break;

                    case 'x':
                    case 'X':total+=24;
                        break;

                    case 'y':
                    case 'Y':total+=25;
                        break;

                    case 'z':
                    case 'Z':total+=26;
                        break;

                    default:break;}

            }

        } // end for

        return total;
    }


    public long one_Digit( long n ){
        long  mod,digit;
        while(n>9){
            digit = 0;
            while(n != 0){
                mod = n % 10;
                n = n / 10;
                digit = digit + mod;
            }
            n = digit;
        }
        return n;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("About Me");
            alert.setIcon(R.drawable.lovea);
            String name = "I'm Mehedi Hassan Piash \n CSE graduate \n working as a software Engineer";
            alert.setMessage(name);
            alert.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
