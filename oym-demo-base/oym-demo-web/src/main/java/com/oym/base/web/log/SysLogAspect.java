package com.oym.base.web.log;

import com.oym.base.web.log.annotation.LogSign;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


/**
 * 系统日志，切面处理类
 *
 * @author Caiyw
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    private BaseControllerLog baseControllerLog;

    @Autowired(required = false)
    public SysLogAspect(BaseControllerLog baseControllerLog) {
        this.baseControllerLog = baseControllerLog;
    }

    @Pointcut("@annotation(com.oym.base.web.log.annotation.LogSign)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        if (baseControllerLog == null) {
            return point.proceed();
        }
        long startTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - startTime;
        //保存日志
        try {
            saveSysLog(point, startTime, time);
        } catch (Exception e) {
            log.warn("写入执行日志失败", e);
        }
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long startTime, long execTime) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LogSign logSign = signature.getMethod().getAnnotation(LogSign.class);
        if (logSign == null) {
            return;
        }
        LogInfo logInfo = new LogInfo();
        logInfo.setType(logSign.logType())
                .setAction(logSign.logAction())
                .setStartTime(startTime)
                .setExecTime(execTime);
        setMethodName(logInfo, joinPoint);
        if (logSign.recordParam()) {
            setArgs(logInfo, joinPoint.getArgs());
        }
        setIp(logInfo);
        logInfo.setIp(getIp());
        baseControllerLog.insert(logInfo);
    }

    private void setMethodName(LogInfo logInfo, ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName();
        logInfo.setMethod(methodName);
    }

    private void setArgs(LogInfo logInfo, Object[] args) {
        //请求的参数
        try {
            if (args.length > 0 && args[0] instanceof Serializable) {
                String params = args[0].toString();
                logInfo.setParams(params);
            }
        } catch (Exception e) {
            log.warn("入参日志解析失败", e);
        }
    }

    private void setIp(LogInfo logInfo) {
        logInfo.setIp(getIp());
    }

    private String getIp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String ip = null;
        String unknown = "unknown";
        String separator = ",";
        int maxLength = 15;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.warn("获取ip失败 ", e);
        }
        // 使用代理，则获取第一个IP地址
        if (ip != null && ip.length() > maxLength) {
            int idx = ip.indexOf(separator);
            if (idx > 0) {
                ip = ip.substring(0, idx);
            }
        }
        return ip;
    }

}
