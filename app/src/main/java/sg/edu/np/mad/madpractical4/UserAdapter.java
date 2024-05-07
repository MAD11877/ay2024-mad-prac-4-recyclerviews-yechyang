package sg.edu.np.mad.madpractical4;

import static androidx.core.content.ContextCompat.startActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private ArrayList<User> list_objects;
    private static final String TAG = "list_objects";

    public UserAdapter(ArrayList<User> list_objects, ListActivity activity){
        this.list_objects = list_objects;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(UserViewHolder holder, int position){
        User list_items = list_objects.get(position);
        holder.name.setText(list_items.getName());
        holder.description.setText(list_items.getDescription());
        Log.d(TAG, list_items.getName());

        int lastDigit = Integer.parseInt(list_items.getName()) % 10;
        if (lastDigit == 7){
            holder.bigImage.setVisibility(View.VISIBLE);
        }
        else{
            holder.bigImage.setVisibility(View.GONE);
        }
    }

    public int getItemCount(){return list_objects.size();}

    // Number 7 Here

}
