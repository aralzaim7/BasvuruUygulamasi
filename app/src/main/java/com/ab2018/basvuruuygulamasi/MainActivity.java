package com.ab2018.basvuruuygulamasi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity {
    //test
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText name,email,bday;
    RadioButton maleBtn, femaleBtn;
    CheckBox attended;
    Spinner city;
    String sehir="Ankara";

 //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCalendar = Calendar.getInstance();

        name = findViewById(R.id.nameEt);
        email = findViewById(R.id.emailEt);
        bday = findViewById(R.id.bdayEt);

        city = findViewById(R.id.citySpn);
        maleBtn = findViewById(R.id.maleRd);
        femaleBtn = findViewById(R.id.femaleRd);
        attended = findViewById(R.id.chckbox);

        city = findViewById(R.id.citySpn);


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
         };

        bday.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int index, long l) {

                   sehir = city.getSelectedItem().toString();


                // if (index==0)
                //sehir="Ankara";

                //else if (index==1)
                 //   sehir="İzmir";

                //else
                    //sehir="Karabük";

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        bday.setText(sdf.format(myCalendar.getTime()));
    }

    public void onClickBtn(View v)
    {
        if(email.getText().toString().isEmpty() || name.getText().toString().isEmpty()  || bday.getText().toString().isEmpty() )
        {
            Toast.makeText(this, "Lütfen bütün alanları doldurunuz", Toast.LENGTH_SHORT).show();
        }
        else{
            String msg="";
            msg+="İsim: "+name.getText();
            msg+="\nMail: "+email.getText();
            msg+="\nDoğum Tarihi: "+bday.getText();
            msg+="\nŞehir: "+sehir;

            if(femaleBtn.isChecked())
                msg+="\nCinsiyet: Kadın";
            else
                msg+="\nCinsiyet: Erkek";

            if (attended.isChecked())
                msg+="\nAkademik Bilişim'e önceden katıldı mı: Evet";
            else
                msg+="\nAkademik Bilişim'e önceden katıldı mı: Hayır";

            makeAndShowDialogBox(msg);

        }
    }

    private void makeAndShowDialogBox(String message) {

        AlertDialog.Builder myDialogBox = new AlertDialog.Builder(this);

        // set message, title, and icon
        myDialogBox.setTitle("Başvuru tamamlandı");
        myDialogBox.setMessage(message);
        myDialogBox.setIcon(R.drawable.ablogo);

        // Set three option buttons
        myDialogBox.setPositiveButton("Tamam",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // whatever should be done when answering "YES" goes
                        // here

                    }
                });

        // myDialogBox.setNegativeButton("NO",
        // new DialogInterface.OnClickListener() {
        // public void onClick(DialogInterface dialog, int whichButton) {
        // // whatever should be done when answering "NO" goes here
        //
        // }
        // });

        // myDialogBox.setNeutralButton("Cancel",
        // new DialogInterface.OnClickListener() {
        // public void onClick(DialogInterface dialog, int whichButton) {
        // // whatever should be done when answering "NO" goes here
        //
        // }
        // });

        myDialogBox.create();
        myDialogBox.show();
    }



}
