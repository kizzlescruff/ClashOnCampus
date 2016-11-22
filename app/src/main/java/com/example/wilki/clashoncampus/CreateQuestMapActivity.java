package com.example.wilki.clashoncampus;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.LocationListener;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Random;

public class CreateQuestMapActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private CameraPosition cameraPosition;
    private Location location;
    private Marker player;
    private Marker monster;
    private Circle radius;
    private LinearLayout settings_menu;
    private ImageButton settings;
    private ImageButton close;
    private ImageButton add_marker;
    private ImageButton finish;
    private Button main_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quest_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        settings_menu = (LinearLayout) findViewById(R.id.settings_menu);
        settings_menu.setVisibility(View.INVISIBLE);

        settings = (ImageButton) findViewById(R.id.settings_button);
        settings.setVisibility(View.VISIBLE);
        settings.setEnabled(true);

        add_marker = (ImageButton) findViewById(R.id.add_marker_button);
        add_marker.setEnabled(true);

        finish = (ImageButton) findViewById(R.id.finish_button);
        finish.setEnabled(true);

        close = (ImageButton) findViewById(R.id.close_button);
        close.setVisibility(View.INVISIBLE);
        close.setEnabled(false);

        main_menu = (Button) findViewById(R.id.main_menu);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings_menu.setVisibility(View.VISIBLE);
                settings.setVisibility(View.INVISIBLE);
                settings.setEnabled(false);

                close.setVisibility(View.VISIBLE);
                close.setEnabled(true);

                finish.setEnabled(false);
                add_marker.setEnabled(false);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings_menu.setVisibility(View.INVISIBLE);
                settings.setVisibility(View.VISIBLE);
                settings.setEnabled(true);

                close.setVisibility(View.INVISIBLE);
                close.setEnabled(false);

                add_marker.setEnabled(true);
                finish.setEnabled(true);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity(view);
            }
        });

        add_marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // HAYDEN_WILL_ADD_SOMETHING_HERE
            }
        });

        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Are you sure you want to return to the Main Menu?")
                        .setMessage("All progress in the quest will be lost.")
                        .setPositiveButton("Return to Main Menu", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                returnMainMenu(view);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));
        mMap.setMyLocationEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null)
        {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 0));

            cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(18)                   // Sets the zoom
                    .bearing(location.getBearing())                // Sets the orientation of the camera to east
                    .tilt(70)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            player = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(location.getLatitude(),  location.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("link", 150, 150)))
            );

            mMap.setOnMarkerClickListener(this);

            radius = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(location.getLatitude(), location.getLongitude()))
                    .radius(25)
                    .strokeWidth(5)
                    .strokeColor(Color.WHITE));

            ValueAnimator vAnimator = new ValueAnimator();
            vAnimator.setRepeatCount(ValueAnimator.INFINITE);
            vAnimator.setRepeatMode(ValueAnimator.RESTART);  /* PULSE */
            vAnimator.setIntValues(0, 100);
            vAnimator.setDuration(2000);
            vAnimator.setEvaluator(new IntEvaluator());
            vAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    // Log.e("", "" + animatedFraction);
                    radius.setRadius(animatedFraction * 25);
                }
            });
            vAnimator.start();
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMapClickListener(this);
        //monsterAppear(location);

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;

        /*if (player != null) {
            player.remove();
        }*/

        if (radius != null) {
            radius.remove();
        }

        /*player = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(),  location.getLongitude()))
                .rotation(location.getBearing())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        );*/

        animateMarker();

        radius = mMap.addCircle(new CircleOptions()
                .center(new LatLng(location.getLatitude(), location.getLongitude()))
                .radius(25)
                .strokeWidth(5)
                .strokeColor(Color.WHITE));

        cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                .zoom(mMap.getCameraPosition().zoom)                   // Sets the zoom
                .bearing(mMap.getCameraPosition().bearing)                // Sets the orientation of the camera to east
                .tilt(mMap.getCameraPosition().tilt)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //Toast.makeText(this, "Location changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        //Toast.makeText(this, "status changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String s) {
        //Toast.makeText(this, "provider enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        //Toast.makeText(this, "provider disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLongClick(LatLng point) {
        monsterAppear();
    }

    public void monsterAppear () {
        if (monster != null) {
            monster.remove();
        }

        double lat = randomDouble();
        double lon = randomDouble();

        final LatLng target = new LatLng(location.getLatitude() + lat, location.getLongitude() + lon);

        final long duration = 400;
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();

        Point startPoint = proj.toScreenLocation(target);
        startPoint.y = 0;
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);

        final Interpolator interpolator = new LinearInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * target.longitude + (1 - t) * startLatLng.longitude;
                double lat = t * target.latitude + (1 - t) * startLatLng.latitude;
                monster.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    // Post again 10ms later.
                    handler.postDelayed(this, 10);
                } else {
                    // animation ended
                }
            }
        });

        monster = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude() + lat,  location.getLongitude() + lon))
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("ganon", 400, 300)))
        );
    }

    public double randomDouble () {
        Random r = new Random();
        double rangeMin = 0.00005;
        double rangeMax = 0.00015;
        double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        ArrayList<Integer> num = new ArrayList();
        num.add(1);
        num.add(-1);
        return randomValue = randomValue * num.get(new Random().nextInt(num.size()));
    }

    public void animateMarker() {
        if (player != null) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
            valueAnimator.setDuration(1000); // duration 1 second
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override public void onAnimationUpdate(ValueAnimator animation) {
                    try {
                        LatLng startPosition = player.getPosition();
                        LatLng endPosition = new LatLng(location.getLatitude(), location.getLongitude());

                        float startRotation = player.getRotation();
                        LatLngInterpolator latLngInterpolator = new LatLngInterpolator.LinearFixed();

                        float v = animation.getAnimatedFraction();
                        LatLng newPosition = latLngInterpolator.interpolate(v, startPosition, endPosition);
                        player.setPosition(newPosition);
                        //player.setRotation(computeRotation(v, startRotation, location.getBearing()));
                    } catch (Exception ex) {
                        // I don't care atm..
                    }
                }
            });

            valueAnimator.start();
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                .zoom(mMap.getCameraPosition().zoom)                   // Sets the zoom
                .bearing(mMap.getCameraPosition().bearing + 45)                // Sets the orientation of the camera to east
                .tilt(mMap.getCameraPosition().tilt)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(monster)) {
            if (getDistance() > 50) {
                Toast.makeText(this, "Monster too far away!", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, "Close enough!", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(this)
                        .setTitle("Ganon - Level 12")
                        .setMessage("Are you prepared to find the Monster?")
                        .setPositiveButton("Scan for Monster", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton("Return to map", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        } else {

        }
        return true;
    }

    private interface LatLngInterpolator {
        LatLng interpolate(float fraction, LatLng a, LatLng b);

        class LinearFixed implements LatLngInterpolator {
            @Override
            public LatLng interpolate(float fraction, LatLng a, LatLng b) {
                double lat = (b.latitude - a.latitude) * fraction + a.latitude;
                double lngDelta = b.longitude - a.longitude;
                // Take the shortest path across the 180th meridian.
                if (Math.abs(lngDelta) > 180) {
                    lngDelta -= Math.signum(lngDelta) * 360;
                }
                double lng = lngDelta * fraction + a.longitude;
                return new LatLng(lat, lng);
            }
        }
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public float getDistance () {
        Location plyr = new Location("Player");
        plyr.setLatitude(player.getPosition().latitude);
        plyr.setLongitude(player.getPosition().longitude);
        Location mstr = new Location("Player");
        mstr.setLatitude(monster.getPosition().latitude);
        mstr.setLongitude(monster.getPosition().longitude);
        return plyr.distanceTo(mstr);
    }

    public void returnMainMenu(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void finishActivity(View view){
        startActivity(new Intent(this, CreateQuestFinishActivity.class));
    }
}

