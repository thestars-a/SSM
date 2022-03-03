package com.zhang.controller.aop;

import com.zhang.pojo.Admin;


import com.zhang.pojo.Car;
import com.zhang.pojo.Driver;
import com.zhang.pojo.LogRecord;
import com.zhang.service.LogRecordService;


import com.zhang.utils.RequestIpUtil;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Component
@Aspect
public class LogAspect {

    @Autowired
    private LogRecordService logRecordService;


    /***
     * 设置切点为注解@OperationLogger标记的方法，有标记的方法触发该AOP，没有标记就没有
     */
    @Pointcut("@annotation(com.zhang.controller.aop.OperationLogger)")
    public void controllerAspect() {
    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========前置通知=========");
        String methodName = joinPoint.getSignature().getName();
        if (methodName.equals("exit")) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Admin user = (Admin) request.getSession().getAttribute("loginUser");
            Class<?> aClass = joinPoint.getTarget().getClass();
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.getName().equals(methodName)
                        && declaredMethod.isAnnotationPresent(OperationLogger.class)
                        && methodName.equals("exit")) {
                    OperationLogger annotation = declaredMethod.getAnnotation(OperationLogger.class);
                    LogRecord logRecord = new LogRecord();
                    logRecord.setUsername(user.getUsername());
                    logRecord.setOptIp(RequestIpUtil.getRemoteHost(request));
                    logRecord.setOperation(annotation.option());
                    logRecord.setContent(annotation.modelName());
                    logRecord.setCreateTime(new Timestamp(new Date().getTime()));
                    logRecordService.insertLog(logRecord);
                }
            }
        }
    }

    /***
     * 后置通知，在将返回值返回时执行
     *
     * @param joinPoint 连接点也就是要增强的方法对象
     */
    @AfterReturning(value = "controllerAspect()",returning = "returnValue")
    public void doAfterReturn(JoinPoint joinPoint,Object returnValue) {
        System.out.println("==========后置通知=========");
        try {
            //拿到要增强方法的方法名称
            String methodName = joinPoint.getSignature().getName();
            if (!methodName.equals("exit")) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                Admin user = (Admin) request.getSession().getAttribute("loginUser");
                /*
                要拿到这个方法，然后获取注解的值，
                就得通过拿到这个方法的类然后获取所有方法和已经获取的方法名对比，从而获取这个方法
                */
                Class<?> aClass = joinPoint.getTarget().getClass();
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    //A.isAnnotationPresent(B.class)判断B注解在不在A方法上，有返回true
                    if (declaredMethod.getName().equals(methodName)
                            && declaredMethod.isAnnotationPresent(OperationLogger.class)) {
                        //获取到这个注解然后拿到注解的参数，也就是我们需要的内容
                        OperationLogger annotation = declaredMethod.getAnnotation(OperationLogger.class);
                        //给数据库实体类赋值，也就是将日志记录到数据库中
                        LogRecord logRecord = new LogRecord();
                        logRecord.setUsername(user.getUsername());
                        //获取登录的ip
                        logRecord.setOptIp(RequestIpUtil.getRemoteHost(request));
                        logRecord.setOperation(annotation.option());

                        //不同的方法不同的描述，用反射来获取方法参数
                        if (methodName.startsWith("deleteCheck")) {
                            Object[] args = joinPoint.getArgs();
                            List arg = (List) args[0];
                            if (arg.get(0).getClass().getName().indexOf("Driver") != -1) {
                                StringBuffer username = new StringBuffer();
                                for (int i = 0; i < arg.size(); i++) {
                                    Driver driver = (Driver) arg.get(i);
                                    if (i == arg.size() - 1) {
                                        username.append(driver.getUsername() + ":" + driver.getIdCard());
                                    } else {
                                        username.append(driver.getUsername() + ":" + driver.getIdCard() + ",");
                                    }
                                }
                                if (returnValue.toString().indexOf("-1")!=-1){
                                    logRecord.setContent(annotation.modelName() + username + "等个人信息失败了");
                                }else {
                                    logRecord.setContent(annotation.modelName() + username + "等个人信息");
                                }
                            } else if (arg.get(0).getClass().getName().indexOf("Car") != -1) {
                                StringBuffer carName = new StringBuffer();
                                for (int i = 0; i < arg.size(); i++) {
                                    Car car = (Car) arg.get(i);
                                    if (i == arg.size() - 1) {
                                        carName.append(car.getCarName() + ":" + car.getLicenseLateNumber());
                                    } else {
                                        carName.append(car.getCarName() + ":" + car.getLicenseLateNumber() + ",");
                                    }
                                }
                                if (returnValue.toString().indexOf("-1")!=-1){
                                    logRecord.setContent(annotation.modelName() + carName + "等车信息失败了");
                                }else {
                                    logRecord.setContent(annotation.modelName() + carName + "等车信息");
                                }
                            }
                        } else if (methodName.startsWith("delete") || methodName.startsWith("update")
                                || methodName.startsWith("save")) {
                            Object[] args = joinPoint.getArgs();
                            //这里我知道只有一个参数所以不用循环
                            Object arg = args[0];
                            Class<?> aClass1 = arg.getClass();
                            if (aClass1.getName().indexOf("Driver") != -1) {
                                Method gun = aClass1.getMethod("getUsername");
                                Method gic = aClass1.getMethod("getIdCard");
                                String username = (String) gun.invoke(arg);
                                String idCard = (String) gic.invoke(arg);
                                if (returnValue.toString().indexOf("-1")!=-1){
                                    logRecord.setContent(annotation.modelName() + username + ":" + idCard + "个人信息失败了");
                                }else {
                                    logRecord.setContent(annotation.modelName() + username + ":" + idCard + "个人信息");
                                }
                            } else if (aClass1.getName().indexOf("Car") != -1) {
                                Method gcn = aClass1.getMethod("getCarName");
                                Method glln = aClass1.getMethod("getLicenseLateNumber");
                                String carName = (String) gcn.invoke(arg);
                                String licenseLateNumber = (String) glln.invoke(arg);
                                if (returnValue.toString().indexOf("-1")!=-1){
                                    logRecord.setContent(annotation.modelName() + carName + ":" + licenseLateNumber + "车信息失败了");
                                }else {
                                    logRecord.setContent(annotation.modelName() + carName + ":" + licenseLateNumber + "车信息");
                                }
                            }else if (aClass1.getName().indexOf("Assign") != -1){
                                Method gdu = aClass1.getMethod("getDriverUsername");
                                Method gcn = aClass1.getMethod("getCarName");
                                String driverUsername = (String) gdu.invoke(arg);
                                String carName = (String) gcn.invoke(arg);
                                if (returnValue.toString().indexOf("-1")!=-1){
                                    logRecord.setContent(annotation.modelName() + driverUsername+"司机和"+carName+"车的分配失败了");
                                }else {
                                    logRecord.setContent(annotation.modelName() + driverUsername+"司机和"+carName+"车的分配");
                                }
                            }
                        } else {
                            logRecord.setContent(annotation.modelName());
                        }
                        logRecord.setCreateTime(new Timestamp(new Date().getTime()));
                        logRecordService.insertLog(logRecord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

