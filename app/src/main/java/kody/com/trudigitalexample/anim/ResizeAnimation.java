package kody.com.trudigitalexample.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by kody on 2/3/16.
 * can be used by kody and people in [kody}]
 */
public class ResizeAnimation extends Animation {
    final int endHeight;
    View view;
    int startHeight, slopeHeight;

    public ResizeAnimation(View view, int endHeight, int startHeight) {
        this.view = view;
        this.endHeight = endHeight;
        this.startHeight = startHeight;
        this.slopeHeight = endHeight - startHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int currentHeight = (int) (slopeHeight * interpolatedTime +startHeight);
        view.getLayoutParams().height = currentHeight;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
