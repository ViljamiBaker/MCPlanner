package craftPlanner;

import java.util.ArrayList;

// item that is crafted with
public class Item {

    public String name;

    public static ArrayList<Item> allItems = new ArrayList<>();

    public static Item createItem(String name){
        Item currentItem = doesItemExsist(name);
        if(currentItem==null){
            return new Item(name);
        }
        return currentItem;
    }

    public Item(String name){
        this.name = name;
        allItems.add(this);
    }

    public static Item doesItemExsist(String name){
        for (Item item : allItems) {
            if(item.name.equals(name)) return item;
        }
        return null;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Item)) return false;
        Item item = (Item) o;
        return item.name.equals(this.name);
    }
    @Override
    public String toString(){
        return name;
    }

    public static void main(String[] args) {
        System.out.println(Item.createItem("Iron Ingot"));
        System.out.println(Item.createItem("Iron Pressure Plate"));
    }
}