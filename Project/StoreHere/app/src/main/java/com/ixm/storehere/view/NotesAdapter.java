package com.ixm.storehere.view;

/**
 * Created by ravi on 20/02/18.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ixm.storehere.R;
import com.ixm.storehere.database.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Note> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView note;
        private TextView title;
         private TextView dot;
        private TextView timestamp;

        public MyViewHolder(View view) {
            super( view );
            note = view.findViewById( R.id.note_row );
            title = view.findViewById( R.id.title_row );
            dot = view.findViewById( R.id.dot );
            timestamp = view.findViewById( R.id.timestamp_row );
        }
    }


    public NotesAdapter(Context context, List<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.note_list_row, parent, false );

        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notesList.get( position );
        holder.dot.setText( Html.fromHtml( "&#8226;" ) );
        holder.note.setText( note.getNote() );
        Log.i( "NOTE**", "onBindViewHolder: "+note.getNote());
        holder.title.setText( note.getTitle() );
        Log.i( "TITLE**", "onBindViewHolder: "+note.getTitle());
        holder.timestamp.setText(formatDate( note.getTimestamp() )   );
        Log.i( "TIMESTAMP**", "onBindViewHolder: "+note.getTimestamp());

    }

    @Override
    public int getItemCount() {
        Log.i( "COUNT BINDER", "getItemCount: "+notesList.size() );
        return notesList.size();
    }

    private String formatDate(String dateStr) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            Date date = fmt.parse( dateStr );
            @SuppressLint("SimpleDateFormat") SimpleDateFormat fmtOut = new SimpleDateFormat( "MMM d" );
            return fmtOut.format( date );
        } catch (ParseException ignored) {

        }

        return "";
    }
}
