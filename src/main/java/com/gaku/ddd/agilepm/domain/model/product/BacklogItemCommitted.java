package com.gaku.ddd.agilepm.domain.model.product;

import com.gaku.ddd.agilepm.domain.model.DomainEvent;

import java.util.Date;

/**
 * 领域事件
 */
public class BacklogItemCommitted implements DomainEvent {

    private Date occurredOn;
    private BacklogItemId backlogItemId;
    private SprintId sprintId;
    private TenantId tenantId;

    public BacklogItemCommitted(Date occurredOn) {
        this.occurredOn = occurredOn;
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }
}
