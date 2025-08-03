package craftPlanner;

public record ItemCost(Item craftingItem, double count) {
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Item)) return false;
        ItemCost ic = (ItemCost) o;
        return ic.craftingItem.equals(this.craftingItem);
    }
    @Override
    public String toString(){
        return count + " " + craftingItem.toString();
    }
}
