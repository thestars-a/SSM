package com.zhang.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class CreateCaptchaImage {

    private static int WIDTH = 90;
    private static int HEIGHT = 35;
    private static int FONT_SIZE = 20; //字符大小
    private static char[] CaptchaCode; //验证码
    private static BufferedImage CaptchaCodeImage; //验证码图片


    /***
     * 获取验证码图片
     * @return
     */
    public static BufferedImage getCaptchaCodeImage() {
        CaptchaCodeImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = CaptchaCodeImage.getGraphics();
        CaptchaCode = generateCheckCode();
        drawBackground(graphics);
        drawRands(graphics, CaptchaCode);

        graphics.dispose();

        return CaptchaCodeImage;
    }


    /***
     * 获取验证码
     * @return
     */
    public static char[] getCaptchaCode() {
        return CaptchaCode;
    }


    /***
     * 随机生成验证码
     * @return
     */
    private static char[] generateCheckCode() {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * (10 + 26 * 2));
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }


    /***
     * 绘制验证码
     * @param g
     * @param rands
     */
    private static void drawRands(Graphics g, char[] rands) {
        g.setFont(new Font("Console", Font.BOLD, FONT_SIZE));

        for (int i = 0; i < rands.length; i++) {

            g.setColor(getRandomColor());
            g.drawString("" + rands[i], i * FONT_SIZE + 10, 25);
        }
    }

    /***
     * 绘制验证码图片背景
     * @param g
     */
    private static void drawBackground(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 绘制验证码干扰点
        for (int i = 0; i < 200; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            g.setColor(getRandomColor());
            g.drawOval(x, y, 1, 1);

        }
    }

    /***
     * 获取随机颜色
     * @return
     */
    private static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(220), ran.nextInt(220), ran.nextInt(220));
    }

    public static String getBase64(BufferedImage image){
        String base64 = null;
        try {
            //输出流
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", stream);
            base64 = Base64.encode(stream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }
}
