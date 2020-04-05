package com.example.ipa.demo;

import com.example.ipa.Domain.User;
import com.example.ipa.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class UserDemo {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final UserService userService;

    public UserDemo(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner userBeanDemo(IUserRepository userRepository ){
        return args -> {
            userRepository.save(new User("jon", 10, 1, "jon@email.com", LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextLong(10))));
            userRepository.save(new User("piotr", 12, 3, "piotr@email.com", LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextLong(10))));
            userRepository.save(new User("henryk", 14, 1, "henryk@email.com", LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextLong(10))));
            userRepository.save(new User("zbyszek", 3, 2, "zbyszek@email.com", LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextLong(10))));
            userRepository.save(new User("seba", 22, 1, "seba@email.com", LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextLong(10))));

            userRepository.findAll().forEach( user -> log.info("User: {}",user));
            userRepository.findAllActiveUsersNative().forEach( user -> log.info("User: {}",user));

            userService.updateUser(666,"jon");

            log.info("{}", userRepository.findDates(LocalDateTime.now().minusDays(10),LocalDateTime.now()));
            userRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
            userRepository.findAll(PageRequest.of(1,10,Sort.Direction.ASC,"name"));



        };
    }
}
