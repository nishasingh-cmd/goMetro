package com.gometro.entity;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class CustomIdGenerator extends SequenceStyleGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        if (object instanceof User user && user.getId() != null) {
            return user.getId();
        }
        return super.generate(session, object);
    }
}
