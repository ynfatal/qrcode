package com.example.qrcode.util;

import com.swetake.util.Qrcode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;

/**
 * QRCode 工具类
 * @author Fatal
 * @date 2020/8/6 8:01
 */
@Slf4j
public class QRCodeUtil {

    /**
     * 生成二维码
     * @param path 二维码本地生成的路径
     * @param qrCode 二维码携带的参数
     */
    public static void generate(String path, String qrCode) {
        BufferedImage bufferedImage = getQRCodeBufferedImage(qrCode);
        try {
            ImageIO.write(bufferedImage, "png", new File(path));
        } catch (IOException e) {
            log.error("【二维码生成】生成失败");
            e.printStackTrace();
        }
    }

    /**
     * 供上传 OSS 使用
     * @param qrData 二维码携带的参数
     * @return
     */
    public static byte[] getQRCodeBytes(String qrData) {
        BufferedImage bufferedImage = getQRCodeBufferedImage(qrData);
        return bufferedImageToBytes(bufferedImage);
    }

    /**
     * 获取生成二维码的源数据 BufferedImage
     * @param qrData 二维码携带的参数
     * @return
     */
    public static BufferedImage getQRCodeBufferedImage(String qrData) {
        Qrcode x = new Qrcode();
        x.setQrcodeErrorCorrect('M');   // 纠错等级
        x.setQrcodeEncodeMode('B');     // N 代表数字， A 代表 a-Z，B 代表其他字符
        x.setQrcodeVersion(7);  // 版本号
        Integer width = 67 + 12 * (7 - 1);
        Integer height = 67 + 12 * (7 - 1);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.setColor(Color.BLACK);
        graphics.clearRect(0, 0, width, height);

        Integer pixoff = 2; // 偏移量

        byte[] d = new byte[0];
        try {
            d = qrData.getBytes("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (d.length > 0 && d.length < 120) {
            boolean[][] s = x.calQrcode(d);

            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        graphics.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                    }
                }
            }
        }

        graphics.dispose();
        bufferedImage.flush();
        return bufferedImage;
    }

    /**
     * BufferedImage转byte[]
     * @implNote 当然也可以返回 ByteArrayOutputStream，看具体需要
     * @param bufferedImage BufferedImage对象
     * @return byte[]
     * @auth zhy
     */
    private static byte[] bufferedImageToBytes(BufferedImage bufferedImage) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", out);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return out.toByteArray();
    }
}
