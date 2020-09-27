package com.gaku.ddd.identityaccess.domain.model;

import com.gaku.ddd.identityaccess.domain.model.identity.EncryptionService;
import com.gaku.ddd.identityaccess.domain.model.identity.TenantRepository;
import com.gaku.ddd.identityaccess.domain.model.identity.UserRepository;


/**
 * 集中管理   资源库  和 领域服务
 */
public class DomainRegistry {


    public static TenantRepository tenantRepository() {
        return null;
    }

    public static EncryptionService encryptionService() {
        return null;
    }

    public static UserRepository userRepository() {
        return null;
    }
}
