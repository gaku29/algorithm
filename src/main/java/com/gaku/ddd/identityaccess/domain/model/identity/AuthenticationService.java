package com.gaku.ddd.identityaccess.domain.model.identity;

/**
 * 领域服务
 */
public interface AuthenticationService {


    public UserDescriptor authenticate(TenantId aTenantId, String aUserName, String aPassword);
}
