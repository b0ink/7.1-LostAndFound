package com.example.lostandfound;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class ViewPostsActivity extends AppCompatActivity {

    private LostAndFoundDatabase lostAndFoundDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_posts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<LostItem> lostItems = new ArrayList<>();
        lostAndFoundDatabase = DatabaseHelper.getInstance(this).getLostAndFoundDatabase();

        lostItems.addAll(lostAndFoundDatabase.lostItemDao().getAllLostItems());

//        LostItem test1 = new LostItem(LostItem.REPORT_TYPE.REPORT_TYPE_FOUND, "iPhone 14 Pro", "Found an iphone near the park", "The park", "2024-05-01", "Bob", "0123xxxxxx");
//        lostAndFoundDatabase.lostItemDao().insert(test1);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            lostItems.add(new LostItem(1, LostItem.REPORT_TYPE.REPORT_TYPE_FOUND,"iPhone 12 Pro", "Found an iphone near the park", "The park", LocalDate.of(2024, 5, 1), "Bob", "0123xxxxxx"));
//            lostItems.add(new LostItem(1, LostItem.REPORT_TYPE.REPORT_TYPE_FOUND,"Wallet", "Wallet left on train", "King station", LocalDate.of(2024, 4, 10), "Bill", "0456xxxxxx"));
//            lostItems.add(new LostItem(1, LostItem.REPORT_TYPE.REPORT_TYPE_LOST,"Keys", "Left my keys around somewhere in the shopping centre", "Big shopping mall", LocalDate.of(2024, 1, 3), "Ben", "0789xxxxxx"));
//        }

        RecyclerView recyclerView = findViewById(R.id.lostItemsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        LostItemAdapter adapter = new LostItemAdapter(this, lostItems);
        recyclerView.setAdapter(adapter);

    }
}