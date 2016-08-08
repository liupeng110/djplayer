package com.jf.djplayer.dialogfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jf.djplayer.R;
import com.jf.djplayer.base.MyApplication;
import com.jf.djplayer.base.baseactivity.BaseActivity;
import com.jf.djplayer.base.basefragment.SongListFragment;
import com.jf.djplayer.database.SongInfoOpenHelper;
import com.jf.djplayer.module.Song;

/**
 * Created by jf on 2016/7/13.
 * 歌曲列表界面里面所有可支持的歌曲操作都在这里，其中包括：
 * >收藏/取消收藏某一首歌
 * >删除歌曲
 * >将歌曲设置为铃声
 * >发送歌曲
 * >查看歌曲详细信息
 */
public class SongOperationDialog extends DialogFragment implements View.OnClickListener{

    private int position;//被点击的歌曲位置
    private Song song;//被点击的歌曲信息

    public static final String KEY_POSITION = "position";
    public static final String KEY_SONG = "song";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取参数
        Bundle arguments = getArguments();
        if(arguments == null){
            return;
        }
        position = arguments.getInt(KEY_POSITION);
        song = (Song)arguments.getSerializable(KEY_SONG);

        //去掉标题的同时保留了"DialogFragment"宽度正常
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.dialog_song_operation, container);
        initView(layoutView);
        return layoutView;
    }

    private void initView(View layoutView){
        //设置歌曲的名字
        ((TextView)layoutView.findViewById(R.id.tv_dialog_song_operation_songName)).setText(song.getSongName());
        //设置各个功能选项
        TextView collectionTv = (TextView)layoutView.findViewById(R.id.tv_dialog_song_operation_collection);
        if(song.getCollection() == Song.IS_COLLECTION){
            collectionTv.setText(R.string.cancel_collection);
        }
        collectionTv.setOnClickListener(this);
        layoutView.findViewById(R.id.tv_dialog_song_operation_delete).setOnClickListener(this);
        layoutView.findViewById(R.id.tv_dialog_song_operation_add).setOnClickListener(this);
        layoutView.findViewById(R.id.tv_dialog_song_operation_set_to_bell).setOnClickListener(this);
        layoutView.findViewById(R.id.tv_dialog_song_operation_send).setOnClickListener(this);
        layoutView.findViewById(R.id.tv_dialog_song_operation_information).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        //如果点击收藏歌曲
        if(id == R.id.tv_dialog_song_operation_collection){
            //如果歌曲原来已经收藏，取消收藏，否则的话添加收藏
            if(song.getCollection() == Song.IS_COLLECTION){
                new SongInfoOpenHelper(MyApplication.getContext()).updateCollection(song,Song.NOT_COLLECTION);
                song.setCollection(Song.NOT_COLLECTION);
                if(getTargetFragment() != null){
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, new Intent().putExtra(SongListFragment.KEY_POSITION, position));
                }
            }else{
                new SongInfoOpenHelper(MyApplication.getContext()).updateCollection(song,Song.IS_COLLECTION);
                song.setCollection(Song.IS_COLLECTION);
                if(getTargetFragment() != null){
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, new Intent().putExtra(SongListFragment.KEY_POSITION, position));
                }
            }
            dismiss();
            return;
        }

        //装填被点击的位置以及对应歌曲信息
        Bundle arguments = new Bundle();
        arguments.putInt(SongListFragment.KEY_POSITION, position);
        arguments.putSerializable(SongListFragment.KEY_SONG, song);

        //如果点击删除歌曲
        if(id == R.id.tv_dialog_song_operation_delete){
            DeleteSongDialog deleteSongDialogFragment = new DeleteSongDialog();
            deleteSongDialogFragment.setArguments(arguments);
            deleteSongDialogFragment.setTargetFragment(getTargetFragment(), SongListFragment.REQUEST_CODE_DELETE_SONG);
            deleteSongDialogFragment.show(getTargetFragment().getChildFragmentManager(), "DeleteSongDialogFragment");
            dismiss();
            return;
        }
        //如果点击添加按钮
        if(id == R.id.tv_dialog_song_operation_add){
            MyApplication.showToast((BaseActivity)getActivity(), "该功能还未实现");
            dismiss();
            return;
        }
        //如果需要设置歌曲作为铃声
        if(id == R.id.tv_dialog_song_operation_set_to_bell){
            MyApplication.showToast((BaseActivity)getActivity(), "该功能还未实现");
            dismiss();
            return;
        }
        //如果需要发送歌曲
        if(id == R.id.tv_dialog_song_operation_send){
            MyApplication.showToast((BaseActivity)getActivity(), "该功能还未实现");
            dismiss();
            return;
        }
        if(id == R.id.tv_dialog_song_operation_information){
            SongInfoDialog songInfoDialog = new SongInfoDialog();
            songInfoDialog.setArguments(arguments);
            songInfoDialog.setTargetFragment(getTargetFragment(), SongListFragment.REQUEST_CODE_UPDATE_SONG_FILE_INFO);
            songInfoDialog.show(getTargetFragment().getChildFragmentManager(), "songOperationDialog");
            dismiss();
        }
    }
}