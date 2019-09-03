package kr.yeon.test.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

/**
 * Created by zn
 */

public class RoundConstraintLayout extends ConstraintLayout {
    private float radius = 0f;
    private float shadowY = 0f;
    private float shadowBlur = 0f;
    private int shadowColor = Color.TRANSPARENT;

    public RoundConstraintLayout(Context context) {
        super(context);
        this.init((AttributeSet)null, 0);
    }

    public RoundConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(attrs, 0);
    }

    public RoundConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(attrs, defStyleAttr);
    }

    protected void init(AttributeSet attrs, int defStyleAttr) {
        if(attrs != null) {
            TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.RoundConstraintLayout);

            radius = a.getDimensionPixelSize(R.styleable.RoundConstraintLayout_rcl_radius, 0);
            shadowBlur = a.getDimensionPixelSize(R.styleable.RoundConstraintLayout_rcl_shadow_blur, 0);
            shadowY = a.getDimensionPixelSize(R.styleable.RoundConstraintLayout_rcl_shadow_y, 0);
            shadowColor = a.getColor(R.styleable.RoundConstraintLayout_rcl_shadow_color, Color.TRANSPARENT);
        }
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setShadowLayer(shadowBlur, 0, shadowY, shadowColor);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

//        super.dispatchDraw(canvas);
//
        paint.setColor(Color.TRANSPARENT);
        canvas.drawRoundRect(innerRect, radius, radius, paint);


        int count = canvas.save();

        if(path != null) {
            canvas.clipPath(path);
        }
        super.dispatchDraw(canvas);

        canvas.restoreToCount(count);

        paint.setColor(Color.RED);
        canvas.drawLine(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

    }

    Path path;
    RectF rect;
    RectF innerRect;

    Paint paint;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        rect = new RectF(0, 0, w, h);
        innerRect = new RectF(shadowBlur, 0 + shadowBlur - shadowY, w - shadowBlur, h - shadowBlur - shadowY);


        path = new Path();
        path.addRoundRect(innerRect, radius, radius, Path.Direction.CW);
    }
}
