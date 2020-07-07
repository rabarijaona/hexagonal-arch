package net.rabarijaona.hexagonalarch.domain;

import net.rabarijaona.hexagonalarch.domain.model.Product;

public interface ProductSpi {
  Product retrieveProduct(String productRef);
}
