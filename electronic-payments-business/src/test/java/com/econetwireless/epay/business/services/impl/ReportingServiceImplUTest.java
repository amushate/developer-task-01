package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportingServiceImplUTest {

    @InjectMocks
    private ReportingServiceImpl reportingService;

    @Mock
    private SubscriberRequestDao subscriberRequestDao;

    @Test
    public void givenPartnerCode_whenFindSubscriberRequestsByPartnerCode_thenInvokeSubscriberRequestDao() {
        //given
        String partnerCode = "partnerCode";
        List<SubscriberRequest> subscriberRequests = new ArrayList<>();
        when(subscriberRequestDao.findByPartnerCode(partnerCode)).thenReturn(subscriberRequests);

        //when
        List<SubscriberRequest> subscriberRequestList = reportingService.findSubscriberRequestsByPartnerCode(partnerCode);

        //then
        assertEquals(subscriberRequests, subscriberRequestList);
        verify(subscriberRequestDao).findByPartnerCode(partnerCode);

    }

}
