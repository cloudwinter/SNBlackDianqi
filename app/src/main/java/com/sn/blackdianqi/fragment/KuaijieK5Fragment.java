package com.sn.blackdianqi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sn.blackdianqi.R;
import com.sn.blackdianqi.util.LogUtils;
import com.sn.blackdianqi.view.AnjianChangTuoYuanView;
import com.sn.blackdianqi.view.AnjianTuoYuanView;
import com.sn.blackdianqi.view.JiyiView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 快捷K2
 */
public class KuaijieK5Fragment extends KuaijieBaseFragment implements View.OnTouchListener {


    @BindView(R.id.img_anjian_top_icon)
    ImageView topIconImgView;
    @BindView(R.id.text_anjian_top_title)
    TextView topTitleTextView;

    // 分体
    @BindView(R.id.layout_fenti)
    LinearLayout fentiLayout;
    @BindView(R.id.view_fenti)
    JiyiView fentiView;
    @BindView(R.id.view_fenti_jiyi_left)
    AnjianTuoYuanView fentiJiyiLeftView;
    @BindView(R.id.view_fenti_jiyi_right)
    AnjianTuoYuanView fentiJiyiRightView;
    @BindView(R.id.view_fenti_kandianshi_left)
    AnjianTuoYuanView fentiKandianshiLeftView;
    @BindView(R.id.view_fenti_kandianshi_right)
    AnjianTuoYuanView fentiKandianshiRightView;
    @BindView(R.id.view_fenti_fuyuan)
    AnjianChangTuoYuanView fentiFuyuanView;


    // 同步
    @BindView(R.id.layout_tongbu)
    LinearLayout tongbuLayout;
    @BindView(R.id.view_tongbu)
    JiyiView tongbuView;
    @BindView(R.id.view_tongbu_jiyi_left)
    AnjianTuoYuanView tongbuJiyiLeftView;
    @BindView(R.id.view_tongbu_jiyi_right)
    AnjianTuoYuanView tongbuJiyiRightView;
    @BindView(R.id.view_tongbu_kandianshi)
    AnjianChangTuoYuanView tongbuKandianshiView;
    @BindView(R.id.view_tongbu_fuyuan)
    AnjianChangTuoYuanView tongbuFuyuanView;



