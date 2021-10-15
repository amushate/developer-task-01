package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.dao.requestpartner.api.RequestPartnerDao;
import com.econetwireless.epay.domain.RequestPartner;
import com.econetwireless.utils.execeptions.EpayException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartnerCodeValidatorImplUTest {

    @InjectMocks
    private PartnerCodeValidatorImpl partnerCodeValidator;

    @Mock
    private RequestPartnerDao requestPartnerDao;

    @Test
    public void givenValidPartnerCode_whenValidatePartnerCode_thenReturnTrue() {
        //given
        String partnerCode = "validPartnerCode";
        RequestPartner requestPartner = new RequestPartner();
        when(requestPartnerDao.findByCode(partnerCode)).thenReturn(requestPartner);

        //when
        boolean isValid = partnerCodeValidator.validatePartnerCode(partnerCode);

        //then
        assertTrue(isValid);
        verify(requestPartnerDao).findByCode(partnerCode);
    }

    @Test(expected = EpayException.class)
    public void givenInValidPartnerCode_whenValidatePartnerCode_thenReturnFalse() {
        //given
        String partnerCode = "InvalidPartnerCode";
        when(requestPartnerDao.findByCode(partnerCode)).thenReturn(null);

        //when then throws
        partnerCodeValidator.validatePartnerCode(partnerCode);

    }
}
