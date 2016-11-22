package shifatkhan.com.dinoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shifat Khan
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "dino.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_DINO = "dino";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_INFO = "info";
    public static final String COL_ICON = "icon_id";
    public static final String COL_IMAGE = "image_id";

    private static DBHelper dbh = null;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create the Database
        String query = "create table " + TABLE_DINO + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT," +
                COL_INFO + " TEXT," +
                COL_ICON + " INTEGER," +
                COL_IMAGE + " INTEGER);";

        sqLiteDatabase.execSQL(query);

        populateDatabase(sqLiteDatabase);

    }

    /**
     * Populate db
     */
    private void populateDatabase(SQLiteDatabase db){
        // Populate the database
        ContentValues cv = new ContentValues();

        int [] dinoImages = {R.drawable.antarctosaurus,R.drawable.ankylosaurus,R.drawable.anchiceratops,
                R.drawable.allosaurus,R.drawable.albertosaurus,R.drawable.alamosaurus,R.drawable.afrovenator,
                R.drawable.achelousaurus,R.drawable.abrosaurus,R.drawable.abrictosaurus};
        int [] dinoImagesIco ={R.drawable.antarctosaurus_icon,R.drawable.ankylosaurus_icon,R.drawable.anchiceratops_icon,
                R.drawable.allosaurus_icon,R.drawable.albertosaurus_icon,R.drawable.alamosaurus_icon,R.drawable.afrovenator_icon,
                R.drawable.achelousaurus_icon,R.drawable.abrosaurus_icon,R.drawable.abrictosaurus_icon};
        String [] dinoNames={"antarctosaurus","ankylosaurus","anchiceratops","allosaurus",
                "albertosaurus","alamosaurus","afrovenator","achelousaurus","abrosaurus","abrictosaurus"};
        String [] dinoInfo = {"desc1","desc2","desc3","desc4",
                "desc5","desc6","desc7","desc8","desc9","desc10"};

        for(int i = 0; i < dinoNames.length; i++){
            cv.put(COL_NAME, dinoNames[i]);
            cv.put(COL_INFO, dinoInfo[i]);
            cv.put(COL_ICON, dinoImagesIco[i]);
            cv.put(COL_IMAGE, dinoImages[i]);

            long id = db.insert(TABLE_DINO, null,cv);
        }
    }

    /**
     * Return DBH
     * @param context
     * @return
     */
    public static DBHelper getDBHelper(Context context){
        if(dbh == null)
            dbh = new DBHelper(context.getApplicationContext());

        return dbh;
    }

    /**
     * Returns all the dinos from database
     * @return Cursor
     */
    public Cursor getAllDinos(){
        return getReadableDatabase().query(TABLE_DINO, null, null, null, null, null, null);
    }

    /**
     * Returns a dino from a specified name from database
     * @return Cursor
     */
    public Cursor getDino(String dinoName){
        return getReadableDatabase().query(TABLE_DINO, new String[]{COL_INFO, COL_IMAGE}, COL_NAME+"=?", new String[]{dinoName},
                null, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
