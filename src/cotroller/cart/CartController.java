package cotroller.cart;

import model.account.User;
import model.cart.Cart;
import service.cart.CartServiceIMPL;
import service.pet.PetServiceIMPL;
import service.user.IUserService;
import service.user.UserServiceIMPL;

public class CartController {
    CartServiceIMPL cartServiceIMPL = new CartServiceIMPL();
    IUserService userService = new UserServiceIMPL();
    User currentUser = userService.getCurrentUser();
    PetServiceIMPL petServiceIMPL = new PetServiceIMPL();

    public void createShoppingCart(int idPet){
        Cart cart = getMyShoppingCart();
        if(cart==null){
            cart = new Cart(cartServiceIMPL.getLastId(),currentUser.getId());
        }
        if (petServiceIMPL.findById(idPet) == null) {
            System.out.println("CREATE ERROR !!!");
            return;
        }
        cart.addToCart(idPet);
        cartServiceIMPL.save(cart);
    }

    public Cart getMyShoppingCart(){
        for (Cart cart : cartServiceIMPL.findAll()) {
            if (cart.getIdUser() == userService.getCurrentUser().getId() && !cart.isStatus()){
                return cart;
            }
        }
        return null;
    }

    public void save(Cart cart){
        cartServiceIMPL.save(cart);
    }

}
