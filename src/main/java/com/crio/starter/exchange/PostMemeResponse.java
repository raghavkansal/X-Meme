package com.crio.starter.exchange;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostMemeResponse {
  
  @NotNull
  private String id;    
}
