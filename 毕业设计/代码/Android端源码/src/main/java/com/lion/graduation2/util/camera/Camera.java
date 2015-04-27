package com.lion.graduation2.util.camera;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.FileUtils;
import com.lion.graduation2.util.TimeUtils;

import java.io.File;
import java.io.FileOutputStream;

public class Camera {

    /**
     * storeImageToSDCARD 将bitmap存放到sdcard中
     */
    public static void storeImageToSDCARD(Bitmap colorImage, String imageName, String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        File imagefile = new File(file, imageName);
        try {
            imagefile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagefile);
            colorImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
