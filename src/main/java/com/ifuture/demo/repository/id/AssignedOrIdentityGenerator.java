package com.ifuture.demo.repository.id;

import java.io.Serializable;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.IdentityGenerator;

public class AssignedOrIdentityGenerator extends IdentityGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        Serializable id = s.getEntityPersister(null, obj)
            .getClassMetadata().getIdentifier(obj, s);
        return id != null ? id : super.generate(s, obj);
    }
}
