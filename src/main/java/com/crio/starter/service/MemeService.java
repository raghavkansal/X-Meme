package com.crio.starter.service;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.PostMemeRequest;
import com.crio.starter.exchange.PostMemeResponse;
import com.crio.starter.repositoryservice.MemeRepositoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemeService {

  @Autowired
  private final MemeRepositoryService memeRepositoryService;

  public PostMemeResponse saveMeme(PostMemeRequest postMemeRequest) {
    String id = memeRepositoryService.saveMeme(postMemeRequest);
    return new PostMemeResponse(id);
  }

  public List<Meme> getMemeList() {
    List<Meme> memeList = memeRepositoryService.getMemeList();
    return memeList;
  }

public Meme getMeme(long id) {
    Meme meme = memeRepositoryService.getMeme(id);
    return meme;
}

  

  
}
