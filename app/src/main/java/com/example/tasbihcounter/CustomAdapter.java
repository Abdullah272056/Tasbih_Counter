package com.example.tasbihcounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    private List<Note> allNote;
    private DataBaseHelper databaseHelper;

    public CustomAdapter(Context context, List<Note> allNote) {
        this.context = context;
        this.allNote = allNote;
        databaseHelper=new DataBaseHelper(context);

       // copyAllNotes = new ArrayList<> (allNote);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.zikirNameTextView.setText(allNote.get(position).getZikirName ());
        holder.dateTextView.setText(allNote.get(position).getTime ());
        holder.countValueTextView.setText(String.valueOf (allNote.get(position).getCountValue ()));

        holder.menuImageView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                androidx.appcompat.app.AlertDialog.Builder builder  = new androidx.appcompat.app.AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_operation,null);

                builder.setView(view);

                final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
                Toast.makeText (context, "Clicked", Toast.LENGTH_SHORT).show ();


                TextView updateTextView=view.findViewById(R.id.updateTextViewId);
                TextView deleteTextView=view.findViewById(R.id.deleteTextViewId);
                TextView cancelTextView=view.findViewById(R.id.cancelTextViewId);

                updateTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // customDialog(position);
                        alertDialog.dismiss();

                    }
                });

                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        int status = databaseHelper.deleteData(allNotes.get(position).getId());
//                        if (status == 1){
//                            allNotes.remove(allNotes.get(position));
//                            alertDialog.dismiss();
//                            notifyDataSetChanged();
//                        }else {
//                        }
                    }
                });

                cancelTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });


                alertDialog.show ();

            }
        });
    }

    @Override
    public int getItemCount() {
        return allNote.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView zikirNameTextView,countValueTextView,dateTextView;
        ImageView menuImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            zikirNameTextView= itemView.findViewById(R.id.zikirNameTextViewId);
           dateTextView= itemView.findViewById(R.id.dateTextViewId);
            countValueTextView= itemView.findViewById(R.id.counterValueTextViewId);
            menuImageView= itemView.findViewById(R.id.menuImageViewId);


        }
    }
}
