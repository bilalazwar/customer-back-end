package com.TechTron.customerbackend.service;

import com.TechTron.customerbackend.data.MicroserviceClass.ProductMicro;
import com.TechTron.customerbackend.data.dto.CartItemDto;
import com.TechTron.customerbackend.data.entity.Cart;
import com.TechTron.customerbackend.data.entity.CartItem;
import com.TechTron.customerbackend.repository.CartItemRepository;
import com.TechTron.customerbackend.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public CartItemDto createCartItemEntity(CartItem cartItem){
        //Here we are getting Cart obj having cart_item_id, product_id_fk, cart_id, quantity.
        //Then we are saving them after getting the product unit price and after calculating the total price we save the cartItem entity
        //then we return cartItemDto

        //url,  response, uriVariables
        ProductMicro productMicro = restTemplate.getForObject("http://localhost:8082/products/{id}", ProductMicro.class, cartItem.getProductIdFK());

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setCartItemId(cartItem.getCartItemId());
        cartItemDto.setCartIdFk(cartItem.getCrt().getCartId()); //cart FK
        cartItemDto.setQuantity(cartItem.getQuantity());

        cartItemDto.setProductMicro(productMicro);

        cartItemDto.setUnitPrice(cartItemDto.getProductMicro().getPrice());
        cartItem.setUnitPrice(cartItemDto.getUnitPrice());

        cartItemDto.setTotalPrice(cartItem.getUnitPrice() * cartItemDto.getQuantity());
        cartItem.setTotalPrice(cartItemDto.getTotalPrice());

        cartItemRepository.save(cartItem);

        return cartItemDto;
    }
@Transactional
public CartItemDto createCartItem(CartItem cartItem){

    ProductMicro productMicro = restTemplate.getForObject("http://localhost:8082/products/{id}", ProductMicro.class, cartItem.getProductIdFK());

    if (productMicro != null) {

        cartItem.setUnitPrice(productMicro.getPrice());
        cartItem.setTotalPrice(productMicro.getPrice() * cartItem.getQuantity());

        cartItemRepository.save(cartItem);

        return mapCartItemEntityToDto(cartItem);

    }
    return null;
}


    public Optional<CartItemDto> getCartItemById(int id){
        Optional<CartItem> cartItem = cartItemRepository.findById(id);

        if(cartItem.isPresent()){
            CartItem optionalCartItem = cartItem.get();

            CartItemDto cartItemDto = mapCartItemEntityToDto(optionalCartItem);

            return Optional.of(cartItemDto);  // Return an Optional containing the DTO
        }
        else{
            return Optional.empty();
        }
    }

    public List<CartItemDto> getAllCartItem(){
        List<CartItem> cartItems = cartItemRepository.findAll();

        return mapCartItemEntityToDtoList(cartItems);
    }

    public int findTotalCartItemsForCartId(int cartId){

        return cartItemRepository.countCartItemsByCartId(cartId);
    }

    @Transactional
    public void deleteCartItemById(int id){

        cartItemRepository.deleteById(id);
    }


    public boolean cartItemExist(int id){

        return cartItemRepository.existsById(id);
    }


    @Transactional
    public CartItem updateCartItem(int id,CartItem updatedCartItem){

        if (cartItemExist(id)){

            CartItem outDatedCartItem = cartItemRepository.getReferenceById(id);

            if(outDatedCartItem.getQuantity() > 0 ){

                outDatedCartItem.setQuantity(updatedCartItem.getQuantity());
                outDatedCartItem.setTotalPrice(updatedCartItem.getQuantity() * outDatedCartItem.getUnitPrice());

                return cartItemRepository.save(outDatedCartItem);
            }
            else {
                return null;
            }
        }
        else{
            return null;
        }
    }

    public List<CartItemDto> getAllCartItemsFromCartId(int id){

        List<CartItem> cartItem= cartItemRepository.getAllCartItemsFromCartId(id);

        return mapCartItemEntityToDtoList(cartItem);
    }








    public List<CartItemDto> mapCartItemEntityToDtoList(List<CartItem> cartItems){

        List<CartItemDto> cartItemDto = new ArrayList<>();

        for (CartItem cartItem : cartItems) {

            CartItemDto cartItemDto1 = new CartItemDto();

            cartItemDto1.setCartItemId(cartItem.getCartItemId());
            cartItemDto1.setQuantity(cartItem.getQuantity());
            cartItemDto1.setTotalPrice(cartItem.getTotalPrice());
            cartItemDto1.setUnitPrice(cartItem.getUnitPrice());
            cartItemDto1.setCartIdFk(cartItem.getCrt().getCartId());

            ProductMicro productMicro = new ProductMicro();
            productMicro.setProductId(cartItem.getProductIdFK());
            cartItemDto1.setProductMicro(productMicro);

            cartItemDto.add(cartItemDto1);
        }
        return cartItemDto;
    }

    @Transactional
    public CartItemDto mapCartItemEntityToDto(CartItem cartItem){

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setCartItemId(cartItem.getCartItemId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setTotalPrice(cartItem.getTotalPrice());
        cartItemDto.setUnitPrice(cartItem.getUnitPrice());

//        System.out.println(cartItem.getCrt().getCartId());
//
//        cartItemDto.setCartIdFk(cartItem.getCrt().getCartId());

        ProductMicro productMicro = new ProductMicro();

        productMicro.setProductId(cartItem.getProductIdFK());
        cartItemDto.setProductMicro(productMicro);

        return cartItemDto;

//        private int cartItemId;
//        private int cartIdFk;
//        private int quantity;
//        private float unitPrice;
//        private float totalPrice;

//        private ProductMicro productMicro;


    }


    @Transactional
    public CartItem mapCartItemDtoToEntity(CartItemDto cartItemDto){

        CartItem cartItem = new CartItem();

        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setTotalPrice(cartItemDto.getTotalPrice());
        cartItem.setUnitPrice(cartItemDto.getUnitPrice());

        cartItem.setProductIdFK(cartItemDto.getProductMicro().getProductId());

        Optional<Cart> cart = cartRepository.findById(cartItemDto.getCartIdFk());

        if (cart.isPresent()) {
            cartItem.setCrt(cart.get());
        }

        return cartItem;
    }


    //delete cartItem
    //find by Customer id/ cart id FK
    //Patch CartItem
    //getCartItems when customer_Id_Fk ==5







}
