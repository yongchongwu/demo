package com.ifuture.demo.repository.id;

import com.ifuture.demo.util.UUIDUtils;
import java.io.Serializable;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class AssignedOrUUIDGenerator implements IdentifierGenerator, Configurable {

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry)
        throws MappingException {

    }

    @Override
    public Serializable generate(SharedSessionContractImplementor s,
        Object obj) throws HibernateException {
        Serializable id = s.getEntityPersister(null, obj)
            .getClassMetadata().getIdentifier(obj, s);
        return id != null ? id : UUIDUtils.getUUID();
    }
}
