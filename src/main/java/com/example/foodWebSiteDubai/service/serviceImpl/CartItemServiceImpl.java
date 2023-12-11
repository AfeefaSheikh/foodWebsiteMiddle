package com.example.foodWebSiteDubai.service.serviceImpl;

import com.example.foodWebSiteDubai.dto.request.BuyNowDto;
import com.example.foodWebSiteDubai.dto.request.CartMasterDto;

import com.example.foodWebSiteDubai.entity.CartItem;

import com.example.foodWebSiteDubai.entity.ProductMaster;
import com.example.foodWebSiteDubai.entity.UserMaster;
import com.example.foodWebSiteDubai.repository.CartItemRepo;
import com.example.foodWebSiteDubai.repository.CartMasterRepo;
import com.example.foodWebSiteDubai.repository.ProductMasterRepo;
import com.example.foodWebSiteDubai.repository.UserMasterRepo;
import com.example.foodWebSiteDubai.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.foodWebSiteDubai.constant.ErrorConstantMessage.*;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    ProductMasterRepo productMasterRepo;

    @Autowired
    UserMasterRepo userMasterRepo;

    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    CartMasterRepo cartMasterRepo;
    @Override
    public String addToCart(Long quantity, Long id, Long userId) {
        Optional<UserMaster> userMaster = userMasterRepo.findById(userId);
        UserMaster userMaster1 = userMaster.get();
        Optional<ProductMaster> productMaster = productMasterRepo.findById(id);
        if(productMaster.isEmpty()){
            throw new RuntimeException(PRODUCT_ID_NOT_PRESENT);
        }
        ProductMaster productMaster1 = productMaster.get();
        if (cartItemRepo.existsByProductMasterAndUserMaster(productMaster1, userMaster1)){
            CartItem cartItem2 = cartItemRepo.findByProductMasterAndUserMaster(productMaster1,userMaster1);
            Long qty = cartItem2.getQuantity() + quantity;
            cartItem2.setQuantity(qty);
            cartItemRepo.save(cartItem2);
        }
        else {
            CartItem cartItem1 = new CartItem();
            cartItem1.setUserMaster(userMaster1);
            cartItem1.setQuantity(quantity);
//            cartItem1.setSubTotal(quantity * productMaster1.getProductPrice());
            cartItem1.setProductMaster(productMaster1);
            cartItemRepo.save(cartItem1);
        }
        return "Item added to cart";
    }

    @Override
    public String buyNow(Long userId, Long id) {
        Optional<UserMaster> userMaster = userMasterRepo.findById(userId);
        UserMaster userMaster1 = userMaster.get();
        Optional<ProductMaster> productMaster = productMasterRepo.findById(id);
        if(productMaster.isEmpty()){
            throw new RuntimeException(PRODUCT_ID_NOT_PRESENT);
        }
        BuyNowDto buyNowDto = new BuyNowDto();
        CartMasterDto cartMasterDto = new CartMasterDto();
        CartItem cartItem = new CartItem();
//        buyNowDto.setUserMaster(userMaster1);
//        buyNowDto.setTotalPrice(cartMasterDto.getTotalPrice());
//        buyNowDto.setQuantity(cartItem.getQuantity());
        return null;
    }
}
