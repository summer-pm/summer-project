package com.example.crudmicroservice.chat.repository.mongotemplate;

import com.example.crudmicroservice.chat.dal.ChatRoomDAL;
import com.example.crudmicroservice.chat.model.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRoomDALImpl implements ChatRoomDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ChatRoom findChatRoomByUsersContaining(List<String> users) {
        Query query = new Query();
        query.addCriteria(Criteria.where("users").all(users));
        return mongoTemplate.findOne(query, ChatRoom.class);
    }
}
