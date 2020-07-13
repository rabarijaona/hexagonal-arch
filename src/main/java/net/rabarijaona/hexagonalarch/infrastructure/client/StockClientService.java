package net.rabarijaona.hexagonalarch.infrastructure.client;

import net.rabarijaona.hexagonalarch.domain.StockSpi;

public class StockClientService implements StockSpi {
  // You can use here WebClient instance to call distant REST endpoint
  @Override
  public int getProductAvailability(String productRef) {
    return 0;
  }
}
