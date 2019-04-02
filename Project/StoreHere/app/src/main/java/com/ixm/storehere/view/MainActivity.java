package com.ixm.storehere.view;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ixm.storehere.R;
import com.ixm.storehere.database.DatabaseHelper;
import com.ixm.storehere.database.model.Note;
import com.ixm.storehere.utils.MyDividerItemDecoration;
import com.ixm.storehere.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;


public class MainActivity extends AppCompatActivity {
    private NotesAdapter mAdapter;
    private List<Note> notesList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

       /* Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar);
        setSupportActionBar(toolbar);
*/

        coordinatorLayout = findViewById( R.id.coordinator_layout );
        recyclerView = findViewById( R.id.recycler_view );
        noNotesView = findViewById( R.id.empty_notes_view );

        db = new DatabaseHelper( this );

        notesList.addAll( db.getAllNotes() );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoteDialog( false, null, -1 );
            }
        } );

        mAdapter = new NotesAdapter( this, notesList );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( mLayoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.addItemDecoration( new MyDividerItemDecoration( this, LinearLayoutManager.VERTICAL, 16 ) );
        recyclerView.setAdapter( mAdapter );
  //      mAdapter.notifyDataSetChanged();
        toggleEmptyNotes();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerView.addOnItemTouchListener( new RecyclerTouchListener( this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog( position );
            }
        } ) );
    }

    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService( Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }

    @Override
    public void onBackPressed() {
        ExitActivity.exitApplicationAndRemoveFromRecent(MainActivity.this);
        super.onBackPressed();
    }

    private void createNote(String note,String timestamp, String title) {

        long id = db.insertNote( note, timestamp,title);

        Note n = db.getNotes( id );


        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add( 0, n );
            mAdapter.notifyDataSetChanged();
            toggleEmptyNotes();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateNote(String note, String title, int position) {
        Note n = notesList.get( position );
        // updating note text
        n.setNote( note );
        n.setTitle( title );
        // updating note in db
        db.updateNote( n );

        // refreshing the list
        notesList.set( position, n );
        mAdapter.notifyItemChanged( position );

        toggleEmptyNotes();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteNote(int position) {
        // deleting the note from db
        db.deleteNote( notesList.get( position ) );

        // removing the note from the list
        notesList.remove( position );
        mAdapter.notifyItemRemoved( position );

        toggleEmptyNotes();
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Choose option" );
        builder.setItems( colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showNoteDialog( true, notesList.get( position ), position );
                } else {
                    deleteNote( position );
                }
            }
        } );
        builder.show();
    }


    /**
     * Shows alert dialog with EditText options to enter / edit
     * a note.
     * when shouldUpdate=true, it automatically displays old note and changes the
     * button text to UPDATE
     */
    private void showNoteDialog(final boolean shouldUpdate, final Note note, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from( getApplicationContext() );
        View view = layoutInflaterAndroid.inflate( R.layout.note_dialog, null );

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder( MainActivity.this );
        alertDialogBuilderUserInput.setView( view );
        final EditText inputTitle = view.findViewById( R.id.note_title_dialog );
        final EditText inputNote = view.findViewById( R.id.note_dialog_note );
       // final EditText timestamp=view.findViewById( R.id.timestamp );


        TextView dialogTitle = view.findViewById( R.id.dialog_title );
        dialogTitle.setText( !shouldUpdate ? getString( R.string.lbl_new_note_title ) : getString( R.string.lbl_edit_note_title ) );

        if (shouldUpdate && note != null) {
            inputTitle.setText( note.getTitle() );
            Log.i( "inputTitle", "showNoteDialog: "+note.getTitle() );
            inputNote.setText( note.getNote() );
            Log.i( "inputNote", "showNoteDialog: "+note.getNote() );

        }
        alertDialogBuilderUserInput
                .setCancelable( false )
                .setPositiveButton( shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                } )
                .setNegativeButton( "cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        } );

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton( AlertDialog.BUTTON_POSITIVE ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty( inputTitle.getText().toString() )) {
                    Toast.makeText( MainActivity.this, "Enter Title!", Toast.LENGTH_SHORT ).show();
                    return;
                }else  if (TextUtils.isEmpty( inputNote.getText().toString() )) {
                    Toast.makeText( MainActivity.this, "Enter note!", Toast.LENGTH_SHORT ).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && note != null) {
                    // update note by it's id
                    updateNote( inputNote.getText().toString(), inputTitle.getText().toString(), position );
                } else {
                    Log.i( "Input", "onClick: "+inputNote.getText().toString() );
                    Log.i( "Input", "onClick: "+inputTitle.getText().toString() );
                    createNote( inputNote.getText().toString(),"", inputTitle.getText().toString() );
                }
            }
        } );
    }

    /**
     * Toggling list and empty notes view
     */
    private void toggleEmptyNotes() {
        // you can check notesList.size() > 0

        if (db.getNotesCount() > 0) {
            noNotesView.setVisibility( View.GONE );
        } else {
            noNotesView.setVisibility( View.VISIBLE );
        }
    }
}
