package com.example.administrator.magemata.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.administrator.magemata.R;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends BaseActivity implements OnMenuItemClickListener {
    @BindView(R.id.launch_btn_login)
    Button btn_login;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanuch);
        fragmentManager = getSupportFragmentManager();
        initMenuFragment();
        ButterKnife.bind(this);
    }
    @OnClick(R.id.launch_btn_login)
    public void login() {
        LoginActivity.actionStart(LaunchActivity.this);
    }


    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize(160);
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
    }
    private List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();
        String MenuTitle[]={"发消息","点赞"};
        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);
        MenuObject send = new MenuObject("发消息");
        send.setResource(R.drawable.icn_1);
        MenuObject like = new MenuObject("点赞");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);
        MenuObject addFr = new MenuObject("Add to friends");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);
        MenuObject addFav = new MenuObject("Add to favorites");
        addFav.setResource(R.drawable.icn_4);
        MenuObject block = new MenuObject("Block user");
        block.setResource(R.drawable.icn_5);
        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);
        return menuObjects;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.share_menu, menu);
    return true;
}
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.circle_add:
//                Intent sendIntent =new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT,"成功分享我的日报");
//                sendIntent.setType("text/plain");
//                startActivity(Intent.createChooser(sendIntent, getTitle()));
                mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();

    }



}
