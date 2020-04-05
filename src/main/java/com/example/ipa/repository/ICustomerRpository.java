package com.example.ipa.repository;

import com.example.ipa.Domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ICustomerRpository extends CrudRepository<Customer,Long> {

}
