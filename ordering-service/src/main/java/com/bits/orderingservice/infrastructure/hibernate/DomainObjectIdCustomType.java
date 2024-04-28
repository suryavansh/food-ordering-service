package com.bits.orderingservice.infrastructure.hibernate;


import com.bits.orderingservice.domain.DomainObjectId;
//import com.bits.sharedkernel.domain.DomainObjectIdTest;

import org.hibernate.id.ResultSetIdentifierConsumer;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DomainObjectIdCustomType<T extends DomainObjectId> extends AbstractSingleColumnStandardBasicType<T>
        implements ResultSetIdentifierConsumer {

    public DomainObjectIdCustomType(DomainObjectIdTypeDescriptor<T> domainObjectIdTypeDescriptor) {
        super(VarcharTypeDescriptor.INSTANCE, domainObjectIdTypeDescriptor);

    }

    @Override
    public Serializable consumeIdentifier(ResultSet resultSet) {
        try {
            var id = resultSet.getString(1);
            return (Serializable) this.getJavaTypeDescriptor().fromString(id);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not extract ID from ResultSet", ex);
        }
    }

    @Override
    public String getName() {
        return this.getJavaTypeDescriptor().getJavaType().getSimpleName();
    }
}