package net.rabarijaona.hexagonalarch.domain;

import net.rabarijaona.hexagonalarch.domain.model.Cart;

public interface CartApi {

  Cart getCart(String cartId);

  Cart addProductToCart(String productRef, int quantityRequested, String cartId);
}
