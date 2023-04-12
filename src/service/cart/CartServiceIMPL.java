package service.cart;

import config.Config;
import model.cart.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartServiceIMPL implements ICartService{
    static String PATH_CART = "src/data/cart.txt";
    static Config<List<Cart>> config = new Config<>();
    static List<Cart> cartList = config.read(PATH_CART);
    static {
        if (cartList == null){
            cartList = new ArrayList<>();
        }
    }

    @Override
    public List<Cart> findAll() {
        return cartList;
    }

    @Override
    public void save(Cart cart) {
        Cart oldCart = findById(cart.getId());
        if(oldCart == null){
            cartList.add(cart);
        } else {
            oldCart.setCartMap(cart.getCartMap());
        }
        updateData();

    }

    @Override
    public void remote(int id) {
        for (Cart cart : cartList){
            if (cart.getId()==id){
                cartList.remove(id);
            }
        }
    }

    @Override
    public Cart findById(int id) {
        for (Cart cart : cartList){
            if (cart.getId()==id)
                return cart;
        }
        return null;
    }

    @Override
    public int getLastId() {
        return cartList.isEmpty() ? 1 : cartList.get(cartList.size() - 1).getId() + 1;
    }

    @Override
    public void updateData() {
        config.write(PATH_CART, cartList);
    }

    @Override
    public Cart findByName(String name) {
        return null;
    }

    @Override
    public void changeStatus(int id) {
        Cart cart = findById(id);
        cart.setStatus(!cart.isStatus());
        updateData();
    }
}
