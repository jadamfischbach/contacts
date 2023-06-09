package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameEdit;
    Spinner spinner;
    Button addButton;
    Button getButton;
    DatabaseControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = new DatabaseControl(this);

        nameEdit = findViewById(R.id.nameEdit);
        spinner = findViewById(R.id.spinner);
        addButton = findViewById(R.id.addButton);
        getButton = findViewById(R.id.getButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                String state = ((TextView) spinner.getSelectedView()).getText().toString();
                control.open();
                boolean itWorked = control.insert(name, state);
                control.close();
                if (itWorked)
                    Toast.makeText(getApplicationContext(), "Added "+name+" "+state, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "FAILED "+name+" "+state, Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}





