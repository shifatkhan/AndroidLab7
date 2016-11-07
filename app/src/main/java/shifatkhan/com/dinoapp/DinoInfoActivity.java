package shifatkhan.com.dinoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This takes care of the dinosaur info activity
 *
 * @author Shifat Khan
 */
public class DinoInfoActivity extends AppCompatActivity {

    private String dinoName;
    private int index;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dino_info);

        dinoName = getIntent().getStringExtra("dinoName");
        index = getIntent().getIntExtra("index", -1);
        description = getResources().getStringArray(R.array.dinoInfo_array)[index];

        displayDinoInfo();
    }

    /**
     * Display all the info about the dinosaur on screen
     */
    private void displayDinoInfo(){
        LinearLayout ll = (LinearLayout) findViewById(R.id.dinoLinearLayout);

        TextView title = new TextView(this);
        title.setText(dinoName);
        title.setGravity(Gravity.CENTER_VERTICAL);
        ll.addView(title);

        ImageView dinoImg = new ImageView(this);
        final int resourceId = getResources().getIdentifier(dinoName, "drawable", getPackageName());
        dinoImg.setImageDrawable(getResources().getDrawable(resourceId));
        ll.addView(dinoImg);

        TextView desc = new TextView(this);
        desc.setText(description);
        desc.setGravity(Gravity.CENTER_VERTICAL);
        ll.addView(desc);
    }
}
