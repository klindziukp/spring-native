/*
 * Copyright (c) 2021. Dandelion tutorials
 */

package com.klindziuk.nativeflux.repository;

import com.klindziuk.nativeflux.model.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PlayerRepository extends ReactiveCrudRepository<Player, Long> {

  Flux<Player> findByClub(String club);
  Flux<Player> findByNationality(String nationality);

}