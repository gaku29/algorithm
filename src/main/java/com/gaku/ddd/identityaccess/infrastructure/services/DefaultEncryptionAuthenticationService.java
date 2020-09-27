package com.gaku.ddd.identityaccess.infrastructure.services;

import com.gaku.ddd.identityaccess.domain.model.DomainRegistry;
import com.gaku.ddd.identityaccess.domain.model.identity.*;

/**
 * 领域服务
 */
public class DefaultEncryptionAuthenticationService implements AuthenticationService {
    @Override
    public UserDescriptor authenticate(TenantId aTenantId, String aUserName, String aPassword) {
        if (aTenantId == null){
            throw new IllegalArgumentException("TenantId must not be null.");
        }

        if (aUserName == null){
            throw new IllegalArgumentException("UserName must not be null.");
        }

        if (aPassword == null){
            throw new IllegalArgumentException("Password must not be null.");
        }

        UserDescriptor userDescriptor = null;

        Tenant tenant = DomainRegistry
                .tenantRepository()
                .tenantOfId(aTenantId);

        if (tenant != null && tenant.isActive()){
            String encryptedPassword =
                    DomainRegistry.encryptionService()
                    .encryptedValue(aPassword);

            User user =
                    DomainRegistry
                            .userRepository()
                            .userFromAuthenticCredentials(aTenantId, aUserName, encryptedPassword);

            if (user != null && user.isEnabled()){
                userDescriptor = user.userDescripter();
            }
        }

        return userDescriptor;
    }
}
