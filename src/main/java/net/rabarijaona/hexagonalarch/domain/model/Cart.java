package net.rabarijaona.hexagonalarch.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cart {

  private final String id;
  private final List<Product> products;

  public double totalCartAmount() {
    Double cartAmount = products.stream()
        .map(product -> product.getPrice() * product.getQuantityBought())
        .reduce(0.0, Double::sum);
    if (cartAmount > 100) {
      cartAmount = cartAmount * 0.9;
    }
    return cartAmount;
  }
}
