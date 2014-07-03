package org.yegie.helloworld.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sergey on 7/3/2014.
 */
public class MyArrayAdapter extends ArrayAdapter {

    public MyArrayAdapter(Context context, int resource, ArrayList<NoDetail> objects){
        super(context,resource,objects);

        objs = objects;
    }

    ArrayList<NoDetail> objs;

    @Override
    public View getView(int i, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int abc = R.layout.no_detail_object;

        ViewGroup vg = (ViewGroup) inflater.inflate(abc,null);


        TextView a_r_r= (TextView) vg.findViewById(R.id.width_ratio_rim);
        a_r_r.setText(objs.get(i).width+"-"+objs.get(i).ratio+"/"+objs.get(i).rim);

        TextView mph= (TextView) vg.findViewById(R.id.mph);
        mph.setText("60 mph would be "+objs.get(i).mph+" mph");

        TextView miles= (TextView) vg.findViewById(R.id.miles);
        miles.setText("1000 miles would be "+objs.get(i).miles+" miles");


        return vg;
    }


}
