package craftPlanner;

import java.util.ArrayList;

// the thing that contains all of the steps
public class RecipePlan {

    public Recipe craft;
    public double count;
    public RecipePlan[] previousSteps;

    public static final String indentString = "   ";

    public static RecipePlan createRecipe(String item, double count){
        return RecipePlan.createRecipe(Item.doesItemExsist(item), count);
    }

    public static RecipePlan createRecipe(Item item, double count){
        Recipe rec = Recipe.getRecipe(item);
        return new RecipePlan(rec, rec.getCraftCountOfItem(item) * count);
    }

    private RecipePlan(Recipe craft, double count){
        this.craft = craft;
        this.count = count;
        previousSteps = new RecipePlan[craft.requirements.length];
        for (int i = 0; i<craft.requirements.length; i++) {
            previousSteps[i] = RecipePlan.createRecipe(craft.requirements[i].craftingItem(),craft.requirements[i].count()*count);
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
            return indent + count + " " + craft.toString() + (craft.endItem?" ------":"")+ "\n";
        }
        return indent + "Craft " + count + " " + craft.toString() + " With\n" + step1;
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

    public ItemCost[] getBaseCost(){
        if (previousSteps.length == 0){
            return craft.products;
        }
        ArrayList<ItemCost> itemCosts = new ArrayList<>();
        for (RecipePlan rec : previousSteps) {
            for (ItemCost itemCost : rec.getBaseCost()) {
                int dupe = itemCosts.indexOf(itemCost);
                if(dupe==-1){
                    itemCosts.add(itemCost);
                }else{
                    double lastCount = itemCosts.get(dupe).count();
                    itemCosts.remove(dupe);
                    itemCosts.add(new ItemCost(itemCost.craftingItem(), lastCount+itemCost.count()*rec.count));
                }
            }
        }
        return itemCosts.toArray(new ItemCost[0]);
    }
}
