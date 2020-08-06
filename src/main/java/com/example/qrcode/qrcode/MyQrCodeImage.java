package com.example.qrcode.qrcode;

import java.awt.image.BufferedImage;
import jp.sourceforge.qrcode.data.QRCodeImage;

/**
 * @author Fatal
 * @date 2020/8/6 8:28
 */
public class MyQrCodeImage implements QRCodeImage {

    BufferedImage bufferedImage;

    public MyQrCodeImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    @Override
    public int getPixel(int i, int i1) {
        return bufferedImage.getRGB(i, i1);
    }
}
