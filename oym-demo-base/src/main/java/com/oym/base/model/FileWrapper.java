package com.oym.base.model;

import lombok.Data;

/**
 * @author zyd
 * @date 2019/10/27 12:11
 * @desc 文件包装类
 */
@Data
public class FileWrapper {
    private String fileName;
    private String filePath;
    private String fileType;
}
