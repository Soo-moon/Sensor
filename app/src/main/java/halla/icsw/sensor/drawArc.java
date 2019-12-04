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
    public static int angle=10;
    SensorManager m ;
    Sensor s;
    myView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v= new myView(this);
        setContentView(v);

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

        if(x*x +y*y + z*z >100){
            angle+=10;
            v.invalidate();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
class myView extends View{

    public myView(Context context) {
        super(context);
        setBackgroundColor(Color.BLUE);
    }
    protected void onDraw(Canvas canvas){
        int x =getWidth()/2;
        int y = getHeight()/2;
        int angle =drawArc.angle;

        Paint paint =new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawArc(new RectF(0,y-x,x*2,y+x),270,angle,true,paint);
        if(angle >360){
            Toast.makeText(getContext(),"축하합니다" , Toast.LENGTH_LONG).show();
           drawArc.angle=0;
        }
    }


}
