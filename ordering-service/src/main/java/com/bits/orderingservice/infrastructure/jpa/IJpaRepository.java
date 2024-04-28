package com.bits.orderingservice.infrastructure.jpa;


import com.bits.orderingservice.domain.AbstractAggregateRoot;
import com.bits.orderingservice.domain.DomainObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IJpaRepository<T extends AbstractAggregateRoot<I>, I extends DomainObjectId> extends JpaRepository<T,I>, JpaSpecificationExecutor<T> {

}