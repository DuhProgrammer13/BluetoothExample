package kody.com.trudigitalexample.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import kody.com.trudigitalexample.R;
import kody.com.trudigitalexample.anim.ResizeAnimation;
import kody.com.trudigitalexample.model.App;

/**
 * Created by kody on 2/1/16.
 * can be used by kody and people in [kody}]
 */
public class AppItem {

    private boolean selected;

    private App app;
    private View fullView;
    private LinearLayout extraInfo;
    private SquareImageView imageView;

    private Animation scaleInAnimation, scaleOutAnimation;

    public AppItem(App app, Context context) {
        this.app = app;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        fullView = inflater.inflate(R.layout.app_item, null);
        LinearLayout fullLayout = (LinearLayout) fullView.findViewById(R.id.full_view);
        extraInfo = (LinearLayout) fullLayout.findViewById(R.id.app_info);

        scaleInAnimation = new ResizeAnimation(
                extraInfo,
                (int)context.getResources().getDimension(R.dimen.app_info_height),
                0
        );
        scaleInAnimation.setInterpolator(new DecelerateInterpolator(2));
        scaleInAnimation.setDuration(1000);
        scaleInAnimation.setFillEnabled(true);
        scaleInAnimation.setFillAfter(true);
//        scaleOutAnimation = new ScaleAnimation(1f, 1f, 1f, 0f);
        scaleOutAnimation = new ResizeAnimation(
                extraInfo,
                0,
                (int)context.getResources().getDimension(R.dimen.app_info_height)
        );
        scaleOutAnimation.setFillEnabled(true);
        scaleOutAnimation.setFillAfter(true);
        scaleOutAnimation.setInterpolator(new DecelerateInterpolator(2));
        extraInfo.startAnimation(scaleOutAnimation);
        scaleOutAnimation.setDuration(1000);

        ((TextView) extraInfo.findViewById(R.id.app_title)).setText(app.getName());
        ((TextView) extraInfo.findViewById(R.id.app_description_short)).setText(app.getShortDescription());
        imageView = ((SquareImageView) fullLayout.findViewById(R.id.app_image));
        imageView.setImageDrawable(app.getIcon());

//        extraInfo.setScaleY(0);
        extraInfo.getLayoutParams().height = 0;
    }

    public void select() {
        selected = true;
        extraInfo.startAnimation(scaleInAnimation);
        extraInfo.setVisibility(View.VISIBLE);
    }

    public View getImageView() {
        return imageView;
    }

    public void deselect() {
        if (selected) {
            selected = !selected;
            extraInfo.startAnimation(scaleOutAnimation);
        }
    }

    public View getView() {
        return fullView;
    }

    public App getApp() {
        return app;
    }
}
