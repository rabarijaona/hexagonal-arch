package net.rabarijaona.hexagonalarch.infrastructure.client;

import net.rabarijaona.hexagonalarch.domain.ProductSpi;
import net.rabarijaona.hexagonalarch.domain.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductClientService implements ProductSpi {
  // You can use here WebClient instance to call distant REST endpoint
  @Override
  public Product retrieveProduct(String productRef) {
    return null;
  }
}
