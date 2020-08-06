# qrcode
## qrcode
参考链接：https://www.imooc.com/video/10317
生成 ： http://www.swetake.com/qrcode/java/qr_java.html
读取 ： https://zh.osdn.net/projects/qrcode/

使用日本的QRcode生成二维码，但是只有jar包，所以只能把jar做成maven，命令如下：
> qrcodew 表示写，或者生成；qrcoder 表示读取。
>
```
mvn install:install-file -Dfile=D:\tool\qrcode\qrcode\lib\qrcode.jar -DgroupId=qrcode -DartifactId=qrcodew -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=D:\tool\qrcode\qrcode_java0.50beta10\lib\Qrcode.jar -DgroupId=qrcode -DartifactId=qrcoder -Dversion=1.0 -Dpackaging=jar
```

## zxing
加入两个依赖就行
```xml
<dependency>
  <groupId>com.google.zxing</groupId>
  <artifactId>core</artifactId>
  <version>3.4.0</version>
</dependency>
<dependency>
  <groupId>com.google.zxing</groupId>
  <artifactId>javase</artifactId>
  <version>3.4.0</version>
</dependency>
```