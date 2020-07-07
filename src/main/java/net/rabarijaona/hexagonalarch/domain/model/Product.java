package net.rabarijaona.hexagonalarch.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Product {

  private final String ref;
  private final String name;
  private final double price;
  private final int quantityAvailable;
  private final int quantityBought;

}
