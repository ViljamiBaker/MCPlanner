package craftPlanner;

import java.util.ArrayList;
import java.util.Arrays;

// a thing that keeps track of recipes
public class Recipe {

    public static Recipe doesRecipeExsist(ItemCost[] requirements, ItemCost[] products){
        for (Recipe rec : allRecipes) {
            if(
                Arrays.equals(requirements, rec.requirements) &&
                Arrays.equals(products, rec.products)) return rec;
        }
        return null;
    }

    public static ArrayList<Recipe> allRecipes = new ArrayList<>();

    private static ItemCost[] createItemCosts(String input){
        String[] inputs = input.split("[,]");
        ItemCost[] realInput = new ItemCost[inputs.length];
        if(inputs[0].isEmpty()){
            return new ItemCost[0];
        }
        for (int i = 0; i < inputs.length; i++) {
            String[] itemParts = inputs[i].split("[ ]");
            boolean spaceAtStart = itemParts[0].isBlank();
            double itemreq = Double.valueOf(itemParts[(spaceAtStart?1:0)]);
            String nameOfPart = "";
            for (int j = spaceAtStart?2:1; j < itemParts.length; j++) {
                nameOfPart+=itemParts[j];
                if(j<itemParts.length-1){
                    nameOfPart+=" ";
                }
            }
            Item item = Item.doesItemExsist(nameOfPart);
            if(item==null){
                throw new RuntimeException("ERROR: Item \"" + nameOfPart + "\" Does not exist yet and was used in recipe.");
            }
            realInput[i] = new ItemCost(item, itemreq);
        }
        return realInput;
    }

    public static Recipe getRecipe(Item item){
        for (Recipe recipe : allRecipes) {
            for (ItemCost ic : recipe.products) {
                if(ic.craftingItem().equals(item)){
                    return recipe;
                }
            }
        }
        return null;
    }

    public static Recipe createRecipe(String input){
        String[] reqandprod = input.split("[|]");

        ItemCost[] realProducts = createItemCosts(reqandprod[0]);
        ItemCost[] realRequirements = new ItemCost[0];
        if(reqandprod.length != 1){
            realRequirements = createItemCosts(reqandprod[1]);
        }

        return createRecipe(realRequirements, realProducts);
    }

    public static Recipe createRecipe(ItemCost[] requirements, ItemCost[] products){
        Recipe currentRecipe = doesRecipeExsist(requirements,products);
        if(currentRecipe==null){
            return new Recipe(requirements, products);
        }
        return currentRecipe;
    }

    public ItemCost[] requirements;
    public ItemCost[] products;
    public boolean endItem;

    public Recipe(ItemCost[] requirements, ItemCost[] products){
        this.requirements = requirements; 
        this.products = products; 
        this.endItem = requirements.length==0;
        allRecipes.add(this);
    }
    
    private static String CreateRecipeString(ItemCost[] requirements) {
        int iMax = requirements.length - 1;
        if (iMax == -1)
            return null;

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(requirements[i].count() +  " " + requirements[i].craftingItem().name);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public double getCraftCountOfItem(Item item){
        for (ItemCost ic : products) {
            if(ic.craftingItem().equals(item)){
                return ic.count();
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Recipe)) return false;
        Recipe req = (Recipe) o;
        return 
            Arrays.equals(requirements, req.requirements) &&
            Arrays.equals(products, req.products) &&
            (endItem == req.endItem);
    }
    @Override
    public String toString(){
        String recString = Recipe.CreateRecipeString(requirements);
        String prodString = Recipe.CreateRecipeString(products);
        if(recString == null){
            return prodString;
        }
        return prodString + " With: " + recString;
    }

    public static void main(String[] args) {
        System.out.println(Item.createItem("Iron Ingot"));
        System.out.println(Item.createItem("Iron Pressure Plate"));
        System.out.println(Recipe.createRecipe("2 Iron Pressure Plate | 2 Iron Ingot"));
        System.out.println(Recipe.createRecipe("2 Iron Pressure Plate"));
    }
}
