package com.oym.base.controller;

import com.oym.base.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zhangyd
 * @date 2019/10/24 15:58
 * @desc
 */
@Slf4j
public abstract class BaseController implements ValidFun {

    protected static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    protected static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getResponse();
    }

    /**
     * 本方法不应该当作接口踢出去 否则下载文件可能不安全
     */
    protected Result download(String fileName, String filePath) {
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
        ) {
            HttpServletResponse response = getResponse();
            if (response == null) {
                log.error("获取到的response为空!");
                return Result.fail("下载文件失败");
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = bufferedInputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            log.error("关闭流失败");
        }
        return Result.ok();
    }

}
