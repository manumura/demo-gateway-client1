package com.example.demo.controller;

import com.example.demo.dto.TestPayload;
import com.example.demo.dto.Topic;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/config/topics")
  public ResponseEntity<List<Topic>> getTopics() {
    final List<Topic> topics = Arrays.asList(new Topic(1, "JOB_QUEUE"));
    System.out.println("client1 topics " + topics);
    return ResponseEntity.ok(topics);
  }

  @GetMapping("/config/topics/{topicName}")
  public ResponseEntity<String> getTopic(@PathVariable String topicName) {
    System.out.println("client1 Test OK " + topicName);
    return ResponseEntity.ok("client1 test OK " + topicName);
  }

  @GetMapping("/config/types/{typeId}")
  public ResponseEntity<String> getConfigType(@RequestHeader(value = "Authorization", required = false) String token,
                                              @RequestHeader(value = "X-client-name", required = false) String username,
                                              @PathVariable Integer typeId) {
    System.out.println("client1 Test OK " + typeId + " - username: " + username);
    return ResponseEntity.ok("client1 test OK " + typeId + " - token " + token + " - username: " + username);
  }

  @PostMapping("/config/types/{typeId}")
  public ResponseEntity<String> getConfigType(@RequestHeader(value = "Authorization", required = false) String token,
      @PathVariable Integer typeId,
      @RequestBody TestPayload payload) {
    System.out.println("POST client1 Test OK " + typeId + " " + payload);
    return ResponseEntity.ok("client1 test OK " + typeId + " - token " + token + " - payload " + payload);
  }

  @GetMapping("/config/test/exception")
  public ResponseEntity<String> getException(@RequestHeader(value = "Authorization", required = false) String token) {
    System.out.println("client1 Test exception " + token);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Test exception");
  }
}
