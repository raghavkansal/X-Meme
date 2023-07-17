package com.crio.starter.repository;

import com.crio.starter.data.MemeEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends MongoRepository<MemeEntity, String> {

  List<MemeEntity> findTop100ByOrderByEntityIdDesc();

  List<MemeEntity> findByEntityId(Long valueOf);

  List<MemeEntity> findByUrl(String url);

  List<MemeEntity> findByNameAndUrlAndCaption(String name, String url, String caption);

  
}
