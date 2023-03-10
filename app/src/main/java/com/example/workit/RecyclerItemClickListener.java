package com.example.workit;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private ClickListener clickListener;
    private GestureDetector gestureDetector;

    public interface ClickListener {
        void onClick(View view, int i);

        void onLongClick(View view, int i);
    }

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener2) {
        this.clickListener = clickListener2;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            public void onLongPress(MotionEvent e) {
                ClickListener clickListener;
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && (clickListener = clickListener2) != null) {
                    clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child == null || this.clickListener == null || !this.gestureDetector.onTouchEvent(e)) {
            return false;
        }
        this.clickListener.onClick(child, rv.getChildAdapterPosition(child));
        return false;
    }

    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
