package org.yegie.helloworld.app;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class HelloWorldHome extends ActionBarActivity {

    private static final String TAG = "HelloWorld";

    private class PromptClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            TextView tv=(TextView)view;
            String current=tv.getText().toString();
            String base=getResources().getString(R.string.tire_size_enter);
            if(current.equals(base))
                tv.setText(R.string.new_prompt);
            else
                tv.setText(base);
        }

    }
    private class PlanetSelectedListener implements Spinner.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View tv,
                                   int pos, long id){
            String Sel = tv.getResources().getStringArray(R.array.planets_array)[pos];
            TextView view= (TextView) findViewById(R.id.TextPrompt);
            view.setText(Sel);
            arr.add(Sel);
            l.setAdapter(adapter);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }

    ArrayList<String> arr = new ArrayList<String>();
    ListView l;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world_home);

        View view=findViewById(R.id.TextPrompt);
        Spinner a = (Spinner) findViewById(R.id.test_spinner);

        view.setOnClickListener(new PromptClickListener());
        a.setOnItemSelectedListener(new PlanetSelectedListener());

        l = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        l.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hello_world_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Log.d(TAG, "Someone wants settings");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
