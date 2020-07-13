package net.rabarijaona.hexagonalarch.app;

import lombok.AllArgsConstructor;
import net.rabarijaona.hexagonalarch.domain.CartService;
import net.rabarijaona.hexagonalarch.domain.model.Cart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CartController {

  private CartService cartService;

  public CartRest getCart(String cartId) {
    Cart cart = cartService.getCart(cartId);
    return toCartRest(cart);
  }

  private CartRest toCartRest(Cart cart) {
    return new CartRest();
  }

  public CartRest addProductToCart(String productRef, int quantityRequested, String cartId) {
    Cart cart = cartService.addProductToCart(productRef, quantityRequested, cartId);
    return toCartRest(cart);
  }
}
