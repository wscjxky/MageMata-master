package com.example.administrator.magemata.fragment;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.magemata.AppData;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.MychatActivity;
import com.example.administrator.magemata.activity.TestActivity;
import com.example.administrator.magemata.model.Dialog;
import com.example.administrator.magemata.model.Message;
import com.example.administrator.magemata.model.User;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/24.
 */

public class MychatFragment extends Fragment implements DialogsListAdapter.OnDialogClickListener<Dialog>, DialogsListAdapter.OnDialogLongClickListener<Dialog>  {
    private   User NEWUSER
            = new User("2","我是卖家","http://img.qq745.com/uploads/allimg/141231/1-1412310J544-51.jpg",true);
    public static boolean ADDUSER=false;

    public static final ArrayList<String> IMAGES = new ArrayList<String>() {
        {
            add("http://img.qq745.com/uploads/allimg/141231/1-1412310J545.jpg");
            add("http://img.qq745.com/uploads/allimg/141231/1-1412310J544-51.jpg");
            add("http://p1.qzone.la/Upload/20170428/20170428112456185577.jpg");
            add("http://i.imgur.com/hRShCT3.png");
            add("http://img06.tooopen.com/images/20170514/tooopen_sy_210126153448.jpg");

        }
    };
    public static final ArrayList<String> NAMES = new ArrayList<String>() {
        {
            add("小明");
            add("小李");
            add("老王");
        }
    };
    public static final User SENDER=new User("0",NAMES.get(0),IMAGES.get(0),true);
    public static final User RECEIVER =new User("1",NAMES.get(1),IMAGES.get(1),true);
    public static final User OTHER =new User("2",NAMES.get(2),IMAGES.get(2),false);
    private ImageLoader imageLoader;


    private DialogsListAdapter<Dialog> dialogsAdapter;
    @BindView(R.id.mychat_dialoglist)
    public DialogsList dialogsList;
    private Activity activity;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_mychat, container, false);
        this.activity = getActivity();
        ButterKnife.bind(this,mview);
        initAdapter();

        return mview;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        if(isVisibleToUser){
            if(ADDUSER){
                ArrayList<User> users = new ArrayList<User>();
                users.add(SENDER);
                onNewDialog(new Dialog("0", "测试商家", IMAGES.get(0), users, new Message("1", SENDER, "商家互动测试"), 1));
                ADDUSER=false;
            }
        }
    }

    private void initAdapter() {
        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(activity).load(url).into(imageView);
            }
        };
        dialogsAdapter = new DialogsListAdapter<>(imageLoader);
        dialogsAdapter.setItems(getDialogs());

        dialogsAdapter.setOnDialogClickListener(this);
        dialogsAdapter.setOnDialogLongClickListener(this);

        dialogsList.setAdapter(dialogsAdapter);
    }

    private  ArrayList<Dialog> getDialogs() {
        ArrayList<Dialog> chats = new ArrayList<>();
        ArrayList<User> users = new ArrayList<User>();
        users.add(SENDER);
        users.add(RECEIVER);
        Dialog dialog2 = new Dialog("1", "我是小骆", IMAGES.get(0), users, new Message("1", SENDER, "第一个单聊测试"), 2);
        chats.add(dialog2);
        Dialog dialog3 = new Dialog("2", "单聊", IMAGES.get(1), users, new Message("2", RECEIVER, "第一个单聊"), 99);
        chats.add(dialog3);
        users.add(OTHER);
        Dialog dialog = new Dialog("0", "测试群聊", IMAGES.get(3), users, new Message("0", SENDER, "第一个群聊测试"), 1);
        chats.add(dialog);
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
    public void onNewDialog(Dialog dialog) {

        dialogsAdapter.addItem(dialog);
        dialogsAdapter.notifyDataSetChanged();
    }
    @Override
    public void onDialogClick(Dialog dialog) {
        MychatActivity.actionStart(activity);
    }
    @Override
    public void onDialogLongClick(Dialog dialog) {
        Toast.makeText(activity,"长点击",Toast.LENGTH_SHORT).show();
    }
}
