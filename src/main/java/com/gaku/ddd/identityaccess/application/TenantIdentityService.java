/**
 *
 * 应用程序
 * 应用服务
 * 应用层
 */
package com.gaku.ddd.identityaccess.application;

import com.gaku.ddd.identityaccess.domain.model.identity.Tenant;
import com.gaku.ddd.identityaccess.domain.model.identity.TenantId;

public interface TenantIdentityService {

    public Tenant tenant(TenantId aTenantId);

    public void activateTenant(TenantId aTenantId);

    public void deactiveTenant(TenantId aTenantId);

}
