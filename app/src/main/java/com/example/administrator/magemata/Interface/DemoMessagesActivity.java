package com.example.administrator.magemata.Interface;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.model.Message;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/*
 * Created by troy379 on 04.04.17.
 */
public abstract class DemoMessagesActivity extends AppCompatActivity
        implements MessagesListAdapter.SelectionListener,
        MessagesListAdapter.OnLoadMoreListener {

    private static final int TOTAL_MESSAGES_COUNT = 100;

    protected final String senderId = "0";
    protected ImageLoader imageLoader;
    protected MessagesListAdapter<Message> messagesAdapter;

    private Menu menu;
    private int selectionCount;
    private Date lastLoadedDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(DemoMessagesActivity.this).load(url).into(imageView);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        messagesAdapter.addToStart(MessagesFixtures.getTextMessage(), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.back_menu, menu);
        onSelectionChanged(0);
        return true;
    }




//    @Override
//    public void onLoadMore(int page, int totalItemsCount) {
//        if (totalItemsCount < TOTAL_MESSAGES_COUNT) {
//            loadMessages();
//        }
//    }


//    protected void loadMessages() {
//        new Handler().postDelayed(new Runnable() { //imitation of internet connection
//            @Override
//            public void run() {
//                ArrayList<Message> messages = MessagesFixtures.getMessages(lastLoadedDate);
//                lastLoadedDate = messages.get(messages.size() - 1).getCreatedAt();
//                messagesAdapter.addToEnd(messages, false);
//            }
//        }, 1000);
//    }

    private MessagesListAdapter.Formatter<Message> getMessageStringFormatter() {
        return new MessagesListAdapter.Formatter<Message>() {
            @Override
            public String format(Message message) {
                String createdAt = new SimpleDateFormat("MMM d, EEE 'at' h:mm a", Locale.getDefault())
                        .format(message.getCreatedAt());

                String text = message.getText();
                if (text == null) text = "[attachment]";

                return String.format(Locale.getDefault(), "%s: %s (%s)",
                        message.getUser().getName(), text, createdAt);
            }
        };
    }
}
