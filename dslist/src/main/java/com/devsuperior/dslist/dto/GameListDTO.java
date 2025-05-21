package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;

public class GameListDTO {

    private Long id;
    private String name;
    public GameListDTO() {

    }
    public GameListDTO(GameList entity) {
        // pode ser feito assim ou com o Beans, no outro caso, usei pois tinhas muitos atributos da entity
        id = entity.getId();
        name = entity.getName();
    }

// nesse caso que não usou o Beans, cria so os getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
