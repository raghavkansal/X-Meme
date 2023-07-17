package com.crio.starter.repositoryservice;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.PostMemeRequest;
import com.crio.starter.repository.MemeRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemeRepositoryServiceImp implements MemeRepositoryService {
  
  @Autowired
  MemeRepository memeRepository;

  @Autowired
  private Provider<ModelMapper> modelMapperProvider;

  //private final AtomicLong incrementor = new AtomicLong();

  @Override
  public String saveMeme(PostMemeRequest meme) {
    
    String url = meme.getUrl();
    List<MemeEntity> listOfDuplicates = memeRepository.findByNameAndUrlAndCaption(meme.getName(), meme.getUrl(), meme.getCaption());
    if(listOfDuplicates.size() != 0) {
      return "Duplicate Meme";
    }
    
    ModelMapper mapper = modelMapperProvider.get();
    //MemeEntity memee = new MemeEntity(null, null, null);
    MemeEntity entity;
    try {
      entity = mapper.map(meme,MemeEntity.class);
    } catch (Exception e) {
      return e.getMessage();
    }
    //return entity.toString();
    
    //entity.setEntityId(incrementor.incrementAndGet());
    entity.setEntityId(memeRepository.count() + 1);

    MemeEntity savedEntity = memeRepository.save(entity);

    //return memeRepository.findTopByOrderByEntityIdDesc().getUrl();

    return String.valueOf(savedEntity.getEntityId());
    // return savedEntity.toString()+"-----"+entity.toString()+"-----"+meme.toString();
  }

  @Override
  public List<Meme> getMemeList() {

    List<MemeEntity> memeEntityList = memeRepository.findTop100ByOrderByEntityIdDesc();
    ModelMapper mapper = modelMapperProvider.get();
    try {  
      TypeMap<MemeEntity, Meme> propertyMapper = mapper.createTypeMap(MemeEntity.class, Meme.class);
      propertyMapper.addMapping(MemeEntity::getEntityId, Meme::setId);
    } catch (Exception e) {
      //TODO: handle exception
    }
    
    List<Meme> memeList = new ArrayList<>();
    for (MemeEntity memeEntity : memeEntityList) {
      memeList.add(mapper.map(memeEntity,Meme.class));
    }
    return memeList;
    //  catch (Exception e) {
    //   //TODO: handle exception
    //   Meme abc = new Meme();
    //   abc.setCaption(e.getMessage());
    //   List<Meme> array = new ArrayList<>();
    //   array.add(abc);
    //   return array;
    // }
    
  }

  @Override
  public Meme getMeme(long id) {

    List<MemeEntity> memeEntity = memeRepository.findByEntityId(id);

    if(memeEntity.size() == 0) return null;

    ModelMapper mapper = modelMapperProvider.get();

    try {
      TypeMap<MemeEntity, Meme> propertyMapper = mapper.createTypeMap(MemeEntity.class, Meme.class);
      propertyMapper.addMapping(MemeEntity::getEntityId, Meme::setId);
    } catch (Exception e) {
    }

    Meme meme = mapper.map(memeEntity.get(0),Meme.class);
    return meme;
    
  }
    
}
