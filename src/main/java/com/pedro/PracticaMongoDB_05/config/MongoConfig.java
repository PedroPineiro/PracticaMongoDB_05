package com.pedro.PracticaMongoDB_05.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.pedro.PracticaMongoDB_05.exceptions.ConnectionDbException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoClient mongoClient() {
        try{
            return MongoClients.create(mongoUri);
        }catch(Exception e){
            throw new ConnectionDbException("Error al conectarse a la base de mongo");
        }
    }
}
