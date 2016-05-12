package com.jf.djplayer.classifysongshow;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.jf.djplayer.R;
import com.jf.djplayer.base.basefragment.BaseExpandFragment;
import com.jf.djplayer.database.SongInfoOpenHelper;
import com.jf.djplayer.other.SongInfo;

import java.util.List;

/**
 * Created by JF on 2016/5/1.
 */
public class ClassifySongListFragment extends BaseExpandFragment {

    private View footerView;
    private List<SongInfo> songInfoList;
    private String columnName;
    private String columnValues;

    public void setDataResource(String columnName, String columnValues){
        this.columnName = columnName;
        this.columnValues = columnValues;
    }

    @Override
    protected void initBeforeReturnView() {

    }

    @Override
    protected View getExpandableLoadingView() {
        return null;
    }

    @Override
    protected View getExpandListEmptyView() {
        return null;
    }

    @Override
    protected List getData() {
        songInfoList = new SongInfoOpenHelper(getActivity()).getClassifySongInfo(columnName, columnValues);
        return songInfoList;
    }

    @Override
    protected void asyncReadDataFinished(List dataList) {

    }

    @Override
    protected BaseExpandableListAdapter getExpandableAdapter() {
        return null;
    }

    @Override
    protected boolean doOnGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }

    @Override
    protected boolean doExpandableItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    protected View getExpandableHeaderView() {
        return null;
    }

    @Override
    protected View getExpandableFooterView() {
        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.list_footer_view, null);
        if(songInfoList == null){
            return null;
        }
        ((TextView)footerView.getParent()).setText(songInfoList.size()+"首歌");
        return footerView;
    }
}