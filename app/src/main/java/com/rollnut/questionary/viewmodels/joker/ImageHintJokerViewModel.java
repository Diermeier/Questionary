package com.rollnut.questionary.viewmodels.joker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.rollnut.questionary.models.joker.ImageHintJoker;
import com.rollnut.questionary.models.joker.JokerBase;

public class ImageHintJokerViewModel extends JokerViewModel {

    private ImageHintJoker joker;

    public ImageHintJokerViewModel(ImageHintJoker joker) {
        super(joker);
        this.joker = joker;
    }

    // Properties

    public String getImageAsBase64String() {
        return this.joker.ImageAsBase64String;
    }

    public byte[] getImageAsBase64ByteArray() {
        byte[] decodedString = Base64.decode(getImageAsBase64String(), Base64.DEFAULT);
        return decodedString;
    }

    public Bitmap getImageAsBitmap() {
        byte[] byteArray = getImageAsBase64ByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
        return bitmap;
    }
}
