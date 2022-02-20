package com.example.frontend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    protected static final String DATABASE_NAME = "EresepDatabase";

    public static final String TABLE_NAME = "UserData";
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "mail";
    public static final String COL_4 = "password";
    public static final String COL_5 = "role";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Users " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "mail TEXT ) ";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE Resep " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userid TEXT, " +
                "daftarresep TEXT, " +
                "penyakit TEXT, " +
                "tanggal TEXT) ";
        db.execSQL(sql2);

        String sql3 = "CREATE TABLE UserData" +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "mail TEXT, " +
                "password TEXT, " +
                "role TEXT )";
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Users";
        db.execSQL(sql);
        String sql2 = "DROP TABLE IF EXISTS Resep";
        db.execSQL(sql2);
        String sql3 = "DROP TABLE IF EXISTS UserData";
        db.execSQL(sql3);
        onCreate(db);
        //if(newVersion > oldVersion) {
        //db.execSQL("ALTER TABLE UserData ADD COLUMN role TEXT");
        //db.execSQL("ALTER TABLE Resep ADD COLUMN tanggal TEXT");
        //}
    }

    public long addUser(String user, String mail, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user);
        contentValues.put("mail", mail);
        contentValues.put("password", password);
        contentValues.put("role", role);

        long res = db.insert("UserData", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String mail, String password) {
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_3 + "=?" + " and " + COL_4 + "=?";
        String[] selectionArgs = {mail, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }

    public Cursor ViewResep() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Resep";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;

    }

    public List searchdatabymail(String email) {
        int userid = -1;
        String sql = "SELECT * FROM UserData WHERE mail = " + email;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery(sql, null);

        if (cursor1.moveToFirst()) {
            int id = Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("id")));
            userid = id;
        }
        cursor1.close();


        if (userid != -1) {
            List<ResepData> recordsList = new ArrayList<ResepData>();
            String sql2 = String.format("SELECT * FROM Resep WHERE userid =%s", userid);
            Cursor cursor = db.rawQuery(sql2, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));

                    String studentName = cursor.getString(cursor.getColumnIndex("name"));
                    String studentMail = cursor.getString(cursor.getColumnIndex("mail"));

                    ResepData resepData = new ResepData();

                    resepData.id = id;
                    resepData.daftarresep = studentName;
                    resepData.penyakit = studentMail;

                    recordsList.add(resepData);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            recordsList.get(recordsList.size() - 1);
            return recordsList;
        }
        return null;
    }

    public UserData getUserData(String email, String password) {
        UserData userData = null;
        //String sql = String.format("SELECT * FROM UserData WHERE mail =%s '", email+"'");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("select * from UserData where mail = ? and password = ?", new String[]{email, password});
        //Cursor cursor1 = db.rawQuery(sql,null);

        if (cursor1.moveToFirst()) {
            int id = Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("id")));
            String mail = cursor1.getString(cursor1.getColumnIndex("mail"));
            String role = cursor1.getString(cursor1.getColumnIndex("role"));
            String name = cursor1.getString(cursor1.getColumnIndex("name"));
            String pword = cursor1.getString(cursor1.getColumnIndex("password"));
            userData = new UserData();
            userData.setId(id);
            userData.setEmail(mail);
            userData.setRole(role);
            userData.setName(name);
            userData.setPassword(pword);
        }
        cursor1.close();
        return userData;
    }

    public long addResep(String email, String penyakit, String deskripsi, String tanggal) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("select id from UserData where mail = ? ", new String[]{email});
        cursor1.moveToFirst();
        int userid = Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("id")));
        ContentValues contentValues = new ContentValues();
        contentValues.put("userid", Integer.toString(userid));
        contentValues.put("penyakit", penyakit);
        contentValues.put("daftarresep", deskripsi);
        contentValues.put("tanggal", tanggal);
        long res = db.insert("Resep", null, contentValues);
        db.close();
        return res;
    }

    public ArrayList<ResepData> getAllData() {
        ArrayList<ResepData> reseplist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Resep ORDER BY tanggal(dateColumn) DESC Limit 1", null);


        while (res.moveToNext()) {
            int id = Integer.parseInt(res.getString(res.getColumnIndex("id")));
            String userid = res.getString(res.getColumnIndex("userid"));
            String daftarresep = res.getString(res.getColumnIndex("daftarresep"));
            String penyakit = res.getString(res.getColumnIndex("penyakit"));
            String tanggal = res.getString(res.getColumnIndex("tanggal"));

            ResepData resepData = new ResepData();
            resepData.id = id;
            resepData.userid = userid;
            resepData.daftarresep = daftarresep;
            resepData.penyakit = penyakit;
            resepData.tanggal = tanggal;

            reseplist.add(resepData);
        }
        return reseplist;
    }

    public Cursor ViewResepbyemail(String Email) {
        int userid = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("select id from UserData where mail = ? ", new String[]{Email});

        if (cursor1.moveToFirst()) {
            int id = Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("id")));
            userid = id;
        }
        cursor1.close();

        Cursor cursor = db.rawQuery("select * from Resep where userid = ? ", new String[]{Integer.toString(userid)});
        return cursor;
    }

    public boolean deleteResep(String id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("Resep", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;
    }
    public boolean updateResep(ResepData resepData){
        ContentValues values = new ContentValues();
        values.put("userid", resepData.userid);
        values.put("daftarresep", resepData.daftarresep);
        values.put("penyakit", resepData.penyakit);
        values.put("tanggal", resepData.tanggal);
        String where = "id = ?";
        String[] whereArgs = {Integer.toString(resepData.id)};
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful = db.update("Resep", values, where, whereArgs) > 0;
        db.close();
        return updateSuccessful;
    }
    public ResepData getResepDatabyid(String id){
        ResepData resepData = new ResepData();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery("select * from Resep where id = ? ", new String[]{id});

        if(cursor1.moveToFirst()){
            resepData.id = Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("id")));
            resepData.userid = cursor1.getString(cursor1.getColumnIndex("userid"));
            resepData.daftarresep = cursor1.getString(cursor1.getColumnIndex("daftarresep"));
            resepData.tanggal = cursor1.getString(cursor1.getColumnIndex("tanggal"));
            resepData.penyakit = cursor1.getString(cursor1.getColumnIndex("penyakit"));
        }
        cursor1.close();
        return resepData;
    }
}