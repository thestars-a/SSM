package com.zhang.controller;

import com.zhang.controller.aop.OperationLogger;
import com.zhang.pojo.Admin;
import com.zhang.service.LoginService;
import com.zhang.utils.CreateCaptchaImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private LoginService loginService;

    /***
     * 登录请求
     * @param username
     * @param password
     * @param captcha
     * @param request
     * @return
     */
    @PostMapping("/login")
    @OperationLogger(modelName = "登录了系统",option = "/system/login")
    public Map<String, Object> login(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("captcha") String captcha,
                                     @RequestParam("isNoPWLogin") String isNoPWLogin,
                                     HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        String captchaCode = (String) session.getAttribute("CaptchaCode");
        Admin loginAdmin = loginService.login(username, password);
        String power = loginService.getPower(username, password);
        if (loginAdmin != null && captcha.equals(captchaCode)) {
            session.setAttribute("loginUser", loginAdmin);
            Cookie powerCookie = new Cookie("power", power);
            powerCookie.setPath("/");
            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setPath("/");
            if ("true".equals(isNoPWLogin)) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                loginService.updateUUID(username,uuid);
                powerCookie.setMaxAge(3 * 24 * 60 * 60);
                usernameCookie.setMaxAge(3 * 24 * 60 * 60);
                Cookie loginState = new Cookie("loginState",uuid);
                loginState.setPath("/");
                loginState.setMaxAge(3 * 24 * 60 * 60);
                response.addCookie(loginState);
            }
            response.addCookie(powerCookie);
            response.addCookie(usernameCookie);
            result.put("success", true);
        } else {
            result.put("success", false);
            if (!captcha.equals(captchaCode)) {
                result.put("reason", "验证码错误");
            } else if (loginAdmin == null) {
                result.put("reason", "用户名或密码错误");
            }
        }
        return result;
    }


    /***
     * 退出登录，并且清除登录要的cookie
     * @param response
     * @return
     */
    @GetMapping("/exit")
    @OperationLogger(modelName = "退出了登录",option = "/system/exit")
    public void exit(HttpServletResponse response,HttpServletRequest request) throws IOException {
        Cookie powerCookie = new Cookie("power", "");
        powerCookie.setMaxAge(0);
        powerCookie.setPath("/");
        response.addCookie(powerCookie);
        Cookie usernameCookie = new Cookie("username", "");
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);
        Cookie loginState = new Cookie("loginState", "");
        loginState.setMaxAge(0);
        loginState.setPath("/");
        response.addCookie(loginState);
        /*这块不删除session 退出登录不关闭浏览器再去点击别的页面拦截器就不拦截了*/
        request.getSession().invalidate();
        response.sendRedirect("/ssm");
    }


    /***
     * 登录页面验证码图片请求
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/getCaptchaImage")
    public Map<String, Object> getCaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> result= new HashMap<String, Object>();
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");
        // 验证码图片
        BufferedImage CaptchaCodeImage = CreateCaptchaImage.getCaptchaCodeImage();
        String base64 = CreateCaptchaImage.getBase64(CaptchaCodeImage);
        result.put("base64",base64);
        // 验证码
        String CaptchaCode = String.valueOf(CreateCaptchaImage.getCaptchaCode());
        // 将验证码图片输出到登录页面,过时写法
        /*try {
            ImageIO.write(CaptchaCodeImage, "PNG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        // 存储验证码Session
        request.getSession().setAttribute("CaptchaCode", CaptchaCode);
        return result;
    }

    @PostMapping("/pwIsRight")
    public Map<String, Object> pwIsRight(@RequestParam("username") String username) {
        Map<String,Object> result= new HashMap<String, Object>();
        String pw = loginService.pwIsRight(username);
        result.put("pw",pw);
        return result;
    }
    @PostMapping("/updatePW")
    public Map<String, Object> updatePW(@RequestParam("username") String username,@RequestParam("password") String password) {
        Map<String,Object> result= new HashMap<String, Object>();
        int i = loginService.updatePW(username,password);
        result.put("code",i);
        return result;
    }

}
