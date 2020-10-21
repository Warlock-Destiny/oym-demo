package com.oym.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyd
 * @date 2019/12/27 10:57
 * @desc
 */
@Component
@Primary
@Slf4j
public class SwaggerConfig implements SwaggerResourcesProvider {
    private static final String API_URI = "/v2/api-docs";
    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    public SwaggerConfig(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
    }

    @Value("${server.port}")
    private Integer port;


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> {
                            String name = routeDefinition.getId();
                            String url = predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                    .replace("/**", API_URI);
                            if (!checkLive()) {
                                return;
                            }
                            resources.add(swaggerResource(name, url));
                        }));
        return resources;
    }

    /**
     * 如果要处理成只运行的swagger 可以用httputil去探测 或者通过nacos查看正在运行的服务
     */
    private boolean checkLive() {
//        String allUrl = "http://localhost:" + port + url;
//        try {
//            HttpUtil.get(allUrl, null, 1000, 1000, "UTF-8");
//        } catch (Exception e) {
//            log.warn("地址url请求失败:{}", e.getMessage());
//            return false;
//        }
        return true;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}