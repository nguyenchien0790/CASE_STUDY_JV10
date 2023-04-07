package model.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    private int id;
    private  int idUser;
    private Map<Integer, Integer> cartMap = new HashMap<>();
    private boolean status;

    public Cart() {
    }

    public Cart(int id, int idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    public Cart(int id, int idUser, Map<Integer, Integer> cartMap, boolean status) {
        this.id = id;
        this.idUser = idUser;
        this.cartMap = cartMap;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Map<Integer, Integer> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<Integer, Integer> cartMap) {
        this.cartMap = cartMap;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cart { " +
                "ID = " + id +
                " - IdUser = " + idUser +
                " - CartMap = " + cartMap +
                " - Status = " + status + " }";
    }
}
