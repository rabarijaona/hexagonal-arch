package net.rabarijaona.hexagonalarch.domain;

import java.util.Collections;
import net.rabarijaona.hexagonalarch.domain.model.Cart;
import net.rabarijaona.hexagonalarch.domain.model.Product;

public class CartService implements CartApi {

  private final StockSpi stockSpi;
  private final ProductSpi productSpi;
  private final CartPersistenceSpi cartPersistenceSpi;

  public CartService(StockSpi stockSpi, ProductSpi productSpi,
      CartPersistenceSpi cartPersistenceSpi) {
    this.stockSpi = stockSpi;
    this.productSpi = productSpi;
    this.cartPersistenceSpi = cartPersistenceSpi;
  }


  public Cart getCart(String cartId) {
    return cartPersistenceSpi.getCart(cartId);
  }

  public Cart addProductToCart(String productRef, int quantityRequested, String cartId) {
    Product product = productSpi.retrieveProduct(productRef);
    int quantityAvailable = stockSpi.getProductAvailability(productRef);

    int quantityBought = Math.min(quantityAvailable, quantityRequested);
    Product productToAdd = product.toBuilder()
        .quantityAvailable(quantityAvailable)
        .quantityBought(quantityBought)
        .build();

    Cart cart = Cart
        .builder()
        .products(Collections.singletonList(productToAdd))
        .build();
    return cartPersistenceSpi.updateCart(cart);
  }

}
