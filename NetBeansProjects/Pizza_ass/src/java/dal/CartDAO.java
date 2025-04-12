package dal;

import model.Item;
import java.util.ArrayList;
import java.util.List;
import model.Products;


public final class CartDAO {

    private List<Item> items;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public CartDAO() {
        items = new ArrayList<>();
    }

    public List<Item> getListItems() {
        return items;
    }
    
    private Item getItemById(int id) {
        for (Item i : items) {
            if (i.getProducts().getProductID() == id) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }

    public void addItem(Item item) {
        if (getItemById(item.getProducts().getProductID()) != null) {
            Item m = getItemById(item.getProducts().getProductID());
            m.setQuantity(item.getQuantity() + m.getQuantity());
        }else
            items.add(item);
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public double getTotalMoney() {
        double price = 0;
        for (Item item : getListItems()) {
            price += item.getQuantity() * item.getUnitPrice();
        }
        return price;
    }

    public Products getProductByID(int id, List<Products> list) {
        for (Products p : list) {
            if (p.getProductID() == id) {
                return p;
            }
        }
        return null;
    }

    public CartDAO (String txt, List<Products> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split(",");
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt( n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    Products product = getProductByID(id, list);
                    Item item = new Item(product, quantity, product.getUnitPrice());
                    addItem(item);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
}
