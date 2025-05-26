package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    // informa que não vou fazer escrita no banco, fica mais rápido nesse caso
    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> listaGames = gameListRepository.findAll();

        return listaGames.stream().map(GameListDTO::new).toList();
    }


    // função para atualizar as posições da tabela de position
    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        var obj =  list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        // nova forma de encontrar
        int min = Math.min(sourceIndex, destinationIndex);

        // forma ternaria da operação acima
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        for (int i = min; i <= max; i++){
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(),i);
        }
    }




}
