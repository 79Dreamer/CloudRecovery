package com.example.cloudrecovery.DoctorPlanUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ConfigUtil {
    public static final String SERVER_ADDR = "http://10.7.81.109:8080/CakeShop/";

    /**
     * @param 将字符数组转换成InmageView可调用的Bitmap对象
     */
    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts){
        Bitmap bitmap = null;
        if(bytes != null){
            if(opts != null){
                bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length,opts);
            }else{
                bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            }
        }
        return bitmap;
    }

    /**
     * @param 图片缩放
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newBmp;
    }

    /**
     * @param 将图片内容解析成字节数组
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }
}
