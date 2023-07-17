package com.crio.starter.repositoryservice;

import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.PostMemeRequest;
import java.util.List;


public interface MemeRepositoryService {
  String saveMeme(PostMemeRequest meme);

  List<Meme> getMemeList();

  Meme getMeme(long id);
}
