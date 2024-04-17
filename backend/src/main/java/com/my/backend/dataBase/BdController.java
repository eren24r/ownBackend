package com.my.backend.dataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class BdController {

    @Autowired
    public BdConnection bdConnection = new BdConnection();

    public AnswerFromBd getCntMessageById(String id) {
        AnswerFromBd res = new AnswerFromBd();

        try (Connection connection = bdConnection.getConnection()) {
            String sql = "SELECT COUNT(*) AS message_count FROM deleted_messages WHERE from_user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int messageCount = resultSet.getInt("message_count");
                        if(messageCount == 0){
                            res.setRes("Not find any message with ID " + id);
                        }else {
                            res.setRes("Count of message with userId=" + id + " : " + messageCount);
                        }
                    } else {
                        res.setRes("Not find any user with ID " + id);
                    }
                }
                res.setStatus(StatusEnum.OK);
            } catch (SQLException e) {
                res.setErrors(e.getMessage());
                res.setStatus(StatusEnum.BD_ERROR);

            }
        } catch (SQLException e) {
            res.setErrors(e.getMessage());
            res.setStatus(StatusEnum.CON_ERROR);
        }

        return res;
    }
}
