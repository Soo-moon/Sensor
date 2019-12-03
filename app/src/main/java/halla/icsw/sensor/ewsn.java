package halla.icsw.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ewsn extends AppCompatActivity implements SensorEventListener {

    SensorManager m ; Sensor s; MYView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v =new MYView(this);
        setContentView(v);
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
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION){
            v.angle =sensorEvent.values[0];
            //float pitch = sensorEvent.values[1];
            //float roll = sensorEvent.values[2];
            v.invalidate();

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

class MYView extends View{
    float angle =0;
    public MYView(Context context) {
        super(context);
    }
    protected void onDraw(Canvas canvas){
        int x = getWidth()/2;
        int y = getHeight()/2;
        Paint paint = new Paint();

        Bitmap back = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        back =Bitmap.createScaledBitmap(back,x*2,x*2,false);
        canvas.drawBitmap(back,0,y-x,null);


        Bitmap cursor = BitmapFactory.decodeResource(getResources(),R.drawable.cursor);
        cursor = Bitmap.createScaledBitmap(cursor,x*2,x*2,false);
        //paint.setStrokeWidth(20);
        //paint.setColor(Color.GRAY);
        //canvas.drawCircle(x,y,x,paint);

        canvas.save();
        canvas.rotate(-angle,x,y);
        paint.setColor(Color.RED);
        canvas.drawBitmap(cursor,0,y-x,null);
        canvas.restore();
    }
}
