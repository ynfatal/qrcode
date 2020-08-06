package com.example.qrcode.util;

import com.example.qrcode.qrcode.MyQrCodeImage;
import com.swetake.util.Qrcode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;
import jp.sourceforge.qrcode.QRCodeDecoder;
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
            log.error("【二维码】生成失败");
            e.printStackTrace();
        }
    }

    /**
     * 解析二维码
     * @param path 二维码本地生成的路径
     * @return
     */
    public static String decode(String path) {
        try {
            File file = new File(path);
            BufferedImage bufferedImage = ImageIO.read(file);
            QRCodeDecoder codeDecoder = new QRCodeDecoder();
            return new String(codeDecoder.decode(new MyQrCodeImage(bufferedImage)), "gb2312");
        } catch (IOException e) {
            log.error("【二维码】解析失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 供上传 OSS 使用
     * @param qrData 二维码携带的参数
     * @return
     */
    public static byte[] getQRCodeBytes(String qrData) {
        BufferedImage bufferedImage = getQRCodeBufferedImage(qrData);
        return BufferedImageUtil.bufferedImageToBytes(bufferedImage);
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
}
