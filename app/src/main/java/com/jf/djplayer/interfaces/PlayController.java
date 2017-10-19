package com.jf.djplayer.interfaces;

import com.jf.djplayer.bean.Song;

import java.util.List;

/**
 * Created by JF on 2016/1/26.
 * Activity通过实现这个接口成为音乐播放的控制者，
 * Fragment通过这个接口控制Activity从而控制播放
 */
public interface PlayController {

    /**
     * 传入要播放的歌曲列表
     * 以及要播放的歌曲所在序号
     * @param songInfoList
     * @param position
     */
    public void play(List<Song> songInfoList,int position);

    public void play(String playListName, List<Song> songList, int playPosition);
    /**
     * 暂停之后继续播放用的
     */
    public void play();

    /**
     * 查询当前是否正在播放
     */
    public boolean isPlaying();
    /**
     * 暂停
     */
    public void pause();

    /**
     * 播放列表里的下一首歌
     */
    public void nextSong();

    /**
     * 播放列表里面的前一手歌曲
     */
    public void previousSong();

    /**
     * 停止进行歌曲播放
     */
    public void stop();
}
