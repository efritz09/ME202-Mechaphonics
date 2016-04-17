package xyz.efritz.bikecurious;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class RideHistoryActivity extends Activity {
    static final String HISTORY_FILENAME = "history";
//    ArrayList<BikeHistoryAdapter.Ride> ride_history;
    static ArrayList<BikeHistoryAdapter.Ride> ride_history = new ArrayList<>();
    int[] smash = {R.mipmap.dk, R.mipmap.falcon, R.mipmap.fox, R.mipmap.jiggly, R.mipmap.kirby,
            R.mipmap.link, R.mipmap.luigi, R.mipmap.mario, R.mipmap.ness, R.mipmap.pikachu,
            R.mipmap.samus, R.mipmap.yoshi};
    final int smash_max = 12;
    Random random = new Random();

    BikeHistoryAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_history);

//        ride_history = readFromInternalStorage();

        //http://stackoverflow.com/questions/21353723/how-to-save-and-load-an-arraylist-myserializedcustomobject-from-a-file-saved
        //set up a load and save to internal storage

        ListView listView = (ListView) findViewById(R.id.listView_history);
        arrayAdapter = new BikeHistoryAdapter(this, R.layout.history_user, ride_history);
        listView.setAdapter(arrayAdapter);
        //use this to get click shit
//        public void onItemClickListener(AdapterView<> parent, View v, int position, long id) {

        ImageView imageView = (ImageView) findViewById(R.id.history_user_image);
        Picasso.with(this).load(R.drawable.face).fit().transform(new CircleTransform()).into(imageView);
    }

    public void click_add_ride(View view) {
        Context context = getApplicationContext();
        EditText new_ride = (EditText) findViewById(R.id.editText_add_ride);
        BikeHistoryAdapter.Ride ride = new BikeHistoryAdapter.Ride();
        if (new_ride.getText().toString().equals("")) {
//            Toast.makeText(context, R.string.toast_add_ride_empty,Toast.LENGTH_SHORT).show();
            ride.location = getString(R.string.new_ride_empty_location);
        } else {
            ride.location = new_ride.getText().toString();
        }
        ride.date = DateFormat.getDateTimeInstance().format(new Date());
        int randomNumber = random.nextInt(smash_max);

        ride.imageID = smash[randomNumber];
        ride_history.add(0, ride);
        new_ride.getText().clear();
        arrayAdapter.notifyDataSetChanged();
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        saveToInternalStorage(ride_history);
    }

//    public void saveToInternalStorage(ArrayList<BikeHistoryAdapter.Ride> data) {
//        try {
//            FileOutputStream fos = openFileOutput(HISTORY_FILENAME, Context.MODE_PRIVATE);
//            ObjectOutputStream of = new ObjectOutputStream(fos);
//            of.writeObject(data);
//            of.flush();
//            of.close();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<BikeHistoryAdapter.Ride> readFromInternalStorage() {
//        ArrayList<BikeHistoryAdapter.Ride> data = new ArrayList<>();
//        FileInputStream fis;
//        try {
//            fis = openFileInput(HISTORY_FILENAME);
//            ObjectInputStream oi = new ObjectInputStream(fis);
//            data = (ArrayList<BikeHistoryAdapter.Ride>) oi.readObject();
//            oi.close();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (OptionalDataException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
}


