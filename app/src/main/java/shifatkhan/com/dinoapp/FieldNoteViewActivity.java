package shifatkhan.com.dinoapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by Shifat Khan on 22-Nov-16.
 */

public class FieldNoteViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        if(savedInstanceState == null){
            FieldNoteWebViewFragment details = new FieldNoteWebViewFragment();
            details.setArguments((getIntent().getExtras()));
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(android.R.id.content, details);
            ft.commit();
        }
    }
}
