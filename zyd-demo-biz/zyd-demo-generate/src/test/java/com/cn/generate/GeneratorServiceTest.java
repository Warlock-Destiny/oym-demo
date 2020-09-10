package com.cn.generate;

import com.cn.generate.service.GeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zyd
 * @date 2020/3/30 14:46
 * @desc
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainTest.class)
public class GeneratorServiceTest {

    @Autowired
    private GeneratorService generatorService;

    @Test
    public void generate() {
        final String outPutFile = "D:/generate/test.zip";
        final String packageName = "com.ctff.cloud";
        final String moduleName = "oa";
        final String tableSchema = "ctff-cloud";
        final String[] tableNameArray = {"biz_contract", "biz_contract_device", "biz_contract_fault_level"};
        try (
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outPutFile));
        ) {
            byte[] bytes = generatorService.generatorCode(tableSchema, packageName, moduleName, tableNameArray);
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
