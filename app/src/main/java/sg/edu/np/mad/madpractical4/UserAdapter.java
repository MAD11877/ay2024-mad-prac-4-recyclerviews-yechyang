package sg.edu.np.mad.madpractical4;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> data;
    private Context context;

    // Constructor accepting data list
    public UserAdapter(ArrayList<User> input, Context context) {
        this.data = input;
        this.context = context;
    }

    // Correct implementation of onCreateViewHolder
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View item = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.custom_activity_list, parent, false); // Inflate custom layout
        View item;
        if (viewType == 1) {
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_activity_list_ending_7, parent, false);
        } else {
            // Default layout for other names
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_activity_list, parent, false);
        }
        return new UserViewHolder(item); // Correct ViewHolder type
    }

    @Override
    public int getItemViewType(int position) {
        String name = data.get(position).name;
        char lastChar = name.charAt(name.length() - 1); // Get the last character
        if (lastChar == '7') { // Correctly check if it's '7'
            return 1; // Return view type for custom layout
        }
        return 0; // Default view type
    }

    public void onBindViewHolder(
            UserViewHolder holder,
            int position) {
        User s = data.get(position);

        Random random = new Random();
        int randomNumber = random.nextInt(1000000) + 1;
        String nameWithRandom = s.name + randomNumber;
        holder.txtName.setText(nameWithRandom);
        holder.txtDescription.setText(s.description);
        holder.imgUser.setOnClickListener(view -> {
            if (context != null) { // Check if context is valid
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(nameWithRandom);

                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)  {
                        dialog.dismiss(); // Simply close the dialog
                    }
                });

                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("user_name", nameWithRandom);
                        intent.putExtra("description", s.description);
                        context.startActivity(intent);
                    }
                });
                builder.show(); // Show the AlertDialog
            }
        });
    }

    public int getItemCount() {
        return data.size();
    }
}



