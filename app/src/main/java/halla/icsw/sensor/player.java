package halla.icsw.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class player extends AppCompatActivity implements SensorEventListener {
    MediaPlayer player;
    SensorManager m ;
    Sensor s;

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
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float pitch = sensorEvent.values[1];
       if(pitch<-165 ||pitch>165)
           player.pause();
       else
           player.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
