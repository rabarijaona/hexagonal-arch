package net.rabarijaona.hexagonalarch.domain;

import net.rabarijaona.hexagonalarch.domain.model.Cart;

public interface CartPersistenceSpi {

  Cart getCart(String cartId);

  Cart updateCart(Cart cart);
}
