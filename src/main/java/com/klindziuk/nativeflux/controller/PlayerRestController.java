/*
 * Copyright (c) 2021. Dandelion tutorials
 */

package com.klindziuk.nativeflux.controller;

import com.klindziuk.nativeflux.model.Player;
import com.klindziuk.nativeflux.service.PlayerService;
import java.util.logging.Level;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class PlayerRestController {

  private final PlayerService playerService;

  @Autowired
  public PlayerRestController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @GetMapping("players/all")
  Flux<Player> getAllPlayers() {
    log.info("Get all players using 'getAllPlayers' query");
    return processWithLog(this.playerService.getAllPlayers());
  }

  @GetMapping("player/{id}")
  Mono<Player> getPlayerById(@PathVariable Long id) {
    log.info("Get player by id using 'getPlayerById' query");
    return processWithLog(this.playerService.getPlayerById(id));
  }

  @GetMapping("players/club/{club}")
  Flux<Player> getPlayersByClub(@PathVariable String club) {
    log.info("Get players by club using 'getPlayersByClub' query");
    return processWithLog(this.playerService.getPlayersByClub(club));
  }

  @GetMapping("players/nationality/{nationality}")
  Flux<Player> getPlayersByNationality(@PathVariable String nationality) {
    log.info("Get players by nationality using 'getPlayersByNationality' query");
    return processWithLog(this.playerService.getPlayersByNationality(nationality));
  }

  @PostMapping("player/add")
  Mono<Player> addPlayer(@RequestBody Player player) {
    log.info("Add player using 'addPlayer' mutation");
    return processWithLog(this.playerService.addPlayer(player));
  }

  @PostMapping("player/update/{id}")
  Mono<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
    log.info("Updating player using 'updatePlayer' mutation");
    return processWithLog(this.playerService.updatePlayer(id, player));
  }

  @DeleteMapping("player/delete/{id}")
  Mono<Player> deletePlayerById(@PathVariable Long id) {
    log.info("Delete player using 'deletePlayerById' mutation");
    return processWithLog(this.playerService.deletePlayerById(id));
  }

  private <T> Mono<T> processWithLog(Mono<T> monoToLog) {
    return monoToLog
        .log("PlayerRestController.", Level.INFO, SignalType.ON_NEXT, SignalType.ON_COMPLETE);
  }

  private <T> Flux<T> processWithLog(Flux<T> fluxToLog) {
    return fluxToLog
        .log("PlayerRestLController.", Level.INFO, SignalType.ON_NEXT, SignalType.ON_COMPLETE);
  }
}
