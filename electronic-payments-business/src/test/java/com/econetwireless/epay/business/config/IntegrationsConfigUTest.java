package com.econetwireless.epay.business.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationsConfigUTest {

    private static final Logger log = LoggerFactory.getLogger(IntegrationsConfigUTest.class);

    @InjectMocks
    private IntegrationsConfig integrationsConfig;

    @Mock
    private Environment env;

    @Test
    public void givenValidPropertyFile_whenIntelligentNetworkService_thenReturnJaxWsPort() {
        when(env.getProperty("configs.econetwebservice.ws.serviceInterface"))
                .thenReturn("com.econetwireless.in.webservice.IntelligentNetworkService");

        when(env.getProperty("configs.econetwebservice.ws.wsdl"))
                .thenReturn("wsdls/IntelligentNetworkService.wsdl");
        JaxWsPortProxyFactoryBean jaxWsPortProxyFactoryBean = integrationsConfig.intelligentNetworkService();

        log.info("{}", jaxWsPortProxyFactoryBean);
        assertNotNull(jaxWsPortProxyFactoryBean);
        verify(env, times(6)).getProperty(anyString());
    }

    @Test
    public void givenInvalidPropertyFile_whenIntelligentNetworkService_thenReturnNull() {
        when(env.getProperty("configs.econetwebservice.ws.serviceInterface"))
                .thenReturn("com.econetwireless.in.webservice.IntelligentNetworkService");

        when(env.getProperty("configs.econetwebservice.ws.wsdl"))
                .thenReturn(null);
        JaxWsPortProxyFactoryBean jaxWsPortProxyFactoryBean = integrationsConfig.intelligentNetworkService();

        log.info("{}", jaxWsPortProxyFactoryBean);
        assertNull(jaxWsPortProxyFactoryBean);
        verify(env, times(2)).getProperty(anyString());
    }
}
