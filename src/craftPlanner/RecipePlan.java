package craftPlanner;

import java.util.ArrayList;

// the thing that contains all of the steps
public class RecipePlan {

    private record ItemCost(Item craftingItem, double count) {
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

    public Item craftingItem;
    public double count;
    public RecipePlan[] previousSteps;

    public static final String indentString = "   ";

    public static RecipePlan CreateRecipe(String name, double count){
        return new RecipePlan(Item.doesItemExsist(name), count);
    }

    private RecipePlan(Item craftingItem, double count){
        this.craftingItem = craftingItem;
        this.count = count;
        previousSteps = new RecipePlan[craftingItem.recipe.length];
        for (int i = 0; i < craftingItem.recipe.length; i++) {
            previousSteps[i] = new RecipePlan(craftingItem.recipe[i], count * craftingItem.recipeItemReq[i]);
        }
    }
    @Override
    public String toString(){
        return toString(0);
        //return "Craft: " + count + " " + craftingItem.name + " With\n" + toStringStep1();
    }

    private String toString(int depth){
        String indent = "";
        for (int i = 0; i < depth; i++) {
            indent += indentString;
        }
        String step1 = toStringStep1(depth);
        if(step1 == null){
            return indent + count + " " + craftingItem.name + (craftingItem.endItem?" ------":"")+ "\n";
        }
        return indent + "Craft " + count + " " + craftingItem.name + " With\n" + step1;
    }

    private String toStringStep1(int depth){
        String string = "";
        if(previousSteps.length == 0){
            return null;
        }
        for (int i = 0; i < previousSteps.length; i++) {
            string += previousSteps[i].toString(depth + 1);
        }
        return string;
    }

    public ItemCost[] getBaseItemCost(){
        if (previousSteps.length == 0){
            return new ItemCost[] {new ItemCost(craftingItem, count)};
        }
        ArrayList<ItemCost> itemCosts = new ArrayList<>();
        for (RecipePlan rec : previousSteps) {
            for (ItemCost itemCost : rec.getBaseItemCost()) {
                int dupe = itemCosts.indexOf(itemCost);
                if(dupe==-1){
                    itemCosts.add(itemCost);
                }else{
                    double lastCount = itemCosts.get(dupe).count();
                    itemCosts.remove(dupe);
                    itemCosts.add(new ItemCost(itemCost.craftingItem, lastCount+itemCost.count));
                }
            }
        }
        return itemCosts.toArray(new ItemCost[0]);
    }
}
