### Developer Task 1 

* You will receive instructions from Cassava Smartech on what's required.


###Bug Fixes
1. Fix LOGGER in MobileNumberUtils.java
2. Fix @PreInsert to @PrePersist in SubscriberRequest.java
3. Fix query to match entity:  "select r from Request r where" to "select r from request r where" in SubscriberRequest.java
4. Remove this(super); from the PartnerCodeValidatorImpl
5. Fix subscriberRequestDao.persist to subscriberRequestDao.save in CreditsServiceImpl.java
6. Change subscriberRequestDao.persist to subscriberRequestDao.save in EnquiriesServiceImpl.java
7. Fix Constructor bean autowire add missing @PathVariable in EpayResource.java
8. Fix Http binding in IntelligentNetworkServiceImpl.java
9. Fix Enum constructor ResponseCode assignment
10. Fix: WebParam for partnerCode in IntelligentNetworkService
11. Add logger config in src/main/test/resources for debug logging console
12. Add git ignore to for target folders
13. Add connection pooling