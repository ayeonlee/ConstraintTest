package kr.yeon.test.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by zn
 */

public class RoundConstraintLayout3 extends RoundConstraintLayout2 {

//    private float shadowY = 0f;
//    private float shadowBlur = 0f;
//    private int shadowColor = Color.TRANSPARENT;
    private Drawable shadowDrawable = null;

    public RoundConstraintLayout3(Context context) {
        super(context);
        this.init((AttributeSet)null, 0);
    }

    public RoundConstraintLayout3(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(attrs, 0);
    }

    public RoundConstraintLayout3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(attrs, defStyleAttr);
    }

    protected void init(AttributeSet attrs, int defStyleAttr) {
        super.init(attrs, defStyleAttr);
        if(attrs != null) {
            TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.RoundConstraintLayout);

//            radius = a.getDimensionPixelSize(R.styleable.RoundConstraintLayout_rcl_radius, 0);
//            shadowBlur = a.getDimensionPixelSize(R.styleable.RoundConstraintLayout_rcl_shadow_blur, 0);
//            shadowY = a.getDimensionPixelSize(R.styleable.RoundConstraintLayout_rcl_shadow_y, 0);
//            shadowColor = a.getColor(R.styleable.RoundConstraintLayout_rcl_shadow_color, Color.TRANSPARENT);
            shadowDrawable = a.getDrawable(R.styleable.RoundConstraintLayout_rcl_shadow_drawable);
        }
//        setLayerType(LAYER_TYPE_SOFTWARE, null);

//        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.RED);
//        paint.setShadowLayer(shadowBlur, 0, shadowY, shadowColor);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

//        super.dispatchDraw(canvas);
//
        Log.d("TEST", "canvas:"+canvas.getWidth());
        if(shadowDrawable != null) {

            Rect t = new Rect();
            shadowDrawable.getPadding(t);
            Log.d("TEST", "drawable1:"+t.toShortString());


            shadowDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            t = new Rect();
            shadowDrawable.getPadding(t);
            Log.d("TEST", "drawable2:"+t.toShortString());
            shadowDrawable.draw(canvas);
        }
//        paint.setColor(Color.TRANSPARENT);
        Log.d("TEST", "canvas:"+canvas.getWidth());

//        if(path != null) {
//            canvas.clipPath(path);
//        }

        int count = canvas.save();

        Matrix matrix = new Matrix();
//        matrix.mapRect(innerRect);
        matrix.setRectToRect(rect, innerRect, Matrix.ScaleToFit.START);
        canvas.concat(matrix);

//        canvas.scale((innerRect.right - innerRect.left) / (rect.right - rect.left),
//                (innerRect.bottom - innerRect.top) / (rect.bottom - rect.top));
        super.dispatchDraw(canvas);

        canvas.restoreToCount(count);

        paint.setColor(Color.RED);
        canvas.drawLine(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
        paint.setColor(Color.GREEN);
        canvas.drawLine(20, 0, 20, 376, paint);

    }

//    Path path;
//    RectF rect;
    RectF innerRect;

//    Paint paint;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


//        rect = new RectF(0, 0, w, h);
        if(shadowDrawable != null && shadowDrawable instanceof NinePatchDrawable) {
            NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) shadowDrawable;
            Rect padding = new Rect();
            ninePatchDrawable.getPadding(padding);
            innerRect = new RectF(padding.left, padding.top, w - padding.right, h - padding.bottom);
        }
        else
            innerRect = rect;

//        innerRect = rect;

        Log.d("TEST", "size:"+rect.toShortString()+"===="+innerRect.toShortString());
//        path = new Path();
//        path.addRoundRect(innerRect, radius, radius, Path.Direction.CW);
    }
}
