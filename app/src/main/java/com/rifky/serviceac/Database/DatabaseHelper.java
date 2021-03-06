package com.rifky.serviceac.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //nama database
    public static final String DATABASE_NAME = "db_apk";
    // nama tabel dan kolom pada user
    public static final String TABLE_USER = "tb_user";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "name";

    //nama tabel booking
    public static final String TABLE_BOOKING = "tb_booking";
    public static final String COL_ID_BOOKING= "ID_Booking";
    public static final String COL_NAMA = "nama";
    public static final String COL_ALAMAT = "alamat";
    public static final String COL_TELP = "no_telp";
    public static final String COL_TANGGAL = "tanggal";
    public static final String COL_HELP = "keluhan";


    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        //Tabel  User
        db.execSQL("create table " + TABLE_USER + " (" + COL_USERNAME + " TEXT PRIMARY KEY, " + COL_PASSWORD +
                " TEXT, " + COL_NAME + " TEXT)");
        //sample record tabel user
        db.execSQL("insert into " + TABLE_USER + " values ('admin@gmail.com','admin','Admin Perusahaan');");
        //Tabel Booking
        db.execSQL("create table " + TABLE_BOOKING + " ( " + COL_ID_BOOKING + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAMA + " TEXT, " + COL_ALAMAT + " TEXT, " + COL_TELP + " TEXT,  " + COL_TANGGAL + " TEXT, " +
                " " + COL_HELP + " TEXT )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public boolean Register(String username, String password, String name) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_USER + "(" + COL_USERNAME + ", " + COL_PASSWORD + ", " + COL_NAME + ") VALUES (?,?,?)", new String[]{username, password, name});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException {
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

}