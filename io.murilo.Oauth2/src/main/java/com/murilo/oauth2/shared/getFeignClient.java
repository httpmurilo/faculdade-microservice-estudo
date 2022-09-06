package com.murilo.oauth2.shared;

import com.murilo.oauth2.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name="api-core", path = "/users")
public interface getFeignClient {

    @GetMapping(value = "/search")
    ResponseEntity<User> findByEmail(@RequestParam String email);
}
