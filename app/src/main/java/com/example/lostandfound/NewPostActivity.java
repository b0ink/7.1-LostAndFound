package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewPostActivity extends AppCompatActivity {

    private EditText etItemName;
    private EditText etDescription;
    private EditText etLocation;
    private EditText etDate;

    private EditText etPosterName;
    private EditText etMobile;

    private Button btnSavePost;

    private TabLayout tlReportType;

    private LostAndFoundDatabase lostAndFoundDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        lostAndFoundDatabase = DatabaseHelper.getInstance(this).getLostAndFoundDatabase();

        etItemName = findViewById(R.id.etItemName);
        etDescription = findViewById(R.id.etDescription);
        etLocation = findViewById(R.id.etLocation);
        etDate = findViewById(R.id.etDate);
        etPosterName = findViewById(R.id.etPosterName);
        etMobile = findViewById(R.id.etMobile);
        btnSavePost = findViewById(R.id.btnSavePost);
        tlReportType = findViewById(R.id.tlReportType);

        btnSavePost.setOnClickListener(view -> {
            LostItem.REPORT_TYPE reportType = LostItem.REPORT_TYPE.values()[tlReportType.getSelectedTabPosition()];

            String itemName = etItemName.getText().toString();
            String description = etDescription.getText().toString();
            String location = etLocation.getText().toString();
            String date = etDate.getText().toString();
            String posterName = etPosterName.getText().toString();
            String mobile = etMobile.getText().toString();

            try {
                LocalDate localDate = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    localDate = LocalDate.parse(date, formatter);
                }
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(this, "Invalid date format: yyyy-MM-dd", Toast.LENGTH_SHORT).show();
                return;
            }

            if(itemName.isEmpty() || description.isEmpty() || location.isEmpty() || date.isEmpty() || posterName.isEmpty() || mobile.isEmpty()){
                Toast.makeText(this, "Fields cannot be left blank.", Toast.LENGTH_SHORT).show();
                return;
            }

            LostItem item = new LostItem(reportType, itemName, description, location, date, posterName, mobile);
            lostAndFoundDatabase.lostItemDao().insert(item);

            Toast.makeText(this, "Created post!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ViewPostsActivity.class);
            startActivity(intent);
            finish();
        });

    }
}