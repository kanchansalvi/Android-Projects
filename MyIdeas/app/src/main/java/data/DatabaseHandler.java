package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import model.MyIdea;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final ArrayList<MyIdea> ideaList = new ArrayList<>();

    public DatabaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IDEA_TABLE = "CREATE TABLE " + Constants.TABLE_NAME +  "(" +
                Constants.KEY_ID        + " INTEGER PRIMARY KEY, " +
                Constants.TITLE_NAME    + " TEXT, "                +
                Constants.CONTENT_NAME  + " TEXT, "                +
                Constants.DATE_NAME     + " LONG                   );";
        db.execSQL(CREATE_IDEA_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);

    }

    public void deleteIdea(int id){

        SQLiteDatabase db = this.getWritableDatabase();

        Log.v("** Deleted Key ::::", String.valueOf(id));
        db.delete(Constants.TABLE_NAME,
                  Constants.KEY_ID + " = ? ",
                   new String[]{String.valueOf(id)}
                   );

        db.close();
    }
    public void addIdeas(MyIdea idea){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.TITLE_NAME, idea.getTitle());
        Log.v("My title **** ", idea.getTitle());
        values.put(Constants.CONTENT_NAME, idea.getContent());
        values.put(Constants.DATE_NAME, System.currentTimeMillis());

        db.insert(Constants.TABLE_NAME, null,values);
        db.close();

        Log.v("Idea added Successfully", "done*******************************************************************");

    }

    public ArrayList<MyIdea> getIdeaList() {

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                                new String[]{Constants.KEY_ID , Constants.TITLE_NAME, Constants.CONTENT_NAME,Constants.DATE_NAME},
                                null,
                                null,
                                null,
                                null,
                                Constants.DATE_NAME + " DESC ;"
                                );

        if(cursor.moveToFirst()){
            do {
                MyIdea idea = new MyIdea();
                idea.setTitle(cursor.getString(cursor.getColumnIndex(Constants.TITLE_NAME)));
                idea.setContent(cursor.getString(cursor.getColumnIndex(Constants.CONTENT_NAME)));
                idea.setItemId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));

                Log.v("ItemId set :::::" , String.valueOf(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID))));

                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String ideaDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.DATE_NAME))).getTime());
                idea.setRecorddate(ideaDate);

                ideaList.add(idea);

            } while (cursor.moveToNext());
        }

        return ideaList;
    }
}
