package com.example.foodWebSiteDubai.service.serviceImpl;
import com.example.foodWebSiteDubai.dto.request.CartItemDto;
import com.example.foodWebSiteDubai.dto.request.CartMasterDto;
import com.example.foodWebSiteDubai.dto.request.ProductRequest;
import com.example.foodWebSiteDubai.entity.CartItem;
import com.example.foodWebSiteDubai.entity.ProductMaster;
import com.example.foodWebSiteDubai.entity.UserMaster;
import com.example.foodWebSiteDubai.repository.CartItemRepo;
import com.example.foodWebSiteDubai.repository.CartMasterRepo;
import com.example.foodWebSiteDubai.repository.ProductMasterRepo;
import com.example.foodWebSiteDubai.repository.UserMasterRepo;
import com.example.foodWebSiteDubai.service.CartMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartMasterServiceImpl implements CartMasterService {

    @Autowired
    CartMasterRepo cartMasterRepo;

    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    UserMasterRepo userMasterRepo;


    @Override
    public CartMasterDto getAllItems(Long userId) {
        UserMaster userMaster = new UserMaster();
        userMaster.setUserId(userId);
        CartMasterDto cartMasterDto = new CartMasterDto();
        if(cartItemRepo.existsByUserMaster(userMaster)) {
            List<CartItem> cartItemList1 = cartItemRepo.findByUserMaster(userMaster);
            List<CartItemDto> cartItemDtoList = new ArrayList<>();
            Double price = 0.0;
            for (CartItem cartItem : cartItemList1) {
                CartItemDto cartItemDto = new CartItemDto();
                cartItemDto.setCartId(cartItem.getCartId());
                cartItemDto.setQuantity(cartItem.getQuantity());
                cartItemDto.setSubtotal(cartItem.getSubTotal());
                ProductRequest productRequest = new ProductRequest();
                ProductMaster productMaster = cartItem.getProductMaster();
                productRequest.setProductName(productMaster.getProductName());
                productRequest.setProductDescription(productMaster.getProductDescription());
//                productRequest.setProductPrice(productMaster.getProductPrice());

                cartItemDto.setProductRequest(productRequest);
                cartItemDtoList.add(cartItemDto);
                price = price + cartItemDto.getSubtotal();
            }
            cartMasterDto.setCartItemDto(cartItemDtoList);
            cartMasterDto.setTotalPrice(price);
            cartMasterDto.setOrderedAt(new Date());
            cartMasterDto.setUser(userMasterRepo.findById(userId).get());
        }
        return cartMasterDto;
    }

    @Override
    public String deleteByProductId(Long id, Long userId) {
        ProductMaster productMaster = new ProductMaster();
        productMaster.setId(id);
        UserMaster userMaster = new UserMaster();
        userMaster.setUserId(userId);
            if (cartItemRepo.existsByProductMasterAndUserMaster(productMaster, userMaster)) {
                Long cartItem = cartItemRepo.findByProductMasterAndUserMaster(productMaster, userMaster).getCartId();
                cartItemRepo.deleteById(cartItem);
                return "Product Deleted";
            }
        else {
            return "Product not Found";
        }
    }

    @Override
    public String updateProductInCart(Long id, Long userId) {
        UserMaster userMaster = new UserMaster();
        userMaster.setUserId(userId);
        ProductMaster productMaster = new ProductMaster();
        productMaster.setId(id);
        CartItem cartItem =cartItemRepo.findByProductMasterAndUserMaster(productMaster,userMaster);
        if(cartItemRepo.existsByProductMasterAndUserMaster(productMaster,userMaster)){
            CartItem cartItem1 = cartItemRepo.findByProductMasterAndUserMaster(productMaster,userMaster);
            Long qty = cartItem1.getQuantity() + 1;
            cartItem1.setQuantity(qty);
            cartItemRepo.save(cartItem);
        }
        return null;
    }
}
