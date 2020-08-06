package com.example.qrcode.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Fatal
 * @date 2020/8/6 13:08
 */
@Slf4j
public class ZxingUtil {

    /**
     * 生成二维码
     * @param path 二维码本地生成的路径
     * @param content 二维码携带的参数
     */
    public static void generate(String path, String content) {
        BitMatrix bitMatrix = getQRCodeBitMatrix(content);
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, "png", new File(path).toPath());
        } catch (IOException e) {
            log.error("【二维码生成】生成失败");
            e.printStackTrace();
        }
    }

    /**
     * 供上传 OSS 使用
     * @param content 二维码携带的参数
     * @return
     */
    public static byte[] getQRCodeBytes(String content) {
        BitMatrix bitMatrix = getQRCodeBitMatrix(content);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        return BufferedImageUtil.bufferedImageToBytes(bufferedImage);
    }

    /**
     * 获取生成二维码的源数据 BitMatrix
     * @param content 二维码携带的参数
     * @return
     */
    public static BitMatrix getQRCodeBitMatrix(String content) {
        Integer width = 300;
        Integer height = 300;

        // 定义二维码的参数
        Map map = new HashMap<>();
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        map.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitMatrix;
    }

}
