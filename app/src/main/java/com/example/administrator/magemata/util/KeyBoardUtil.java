package com.example.administrator.magemata.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.stfalcon.chatkit.messages.MessageInput;

/**
 * Created by Administrator on 2017/4/29.
 */


public class KeyBoardUtil {

    public static final String SP_SOFTBOARD_HEIGHT = "sp_softboard_height";

    /**
     * 软键盘是否显示
     *
     * @param activity
     * @return
     */
    public static boolean isShowSoftBoard(Activity activity) {
        return getSoftBoardHeight(activity) > 100;
    }

    /**
     * 软键盘高度
     *
     * @param activity
     * @return
     */
    public static int getSoftBoardHeight(Activity activity) {
        Rect r = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        return getScreenHeight(activity) - r.bottom;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    private static int getScreenHeight(Activity activity) {
        WindowManager wm = activity.getWindowManager();
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 获取软键盘高度
     *
     * @param context
     * @return
     */
    public static int getSoftBoardHeightSP(Context context) {
        SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return sp.getInt(SP_SOFTBOARD_HEIGHT, 400);
    }

    /**
     * 设置软键盘高度
     *
     * @param context
     * @param h
     */
    public static void setSoftBoardHeight(Context context, int h) {
        SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(SP_SOFTBOARD_HEIGHT, h);
        editor.apply();
    }

    /**
     * 打开软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void openKeyboard(MessageInput mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.SHOW_FORCED);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void closeKeyboard(MessageInput mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
