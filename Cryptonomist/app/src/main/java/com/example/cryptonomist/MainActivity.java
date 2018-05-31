package com.example.cryptonomist;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

       // MobileAds.initialize(this,
         //       "ca-app-pub-3940256099942544~3347511713");

        //ca-app-pub-6889338095095737/6494964977

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Declaration Element

        Button button = (Button) findViewById(R.id.button2);

        //Settup Proggram

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent myIntent = new Intent(MainActivity.this, MainFragment.class);
                //myIntent.putExtra("key", value); //Optional parameters
                //MainActivity.this.startActivity(myIntent);

                GetDataFirstTime getData = new GetDataFirstTime();
                getData.execute("");

            }
        });
    }

    //To get data News First time
    class GetDataFirstTime extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... strings) {

            //Emulator 10.0.2.2
            //Gili motion 10.0.3.2
            //Hp tergantung HP
            String Respon = "";

            try {
                //https://cryptopanic.com/api/posts/?auth_token=9d6828a3aba9cf73e4510395aee2be705d142a30&filter=trending
                //URL url = new URL("http://10.0.2.2/testingAndroid/loginUser.php");
                URL url = new URL("https://cryptopanic.com/api/posts/?auth_token=9d6828a3aba9cf73e4510395aee2be705d142a30&public=true&page=1");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");



                //String parameter = "username="+params[0]+"&password="+params[1];


                int responseCode = conn.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK)
                {
                    String line = "" ;
                    BufferedReader br = new BufferedReader((new InputStreamReader(conn.getInputStream())));

                    while((line = br.readLine()) != null)
                    {
                        Respon += line ;
                    }

                    br.close();
                }

                else
                {
                    Respon = "gagal";
                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return Respon;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                //Get Json object fomr the string result
                JSONObject jsonObj = new JSONObject(s);

                // Getting JSON Array node
                JSONArray newsResult = jsonObj.getJSONArray("results");

                ArrayList<News> DataSend = new ArrayList<News>();


                for(int i=0;i<newsResult.length();i++)
                {
                    JSONObject dataNewsGet = (JSONObject) newsResult.get(i);

                    String title = dataNewsGet.getString("title");
                    String body = "";
                    String Coin = "";

                    JSONArray coinResult =  dataNewsGet.optJSONArray("currencies");


                    if(coinResult!=null) {

                        for (int j = 0; j < coinResult.length(); j++) {
                            JSONObject coinResultGet = (JSONObject) coinResult.get(j);
                            Coin = Coin +  coinResultGet.getString("code") + " ";
                        }

                    }

                    String source = dataNewsGet.getString("url");
                    String time = dataNewsGet.getString("published_at");
                    String domain = dataNewsGet.getString("domain");

                    News temp = new News(title,body,Coin,source,1,1,time,domain);

                    DataSend.add(temp);

                }



                Intent myIntent = new Intent(MainActivity.this, MainFragment.class);
                myIntent.putExtra("DataSend", DataSend); //Optional parameters
                MainActivity.this.startActivity(myIntent);
                finish();


            }

            catch (final JSONException f){
                Log.e(TAG, "Json parsing error: " + f.getMessage());



            }



            /*if(tempHelp.equalsIgnoreCase("Selamat"))
            {
                Intent intent = new Intent(MainActivity.this,home.class);
                startActivity(intent);
                finish();
            }*/


        }
    }
}
