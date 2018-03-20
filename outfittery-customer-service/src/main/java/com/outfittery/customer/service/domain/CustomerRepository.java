package com.outfittery.customer.service.domain;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{

    Customer findById(long customerId);

}
