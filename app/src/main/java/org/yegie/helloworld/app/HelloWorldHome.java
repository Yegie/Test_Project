package org.yegie.helloworld.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

    public void recalculate(){
        System.out.println("Recalculating wheels");
        objs.add(new NoDetail(Integer.parseInt(width),Integer.parseInt(ratio),Double.parseDouble(rim),60,1000));

        adapter.notifyDataSetChanged();
    }


    private class SelectedListener implements Spinner.OnItemSelectedListener {


        public boolean checkNeed( View tv, int pos) {

            // This is a mystery wrapped in an enigma -- android calls us
            // twice on rotation, first time with a null view.
            //
            if(tv==null)
                return false;

            if(don == 0) {
                don++;
                return false;
            }

            if(pos == 0)
                return false;

            return true;
            // Log.d(TAG, Log.getStackTraceString(new Exception()));


        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

        public void check(){

            if(
                    width != null &&
                    ratio != null &&
                    rim != null
            )
                recalculate();
            else
                return;
        }

    }
    private class WidthSelectedListener extends SelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View tv, int pos, long id) {
            if(!super.checkNeed(tv, pos)){
                return;
            }

            String Sel = getResources().getStringArray(R.array.width_array)[pos];

            width = Sel;

            System.out.println(width+"-"+ratio+"/"+rim);

            super.check();
        }

    }
    private class RatioSelectedListener extends SelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View tv, int pos, long id) {
            if(!super.checkNeed(tv, pos)){
                return;
            }

            String Sel = getResources().getStringArray(R.array.ratio_array)[pos];

            ratio = Sel;

            System.out.println(width+"-"+ratio+"/"+rim);

            super.check();
        }

    }

    private class RimSelectedListener extends SelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View tv, int pos, long id) {
            if(!super.checkNeed(tv, pos)){
                return;
            }

            String Sel = getResources().getStringArray(R.array.rim_array)[pos];

            rim = Sel;


            System.out.println(width+"-"+ratio+"/"+rim);

            super.check();

        }

    }

    ArrayList<String> arr = new ArrayList<String>();
    ArrayList<NoDetail> objs = new ArrayList<NoDetail>();
    ListView l;
    MyArrayAdapter adapter;

    String width;
    String ratio;
    String rim;

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        arr = savedInstanceState.getStringArrayList("aaa");
        connectArrayAdapter();
    }

    private void connectArrayAdapter() {
        adapter = new MyArrayAdapter(this,android.R.layout.simple_list_item_1,objs);
        l.setAdapter(adapter);
    }

    int don = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world_home);

        objs.add(new NoDetail());
        objs.add(new NoDetail(155,65,14,59,1007));

        View view=findViewById(R.id.TextPrompt);
        view.setOnClickListener(new PromptClickListener());

        Spinner a = (Spinner) findViewById(R.id.test_spinner);
        a.setOnItemSelectedListener(new WidthSelectedListener());

        Spinner b = (Spinner) findViewById(R.id.test_spinner2);
        b.setOnItemSelectedListener(new RatioSelectedListener());

        Spinner c = (Spinner) findViewById(R.id.test_spinner3);
        c.setOnItemSelectedListener(new RimSelectedListener());

        l = (ListView) findViewById(R.id.listView);

        connectArrayAdapter();


//        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        for(int i = 0; i<objs.size(); i++){
//
//            int abc = R.layout.no_detail_object;
//
//            ViewGroup vg = (ViewGroup) inflater.inflate(abc,null);
//
//
//            TextView a_r_r= (TextView) vg.findViewById(R.id.width_ratio_rim);
//            a_r_r.setText(objs.get(i).width+"-"+objs.get(i).ratio+"/"+objs.get(i).rim);
//
//            TextView mph= (TextView) vg.findViewById(R.id.mph);
//            mph.setText("60 mph would be "+objs.get(i).mph+" mph");
//
//            TextView miles= (TextView) vg.findViewById(R.id.miles);
//            miles.setText("1000 miles would be "+objs.get(i).miles+" miles");
//
//
//            ((ViewGroup) findViewById(R.id.vertical_layout)).addView(vg);
//
//
//        }


    }
    @Override
    protected void onSaveInstanceState(Bundle out){
        super.onSaveInstanceState(out);
        out.putStringArrayList("aaa" ,arr);

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