    private long eventDownTime = 0L;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_kuaijie_k5, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 按键以外发送停止码
                sendBlueCmd("FF FF FF FF 05 00 00 00 00 D7 00");
            }
        });
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        fentiLayout.setOnTouchListener(this);
        fentiView.setOnTouchListener(this);
        fentiKandianshiLeftView.setOnTouchListener(this);
        fentiKandianshiRightView.setOnTouchListener(this);
        fentiJiyiLeftView.setOnTouchListener(this);
        fentiJiyiRightView.setOnTouchListener(this);
        fentiFuyuanView.setOnTouchListener(this);

        tongbuLayout.setOnTouchListener(this);
        tongbuView.setOnTouchListener(this);
        tongbuKandianshiView.setOnTouchListener(this);
        tongbuJiyiLeftView.setOnTouchListener(this);
        tongbuJiyiRightView.setOnTouchListener(this);
        tongbuFuyuanView.setOnTouchListener(this);
    }

    /**
     * 设置顶部icon和title
     *
     * @param iconResId
     * @param titleResId
     */
    private void setTopIconAndTitle(int iconResId, int titleResId) {
        topIconImgView.setBackground(ContextCompat.getDrawable(getContext(), iconResId));
        topTitleTextView.setText(getString(titleResId));
    }


    /**
     * 设置顶部的title
     *
     * @param titleResId
     */
    private void setTitle(int titleResId) {
        topTitleTextView.setText(getString(titleResId));
    }


    @Override
    protected void handleReceiveData(String data) {
        if (data.contains("FF FF FF FF 03 12 00 31")) {
            // 分体看电视left
            fentiKandianshiLeftView.setSelected(true);
        }
        if (data.contains("FF FF FF FF 03 12 00 32")) {
            // 分体看电视right
            fentiKandianshiRightView.setSelected(true);
        }
        if (data.contains("FF FF FF FF 03 12 00 33")) {
            // 分体零压力left
            fentiJiyiLeftView.setSelected(true);
            tongbuJiyiLeftView.setSelected(true);
        }
        if (data.contains("FF FF FF FF 03 12 00 34")) {
            // 分体零压力right
            fentiJiyiRightView.setSelected(true);
            tongbuJiyiRightView.setSelected(true);
        }


//        // 分体看电视left 按键回码
//        if (data.contains("FF FF FF FF 05 00 00 10 31 1B 14")) {
//            fentiKandianshiLeftView.setSelected(true);
//        }
//        if (data.contains("FF FF FF FF 05 00 00 1F 31 1E E4")) {
//            fentiKandianshiLeftView.setSelected(false);
//        }
//
//        // 分体看电视right 按键回码
//        if (data.contains("FF FF FF FF 05 00 00 20 32 4F 15")) {
//            fentiKandianshiRightView.setSelected(true);
//        }
//        if (data.contains("FF FF FF FF 05 00 00 2F 32 4A E5")) {
//            fentiKandianshiRightView.setSelected(false);
//        }
//
//        // 记忆left 按键回码
//        if (data.contains("FF FF FF FF 05 00 00 30 33 83 15")) {
//            fentiJiyiLeftView.setSelected(true);
//            tongbuJiyiLeftView.setSelected(true);
//        }
//        if (data.contains("FF FF FF FF 05 00 00 3F 33 86 E5")) {
//            fentiJiyiLeftView.setSelected(false);
//            tongbuJiyiLeftView.setSelected(false);
//        }
//
//        // 记忆right 按键回码
//        if (data.contains("FF FF FF FF 05 00 00 40 34 E7 17")) {
//            fentiJiyiRightView.setSelected(true);
//            tongbuJiyiRightView.setSelected(true);
//        }
//        if (data.contains("FF FF FF FF 05 00 00 4F 34 E2 E7")) {
//            fentiJiyiRightView.setSelected(false);
//            tongbuJiyiRightView.setSelected(false);
//        }
    }
    @Override
    protected void askStatus() {
        try {
            // 看电视左
            sendAskBlueCmd("FF FF FF FF 03 00 64 00 09 DE D9");
            Thread.sleep(500L);
            // 看电视右
            sendAskBlueCmd("FF FF FF FF 03 00 6D 00 09 0E DB");
            Thread.sleep(500L);
            // 记忆左
            sendAskBlueCmd("FF FF FF FF 03 00 76 00 09 7E DC");
            Thread.sleep(500L);
            // 记忆右
            sendAskBlueCmd("FF FF FF FF 03 00 7F 00 09 AE DE");
        } catch (Exception e) {
            LogUtils.e(TAG, "askStatus 异常" + e.getMessage());
            e.printStackTrace();
        }
    }
