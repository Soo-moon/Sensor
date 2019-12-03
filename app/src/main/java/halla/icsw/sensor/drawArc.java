package halla.icsw.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class drawArc extends AppCompatActivity implements SensorEventListener{
    SensorManager m ;
    Sensor s;

    public static int angle=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m = (SensorManager) getSystemService(SENSOR_SERVICE);
        s=m.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(s== null){
            Toast.makeText(this,"방향센서 없음 -> 프로그램 종료",Toast.LENGTH_LONG).show();
            finish();
        }
        m.registerListener(this,s,SensorManager.SENSOR_DELAY_UI);



    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        if(x*x +y*y + z*z >250){
            angle+=10;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
class myview extends View{

    public myview(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas){
        int x = getWidth()/2;
        int y = getHeight()/2;
        Paint paint = new Paint();

    }
}
