package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // informa que não vou fazer escrita no banco, fica mais rápido nesse caso
    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> listaGames = gameRepository.findAll();

        List<GameMinDTO> dto = listaGames.stream().map(x -> new GameMinDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game result = gameRepository.findById(id).get();
        GameDTO dto = new GameDTO(result);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> finByList(Long listId) {
        List<GameMinProjection> listaGames = gameRepository.searchByList(listId);

        List<GameMinDTO> dto = listaGames.stream().map(x -> new GameMinDTO(x)).toList();
        return dto;
    }


}
