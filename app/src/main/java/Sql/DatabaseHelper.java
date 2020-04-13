package Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "safedrive.db";
    public static final String TABLE_NAME = "user.db";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_USER_NOM = "user_nom";
    public static final String COL_USER_PRENOM = "user_prenom";
    public static final String COL_USER_EMAIL= "user_email";
    public static final String COL_USER_MOTDEPASSE= "user_motdepasse";
    public static final String COL_USER_TELEPHONE = "user_telephone";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private String CREATE_USER_TABLE = " CREATE TABLE " + TABLE_NAME +"("
            + COL_USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_USER_NOM + "TEXT," + COL_USER_PRENOM + "TEXT,"
            + COL_USER_EMAIL + "TEXT," + COL_USER_MOTDEPASSE + "TEXT,"
            + COL_USER_TELEPHONE + "TEXT" + ")";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public long AddUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_NOM, user.getNom());
        contentValues.put(COL_USER_PRENOM, user.getPrenom());
        contentValues.put(COL_USER_EMAIL, user.getEmail());
        contentValues.put(COL_USER_MOTDEPASSE, user.getMotdepasse());
        contentValues.put(COL_USER_TELEPHONE, user.getTelephone());

        long res = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return res;
    }


    public Boolean chekUser(String myEmail, String myPwd) {
        String[] columns = {
                COL_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_USER_EMAIL + " = ?" + "AND" + COL_USER_MOTDEPASSE + " = ?" ;
        String[] selectionArgs = {myEmail,myPwd};
        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        else {
            return false;
        }
    }
}


