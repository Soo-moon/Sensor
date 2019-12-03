package halla.icsw.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;


public class player extends AppCompatActivity implements SensorEventListener {
    MediaPlayer player;
    SensorManager m ;
    Sensor s;
    //aaaaaa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        player = MediaPlayer.create(this,R.raw.old_pop);
        player.setLooping(false);

        m = (SensorManager) getSystemService(SENSOR_SERVICE);
        s=m.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if(s== null){
            Toast.makeText(this,"방향센서 없음 -> 프로그램 종료",Toast.LENGTH_LONG).show();
            finish();
        }
        m.registerListener(this,s,SensorManager.SENSOR_DELAY_UI);

       // player.start();

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float roll = sensorEvent.values[2];
        if(roll == 1.0);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
