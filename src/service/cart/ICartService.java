package service.cart;

import model.cart.Cart;
import service.IGenericService;

public interface ICartService extends IGenericService<Cart> {
    void changeStatus(int id);
}
