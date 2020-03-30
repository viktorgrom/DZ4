package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    String[] sampleArray = {"Item1", "Item2", "Item3", "Item4", "Item5", "Item6",
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyAdapter myAdapter = new MyAdapter(sampleArray);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ((position + 1) % 2 == 0) {
                    TextView textView = (TextView) view.findViewById(R.id.label);
                    textView.setText("WAS CLICKED!!!");
                }
            }
        });
    }


    class MyAdapter extends BaseAdapter {

        String[] sampleArray;

        public MyAdapter(@NonNull String[] array) {
            super();
            sampleArray = array;
        }

        @Override
        public int getCount() {
            if (sampleArray == null) return 0;
            return sampleArray.length;
        }

        @Override
        public Object getItem(int position) {
            if (position <= sampleArray.length - 1) return sampleArray[position];
            return null;
        }

        @Override
        public long getItemId(int position) {
            if (position <= sampleArray.length - 1) return position + 1;
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.label.setText((String)getItem(position));

            return convertView;
        }


        private class ViewHolder {
            TextView label;

            public ViewHolder(View view){
                label = (TextView) view.findViewById(R.id.label);
            }
        }
    }

}
