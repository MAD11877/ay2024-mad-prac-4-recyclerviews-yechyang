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
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> data;
    private Context context;
    private List<Integer> randomNumbers;

    // Constructor accepting data list
    public UserAdapter(ArrayList<User> input, Context context) {
        this.data = input;
        this.context = context;
        this.randomNumbers = new ArrayList<>(data.size());
        for (int i = 0; i < data.size(); i++) {
            randomNumbers.add(null); // Use null to signify uninitialized values
        }
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutResource;
        // Check if the position is an even or odd index and determine the correct layout
        if (viewType == 1) { // Layout when the random number ends with 7
            layoutResource = R.layout.custom_activity_list_ending_7; // Your custom layout for condition 7
        } else { // Default layout
            layoutResource = R.layout.custom_activity_list;
        }

        View item = LayoutInflater.from(parent.getContext())
                .inflate(layoutResource, parent, false);

        return new UserViewHolder(item);
    }

    @Override
    public int getItemViewType(int position) {
        // Check if we already have a random number for this position
        if (randomNumbers.get(position) == null) {
            Random random = new Random();
            int randomNumber = random.nextInt(1000000) + 1; // Generate a new random number
            randomNumbers.set(position, randomNumber); // Store the generated random number
        }

        int randomNumber = randomNumbers.get(position);

        // If the last digit is 7, return a specific view type, otherwise the default
        return (randomNumber % 10 == 7) ? 1 : 0;
    }

    public void onBindViewHolder(
            UserViewHolder holder,
            int position) {
        User s = data.get(position);

        int randomNumber = randomNumbers.get(position);
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



