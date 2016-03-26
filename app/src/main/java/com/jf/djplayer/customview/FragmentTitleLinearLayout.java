package com.jf.djplayer.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf.djplayer.R;

/**
 * Created by JF on 2016/1/19.
 * 用来统一Fragment的标题的
 * 左边一个返回箭头
 * 然后一个Fragment标题
 * 最右边有一个搜索图标
 * 还有一个指示更多图标
 */
public class FragmentTitleLinearLayout extends LinearLayout implements OnClickListener{

    private ImageView searchIv;//搜索
    private ImageView moreIv;//更多
    private ImageView titleImageView;//这个就是标题图片
    private LinearLayout titleLinearLayout;//标题图片还有文字放在一个横向线性布局里面
    private TextView titleTextView;//这个就是标题文字
    private TypedArray typedArray;
    private FragmentTitleListener fragmentTitleListener;

    public FragmentTitleLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View rootView = LayoutInflater.from(context).inflate(R.layout.fragment_title,this);
//        从布局文件里找到各个控件
        titleLinearLayout = (LinearLayout)rootView.findViewById(R.id.ll_fragment_title_title_linearlayout);
        titleImageView = (ImageView)rootView.findViewById(R.id.iv_fragment_title_title_image);
        titleTextView = (TextView)rootView.findViewById(R.id.tv_fragment_title_title_text);
        searchIv = (ImageView)rootView.findViewById(R.id.iv_fragment_title_search);
        moreIv = (ImageView)rootView.findViewById(R.id.iv_fragment_title_more);
//        读取XML文件的属性
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.FragmentTitleLinearLayout);
        setAttribute();
        typedArray.recycle();
    }

//    获取并且设置各个控件属性
    private void setAttribute() {
        titleImageView.setImageResource(typedArray.getResourceId(R.styleable.FragmentTitleLinearLayout_fragmentTitleLinearLayout_titleIcon, R.drawable.ic_fragment_mine_loading));
        titleTextView.setText(typedArray.getString(R.styleable.FragmentTitleLinearLayout_fragmentTitleLinearLayout_titleText));
        moreIv.setVisibility(typedArray.getInteger(R.styleable.FragmentTitleLinearLayout_fragmentTitleLinearLayout_moreImageVisibility, View.VISIBLE));
        searchIv.setVisibility(typedArray.getInteger(R.styleable.FragmentTitleLinearLayout_fragmentTitleLinearLayout_searchImageVisibility, View.VISIBLE));
    }

    /**
     * 为标题上各个按钮设置监听
     * @param fragmentTitleListener
     */
    public void setItemClickListener(FragmentTitleListener fragmentTitleListener){
        this.fragmentTitleListener = fragmentTitleListener;
        titleLinearLayout.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);
    }

    /**
     * 设置标题文字内容
     */
    public void setTitleText(String titleText){
        titleTextView.setText(titleText);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ll_fragment_title_title_linearlayout:fragmentTitleListener.onTitleClick();break;
            case R.id.iv_fragment_title_search:fragmentTitleListener.onSearchIvOnclick();break;
            case R.id.iv_fragment_title_more:fragmentTitleListener.onMoreIvOnclick();break;
            default:break;
        }
    }

    /**
     * 点击事件的监听器
     * 分别监听标题点击
     * 搜索按钮点击事件
     *“更多”按钮点击事件监听
     */
    public interface FragmentTitleListener {

        /**
         * 如果标题被按下了
         */
        void onTitleClick();

        /**
         * 如果搜索按钮被按下了
         */
         void onSearchIvOnclick();

        /**
         * 如果更多按钮被按下了
         */
         void onMoreIvOnclick();
    }
}
