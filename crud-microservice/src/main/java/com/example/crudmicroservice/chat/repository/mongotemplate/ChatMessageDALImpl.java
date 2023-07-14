package com.example.crudmicroservice.chat.repository.mongotemplate;

import com.example.crudmicroservice.chat.dal.ChatMessageDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ChatMessageDALImpl implements ChatMessageDAL {

    @Autowired
    private MongoTemplate mongoTemplate;


    // TODO Написать запросы в бд через MongoTemplate
}
