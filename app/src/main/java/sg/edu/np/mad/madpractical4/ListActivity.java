package sg.edu.np.mad.madpractical4;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import java.util.Random;
import android.util.Log;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.Random;

import android.view.View;
import android.widget.ImageView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Intent mainActivity = new Intent(ListActivity.this, MainActivity.class);
//        int randomInt = new Random().nextInt(999999);
//        Log.d("TAG", "My variable value is: " + randomInt);
//        mainActivity.putExtra("randomInt", randomInt);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Profile");
//        builder.setMessage("MADness");
//        builder.setCancelable(false);
//        builder.setPositiveButton("VIEW", new
//                DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startActivity(mainActivity);
//                    }
//                });

        // Practical 4 Starts Here
        ArrayList<User> myUser_List = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int name = new Random().nextInt(999999999);
            int description = new Random().nextInt(999999999);
            boolean followed = new Random().nextBoolean();

            User user = new User("John Doe", "MAD Developer", 1, false);
            user.setName("Name" + String.valueOf(name));
            user.setFollowed(followed);
            myUser_List.add(user);
        }

        // Add This (RecyclerView)
        UserAdapter userAdapter = new UserAdapter(myUser_List, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);


//        builder.setNegativeButton("CLOSE", new
//                DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        AlertDialog alertDialog = builder.create();
//
//        ImageView profileBtn = findViewById(R.id.foreground);
//        profileBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.show();
//            }
//        });
    }
}