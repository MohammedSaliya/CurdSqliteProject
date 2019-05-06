package com.example.curdsqlite;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.MyViewHolder> {

    private Context context;
    private List<Bean> beanResponce;

    DatabaseHelper myDb;


    public RecycAdapter(Context context, List<Bean> beanResponce) {
        this.context = context;
        this.beanResponce = beanResponce;

        myDb = new DatabaseHelper(context);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recyc_adapter, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.id.setText(beanResponce.get(i).getId());
        myViewHolder.name.setText(beanResponce.get(i).getName());
        myViewHolder.surname.setText(beanResponce.get(i).getSurname());
        myViewHolder.marks.setText(beanResponce.get(i).getMarks());


        myViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("id", myViewHolder.id.getText().toString());
                intent.putExtra("name", myViewHolder.name.getText().toString());
                intent.putExtra("surename", myViewHolder.surname.getText().toString());
                intent.putExtra("marks", myViewHolder.marks.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deletedRows = myDb.deleteData(myViewHolder.id.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(context, "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, "Data not Deleted", Toast.LENGTH_LONG).show();
                beanResponce.remove(i);
                notifyDataSetChanged();

                notifyItemRemoved(i);

            }
        });


    }


    @Override
    public int getItemCount() {
        return beanResponce.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name, surname, marks;
        public ImageView edit, delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            surname = itemView.findViewById(R.id.surname);
            marks = itemView.findViewById(R.id.marks);

            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);


        }
    }
}