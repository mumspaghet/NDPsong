package sg.edu.rp.c346.id20024313.ndpsong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "songs.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE= "title";
    private static final String COLUMN_SINGERS= "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS ="stars";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String sql= "CREATE TABLE "+ TABLE_SONG +"("
            +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_TITLE + " TEXT ,"
            +COLUMN_SINGERS+ " TEXT ,"
            +COLUMN_YEAR+ " INTEGER ,"
            +COLUMN_STARS+ " INTEGER )";
    db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE "+ TABLE_SONG +" ADD COLUMN songs TEXT ");
    }

    public long insertSong(String title, String singers, int year, int stars){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        long result = db.insert(TABLE_SONG,null, values);
        db.close();
        return result;
    }
    public ArrayList<Song> getAllSong(){
        ArrayList<Song> songs = new ArrayList<>();
        String sql = "SELECT "+ COLUMN_ID + ", "+ COLUMN_TITLE + ", "
                +COLUMN_SINGERS + ", "
                +COLUMN_YEAR + ", "
                +COLUMN_STARS
                +" FROM " + TABLE_SONG;
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int years = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(title, singers, years, stars );
                song.setId(cursor.getInt(0));
                songs.add(song);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
    public ArrayList<Song> getAllSong(String keyword) {
        ArrayList<Song> songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};

        String condition = COLUMN_STARS + "= ?";
        String[] args = { keyword };
        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do{
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int years = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(title, singers, years, stars );
                song.setId(cursor.getInt(0));
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
    public Integer updateSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song.getTitle());
        values.put(COLUMN_SINGERS, song.getSingers());
        values.put(COLUMN_YEAR, song.getYear());
        values.put(COLUMN_STARS, song.getStars());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(song.getId())};

        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public Integer deleteSong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }
}
