package kody.com.trudigitalexample.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kody.com.trudigitalexample.R;

/**
 * Created by kody on 2/1/16.
 * can be used by kody and people in [kody}]
 */
public class LayeredLinearLayout extends LinearLayout {

    private int rows;
    private int cols;

    private ArrayList<View> children;

    public LayeredLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        children = new ArrayList<>();
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LayeredLinearLayout,
                0, 0);

        try {
            rows = a.getInteger(R.styleable.LayeredLinearLayout_rows, 0);
            cols = a.getInteger(R.styleable.LayeredLinearLayout_cols, 0);
        } finally {
            a.recycle();
        }

        if (getOrientation() == LinearLayout.HORIZONTAL && cols != 0) { // VERTICAL
            if (cols < 0) {
                throw new ExceptionInInitializerError("Value rows can not be less than 0");
            } else {
                for (int i = 1; i <= rows; i++) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT, 1.0f);
                    LinearLayout linearLayout = new LinearLayout(getContext());
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    addView(linearLayout);
                }
            }
        } else if (getOrientation() == LinearLayout.VERTICAL && rows != 0) { // VERTICAL
            if (rows < 0) {
                throw new ExceptionInInitializerError("Value rows can not be less than 0");
            } else {
                for (int i = 1; i <= rows; i++) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT, 1.0f);
                    LinearLayout linearLayout = new LinearLayout(getContext());
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setGravity(Gravity.CENTER_VERTICAL);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    addView(linearLayout);
                    System.out.println("Added View");
                }
            }
        }
    }

    public void addChildView(View view) {
        if (getOrientation() == LinearLayout.VERTICAL) {
            if (rows == 0) {
                addView(view);
            } else {
                System.out.println("Child : " + getChildAt(children.size() % rows));
                System.out.println("View : " + view);
                ((LinearLayout) getChildAt(children.size() % rows)).addView(view);
            }
        } else if (getOrientation() == LinearLayout.HORIZONTAL) {
            if (cols == 0) {
                addView(view);
            } else {
                ((LinearLayout) getChildAt(children.size() % rows)).addView(view);
            }
        }
        children.add(view);
    }

    public View getChild(int child) {
        return children.get(child);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMyChildCount() {
        return children.size();
    }
}
