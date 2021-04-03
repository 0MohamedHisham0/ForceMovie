package com.dal4.ForceMovie.Constance;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

public class ScrollOB implements ObservableScrollViewCallbacks {

    RecyclerView recyclerView ;

    public ScrollOB(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        if (scrollState == ScrollState.UP) {
            recyclerView.setVisibility(View.GONE);

        } else if (scrollState == ScrollState.DOWN) {
            recyclerView.setVisibility(View.VISIBLE);

        }
    }
}
