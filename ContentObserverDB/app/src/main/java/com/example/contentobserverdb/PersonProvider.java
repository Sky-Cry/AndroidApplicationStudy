package com.example.contentobserverdb;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PersonProvider extends ContentProvider {

    private static UriMatcher uriMatcher = new UriMatcher(-1);
    private static final int SUCCESS = 1;
    private PersonDBOpenHelper helper;
    static {
        uriMatcher.addURI("com.example.contentobserverdb","info",SUCCESS);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int code = uriMatcher.match(uri);
        if (code == SUCCESS){
            SQLiteDatabase db = helper.getWritableDatabase();
            int count = db.delete("info",selection,selectionArgs);
            if (count > 0){
                getContext().getContentResolver().notifyChange(uri,null);
            }
            db.close();
            return count;
        } else {
            throw new IllegalArgumentException("路径不正确，我是不会给你删除数据的！");
        }
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int code = uriMatcher.match(uri);
        if (code == SUCCESS){
            SQLiteDatabase db = helper.getWritableDatabase();
            long rowId = db.insert("info",null,values);
            if (rowId > 0){
                Uri insertUri = ContentUris.withAppendedId(uri,rowId);
                getContext().getContentResolver().notifyChange(insertUri,null);
                return insertUri;
            }
            db.close();
            return uri;
        } else {
            throw new IllegalArgumentException("路径不正确，我是不会给你插入数据的！");
        }
    }

    @Override
    public boolean onCreate() {
        helper = new PersonDBOpenHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int code = uriMatcher.match(uri);
        if (code == SUCCESS){
            SQLiteDatabase db = helper.getReadableDatabase();
            return db.query("info",projection,selection,selectionArgs,null,null,sortOrder);
        } else {
            throw new IllegalArgumentException("路径不正确，我是不会给你提供数据的！");
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int code = uriMatcher.match(uri);
        if (code == SUCCESS){
            SQLiteDatabase db = helper.getWritableDatabase();
            int count = db.update("info",values,selection,selectionArgs);
            if (count > 0){
                getContext().getContentResolver().notifyChange(uri,null);
            }
            db.close();
            return count;
        } else {
            throw new IllegalArgumentException("路径不正确，我是不会给你更新数据的！");
        }
    }
}
