package com.ifuture.demo.repository.id;

import com.ifuture.demo.util.IDGeneratorUtil;
import java.io.Serializable;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class AssignedOrWorkerIDGenerator implements IdentifierGenerator, Configurable {

    private IDGeneratorUtil idWorker;
    private long workerId = 0;
    private long dataCenterId = 0;
    private String idType = "string";

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry)
        throws MappingException {

        String workerIdStr = properties.getProperty("workerId");
        if (StringUtils.isNotBlank(workerIdStr)) {
            workerId = Long.valueOf(workerIdStr);
        }
        String dataCenterIdStr = properties.getProperty("dataCenterId");
        if (StringUtils.isNotBlank(dataCenterIdStr)) {
            dataCenterId = Long.valueOf(dataCenterIdStr);
        }
        idType = properties.getProperty("idType", "string");

        idWorker = new IDGeneratorUtil(workerId, dataCenterId);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor s,
        Object obj) throws HibernateException {
        Serializable id = s.getEntityPersister(null, obj)
            .getClassMetadata().getIdentifier(obj, s);
        return id != null ? id
            : ("string".equalsIgnoreCase(idType) ? String.valueOf(idWorker.nextId())
                : Long.valueOf(idWorker.nextId()));
    }
}
