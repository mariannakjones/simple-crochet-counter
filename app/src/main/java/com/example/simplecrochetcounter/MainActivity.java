package com.example.simplecrochetcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int rowNumber;
    int stitchNumber;

    private TextView rowCount;
    private TextView stitchCount;
    private Button addRow;
    private Button addStitch;
    private Button clear;

    public static final String SHARED_PREFERENCES = "sharedPreferences";
    public static final String ROW_NUMBER = "rowNumber";
    public static final String STITCH_NUMBER = "stitchNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowCount = (TextView) findViewById(R.id.txtRowCount);
        stitchCount = (TextView) findViewById(R.id.txtStitchCount);
        addRow = (Button) findViewById(R.id.btnAddRow);
        addStitch = (Button) findViewById(R.id.btnAddStitch);
        clear = (Button) findViewById(R.id.btnClear);

        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowNumber += 1;
                stitchNumber = 0;

                updateRowCountText();
                updateStitchCountText();
                saveData();
            }
        });

        addStitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stitchNumber += 1;

                updateStitchCountText();
                saveData();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowNumber = 0;
                stitchNumber = 0;

                updateRowCountText();
                updateStitchCountText();
                saveData();
            }
        });

        loadData();
        updateRowCountText();
        updateStitchCountText();
    }

    public void updateRowCountText() {
        rowCount.setText(Integer.toString(rowNumber));
    }

    public void updateStitchCountText() {
        stitchCount.setText(Integer.toString(stitchNumber));
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(ROW_NUMBER, rowNumber);
        editor.putInt(STITCH_NUMBER, stitchNumber);

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        rowNumber = sharedPreferences.getInt(ROW_NUMBER, 0);
        stitchNumber = sharedPreferences.getInt(STITCH_NUMBER, 0);
    }
}