//
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (v.getId()) {
            case R.id.view_fenti:
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(FENTI_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(FENTI_WHAT);
                }
                break;
            case R.id.view_tongbu:
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(TONGBU_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(TONGBU_WHAT);
                }
                break;
            case R.id.view_fenti_jiyi_left:
                setTopIconAndTitle(R.mipmap.xr_lingyali_da, R.string.jiyi);
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(FENTI_JIYI_LEFT_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(FENTI_JIYI_LEFT_WHAT);
                    if (isShortClick()) {
                        // 短按
                        if (fentiJiyiLeftView.isSelected()) {
                            sendBlueCmd("FF FF FF FF 05 00 00 31 33 82 85");
                        }
                    }
                }
                break;
            case R.id.view_fenti_jiyi_right:
                setTopIconAndTitle(R.mipmap.xr_lingyali_da, R.string.jiyi);
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(FENTI_JIYI_RIGHT_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(FENTI_JIYI_RIGHT_WHAT);
                    if (isShortClick()) {
                        // 短按
                        if (fentiJiyiLeftView.isSelected()) {
                            sendBlueCmd("FF FF FF FF 05 00 00 41 34 E6 87");
                        }
                    }
                }
                break;
            case R.id.view_fenti_kandianshi_left:
                setTopIconAndTitle(R.mipmap.xr_kandianshi_da, R.string.kandianshi);
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(FENTI_KANDIANSHI_LEFT_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(FENTI_KANDIANSHI_LEFT_WHAT);
                    if (isShortClick()) {
                        // 短按
                        if (fentiKandianshiLeftView.isSelected()) {
                            sendBlueCmd("FF FF FF FF 05 00 00 11 31 1A 84");
                        } else {
                            sendBlueCmd("FF FF FF FF 05 00 00 00 31 16 D4");
                        }
                    }
                }
                break;
            case R.id.view_fenti_kandianshi_right:
                setTopIconAndTitle(R.mipmap.xr_kandianshi_da, R.string.kandianshi);
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(FENTI_KANDIANSHI_RIGHT_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(FENTI_KANDIANSHI_RIGHT_WHAT);
                    if (isShortClick()) {
                        // 短按
                        if (fentiKandianshiLeftView.isSelected()) {
                            sendBlueCmd("FF FF FF FF 05 00 00 21 32 4E 85");
                        } else {
                            sendBlueCmd("FF FF FF FF 05 00 00 00 32 56 D5");
                        }
                    }
                }
                break;
            case R.id.view_fenti_lingyali_left:
                setTopIconAndTitle(R.mipmap.xr_lingyali_da, R.string.lingyali);
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(FENTI_JIYI_LEFT_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(FENTI_JIYI_LEFT_WHAT);
                    if (isShortClick()) {
                        // 短按
                        if (fentiKandianshiLeftView.isSelected()) {
                            sendBlueCmd("FF FF FF FF 05 00 00 31 33 82 85");
                        } else {
                            sendBlueCmd("FF FF FF FF 05 00 00 00 33 97 15");
                        }
                    }
                }
                break;
            case R.id.view_fenti_lingyali_right:
                setTopIconAndTitle(R.mipmap.xr_lingyali_da, R.string.lingyali);
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(FENTI_JIYI_RIGHT_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(FENTI_JIYI_RIGHT_WHAT);
                    if (isShortClick()) {
                        if (fentiKandianshiRightView.isSelected()) {
                            sendBlueCmd("FF FF FF FF 05 00 00 41 34 E6 87");
                        } else {
                            sendBlueCmd("FF FF FF FF 05 00 00 00 34 D6 D7");
                        }
                    }
                }
                break;
            case R.id.view_fenti_fuyuan:
                setTopIconAndTitle(R.mipmap.xr_fuyuan_da, R.string.fuyuan);
                if (MotionEvent.ACTION_DOWN == action) {
                    sendBlueCmd("FF FF FF FF 05 00 00 00 08 D6 C6");
                }
                break;

            case R.id.view_tongbu_jiyi_left:
                setTopIconAndTitle(R.mipmap.xr_lingyali_da, R.string.jiyi);
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(TONGBU_JIYI_LEFT_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(TONGBU_JIYI_LEFT_WHAT);
                    if (isShortClick()) {
                        // 短按
                        if (fentiJiyiLeftView.isSelected()) {
                            sendBlueCmd("FF FF FF FF 05 00 00 31 33 82 85");
                        }
                    }
                }
                break;
            case R.id.view_tongbu_jiyi_right:
                setTopIconAndTitle(R.mipmap.xr_lingyali_da, R.string.jiyi);
                if (MotionEvent.ACTION_DOWN == action) {
                    eventDownTime = System.currentTimeMillis();
                    timeHandler.sendEmptyMessageDelayed(TONGBU_JIYI_RIGHT_WHAT, DEFAULT_INTERVAL);
                } else if (MotionEvent.ACTION_UP == action) {
                    timeHandler.removeMessages(TONGBU_JIYI_RIGHT_WHAT);
                    if (isShortClick()) {
                        // 短按
                        if (fentiJiyiLeftView.isSelected()) {
                            sendBlueCmd("FF FF FF FF 05 00 00 41 34 E6 87");
                        }
                    }
                }
                break;
            case R.id.view_tongbu_kandianshi:
                setTopIconAndTitle(R.mipmap.xr_kandianshi_da, R.string.kandianshi);
                if (MotionEvent.ACTION_DOWN == action) {
                    sendBlueCmd("FF FF FF FF 05 00 05 00 3E 46 D1");
                }
                break;
            case R.id.view_tongbu_fuyuan:
                setTopIconAndTitle(R.mipmap.xr_fuyuan_da, R.string.fuyuan);
                if (MotionEvent.ACTION_DOWN == action) {
                    sendBlueCmd("FF FF FF FF 05 00 00 00 3B 96 D3");
                }
                break;
        }
        return true;
    }


    private static final int FENTI_WHAT = 1;
    private static final int TONGBU_WHAT = 2;
    private static final int FENTI_KANDIANSHI_LEFT_WHAT = 3;
    private static final int FENTI_KANDIANSHI_RIGHT_WHAT = 4;
    private static final int FENTI_JIYI_LEFT_WHAT = 5;
    private static final int FENTI_JIYI_RIGHT_WHAT = 6;
    private static final int TONGBU_JIYI_LEFT_WHAT = 7;
    private static final int TONGBU_JIYI_RIGHT_WHAT = 8;

    /**
     * 记忆1 1
     * 记忆2  2
     * 看电视 3
     * 零压力 4
     * 止鼾 5
     */
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case FENTI_WHAT:
                    fentiLongClick();
                    break;
                case TONGBU_WHAT:
                    tongbuLongClick();
                    break;
                case FENTI_KANDIANSHI_LEFT_WHAT:
                    kandianshiLeftLongClick();
                    break;
                case FENTI_KANDIANSHI_RIGHT_WHAT:
                    kandianshiRightLongClick();
                    break;
                case FENTI_JIYI_LEFT_WHAT:
                    fentiJiyiLeftLongClick();
                    break;
                case FENTI_JIYI_RIGHT_WHAT:
                    fentiJiyiRightLongClick();
                    break;
                case TONGBU_JIYI_LEFT_WHAT:
                    tongbuJiyiLeftLongClick();
                    break;
                case TONGBU_JIYI_RIGHT_WHAT:
                    tongbuJiyiRightLongClick();
                    break;
                default:
                    break;
            }
        }
    };

    private void fentiLongClick() {
        fentiLayout.setVisibility(View.GONE);
        tongbuLayout.setVisibility(View.VISIBLE);
        tongbuView.setSelected(true);
    }

    private void tongbuLongClick() {
        tongbuLayout.setVisibility(View.GONE);
        fentiLayout.setVisibility(View.VISIBLE);
        fentiView.setSelected(true);
    }


    private void kandianshiLeftLongClick() {
        if (fentiKandianshiLeftView.isSelected()) {
            // 有记忆
            sendBlueCmd("FF FF FF FF 05 00 00 1F 31 1E E4");
            fentiKandianshiLeftView.setSelected(false);
        } else {
            sendBlueCmd("FF FF FF FF 05 00 00 10 31 1B 14");
            fentiKandianshiLeftView.setSelected(true);
        }
    }


    private void kandianshiRightLongClick() {
        if (fentiKandianshiRightView.isSelected()) {
            // 有记忆
            sendBlueCmd("FF FF FF FF 05 00 00 2F 32 4A E5");
            fentiKandianshiRightView.setSelected(false);
        } else {
            sendBlueCmd("FF FF FF FF 05 00 00 20 32 4F 15");
            fentiKandianshiRightView.setSelected(true);
        }
    }


    private void fentiJiyiLeftLongClick() {
        if (fentiJiyiLeftView.isSelected()) {
            // 有记忆
            sendBlueCmd("FF FF FF FF 05 00 00 3F 33 86 E5");
            fentiJiyiLeftView.setSelected(false);
        } else {
            sendBlueCmd("FF FF FF FF 05 00 00 30 33 83 15");
            fentiJiyiLeftView.setSelected(true);
        }
    }

    private void tongbuJiyiLeftLongClick() {
        if (tongbuJiyiLeftView.isSelected()) {
            // 有记忆
            sendBlueCmd("FF FF FF FF 05 00 00 3F 33 86 E5");
            tongbuJiyiLeftView.setSelected(false);
        } else {
            sendBlueCmd("FF FF FF FF 05 00 00 30 33 83 15");
            tongbuJiyiLeftView.setSelected(true);
        }
    }

    private void fentiJiyiRightLongClick() {
        if (fentiJiyiRightView.isSelected()) {
            // 有记忆
            sendBlueCmd("FF FF FF FF 05 00 00 4F 34 E2 E7");
            fentiJiyiRightView.setSelected(false);
        } else {
            sendBlueCmd("FF FF FF FF 05 00 00 40 34 E7 17");
            fentiJiyiRightView.setSelected(true);
        }
    }

    private void tongbuJiyiRightLongClick() {
        if (tongbuJiyiRightView.isSelected()) {
            // 有记忆
            sendBlueCmd("FF FF FF FF 05 00 00 4F 34 E2 E7");
            tongbuJiyiRightView.setSelected(false);
        } else {
            sendBlueCmd("FF FF FF FF 05 00 00 40 34 E7 17");
            tongbuJiyiRightView.setSelected(true);
        }
    }


    public boolean isShortClick() {
        long endTime = System.currentTimeMillis();
        if (getInterval(eventDownTime, endTime) < 2000) {
            return true;
        }
        return false;
    }


    /**
     * 单位是毫秒
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private long getInterval(long startTime, long endTime) {
        long interval = endTime - startTime;
        return interval;
    }


}
