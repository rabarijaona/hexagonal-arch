package net.rabarijaona.hexagonalarch.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import net.rabarijaona.hexagonalarch.domain.model.Cart;
import net.rabarijaona.hexagonalarch.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartServiceTest {

  private CartService cartService;

  @BeforeEach
  public void setUp() {

    Map<String, Product> productsInfo = new HashMap<String, Product>() {{
      put("product1", Product.builder().ref("product1").name("A product 1").price(20).build());
      put("product2", Product.builder().ref("product2").name("A product 2").price(100).build());
      put("product3", Product.builder().ref("product3").name("A product 3").price(300).build());
    }};
    ProductSpi productSpi = productsInfo::get;

    Map<String, Integer> productsStock = new HashMap<String, Integer>() {{
      put("product1", 20);
      put("product2", 3);
      put("product3", 15);
    }};
    StockSpi stockSpi = productsStock::get;

    CartPersistenceSpi cartPersistenceSpi = new CartPersistenceSpi() {
      public final Map<String, Cart> carts = new HashMap<String, Cart>() {{
        put("cart1", Cart.builder().build());
      }};


      @Override
      public Cart getCart(String cartId) {
        return carts.get(cartId);
      }

      @Override
      public Cart updateCart(Cart cart) {
        Cart existingCart = carts.get(cart.getId());
        if (existingCart == null) {
          carts.put(UUID.randomUUID().toString(), cart);
          return cart;
        }
        return existingCart;
      }
    };

    cartService = new CartService(stockSpi, productSpi, cartPersistenceSpi);
  }


  @Test
  public void should_get_cart() {
    // Given
    // When
    Cart cart1 = cartService.getCart("cart1");
    // Then
    assertThat(cart1).isNotNull();
  }

  @Test
  public void should_add_product_and_init_cart() {
    // Given
    // When
    Cart cart1 = cartService.addProductToCart("product1", 2, null);
    // Then
    assertThat(cart1).isNotNull();
    assertThat(cart1.getProducts().stream().map(Product::getRef).collect(Collectors.toList()))
        .contains("product1");
    assertThat(cart1.totalCartAmount()).isEqualTo(40);
  }

  @Test
  public void product_quantity_bought_should_be_recomputed_depending_quantity_available_in_stock() {
    // Given
    // 3 available in stock, check @ setUp method
    String product2 = "product2";

    // When
    Cart cart1 = cartService.addProductToCart(product2, 10, null);

    // Then
    assertThat(cart1).isNotNull();
    Optional<Product> product = cart1.getProducts().stream().findFirst();
    assertThat(product).isNotEmpty();
    int quantityBought = product.get().getQuantityBought();
    assertThat(quantityBought).isEqualTo(3);
  }

  @Test
  public void ten_percent_discount_should_be_applied_if_cart_reach_100() {
    // Given
    // product3 price is 300, check @ setUp method
    String product3 = "product3";

    // When
    Cart cart1 = cartService.addProductToCart(product3, 10, null);

    // Then
    assertThat(cart1).isNotNull();
    assertThat(cart1.totalCartAmount()).isEqualTo(2700);
  }

}