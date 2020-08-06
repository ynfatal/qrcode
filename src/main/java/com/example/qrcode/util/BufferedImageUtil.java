package com.example.qrcode.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;

/**
 * 参考链接：https://blog.csdn.net/qq_27721169/article/details/96312517
 * @author Fatal
 * @date 2020/8/6 13:16
 */
@Slf4j
public class BufferedImageUtil {

    /**
     * BufferedImage转byte[]
     * @implNote 当然也可以返回 ByteArrayOutputStream，看具体需要
     * @param bufferedImage BufferedImage对象
     * @return byte[]
     * @auth zhy
     */
    public static byte[] bufferedImageToBytes(BufferedImage bufferedImage) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", out);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return out.toByteArray();
    }

}
