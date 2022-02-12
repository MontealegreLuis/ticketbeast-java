package com.montealegreluis.tickebeast.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@EntityScan("com.montealegreluis.ticketbeast.concerts")
@EnableJpaRepositories("com.montealegreluis.ticketbeast.adapters.jpa")
public class RepositoriesConfiguration {}
