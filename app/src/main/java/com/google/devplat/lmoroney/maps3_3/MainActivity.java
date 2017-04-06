/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.devplat.lmoroney.maps3_3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady = false;

    //TODO 1 : Create CameraPostion :variable :  NEWYORK , LatLng : (40 784, -73.9857), zoom : (21), bearings (0), tilt (45)
    public static final CameraPosition NEWYORK = new CameraPosition.Builder().target(new LatLng(40.784, -73.9857))
            .zoom(21)
            .bearing(0)
            //orientasi
            .tilt(45)
            //sudut pandang
            .build();
    //TODO 2 : Create CameraPostion :variable :  SEATTLE, LatLng (47.6204, -122.3491), zoom (17), bearings 	(0), tilt (45)
    public static final CameraPosition SEATTLE = new CameraPosition.Builder().target(new LatLng(47.6204, -122.3491))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();
    //TODO 3 : Create CameraPostion :variable : DUBLIN , LatLng (53.3478, -6.2597), zoom (17), bearings (90). tilt (45)
    public static final CameraPosition DUBLIN = new CameraPosition.Builder().target(new LatLng(53.3478, -6.2597))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();

    //TODO 4 : Create CameraPostion :variable : BANDUNG ,LatLng (-.9034443,107.5731168), 	zoom (17), bearings (90). tilt (45)
    public static final CameraPosition BANDUNG = new CameraPosition.Builder().target(new LatLng(-6.9034443, 107.5731168))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO 6 : Declaration of button with id : btnSeattle
        Button btnSeattle = (Button) findViewById(R.id.btnSeattle);
        //TODO 7 : Create a function on btnSeattle to change the appearance of a Map to SEATTLE
        btnSeattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady) {
                    flyTo(SEATTLE);
                    m_map.addMarker(new MarkerOptions().position(new LatLng(47.6204, -122.3491)));
                }
            }
        });
        //TODO 8 : Declaration of button with id btnDublin
        Button btnDublin = (Button) findViewById(R.id.btnDublin);
        //TODO 9 : Create function on btnSeattle to change the appearance of a Map to DUBLIN
        btnDublin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady) {
                    flyTo(DUBLIN);
                    m_map.addMarker(new MarkerOptions().position(new LatLng(53.3478, -6.2597)));
                }
            }
        });

        Button btnBandung = (Button) findViewById(R.id.btnBandung);

        btnBandung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady) {
                    //TODO 10 : Navigate the map to BANDUNG
                    flyTo(BANDUNG);
                    //TODO 11 : Create a function to add the marker and set the position to BANDUNG
                    m_map.addMarker(new MarkerOptions().position(new LatLng(-6.9034443, 107.5731168)));
                }
            }
        });

        //TODO 16 : Declare a button with id btnMap
        Button btnMap = (Button) findViewById(R.id.btnMap);
        //TODO 17 : Create a function on btnMap for implicit intent to move the application map
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOpenAddressButton();
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap map) {
        //TODO 5 : Create mapReady and directed to NEWYORK
        mapReady = true;
        m_map = map;
        m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        flyTo(NEWYORK);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
    }


    public void onClickOpenAddressButton() {
        // TODO 18 : Store an address in a String
        String addressString = "Telkom University";

        // TODO 19 : Use Uri.Builder with the appropriate scheme and query to form the Uri for the address
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")
                .query(addressString);
        Uri addressUri = builder.build();

        // TODO 20 : Create an Intent with action type, Intent.ACTION_VIEW
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // TODO 21 : Set the data of the Intent to the Uri passed into this method
        intent.setData(addressUri);

        // TODO 22 : Verify that this Intent can be launched and then call startActivity
        startActivity(intent);


    }
}
