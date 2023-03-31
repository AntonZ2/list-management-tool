package uk.ac.ucl.lists;

import java.util.List;
import java.util.ArrayList;

public class ListData {
    private List<Item> items;

    public ListData() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> new_items){
        this.items = new_items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public Item getItem(int index) {
        return items.get(index);
    }
}

