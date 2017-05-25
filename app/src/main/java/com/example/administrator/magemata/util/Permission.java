package com.example.administrator.magemata.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.yanzhenjie.permission.AndPermission;

/**
 * Created by Administrator on 2017/4/24.
 */

public class Permission {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void requestPermission(Activity activity){
        if(!AndPermission.hasPermission(activity,Manifest.permission.CAMERA)){
            AndPermission.with(activity)
                    .requestCode(100)
                    .permission(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                            Manifest.permission.MANAGE_DOCUMENTS,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.INTERNET)
                    .send();
        }
    }
}
