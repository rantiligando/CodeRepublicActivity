package com.rantiligando.coderepublicactivity.library;

import android.content.Context;
import java.util.HashMap;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentValues;

/**
 * Created by rantiligando on 4/11/2017.
 */

public class Query extends DBConnectionHelper{
    private Context context;
    private ContentValues args;
    private HashMap<String,Object> hm;

    public Query(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }
    public void insertNames(HashMap<String,Object> hm){
        args = new ContentValues();

        args.put("first_name", hm.get("first_name").toString());
        args.put("last_name", hm.get("last_name").toString());

        database.insert(Init.TABLE_USERS, null, args);
    }
    public void updateNames(String firstName,String lastName,int id){
        args = new ContentValues();

        args.put("first_name", firstName);
        args.put("last_name", lastName);

        database.update(Init.TABLE_USERS,args,"_id="+id,null);
    }
    public void deleteNames(String datetime,int id){
        args = new ContentValues();

        args.put("deleted", datetime);

        database.update(Init.TABLE_USERS,args,"_id="+id,null);
    }
    public ArrayList<HashMap<String,Object>> getNames(){
        String all[] = {"_id","first_name","last_name"};

        Cursor cursor = database.query(true,Init.TABLE_USERS,all,"deleted IS NULL", null, null, null, null,null);
        cursor.moveToFirst();
        ArrayList<HashMap<String,Object>> al = new ArrayList<HashMap<String,Object>>();
        for(int i = 1; i <= cursor.getCount(); i++){
            hm = new HashMap<String,Object>();
            for(int col = 0; col < all.length; col++)
                hm.put(all[col], cursor.getString(col));
            al.add(hm);
            cursor.moveToNext();
        }
        cursor.close();
        return al;
    }
}
