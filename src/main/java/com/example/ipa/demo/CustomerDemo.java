package com.example.ipa.demo;

import com.example.ipa.Domain.Customer;
import com.example.ipa.repository.ICustomerRpository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDemo {
    @Bean
    public CommandLineRunner customerDemoBean(ICustomerRpository customerRepository){
        return args  -> {
            customerRepository.save(new Customer("Jan","Rokita"));
            customerRepository.save(new Customer("Henryk","asd"));
            customerRepository.save(new Customer("Zbigi","dsa"));
            customerRepository.save(new Customer("wojciech","ooo"));
            customerRepository.save(new Customer("Kolope","Rokita"));

            System.out.println("Customers find all");
            System.out.println(customerRepository.findAll());
        };
    }
}
