package com.example.cake.chapter9ver2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class new_listActivity extends AppCompatActivity {

    private ListView lvMenu;

    static String[] Topic = {
            "Topic news",
            "Topic news",
            "Topic news",
            "Topic news",
            "Topic news"

    };

    int[] resId = {
            R.drawable.and,
            R.drawable.and,
            R.drawable.and,
            R.drawable.and,
            R.drawable.and
    };

    static String[] Date = {
            "5 ตุลาคม",
            "5 ตุลาคม",
            "5 ตุลาคม",
            "5 ตุลาคม",
            "5 ตุลาคม"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        lvMenu = (ListView) findViewById(R.id.lvMenu);
        lvMenu.setAdapter(new CustomAdepter(getApplicationContext(), Topic, resId, Date));

        setEvent();
    }

    private void setEvent() {
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(new_listActivity.this,new_detailActivity.class);
                i.putExtra("vocabId", (int) id );
                startActivity(i);
            }
        });
    }

}
