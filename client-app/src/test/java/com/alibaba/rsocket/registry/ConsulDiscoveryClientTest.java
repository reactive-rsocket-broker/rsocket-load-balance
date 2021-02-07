package com.alibaba.rsocket.registry;

import com.ecwid.consul.v1.ConsulClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.reactive.ConsulReactiveDiscoveryClient;

import java.util.List;

public class ConsulDiscoveryClientTest {

    private static ConsulReactiveDiscoveryClient discoveryClient;

    @BeforeAll
    public static void beforeAll() {
        ConsulClient consulClient = new ConsulClient("127.0.0.1", 8500);
        ConsulDiscoveryProperties properties = new ConsulDiscoveryProperties(new InetUtils(new InetUtilsProperties()));
        discoveryClient = new ConsulReactiveDiscoveryClient(consulClient, properties);
    }

    @Test
    public void testFindServiceServers() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("com-alibaba-calculator").collectList().block();
        Assertions.assertNotNull(serviceInstances);
        for (ServiceInstance serviceInstance : serviceInstances) {
            System.out.println("Host: " + serviceInstance.getHost());
            System.out.println("RSocket Port:" + serviceInstance.getMetadata().get("rsocketPort"));
        }
    }
}
