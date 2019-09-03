package kr.yeon.test.constraint;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView v = (VideoView) findViewById(R.id.video);
        v.setVideoPath("http://www.ithinknext.com/mydata/board/files/F201308021823010.mp4");
        v.setMediaController(new MediaController(this));
        v.start();

        v = (VideoView) findViewById(R.id.video2);
        v.setVideoPath("http://www.ithinknext.com/mydata/board/files/F201308021823010.mp4");
        v.setMediaController(new MediaController(this));
        v.start();

        v = (VideoView) findViewById(R.id.video3);
        v.setVideoPath("http://www.ithinknext.com/mydata/board/files/F201308021823010.mp4");
        v.setMediaController(new MediaController(this));
        v.start();

        Drawable d = findViewById(R.id.vvv).getBackground();
        if(d instanceof NinePatchDrawable) {
            NinePatchDrawable nd = (NinePatchDrawable) d;
            Rect p = new Rect();
            boolean ppp = nd.getPadding(p);
            Log.d("TEST", "V:"+ppp+"/"+p.toShortString());
        }
    }
}
