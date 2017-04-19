package com.rantiligando.coderepublicactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rantiligando.coderepublicactivity.library.*;

public class NameList extends Activity {
    private ListView listView;
    private ArrayList<HashMap<String,Object>> lst_names;
    private List_View_Adapter listAdapater;
    private Query query;
    private Intent intent;
    private String currentDateTimeString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_list);

        currentDateTimeString = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());

        listView = (ListView) findViewById(R.id.names_listView);

        query = new Query(this);
        lst_names = new ArrayList<HashMap<String,Object>>();
        lst_names = query.getNames();

        listAdapater = new List_View_Adapter(lst_names);
        listView.setAdapter(listAdapater);
        listView.setOnItemClickListener(new List_View_Listener());

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);

        menu.setHeaderTitle("Actions");
        menu.add("Delete");
        menu.add("Cancel");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getTitle().toString()){
            case "Delete" :
                query.deleteNames(currentDateTimeString,Integer.parseInt(this.lst_names.get(index).get("_id").toString()));
                finish();
                startActivity(getIntent());
                break;
            case "Cancel" :
                break;
            default:
                return  super.onContextItemSelected(item);
        }
        return true;
    }

    private class List_View_Adapter extends BaseAdapter{

        private ArrayList<HashMap<String,Object>> lst;

        List_View_Adapter(ArrayList<HashMap<String,Object>> lst){
            this.lst = lst;
        }

        public void setList(ArrayList<HashMap<String,Object>> lst){
            this.lst = lst;
        }
        @Override
        public int getCount() {
            return this.lst.size();
        }

        @Override
        public Object getItem(int index) {
            return this.lst.get(index);
        }

        @Override
        public long getItemId(int index) {
            return index;
        }

        @Override
        public View getView(int index, View view, ViewGroup parentView) {
            TextView tv = (TextView) LayoutInflater.from(NameList.this).inflate(R.layout.items, null);
            tv.setText(this.lst.get(index).get("first_name").toString()+ ' ' + this.lst.get(index).get("last_name").toString());
            tv.setTag(index);

            return tv;
        }
    }
    private class List_View_Listener implements OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parentView, View view, int index, long id) {

            intent = new Intent(NameList.this,Main.class);

            intent.putExtra("_id",Integer.parseInt(lst_names.get(index).get("_id").toString()));
            intent.putExtra("first_name",lst_names.get(index).get("first_name").toString());
            intent.putExtra("last_name",lst_names.get(index).get("last_name").toString());
            intent.putExtra("action","update");

            startActivity(intent);
        }
    }
}
