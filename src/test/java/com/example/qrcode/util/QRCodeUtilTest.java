package com.example.qrcode.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Fatal
 * @date 2020/8/6 12:08
 */
@SpringBootTest
class QRCodeUtilTest {

    @Test
    void generate() {
        String path = "D:/code/qrcode.png";
        String qrData = "https://www.imooc.com/video/10317";
        QRCodeUtil.generate(path, qrData);
    }

    @Test
    void decode() {
        String path = "D:/code/qrcode.png";
        String qrData = QRCodeUtil.decode(path);
        System.out.println(qrData);
    }
}