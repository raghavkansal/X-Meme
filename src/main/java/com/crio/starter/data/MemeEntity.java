package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Document(collection = "memes")
public class MemeEntity {
  
  @Id
  private String id;

  private long entityId;

  @NonNull
  private String name;

  @NonNull
  private String url;

  @NonNull
  private String caption;

}
