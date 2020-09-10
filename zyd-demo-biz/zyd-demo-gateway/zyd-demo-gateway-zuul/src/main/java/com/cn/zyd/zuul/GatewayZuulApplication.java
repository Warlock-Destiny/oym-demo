package com.cn.zyd.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.RoutesEndpoint;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网关启动类
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayZuulApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GatewayZuulApplication.class, args);
    }

    @Component
    @Primary
    static class DocumentationConfig implements SwaggerResourcesProvider {

        @Autowired
        private RoutesEndpoint re;

        @Autowired
        private HttpServletRequest request;

        @Override
        public List<SwaggerResource> get() {
            /**
             * 自动扫描已添加Swagger的路由
             */
            Map<String, String> routes = re.invoke();
            List<SwaggerResource> resources = new ArrayList<>();

            for (Map.Entry<String, String> entry : routes.entrySet()) {
                String url = entry.getKey();
                // 服务不可用，请稍后再试。(服务没开的情况)
                try {
                    url = "http://" + request.getServerName() + url.substring(0, url.lastIndexOf("/")) + "/v2/api-docs";
                    //探测 暂未开发
//                    String result = HttpUtil.get(url, null, 1000, 1000, "UTF-8");
//                    if (result.contains("服务不可用")) {
//                        continue;
//                    }
                } catch (Exception e) {
                    continue;
                }
                String name = entry.getValue();
                resources.add(swaggerResource(name, url, "1.0"));
            }
            return resources;
        }

        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }

}
