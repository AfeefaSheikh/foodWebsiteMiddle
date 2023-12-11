package com.example.foodWebSiteDubai.constant;

public class ControllerUrlConstants {
    public static final String ADMIN_MANAGEMENT_BASE_URI = "/admin";
    public static final String ADD_PRODUCT = "/addProducts";
    public static final String UPDATE_PRODUCT = "/updateProductById/{id}";
    public static final String DELETE_PRODUCT = "/deleteById/{id}";

    public static final String CART_MANAGEMENT_BASE_URI = "/cart";
    public static final String ADD_TO_CART = "/addToCart/{id}/{userId}/{quantity}";

    public static final String CART_MASTER_MANAGEMENT_BASE_URI = "/CartMaster";
    public static final String GET_ALL_CART_ITEMS = "/getAll/{userId}";
    public static final String DELETE_PRODUCT_BY_ID = "/deleteByProductId/{id}/{userId}";
    public static final String ADD_PRODUCT_TO_CART = "UpdateCart/{id}/{userId}";

    public static final String PRODUCT_MANAGEMENT_BASE_URI = "/Product";
    public static final String GET_ALL_PRODUCTS = "/post";

    public static final String USER_MANAGEMENT_BASE_URI = "/userManagement";
    public static final String USER_LOGIN = "/login";
    public static final String CREATE_USER = "/createUser";
}
