package shifatkhan.com.dinoapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

/**
 * This is the main app where we display the listview
 *
 * @author Shifat Khan
 */
public class MainActivity extends AppCompatActivity {

    private static DBHelper dbh;

    ListView mListView;
    Context context;

    ArrayList prgmName;

    private SimpleCursorAdapter sca;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String [] from = {DBHelper.COL_NAME, DBHelper.COL_ICON};
        int[] to = {R.id.textView1, R.id.imageView1};

        context=this;

        setContentView(R.layout.activity_main);
        dbh = DBHelper.getDBHelper(this);

        mListView =(ListView) findViewById(R.id.dinoListView);
        //mListView.setAdapter(new CustomAdapter(this, dinoNames, dinoImages));

        cursor = dbh.getAllDinos();
        sca = new SimpleCursorAdapter(this, R.layout.program_list, cursor, from, to, 0);

        mListView.setAdapter(sca);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("MainActivity", "List Clicked");
                Intent dinoIntent = new Intent(context,DinoInfoActivity.class);
                dinoIntent.putExtra("index", i);

                Cursor s = (Cursor)adapterView.getItemAtPosition(i);

                dinoIntent.putExtra("dinoName", s.getString(1));
                context.startActivity(dinoIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        menu.findItem(R.id.about).setIntent(new Intent(this, AboutActivity.class));

        String url = "http://www.sciencekids.co.nz/pictures/dinosaurs.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        menu.findItem(R.id.website).setIntent(i);

        return true;
    }
}
