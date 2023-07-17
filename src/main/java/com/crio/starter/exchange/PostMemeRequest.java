package com.crio.starter.exchange;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostMemeRequest {

  @NotNull
  private String name;

  @NotNull
  private String url;

  @NotNull
  private String caption;
}
