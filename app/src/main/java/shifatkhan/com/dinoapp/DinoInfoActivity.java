package shifatkhan.com.dinoapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This takes care of the dinosaur info activity
 *
 * @author Shifat Khan
 */
public class DinoInfoActivity extends AppCompatActivity {

    private static DBHelper dbh;

    private String dinoName;
    private int index;
    private String description;
    private int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dino_info);

        dbh = DBHelper.getDBHelper(this);

        dinoName = getIntent().getStringExtra("dinoName");
        index = getIntent().getIntExtra("index", -1);

        Cursor cursor = dbh.getDino(dinoName);
        while(cursor.moveToNext()){
            description = cursor.getString(0);
            img = cursor.getInt(1);
        }

        //description = getResources().getStringArray(R.array.dinoInfo_array)[index];

        displayDinoInfo();
    }

    /**
     * Display all the info about the dinosaur on screen
     */
    private void displayDinoInfo(){
        LinearLayout ll = (LinearLayout) findViewById(R.id.dinoLinearLayout);

        TextView title = (TextView) findViewById(R.id.name);
        title.setText(dinoName);
        title.setGravity(Gravity.CENTER_VERTICAL);

        ImageView dinoImg = (ImageView) findViewById(R.id.dinoimg);
        dinoImg.setImageResource(img);

        TextView desc = (TextView) findViewById(R.id.desc);
        desc.setText(description);
        desc.setGravity(Gravity.CENTER_VERTICAL);
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
