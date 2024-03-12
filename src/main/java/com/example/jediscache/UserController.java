package com.example.jediscache;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final JedisPool jedisPool;

    @GetMapping("/users/{id}/email")
    public String getUserEmail(@PathVariable Long id){

        try( Jedis jedis = jedisPool.getResource()){

            String email = jedis.get("users:%d:email".formatted(id));

            if(email != null){
                return email;
            }

            email = userRepository.findById(id).orElse(User.builder().build()).getEmail();
            jedis.set("users:%d:email".formatted(id),email);
            return email;
        }
    }


}
