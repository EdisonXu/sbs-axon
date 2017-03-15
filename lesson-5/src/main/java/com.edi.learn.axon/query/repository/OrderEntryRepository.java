package com.edi.learn.axon.query.repository;

import com.edi.learn.axon.query.entries.OrderEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Edison Xu on 2017/3/15.
 */
@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderEntryRepository extends MongoRepository<OrderEntry, String> {
}
