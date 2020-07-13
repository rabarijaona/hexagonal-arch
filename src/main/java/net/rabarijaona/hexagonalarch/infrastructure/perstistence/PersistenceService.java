package net.rabarijaona.hexagonalarch.infrastructure.perstistence;

import lombok.AllArgsConstructor;
import net.rabarijaona.hexagonalarch.domain.CartPersistenceSpi;
import net.rabarijaona.hexagonalarch.domain.model.Cart;
import net.rabarijaona.hexagonalarch.infrastructure.perstistence.model.CartP;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersistenceService implements CartPersistenceSpi {

  // You can use any repository you like here
  private CartRepository cartRepository;

  @Override
  public Cart getCart(String cartId) {
    CartP cartP = cartRepository.findCartById(cartId);
    return toCart(cartP);
  }

  @Override
  public Cart updateCart(Cart cart) {
    CartP cartP = cartRepository.saveCart(toCartP(cart));
    return toCart(cartP);
  }

  private CartP toCartP(Cart cart) {
    // Map here the cart from domain to cart persistence
    return new CartP();
  }

  private Cart toCart(CartP cartPById) {
    // Map here the cart from persistence to domain cart
    return Cart.builder().build();
  }
}
