package com.gaku.ddd.identityaccess.domain.model.identity;

/**
 * 领域服务
 */
public interface EncryptionService {
    String encryptedValue(String aPassword);
}
