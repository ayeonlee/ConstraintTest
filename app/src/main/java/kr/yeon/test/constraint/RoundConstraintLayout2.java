package kr.yeon.test.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by zn
 */

public class RoundConstraintLayout2 extends ConstraintLayout {
    protected float radius = 0f;

    public RoundConstraintLayout2(Context context) {
        super(context);
        this.init((AttributeSet)null, 0);
    }

    public RoundConstraintLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(attrs, 0);
    }

    public RoundConstraintLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(attrs, defStyleAttr);
    }

    protected void init(AttributeSet attrs, int defStyleAttr) {
        if(attrs != null) {
            TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.RoundConstraintLayout);

            radius = a.getDimensionPixelSize(R.styleable.RoundConstraintLayout_rcl_radius, 0);
        }

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        int count = canvas.save();

        Log.d("TEST", "dispatchDraw2:"+canvas.getWidth());
        if(path != null) {
            canvas.clipPath(path);
        }
        super.dispatchDraw(canvas);


        canvas.drawLine(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
        canvas.restoreToCount(count);

    }

    Path path;
    RectF rect;
    Paint paint;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rect = new RectF(0, 0, w, h);

        path = new Path();
        path.addRoundRect(rect, radius, radius, Path.Direction.CW);
    }
}
