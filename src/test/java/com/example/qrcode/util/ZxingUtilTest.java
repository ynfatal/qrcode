package com.example.qrcode.util;

import static org.junit.jupiter.api.Assertions.*;

import com.google.zxing.client.j2se.ImageReader;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Fatal
 * @date 2020/8/6 13:21
 */
@SpringBootTest
class ZxingUtilTest {

    @Test
    void generate() {
        String path = "D:/code/img.png";
        String content = "https://www.imooc.com/video/10315";
        ZxingUtil.generate(path, content);
    }

    @Test
    void decode() {
        String path = "D:/code/img.png";
        String context = ZxingUtil.decode(path);
        System.out.println(context);
    }
}