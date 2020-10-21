package com.oym.component.util;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author zyd
 * @date 2020/3/18 8:34
 * @desc
 */
@Slf4j
public class HessianUtil {

    /**
     * Hessian实现序列化
     */
    public static byte[] serialize(Object o) {
        if (o == null) {
            return null;
        }
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {
            // Hessian的序列化输出
            HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
            hessianOutput.writeObject(o);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Hessian实现反序列化
     */
    public static <T> T deserialize(byte[] bytes, Class<T> tClass) {
        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ) {
            // Hessian的反序列化读取对象
            HessianInput hessianInput = new HessianInput(byteArrayInputStream);
            return (T) hessianInput.readObject(tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
