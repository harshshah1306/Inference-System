package com.example.harsh.myapplication1;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends FragmentActivity implements LocationListener,AdapterView.OnItemClickListener {
    GoogleMap mGoogleMap;
    String type2;
    int count=0;
    PolylineOptions polyLineOptions = null;
    String type;
    int k = 0,k1=1;
    HashMap<String, String> hmPlace;
    private static final String LOG_TAG = "ExampleApp";
    double X = 100.00, y = 1.00, z = 1.00;
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";


    private static final String API_KEY = "AIzaSyCXA36jMjYmcsh3Z8lW_0RKeoHMafeB9xQ";

    private int userIcon;
    AutoCompleteTextView autocomplete;
    double mLatitude = 0;
    double mLongitude = 0;

    private Circle mCircle;
    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        userIcon = R.drawable.red_point;
        //Array of Place Types

        autocomplete = (AutoCompleteTextView)
                findViewById(R.id.autocomplete1);

        autocomplete.setAdapter(new GooglePlacesAutocompleteAdapter(this, R.layout.list_item));//sets an list in the list item
        autocomplete.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        Button btnFind;

        btnFind = (Button) findViewById(R.id.btn_find);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
        if (status != ConnectionResult.SUCCESS) {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        } else {
            SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            mGoogleMap = fragment.getMap();

            mGoogleMap.setMyLocationEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);

            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                onLocationChanged(location);
            }

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, this);

            btnFind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    k=0;
                    y = 1;
                    String type1 = autocomplete.getText().toString();
                    type1.toCharArray();
                    for (int i = 0; i < type1.length(); i++) {
                        if (type1.charAt(i) == ' ') {
                            k++;
                        }

                    }
                    if (k == 0) {
                        type2 = type1;
                        radialsearch(X);
                        k1=2;
                    } else {
                         type = type1.replace(' ', '+');
                        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?");
                        sb.append("&query=" + type);
                        sb.append("&sensor=true");
                        sb.append("&key=AIzaSyCXA36jMjYmcsh3Z8lW_0RKeoHMafeB9xQ");
                        PlacesTask placesTask = new PlacesTask();
                        placesTask.execute(sb.toString());
                        System.out.println(sb);
                    }

                }
            });
        }

    }
        public void radialsearch ( double Y){
            Y=Y*y;
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
            sb.append("location=" + mLatitude + "," + mLongitude);
            sb.append("&radius=" + Y);
            sb.append("&types=" + type2);
            sb.append("&sensor=true");
            sb.append("&key=AIzaSyCXA36jMjYmcsh3Z8lW_0RKeoHMafeB9xQ");
            PlacesTask placesTask = new PlacesTask();
            placesTask.execute(sb.toString());
            System.out.println(sb);
y++;
        }

    private String getMapsApiDirectionsUrl(double lat,double lng, String title) {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        //mLatitude = location.getLatitude();
        //mLongitude = location.getLongitude();

        StringBuilder sb1 = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        sb1.append("&origin=" + location.getLatitude() + "," + location.getLongitude());
        sb1.append("&destination="+lat+","+lng);
        sb1.append("&key=AIzaSyDK3yMRIZzOnvdZskqxP-NeSzaTfxXG0uw");
        String url=sb1.toString();
        System.out.print(sb1);
        return url;
    }
    private class ReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                HttpConnection http = new HttpConnection();
                data = http.readUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("In Post");
            new ParserTask1().execute(result);
        }
    }

    private class ParserTask1 extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {



            System.out.println("in Do");
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                System.out.println("In try" + jsonData[0]);
                jObject = new JSONObject(jsonData[0]);
                System.out.println("AFter error");
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;


            // traversing through routes
            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(6);
                if(count==1)
                polyLineOptions.color(Color.BLUE);
                if(count==2)
                    polyLineOptions.color(Color.RED);
                if(count==3)
                    polyLineOptions.color(Color.GREEN);
                if(count==4)
                    polyLineOptions.color(Color.BLACK);
                if(count==5)
                    polyLineOptions.color(Color.MAGENTA);
            }

            mGoogleMap.addPolyline(polyLineOptions);
        }
    }

    private String downloadUrl(String strUrl) throws IOException{
        String data="";
        InputStream iStream=null;
        HttpsURLConnection urlConnection=null;
        try
        {
            URL url=new URL(strUrl);

            urlConnection=(HttpsURLConnection)url.openConnection();

            urlConnection.connect();

            iStream=urlConnection.getInputStream();

            BufferedReader br=new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb=new StringBuffer();
            String line="";

            while ((line=br.readLine())!=null)
            {
                sb.append(line);
            }

            data=sb.toString();

            br.close();

        }
        catch (Exception e)
        {
            //Log.d("Exception while downloading url",e.toString());
        }
        finally
        {
            iStream.close();
            urlConnection.disconnect();
        }

        return data;
    }

    private class PlacesTask extends AsyncTask<String, Integer, String> {
        String data = null;

        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

    @Override
        protected void onPostExecute(String result) {
            ParserTask parserTask = new ParserTask();

            parserTask.execute(result);
        }
    }
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

        JSONObject jObject;

        @Override
        protected List<HashMap<String,String>> doInBackground(String... jsonData){

            List<HashMap<String,String>> places=null;
            PlaceJSONParser placeJSONParser=new PlaceJSONParser();

            try
            {
                jObject=new JSONObject(jsonData[0]);

                places=placeJSONParser.parse(jObject);
            }
            catch (Exception e)
            {
                Log.d("Exception",e.toString());
            }

            return places;
        }
        @Override
        protected void onPostExecute(List<HashMap<String, String>> list){
            mGoogleMap.clear();
            count =0;
            if(k==0) {
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                Criteria criteria = new Criteria();

                String provider = locationManager.getBestProvider(criteria, true);
                Location location = locationManager.getLastKnownLocation(provider);
                mLatitude = location.getLatitude();
                mLongitude = location.getLongitude();

                LatLng latLng1 = new LatLng(mLatitude, mLongitude);
                int strokeColor = 0xffff0000;
                double radiusInMeters = X * y;

                radiusInMeters = (X * (y - 1));
                strokeColor = Color.BLACK;
                int shadeColor = Color.TRANSPARENT;
                CircleOptions circleOptions = new CircleOptions().center(latLng1).radius(radiusInMeters).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(2);
                mCircle = mGoogleMap.addCircle(circleOptions);


                radiusInMeters = (X * (y));
                strokeColor = 0xff00ff00;
                shadeColor = 0x44ff0000;
                CircleOptions circleOptions1 = new CircleOptions().center(latLng1).radius(radiusInMeters).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(2);
                mCircle = mGoogleMap.addCircle(circleOptions1);
                final Circle circle = mGoogleMap.addCircle(new CircleOptions().center(latLng1)
                        .strokeColor(Color.RED).radius(X * y));



                ValueAnimator vAnimator = new ValueAnimator();
                vAnimator.setRepeatCount(ValueAnimator.INFINITE);
                vAnimator.setRepeatMode(ValueAnimator.RESTART);
                vAnimator.setIntValues(0, 100);
                vAnimator.setDuration(5000);
                vAnimator.setEvaluator(new IntEvaluator());
                vAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float animatedFraction = valueAnimator.getAnimatedFraction();
                        // Log.e("", "" + animatedFraction);
                        circle.setRadius(animatedFraction * (X * y));
                    }
                });
                vAnimator.start();

            }

            if(list.size()<5&&k1==2)
