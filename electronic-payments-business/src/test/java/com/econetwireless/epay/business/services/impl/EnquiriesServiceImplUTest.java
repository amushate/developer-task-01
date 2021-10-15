package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.messages.AirtimeBalanceResponse;
import com.econetwireless.utils.pojo.INBalanceResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnquiriesServiceImplUTest {

    private static final Logger log = LoggerFactory.getLogger(EnquiriesServiceImplUTest.class);

    @InjectMocks
    private EnquiriesServiceImpl enquiriesService;

    @Mock
    private ChargingPlatform chargingPlatform;

    @Mock
    private SubscriberRequestDao subscriberRequestDao;

    private INBalanceResponse inBalanceResponse;

    @Before
    public void setup() {

        inBalanceResponse = Mockito.mock(INBalanceResponse.class);

        SubscriberRequest subscriberRequest = Mockito.mock(SubscriberRequest.class);


        when(chargingPlatform.enquireBalance(anyString(),anyString())).thenReturn(inBalanceResponse);

        when(subscriberRequestDao.save(any(SubscriberRequest.class))).thenReturn(subscriberRequest);
    }

    @Test
    public void givenValidPartnerCodeAndMsisdn_whenEnquire_thenReturnBalance() {
        //given
        String partnerCode = "validPartnerCode";
        String msisdn = "validMsisdn";
        when(inBalanceResponse.getResponseCode()).thenReturn("200");

        //when
        final AirtimeBalanceResponse airtimeBalanceResponse = enquiriesService.enquire(partnerCode, msisdn);

        //then
        log.info("{}", airtimeBalanceResponse);
        assertEquals("200", airtimeBalanceResponse.getResponseCode());
        verify(subscriberRequestDao, times(2)).save(any(SubscriberRequest.class));
        verify(chargingPlatform).enquireBalance(partnerCode, msisdn);
    }

    @Test
    public void givenInvalidPartnerCodeOrMsisdn_whenEnquire_thenReturnBalance() {
        //given
        String partnerCode = "invalidPartnerCode";
        String msisdn = "invalidMsisdn";
        when(inBalanceResponse.getResponseCode()).thenReturn("400");

        //when
        final AirtimeBalanceResponse airtimeBalanceResponse = enquiriesService.enquire(partnerCode, msisdn);

        //then
        log.info("{}", airtimeBalanceResponse);
        assertEquals("400", airtimeBalanceResponse.getResponseCode());
        verify(subscriberRequestDao, times(2)).save(any(SubscriberRequest.class));
        verify(chargingPlatform).enquireBalance(partnerCode, msisdn);
    }
}
