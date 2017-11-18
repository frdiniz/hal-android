package io.github.frdiniz.hal.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.io.ByteArrayOutputStream;

public class DataBitmapUtil {

    // convert from bitmap to byte array
    public static byte[] toBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap toImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    // get bitmap from ImageView
    public static Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof GlideBitmapDrawable) {
            return ((GlideBitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }
}
