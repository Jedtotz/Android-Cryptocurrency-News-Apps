package com.example.cryptonomist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URI;
import java.util.ArrayList;

import static android.net.Uri.parse;


public class OneFragment extends Fragment {

    private ListView lv;
    private newsAdapter adapter;
    private ArrayList<News> data;


    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_one_fragment, container,false);

        ListView lv = rootView.findViewById(R.id.listviewnews);
        data = new ArrayList<News>() ;

        //News datagratis = new News("Testing Title","adasdadasds","ETH","adsadasdasaddddddd",2,1);

        //data.add(datagratis);

        Intent intent = getActivity().getIntent();
        data = (ArrayList<News>) intent.getSerializableExtra("DataSend");




        adapter = new newsAdapter(getActivity(),data);

        lv.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News tempTask = data.get(position);

                String url = tempTask.getSource();
                //Log.e("asdasdas",url);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(parse(url));
                startActivity(i);
//
                //intent.putExtra("id", ""+tempTask.getId());
                //startActivity(intent);
               // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            }
        });


        return rootView;
    }

}
