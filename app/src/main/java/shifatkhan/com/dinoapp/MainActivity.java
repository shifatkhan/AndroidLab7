package shifatkhan.com.dinoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This is the main app where we display the listview
 *
 * @author Shifat Khan
 */
public class MainActivity extends AppCompatActivity {

    ListView mListView;
    Context context;

    ArrayList prgmName;

    public static int [] dinoImages ={R.drawable.antarctosaurus_icon,R.drawable.ankylosaurus_icon,R.drawable.anchiceratops_icon,
            R.drawable.allosaurus_icon,R.drawable.albertosaurus_icon,R.drawable.alamosaurus_icon,R.drawable.afrovenator_icon,
            R.drawable.achelousaurus_icon,R.drawable.abrosaurus_icon,R.drawable.abrictosaurus_icon};
    public static String [] dinoNames={"antarctosaurus","ankylosaurus","anchiceratops","allosaurus",
            "albertosaurus","alamosaurus","afrovenator","achelousaurus","abrosaurus","abrictosaurus"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        mListView =(ListView) findViewById(R.id.dinoListView);
        mListView.setAdapter(new CustomAdapter(this, dinoNames, dinoImages));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        menu.findItem(R.id.about).setIntent(new Intent(this, AboutActivity.class));

        //menu.findItem(R.id.website).setIntent(new Intent(this, AboutActivity.class));

        return true;
    }
}
