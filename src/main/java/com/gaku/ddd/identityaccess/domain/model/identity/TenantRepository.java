package com.gaku.ddd.identityaccess.domain.model.identity;

public interface TenantRepository {
    Tenant tenantOfId(TenantId aTenantId);
}
