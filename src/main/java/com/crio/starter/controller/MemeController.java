package com.crio.starter.controller;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.PostMemeRequest;
import com.crio.starter.exchange.PostMemeResponse;
import com.crio.starter.service.MemeService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memes/")
public class MemeController {

  @Autowired
  private final MemeService memeService;

  @PostMapping
  public ResponseEntity<PostMemeResponse> saveMeme(@Valid @RequestBody 
      PostMemeRequest postMemeRequest) {
    PostMemeResponse response = memeService.saveMeme(postMemeRequest);

    if(response.getId().equals("Duplicate Meme")) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    return ResponseEntity.ok().body(response);
    //return ResponseEntity.ok().body(new PostMemeResponse("raghav--?"));
  }

  @GetMapping
  public ResponseEntity<List<Meme>> getMemeList() {
    List<Meme> getMemeListResponse = memeService.getMemeList();
    return ResponseEntity.ok().body(getMemeListResponse);
  }

  @GetMapping("{id}")
  public ResponseEntity<Meme> getMeme(@PathVariable("id") long id) {
    Meme getMemeResponse = memeService.getMeme(id);
    
    if(getMemeResponse == null) {
      return ResponseEntity.notFound().build();//.status(HttpStatus.NOT_FOUND).build();
    }

    return ResponseEntity.ok().body(getMemeResponse);
  }

}
