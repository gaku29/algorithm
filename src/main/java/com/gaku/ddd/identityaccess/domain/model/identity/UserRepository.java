package com.gaku.ddd.identityaccess.domain.model.identity;

public interface UserRepository {
    User userFromAuthenticCredentials(TenantId aTenantId, String aUserName, String encryptedPassword);
}
