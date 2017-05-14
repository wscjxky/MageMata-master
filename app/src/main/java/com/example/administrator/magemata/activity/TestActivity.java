package com.example.administrator.magemata.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.magemata.Interface.DemoDialogsActivity;
import com.example.administrator.magemata.Interface.DialogsFixtures;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.model.Dialog;
import com.example.administrator.magemata.model.Message;
import com.example.administrator.magemata.model.User;
import com.example.administrator.magemata.util.AppUtils;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/4/28.
 */
public class TestActivity extends AppCompatActivity  implements DialogsListAdapter.OnDialogClickListener<Dialog>, DialogsListAdapter.OnDialogLongClickListener<Dialog> {
    static ArrayList<String> avatars = new ArrayList<String>() {
        {
            add("http://i.imgur.com/pv1tBmT.png");
            add("http://i.imgur.com/R3Jm1CL.png");
            add("http://i.imgur.com/ROz4Jgh.png");
            add("http://i.imgur.com/Qn9UesZ.png");
        }
    };

    static final ArrayList<String> groupChatImages = new ArrayList<String>() {
        {
            add("http://i.imgur.com/hRShCT3.png");
            add("http://i.imgur.com/zgTUcL3.png");
            add("http://i.imgur.com/mRqh5w1.png");
        }
    };


    static final ArrayList<String> names = new ArrayList<String>() {
        {
            add("Samuel Reynolds");
            add("Kyle Hardman");
            add("Zoe Milton");
            add("Angel Ogden");
            add("Zoe Milton");
            add("Angelina Mackenzie");
            add("Kyle Oswald");
            add("Abigail Stevenson");
            add("Julia Goldman");
            add("Jordan Gill");
            add("Michelle Macey");
        }
    };

    protected ImageLoader imageLoader;
    protected DialogsListAdapter<Dialog> dialogsAdapter;

    public static void open(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }

    private DialogsList dialogsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mychat);
        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(TestActivity.this).load(url).into(imageView);
            }
        };

        dialogsList = (DialogsList) findViewById(R.id.mychat_dialoglist);
        initAdapter();
    }

    @Override
    public void onDialogClick(Dialog dialog) {
        Log.e("xiaoxi","点击");
    }

    private void initAdapter() {
        dialogsAdapter = new DialogsListAdapter<>(imageLoader);
        dialogsAdapter.setItems(getDialogs());

        dialogsAdapter.setOnDialogClickListener(this);
        dialogsAdapter.setOnDialogLongClickListener(this);

        dialogsList.setAdapter(dialogsAdapter);
    }
    private  ArrayList<Dialog> getDialogs() {
        ArrayList<Dialog> chats = new ArrayList<>();
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < 3; i++) {
            User user = new User(i + "", names.get(i), avatars.get(i), true);
            users.add(user);
        }
        Calendar calendar = Calendar.getInstance();
        Dialog dialog = new Dialog("1", "群聊", groupChatImages.get(2), users, new Message("1", users.get(1), "第一个群聊", calendar.getTime()), 99);
        chats.add(dialog);
        User user = new User("3", "R", "http://i.imgur.com/pv1tBmT.png", true);
        ArrayList<User> users2 = new ArrayList<User>();
        users2.add(user);
        Dialog dialog2 = new Dialog("2", "单聊", "http://i.imgur.com/pv1tBmT.png", users2, new Message("2", user, "第一个单聊", calendar.getTime()), 99);
        chats.add(dialog2);
        return chats;
    }

    //for example
    private void onNewMessage(String dialogId, Message message) {
        boolean isUpdated = dialogsAdapter.updateDialogWithMessage(dialogId, message);
        if (!isUpdated) {
            //Dialog with this ID doesn't exist, so you can create new Dialog or update all dialogs list
        }
    }

    //for example
    private void onNewDialog(Dialog dialog) {
        dialogsAdapter.addItem(dialog);
    }
    @Override
    public void onDialogLongClick(Dialog dialog) {
        Log.e("xiaoxi","点击");
    }
}
