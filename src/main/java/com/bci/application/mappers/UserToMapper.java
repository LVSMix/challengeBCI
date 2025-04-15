package com.bci.application.mappers;

import com.bci.application.port.UserRequest;
import com.bci.application.port.UserResponse;
import com.bci.domain.model.Phone;
import com.bci.domain.model.User;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserToMapper {

    public User toUser(UserRequest request, String token) {
        return new User(UUID.randomUUID(),request.name,
                request.email,
                request.password,
                request.phones.stream()
                        .map(phone -> new Phone(phone.number, phone.citycode, phone.contrycode))
                        .toList(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),token,true);
    }

    public UserResponse toResponse(User result) {
        return new UserResponse(result.getId(), result.getCreated(), result.getModified(), result.getLastLogin(), result.getToken(), result.isActive());
    }
}
