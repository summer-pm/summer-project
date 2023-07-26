package com.example.crudmicroservice.chat.repository.mongotemplate;

import com.example.crudmicroservice.chat.dal.ChatUserDAL;
import com.example.crudmicroservice.chat.model.ChatRoom;
import com.example.crudmicroservice.chat.model.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatUserDALImpl implements ChatUserDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ChatUser> getAllRooms(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        query.fields().include("rooms");

        return mongoTemplate.find(query, ChatUser.class);
    }

    @Override
    public void updateUsersRoom(String userId, String roomId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        Update update = new Update();
        update.push("rooms", roomId);

        mongoTemplate.updateFirst(query, update, ChatUser.class);
    }

    @Override
    public void deleteRoomFromUser(String userId, String roomId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        Update update = new Update();
        update.pull("rooms", roomId);

        mongoTemplate.updateFirst(query, update, ChatUser.class);
    }
}
