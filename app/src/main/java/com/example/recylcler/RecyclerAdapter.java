package com.example.recylcler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Vholder>{

    /*
    -----------CONSTRUCTOR SETUP AND FIELDS-------
    CONTEXT IS NEEDED TO REFERENCE THE ACTIVITY IT'S BEING RUN ON
    LAYOUT INFLATER IS USED TO INFLATE THE VIEWS ON THE CONTEXT
     */

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<Details> listRecyc;

    public RecyclerAdapter(Context context, List<Details> listRecyc) {
        mContext= context;
        this.listRecyc = listRecyc;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //VIEW (itemview) IS BEING INFLATED HERE BY LAYOUT INFLATER
        View itemview = mLayoutInflater.inflate(R.layout.recycler_itemlist,parent,false);


        return new Vholder(itemview);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onBindViewHolder(@NonNull Vholder holder, int position) {
        //THE POSITION VARIABLE IS USED TO GET THE POSITION OF ITEMS IN THE DATA LIST

        //AN OBJ VARIABLE IS CREATED BECAUSE THE LIST IS OF AN OBJECT TYPE OF THE CLASS IT WAS MADE FROM

        Details deets = listRecyc.get(position);
        holder.mNoteTitle.setText(deets.getName());
        holder.mNoteCourses.setText(deets.getOccupation());
        holder.currentPosition = position;

//        holder.mNumCount.setText(holder.currentPosition);



    }

//    public int count(int num){
//        num ++;
//
//        return num;
//    }

    @Override
    public int getItemCount() {
        return listRecyc.size();
    }

// -----VIEW HOLDER NESTED CLASS BEGINS HERE

    public class Vholder extends RecyclerView.ViewHolder{
        public final TextView mNoteTitle;
        public final TextView mNoteCourses;
        public final TextView mNumCount;
        public int currentPosition;

        public Vholder(@NonNull View itemView) {
            super(itemView);
            mNoteTitle = itemView.findViewById(R.id.text_title);
            mNoteCourses = itemView.findViewById(R.id.text_note);
            mNumCount = itemView.findViewById(R.id.num_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, mNoteTitle.getText() + " is the title and " + mNoteCourses.getText() + " is the course",
                            Toast.LENGTH_SHORT).show();
                }
            });

            mNoteTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,
                            "You just clicked the title specifically",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
