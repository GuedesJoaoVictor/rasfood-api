package com.csi.api.rasfood.repository.projection;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface MenuProjection {
    Long getId();
    String getName();
    String getDescription();
    Long getPrice();
    String getCategory();
}
