package sg.edu.np.mad.madpractical4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.graphics.Color;
import com.google.android.material.imageview.ShapeableImageView;


// Add imports here
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent receivingEnd = getIntent();
        String name = receivingEnd.getStringExtra("name");
        Log.d("TAG", "My name is: " + name);

        // Initialize a new User object
        User user = new User("MAD", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus purus leo, tincidunt nec sapien et, convallis lobortis lacus", 1, false);

//        ShapeableImageView background = findViewById(R.id.background);
//        background.setColorFilter(Color.parseColor("#0047AB"));

        // Get the TextViews and Button from the layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnfollow = findViewById(R.id.followbtn);
        Button btnMessage = findViewById(R.id.msgbtn);

        // Set the TextViews with the User's name, description and default button message
        tvName.setText(name);
        tvDescription.setText(user.description);
        btnfollow.setText("Follow");

        btnfollow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (user.followed){
                    btnfollow.setText("Follow");
                    user.followed = false;
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                else if (!user.followed){
                    btnfollow.setText("Unfollow");
                    user.followed = true;
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
            }
        });
//
//        Intent messageGroup = new Intent(MainActivity.this, MessageGroup.class);
//        btnMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(messageGroup);
//            }
//        });
    }
}