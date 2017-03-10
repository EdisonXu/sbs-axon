package com.edi.poc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Edison Xu on 2017/3/10.
 */
@RepositoryRestResource(collectionResourceRel = "bank", path = "bank")
public interface BankAccountRepository extends CrudRepository<BankAccount, AccountId> {

}
