package org.faskan.noname;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContactsScrollChangeListener implements RecyclerView.OnScrollChangeListener {

    private final OnLoadMoreListener onLoadMoreListener;

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        System.out.println(" ***** scrollY : " + scrollY + " *** oldScrollY : " + oldScrollY);
        if (scrollY == 0 && oldScrollY == 1) {
            onLoadMoreListener.loadMore();
        }
    }
}
