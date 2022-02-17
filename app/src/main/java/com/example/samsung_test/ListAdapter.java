package com.example.samsung_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.UserView> {
    private ArrayList<DBHelper> arrayList;
    public ListAdapter(ArrayList<DBHelper> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UserView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.users_list,parent,false);
        return new UserView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserView holder, int position) {
        String user =String.valueOf(arrayList.get(position).getFirst_name()+ " " + arrayList.get(position).getLast_name());
        String id =String.valueOf(arrayList.get(position).getId());
        String email =arrayList.get(position).getEmail();
        String userImageUrl = arrayList.get(position).getAvatar();

        Picasso.get().load(userImageUrl).into(holder.userImage);

        holder.textUser.setText(user);
        holder.textEmail.setText(email);
        holder.textId.setText(id);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class UserView extends RecyclerView.ViewHolder{
        TextView textUser;
        TextView textId;
        TextView textEmail;
        ImageView userImage;

        public UserView(@NonNull View itemView) {

            super(itemView);
            textUser = (TextView) itemView.findViewById(R.id.textUser);
            textId = (TextView) itemView.findViewById(R.id.textId);
            textEmail = (TextView) itemView.findViewById(R.id.textEmail);
            userImage = (ImageView) itemView.findViewById(R.id.userImage);
        }
    }
}
