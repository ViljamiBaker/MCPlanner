package craftPlanner;

import java.util.ArrayList;

// the thing that contains all of the steps
public class RecipePlan {

    public Recipe craft;
    public double count;
    public RecipePlan[] previousSteps;

    public static RecipePlan createRecipe(String item, double count){
        return RecipePlan.createRecipe(Item.doesItemExsist(item), count);
    }

    public static RecipePlan createRecipe(Item item, double count){
        Recipe rec = Recipe.getRecipe(item);
        if(rec == null){
            
            if(IOHandler.automaticallyAddItems){
                rec = Recipe.createRecipe(new ItemCost[0], new ItemCost[] {new ItemCost(item,1)});
                IOHandler.addToDebug("Recipe for item \"" + item.name + "\" automatically created.");
            }else{
                throw new RuntimeException("ERROR: No recipe for item: " + item);
            }
        }
        return new RecipePlan(rec, (IOHandler.requireRoundCrafts?Math.ceil(count/rec.getCraftCountOfItem(item)):count/rec.getCraftCountOfItem(item)));
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
            indent += IOHandler.indent;
        }
        String step1 = toStringStep1(depth);
        if(step1 == null){
            return indent + count + " " + craft.toString() + (craft.endItem?" ------":"")+ IOHandler.linebreak;
        }
        return indent + "Craft " + count + " " + craft.toString() + " With" + IOHandler.linebreak + step1;
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
            ItemCost[] itemCostmul = new ItemCost[craft.products.length];
            for (int i = 0; i < itemCostmul.length; i++) {
                itemCostmul[i] = new ItemCost(craft.products[i].craftingItem(), craft.products[i].count()*this.count);
            }
            return itemCostmul;
        }
        ArrayList<ItemCost> itemCosts = new ArrayList<>();
        for (RecipePlan rec : previousSteps) {
            for (ItemCost itemCost : rec.getBaseCost()) {
                int dupe = itemCosts.indexOf(itemCost);
                if(dupe==-1){
                    itemCosts.add(new ItemCost(itemCost.craftingItem(), itemCost.count()));
                }else{
                    double lastCount = itemCosts.get(dupe).count();
                    itemCosts.remove(dupe);
                    itemCosts.add(new ItemCost(itemCost.craftingItem(), lastCount+itemCost.count()));
                }
            }
        }
        return itemCosts.toArray(new ItemCost[0]);
    }
}
