package kody.com.trudigitalexample.views;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kody.com.trudigitalexample.R;
import kody.com.trudigitalexample.model.App;

/**
 * Created by kody on 1/31/16.
 * can be used by kody and people in [kody}]
 */
public class AppAdapter {

//    LinearLayout topRow, bottomRow;
    private HorizontalScrollView horizontalScrollView;
    ArrayList<LinearLayout> rows;
    private ArrayList<AppItem> appItems;
    private int selectedApp, appItemWidth, screenWidth;

    public AppAdapter (LinearLayout basicLayout, HorizontalScrollView horizontalScrollView,
                       Context context, ArrayList<App> appsArrayList) {
//        topRow = (LinearLayout) basicLayout.findViewById(R.id.top_row);
//        bottomRow = (LinearLayout) basicLayout.findViewById(R.id.bottom_row);
        this.horizontalScrollView = horizontalScrollView;
        this.appItems = new ArrayList<>();
        rows = new ArrayList<>();
        int childcount = basicLayout.getChildCount();
        for (int i=0; i < childcount; i++){
            if (basicLayout.getChildAt(i) instanceof LinearLayout) {
                rows.add((LinearLayout) basicLayout.getChildAt(i));
            }
        }

        appItemWidth = (int) (
                context.getResources().getDimension(R.dimen.app_icon_size) +
                context.getResources().getDimension(R.dimen.app_item_padding) * 2 +
                context.getResources().getDimension(R.dimen.app_item_margin) * 2
        );

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;

        fill(appsArrayList, context);
        selectedApp = 0;
        refreshAppSelection();
    }

    public void fill(ArrayList<App> apps, Context context) {
        for (App app: apps) {
            AppItem appItem = new AppItem(app, context);
            rows.get(appItems.size() % rows.size()).addView(appItem.getView());
            this.appItems.add(appItem);
//            if ((appItems.size() % 2) == 1) {
//                topRow.addView(appItem.getView());
//            } else {
//                bottomRow.addView(appItem.getView());
//            }
        }
    }

    public void selectRight() {
        if (selectedApp + rows.size() < appItems.size()) {
            selectedApp += rows.size();
            refreshAppSelection();
        }
    }

    public void selectLeft() {
        if (selectedApp - rows.size() >= 0) {
            selectedApp -= rows.size();
            refreshAppSelection();
        }
    }

    public void selectUp() {
        if (selectedApp - 1 >= 0) {
            selectedApp -= 1;
            refreshAppSelection();
        }
    }

    public void selectDown() {
        if (selectedApp + 1 < appItems.size()) {
            selectedApp += 1;
            refreshAppSelection();
        }
    }

    public void refreshAppSelection() {
        int i = 0;
        for (AppItem appItem: appItems) {
            if (i == selectedApp) {
                appItem.select();
            } else {
                appItem.deselect();
            }
            i++;
        }
        horizontalScrollView.smoothScrollTo((selectedApp / rows.size()) * (appItemWidth) - screenWidth/2 + appItemWidth/2, 0);
    }

    public void selectApp(View view) {
        int i = 0;
        System.out.println("Item : " + view);
        for (AppItem app : appItems) {
            if (app.getView().equals(view)) {
                selectedApp = i;
            }
            i++;
        }
        refreshAppSelection();
    }

    public AppItem getSelectedAppItem() {
        return appItems.get(selectedApp);
    }
}