radialsearch(X);
else {
                for (int i = 0; i < list.size(); i++) {

                    final MarkerOptions markerOptions = new MarkerOptions();

                    final HashMap<String, String> hmPlace = list.get(i);

                    double lat = Double.parseDouble(hmPlace.get("lat"));

                    double lng = Double.parseDouble(hmPlace.get("lng"));

                    String name = hmPlace.get("place_name");

                    String vicinity = hmPlace.get("vicinity");

                    LatLng latLng = new LatLng(lat, lng);

                    markerOptions.position(latLng);

                    markerOptions.title(name + ":" + vicinity);

                    mGoogleMap.addMarker(markerOptions).showInfoWindow();

                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


                }



                mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                    @Override
                    public boolean onMarkerClick(Marker arg0) {

                        //final HashMap<String, String> markerdetails=arg0.toString();
                        //arg0.getPosition();
                        /*LatLng position=arg0.getPosition();

                        System.out.println(hmPlace+" Arg="+arg0.getPosition());
                        double lat = arg0.getPosition().latitude;
                        double lng = arg0.getPosition().longitude;
                        String url = getMapsApiDirectionsUrl(lat, lng, arg0.getTitle());
                        ReadTask downloadTask = new ReadTask();
                        downloadTask.execute(url);
                        return true;*/


                        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                        Criteria criteria = new Criteria();

                        String provider = locationManager.getBestProvider(criteria, true);
                        Location location = locationManager.getLastKnownLocation(provider);
                        //mLatitude = location.getLatitude();
                        //mLongitude = location.getLongitude();

                        StringBuilder sb1 = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
                        sb1.append("&origin=" + location.getLatitude() + "," + location.getLongitude());
                        sb1.append("&destination=" + arg0.getPosition().latitude + "," + arg0.getPosition().longitude);
                        sb1.append("&key=AIzaSyDK3yMRIZzOnvdZskqxP-NeSzaTfxXG0uw");
                        String url = sb1.toString();


                        ReadTask downloadTask = new ReadTask();
                        downloadTask.execute(url);
                        count++;
                        arg0.setTitle(arg0.getTitle());
                        arg0.showInfoWindow();
                        System.out.println("Answer=" + arg0.getTitle());
                        //mGoogleMap.addMarker("arg0.getTitle());
                        return true;
                    }

                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onLocationChanged(Location location){
        mLatitude=location.getLatitude();
        mLongitude=location.getLongitude();

        LatLng latLng=new LatLng(mLatitude,mLongitude);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onProviderDisabled(String provider){
    }
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onProviderEnabled(String provider){

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){

    }
    public static ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            sb.append("&components=country:IN");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());

            System.out.println("URL: "+url);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }

    class GooglePlacesAutocompleteAdapter extends ArrayAdapter<String> implements Filterable {
        private ArrayList<String> resultList;

        public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        resultList = autocomplete(constraint.toString());

                        // Assign the data to the FilterResults
                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }

    }



}