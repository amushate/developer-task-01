package com.econetwireless.epay.business.integrations;

import com.econetwireless.epay.business.integrations.impl.ChargingPlatformImpl;
import com.econetwireless.in.webservice.BalanceResponse;
import com.econetwireless.in.webservice.IntelligentNetworkService;
import com.econetwireless.utils.pojo.INBalanceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChargingPlatformImplUTest {

    private static final Logger log = LoggerFactory.getLogger(ChargingPlatformImplUTest.class);

    @InjectMocks
    private ChargingPlatformImpl chargingPlatformImpl;

    @Mock
    private IntelligentNetworkService intelligentNetworkService;
    
    @Test
    public void givenValidMsisdnAndPartnerCode_whenEnquireBalance_thenReturnSuccess() {
        //given
        String partnerCode = "partnerCode";
        String msisdn = "msisdn";
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setResponseCode("200");
        when(intelligentNetworkService.enquireBalance(partnerCode, msisdn)).thenReturn(balanceResponse);
        //when
        final INBalanceResponse inBalanceResponse = chargingPlatformImpl.enquireBalance(partnerCode, msisdn);

        //then
        log.info("{}", inBalanceResponse);
        assertEquals("200", balanceResponse.getResponseCode());
        verify(intelligentNetworkService).enquireBalance(partnerCode, msisdn);
    }
}
