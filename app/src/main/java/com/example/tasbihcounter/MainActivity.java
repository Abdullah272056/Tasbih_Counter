package com.example.tasbihcounter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;
    Button counterButton,resetButton,saveButton,displayButton;
    TextView countTextView;
    TextView newOrOldTextView;
    ImageView backButton,vibrateButton,themeChangerButton;
    int count,vibrateValue;
    int countValue;
    int id;
    int themeValue;
    String val;
    SharedPreferences sharedPreferences;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        vibrateValue=0;
        counterButton=findViewById (R.id.counterButtonId);
        saveButton=findViewById (R.id.saveButtonId);
        resetButton=findViewById (R.id.resetButtonId);
        backButton=findViewById (R.id.backButtonId);
        displayButton=findViewById (R.id.displayButtonId);
        countTextView=findViewById (R.id.counterTextViewId);
        newOrOldTextView=findViewById (R.id.newOrOldZikirTextViewId);
        vibrateButton=findViewById (R.id.vibrateButtonId);
        themeChangerButton=findViewById (R.id.themeImageViewId);

        layout=findViewById (R.id.secondBackgroundID);

        final Vibrator vibrator= (Vibrator) MainActivity.this.getSystemService (Context.VIBRATOR_SERVICE);

        sharedPreferences=getSharedPreferences ("CountDb", Context.MODE_PRIVATE);
        if (sharedPreferences.contains ("totalCountValue")){
            int saveTotalCountValue=sharedPreferences.getInt ("totalCountValue",0);
            countTextView.setText (String.valueOf (saveTotalCountValue));

            val=getIntent ().getStringExtra ("countValue");
             id=getIntent ().getIntExtra ("id",-1);
        if (val!=null && !val.equals ("")){
           // Toast.makeText (this, val, Toast.LENGTH_SHORT).show ();
            newOrOldTextView.setText ("Old");
            countTextView.setText (val);
        }else {
           // Toast.makeText (this, String.valueOf (id), Toast.LENGTH_SHORT).show ();
            }
        }

        saveButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (newOrOldTextView.getText ().toString ()=="Old"){
                    long status=dataBaseHelper.updateData (new Note(id,Integer.parseInt (
                            countTextView.getText ().toString ())));
                    if (status == 1){
                            countTextView.setText ("0");
                            newOrOldTextView.setText ("New");
                        Toast.makeText(MainActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                    }

                }  else {
                    SharedPreferences.Editor editor = sharedPreferences.edit ();
                    int totalCountValue = Integer.parseInt (countTextView.getText ().toString ());
                    editor.putInt ("totalCountValue", totalCountValue);
                    editor.commit ();
                    if (Integer.parseInt (countTextView.getText ().toString ()) > 0) {
                        CustomDialog ();
                    } else {
                        Toast.makeText (MainActivity.this, "Count Value is Empty", Toast.LENGTH_SHORT).show ();
                    }
                }
            }
        });

        themeChangerButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                themeValue++;
                if (themeValue==9){
                    layout.setBackgroundResource (R.drawable.icon);
                    themeValue=0;
                }
                else if (themeValue==1){
                    layout.setBackgroundResource (R.drawable.icon1);
                }  else if (themeValue==2){
                    layout.setBackgroundResource (R.drawable.icon2);
                } else  if (themeValue==3){
                    layout.setBackgroundResource (R.drawable.icon3);
                }
                else  if (themeValue==4){
                    layout.setBackgroundResource (R.drawable.icon4);
                }
                else  if (themeValue==5){
                    layout.setBackgroundResource (R.drawable.icon5);
                }
                else  if (themeValue==6){
                    layout.setBackgroundResource (R.drawable.icon6);
                }
                else  if (themeValue==7){
                    layout.setBackgroundResource (R.drawable.icon7);
                }
                else  if (themeValue==8){
                    layout.setBackgroundResource (R.drawable.icon8);
                }


            }
        });

        vibrateButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (vibrateValue==0){
                    vibrateValue=1;
                    vibrateButton.setImageResource (R.drawable.ic_vibration_on);
                }else {
                    vibrateValue=0;
                    vibrateButton.setImageResource (R.drawable.vibrate_off);
                }
            }
        });

        counterButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
               String countStringValue=countTextView.getText ().toString ();
               count=Integer.parseInt (countStringValue);
                count++;
                countTextView.setText (String.valueOf (count));
                if (vibrateValue==1){
                    vibrator.vibrate (100);
                }
            }

        });

        backButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String countStringValue=countTextView.getText ().toString ();
                count=Integer.parseInt (countStringValue);
                if (count>0){
                    count--;
                    countTextView.setText (String.valueOf (count));
                }
                else {
                    Toast.makeText (MainActivity.this, "Count value is zero", Toast.LENGTH_SHORT).show ();
                }

            }

        });
        resetButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                count=0;
                countTextView.setText (String.valueOf (count));
                newOrOldTextView.setText ("New");
            }
        });

        displayButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (MainActivity.this,DisplayData.class);
                startActivity (intent);
                MainActivity.this.finish ();
            }
        });

        ////DataBase access
        dataBaseHelper=new DataBaseHelper(MainActivity.this);
        dataBaseHelper.getWritableDatabase();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Please confirm");
        builder.setMessage("Are you want to Exit the app?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Please confirm");
                builder.setMessage("Do you want to save data?");
                builder.setPositiveButton ("Save", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor=sharedPreferences.edit ();
                        int totalCountValue=Integer.parseInt (countTextView.getText ().toString ());
                        editor.putInt ("totalCountValue",totalCountValue);
                        editor.commit ();
                        MainActivity.super.onBackPressed();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.super.onBackPressed();
                        //Toast.makeText (MainActivity.this, "thank you", Toast.LENGTH_SHORT).show ();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
               // MainActivity.super.onBackPressed();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText (MainActivity.this, "thank you", Toast.LENGTH_SHORT).show ();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void CustomDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        LayoutInflater layoutInflater =LayoutInflater.from(MainActivity.this);
        View view=layoutInflater.inflate(R.layout.custom_dialog,null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        Button saveButton=view.findViewById(R.id.saveButtonId);
        final EditText zikirNameEditText =view.findViewById(R.id.zikirNameEditTextId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zikirName=zikirNameEditText.getText().toString();

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("dd/MM/yyy  HH:mm:ss");
                Date date=new Date ();
                String currentDate=simpleDateFormat.format (date);

                if (zikirName.isEmpty()){
                    zikirNameEditText.setError("Enter title");
                    return;
                }else {
                     countValue=Integer.parseInt (countTextView.getText ().toString ());
                }
                long id=dataBaseHelper.insertData(new Note(zikirName,currentDate,countValue));

                if (id != -1){
                    alertDialog.dismiss();
                    //loadData();
                    countTextView.setText ("0");
                    Toast.makeText(MainActivity.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    alertDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Failed to Insert", Toast.LENGTH_SHORT).show();
                }
                //  Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }
    

//
}
