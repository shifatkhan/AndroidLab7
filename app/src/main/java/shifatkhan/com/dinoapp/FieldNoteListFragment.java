package shifatkhan.com.dinoapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Shifat Khan on 22-Nov-16.
 */

public class FieldNoteListFragment extends ListFragment
        implements FragmentManager.OnBackStackChangedListener {

    private static final String DEBUG_TAG = "FieldNoteListFragment";
    int mCurPosition = -1;
    boolean mShowTwoFragments;

    private Context context;

    private static DBHelper dbh;

    private SimpleCursorAdapter sca;
    private Cursor cursor;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Define what we want to retrieve from db.
        String [] from = {DBHelper.COL_NAME, DBHelper.COL_ICON};
        int[] to = {R.id.textView1, R.id.imageView1};

        // Get data from the database and use the cursor as a list to cycle through.
        dbh = DBHelper.getDBHelper(context);
        cursor = dbh.getAllDinos();
        sca = new SimpleCursorAdapter(context, R.layout.program_list, cursor, from, to, 0);
        sca = new SimpleCursorAdapter(context, R.layout.program_list, cursor, from, to, 0);

        // Attach the list to the ListAdapter for display.
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(sca);

        // Get a handle on the FrameLayout and check if we can display it side by side.
        View detailsFrame = getActivity().findViewById(R.id.fieldentry);
        mShowTwoFragments = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if(savedInstanceState != null){
            mCurPosition = savedInstanceState.getInt("curChoice", 0);
        }
        if(mShowTwoFragments == true || mCurPosition != -1){
            viewDinoInfo(mCurPosition);
        }

        getFragmentManager().addOnBackStackChangedListener(this);
    }

    private void viewDinoInfo(int index){
        if(mShowTwoFragments){

            // Check what fragment is currently shown; replace if needed.
            FieldNoteWebViewFragment details = (FieldNoteWebViewFragment)getFragmentManager()
                    .findFragmentById(R.id.fieldentry);

            if(details == null || details.getShownIndex() != index){
                FieldNoteWebViewFragment newDetails = FieldNoteWebViewFragment.newInstance(index);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fieldentry, newDetails);

                if(index != -1){
                    if(cursor != null && cursor.moveToFirst())
                        ft.addToBackStack(cursor.getString(cursor.getColumnIndex("name")));
                }

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            } else {
                Intent intent = new Intent();
                intent.setClass(getActivity(), FieldNoteViewActivity.class);
                intent.putExtra("index", index);
            }
        }
    }

    /**
     * Override back stack to be sure that WebView is restored.
     */
    @Override
    public void onBackStackChanged() {
        FieldNoteWebViewFragment details = (FieldNoteWebViewFragment) getFragmentManager()
                .findFragmentById(R.id.fieldentry);

        if(details != null){
            mCurPosition = details.getShownIndex();
            getListView().setItemChecked(mCurPosition, true);

            if(!mShowTwoFragments){
                viewDinoInfo(mCurPosition);
            }
        }
    }
}
