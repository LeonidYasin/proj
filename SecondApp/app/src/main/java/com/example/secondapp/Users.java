package com.example.secondapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.secondapp.database.UserBaseHelper;
import com.example.secondapp.database.UserDbSchema;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private ArrayList<User> userList;
    private SQLiteDatabase database;
    private Context context;

    public Users(Context context) {
        this.context = context.getApplicationContext();
        this.database = new UserBaseHelper(context).getWritableDatabase();
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        // Сопоставляем колонки и свойства объекта User
        values.put(UserDbSchema.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.Cols.USERNAME, user.getUserName());
        values.put(UserDbSchema.Cols.USERLASTNAME, user.getUserLastName());
        values.put(UserDbSchema.Cols.PHONE, user.getPhone());
        return values;
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME, null, values);

    }

    public void deleteUser(String uUID) {


        database.delete(UserDbSchema.UserTable.NAME,
                UserDbSchema.Cols.UUID + " = ?",
                new String[]{uUID});

    }

    public void infoUserByUUID(String uUID) {

        UserCursorWrapper cursor = new UserCursorWrapper(
                database.query(UserDbSchema.UserTable.NAME, null,
                        UserDbSchema.Cols.UUID + " = ?",
                        new String[]{uUID},
                        null, null, null));
        cursor.moveToFirst();
        String uuidString = cursor.getString(cursor.getColumnIndex(UserDbSchema.Cols.UUID));
        String userName = cursor.getString(cursor.getColumnIndex(UserDbSchema.Cols.USERNAME));
        String userLastName = cursor.getString(cursor.getColumnIndex(UserDbSchema.Cols.USERLASTNAME));
        String phone = cursor.getString(cursor.getColumnIndex(UserDbSchema.Cols.PHONE));

        System.out.println("uuidString " + uuidString);
        System.out.println("userName " + userName);
        System.out.println("userLastName " + userLastName);
        System.out.println("phone " + phone);
        // database.insert(UserDbSchema.UserTable.NAME, null,values);
    }

    public void infoUserByUSERNAME(String USERNAME) {

        UserCursorWrapper cursor = new UserCursorWrapper(database.query(UserDbSchema.UserTable.NAME, null,
                UserDbSchema.Cols.USERNAME + " = ?",
                new String[]{USERNAME},
                null, null, null));
        cursor.moveToFirst();
        String uuidString = cursor.getString(cursor.getColumnIndex(UserDbSchema.Cols.UUID));
        String userName = cursor.getString(cursor.getColumnIndex(UserDbSchema.Cols.USERNAME));
        String userLastName = cursor.getString(cursor.getColumnIndex(UserDbSchema.Cols.USERLASTNAME));
        String phone = cursor.getString(cursor.getColumnIndex(UserDbSchema.Cols.PHONE));

        System.out.println("uuidString " + uuidString);
        System.out.println("userName " + userName);
        System.out.println("userLastName " + userLastName);
        System.out.println("phone " + phone);

    }

    private UserCursorWrapper queryUsers() {
        Cursor cursor = database.query(UserDbSchema.UserTable.NAME, null, null, null, null, null, null);
        return new UserCursorWrapper(cursor);
    }

    public ArrayList<User> getUserList() {
        this.userList = new ArrayList<User>();
        UserCursorWrapper cursorWrapper = queryUsers();
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                User user = cursorWrapper.getUser();
                userList.add(user);
                cursorWrapper.moveToNext();

            }
        } finally {
            cursorWrapper.close();
        }
        return userList;
    }
}