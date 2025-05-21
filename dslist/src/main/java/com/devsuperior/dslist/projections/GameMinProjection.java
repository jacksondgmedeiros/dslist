package com.devsuperior.dslist.projections;

public interface GameMinProjection {
    // é necessário criar igual os tipos e nomes que foram colocados na consulta
    Long getId();
    String getTitle();
    Integer getYear();
    String getImgUrl();
    String getShortDescription();
    Integer getPosition();
}
