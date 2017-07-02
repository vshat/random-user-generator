package com.github.vshat.randomusergenerator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersAdapter.OnItemClickListener {

    private static List<String> createStringList() {
        List<String> result = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            result.add(i, "User #" + i);
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsersAdapter usersAdapter = new UsersAdapter(createStringList());
        usersAdapter.setOnItemClickListener(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);

        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onItemClick(View itemView, int position) {
        UserDetailActivity.start(this, ((TextView)itemView.findViewById(R.id.textview_user_name)).getText().toString());
    }


}
