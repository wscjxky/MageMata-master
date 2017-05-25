package com.example.administrator.magemata.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.fragment.MychatFragment;
import com.example.administrator.magemata.model.Message;
import com.example.administrator.magemata.model.User;
import com.example.administrator.magemata.util.KeyBoardUtil;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.VISIBLE;
import static com.example.administrator.magemata.fragment.MychatFragment.IMAGES;
import static com.example.administrator.magemata.fragment.MychatFragment.OTHER;
import static com.example.administrator.magemata.fragment.MychatFragment.RECEIVER;
import static com.example.administrator.magemata.fragment.MychatFragment.SENDER;

public class MychatActivity extends BaseActivity implements MessageInput.InputListener  ,MessageInput.AttachmentsListener{

    protected MessagesListAdapter<Message> messagesAdapter;
    @BindView(R.id.mychat_messagesList)
    public MessagesList messagesList;
    @BindView(R.id.mychat_input)
    public MessageInput messageInput;
    @BindView(R.id.mychat__show_fl)
    FrameLayout showFrameLayout;
    @BindView(R.id.mychat_attach_fl)
    FrameLayout emojiFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mychat);
        ButterKnife.bind(this);
        emojiFrameLayout.setVisibility(View.GONE);
        initAdapter();
    }


    @OnClick(R.id.imageButton)
    public void sendImg(){
        Message message=new Message("2",SENDER,"图片测试");
        message.setImage(new Message.Image(IMAGES.get(4)));
        messagesAdapter.addToStart(message,true);
    }


    @Override
    public boolean onSubmit(CharSequence input) {
        messagesAdapter.addToStart(
                new Message("0", SENDER, input.toString()),true);
        try {
            Robot_Get(input.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }

        return true;
    }
    private void Robot_Get(String content) throws IOException {
        RequestParams requestParams = new RequestParams("http://api.qingyunke.com/api.php?key=free&appid=0&msg="+content);
        x.http().get(requestParams, new Callback.CacheCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                try {
                    messagesAdapter.addToStart(
                            new Message("1", OTHER,jsonObject.getString("content")), true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
            @Override
            public boolean onCache(JSONObject result) {
                return false;
            }
        });
    }


    private void initAdapter() {
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(MychatActivity.this).load(url).into(imageView);
            }
        };
        messagesAdapter = new MessagesListAdapter<>(SENDER.getId(), imageLoader);
        messagesList.setAdapter(messagesAdapter);
        messageInput.setInputListener(this);
        messageInput.setAttachmentsListener(this);
        EditText editText=messageInput.getInputEditText();
        //让EditText失去焦点，然后获取点击事件
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("获取","点击");
                if (emojiFrameLayout.getVisibility() == View.VISIBLE) {
                    setEmojiViewHeight(0);
                    KeyBoardUtil.openKeyboard(messageInput, MychatActivity.this);
                }
            }
        });
//        super.messagesAdapter.enableSelectionMode(this);
//        super.messagesAdapter.setLoadMoreListener(this);

    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MychatActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onAddAttachments() {
        showPop();
    }

    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mychat_addattach, null);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.setFocusable(true);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        window.showAtLocation(messageInput,
//                Gravity.BOTTOM, 0, 0);
        // 这里检验popWindow里的button是否可以点击
        Button first = (Button) view.findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message=new Message("2",SENDER,"图片测试");
                message.setImage(new Message.Image(IMAGES.get(4)));
                messagesAdapter.addToStart(message,true);
                window.dismiss();
            }
        });
    }

    private void showPop(){
        int softHeight = KeyBoardUtil.getSoftBoardHeight(this);
        //当前没有显示软键盘
        if (softHeight <= 0) {
            softHeight = KeyBoardUtil.getSoftBoardHeightSP(this);
        }
        //当前显示了软键盘
        else {
            KeyBoardUtil.setSoftBoardHeight(this, softHeight);
        }
//        给我们的类设置高度
        KeyBoardUtil.setSoftBoardHeight(this, softHeight);
        //如果键盘在显示
        if(KeyBoardUtil.isShowSoftBoard(this)) {
            //锁住消息界面原始的高度
            lockHeight();
            //设置弹出面板的高度=软键盘的高度
            setEmojiViewHeight(softHeight-100);
            emojiFrameLayout.setVisibility(VISIBLE);
            //关闭软键盘
            KeyBoardUtil.closeKeyboard(messageInput, this);
            unlockHeight();
        } else {
            lockHeight();
            setEmojiViewHeight(0);
            KeyBoardUtil.openKeyboard(messageInput, this);
            unlockHeight();
        }
    }
    private void lockHeight() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) showFrameLayout.getLayoutParams();
        lp.height = showFrameLayout.getHeight();
        //设置权重
        lp.weight = 0;
        showFrameLayout.setLayoutParams(lp);
    }
    private void unlockHeight() {
        // 需要做一个延时操作，不然没有效果
        messageInput.postDelayed(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) showFrameLayout.getLayoutParams();
                lp.weight = 1;
                showFrameLayout.setLayoutParams(lp);
            }
        }, 150);
    }
    private void setEmojiViewHeight(int h) {
        ViewGroup.LayoutParams lp = emojiFrameLayout.getLayoutParams();
        lp.height = h;
        emojiFrameLayout.setLayoutParams(lp);
    }
}