package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.messages.AirtimeTopupResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditsServiceImplUTest {

    private static final Logger log = LoggerFactory.getLogger(CreditsServiceImplUTest.class);

    @InjectMocks
    private CreditsServiceImpl creditsServiceImpl;

    @Mock
    private ChargingPlatform chargingPlatform;

    @Mock
    private SubscriberRequestDao subscriberRequestDao;

    private AirtimeTopupRequest airtimeTopupRequest;
    private INCreditResponse inCreditResponse;

    @Before
    public void setup() {

        airtimeTopupRequest = new AirtimeTopupRequest();
        inCreditResponse = Mockito.mock(INCreditResponse.class);

        SubscriberRequest subscriberRequest = Mockito.mock(SubscriberRequest.class);


        when(chargingPlatform.creditSubscriberAccount(any(INCreditRequest.class))).thenReturn(inCreditResponse);

        when(subscriberRequestDao.save(any(SubscriberRequest.class))).thenReturn(subscriberRequest);
    }
    @Test
    public void givenInValidAirtimeTopupRequest_whenCredit_thenReturnFailResponse() {
        //given
        when(inCreditResponse.getResponseCode()).thenReturn("400");

        //when
        AirtimeTopupResponse airtimeTopupResponse = creditsServiceImpl.credit(airtimeTopupRequest);

        //then
        log.info("{}", airtimeTopupResponse);
        assertNotNull(airtimeTopupResponse);
        assertEquals("400", airtimeTopupResponse.getResponseCode());
        verify(subscriberRequestDao, times(2)).save(any(SubscriberRequest.class));
        verify(chargingPlatform).creditSubscriberAccount(any(INCreditRequest.class));
    }


    @Test
    public void givenValidAirtimeTopupRequest_whenCredit_thenReturn200() {
        //given
        when(inCreditResponse.getResponseCode()).thenReturn("200");

        //when
        AirtimeTopupResponse airtimeTopupResponse = creditsServiceImpl.credit(airtimeTopupRequest);

        //then
        log.info("{}", airtimeTopupResponse);
        assertNotNull(airtimeTopupResponse);
        assertEquals("200", airtimeTopupResponse.getResponseCode());
        verify(subscriberRequestDao, times(2)).save(any(SubscriberRequest.class));
        verify(chargingPlatform).creditSubscriberAccount(any(INCreditRequest.class));
    }

}
