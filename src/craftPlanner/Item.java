package craftPlanner;

import java.util.ArrayList;

// item that is crafted with
public class Item {

    public String name;
    public String[] recipeNames;
    public Item[] recipe;
    public double[] recipeItemReq;
    public boolean endItem;

    public static ArrayList<Item> allItems = new ArrayList<>();
    public static Item createItem(String input){
        String[] inputs = input.split("[,|]");
        String name = inputs[0];
        String[] recipe = new String[inputs.length-1];
        double[] recipeItemReq = new double[inputs.length-1];

        for (int i = 0; i < inputs.length-1; i++) {
            String[] itemParts = inputs[i+1].split("[ ]");
            boolean spaceAtStart = itemParts[0].isEmpty();
            recipeItemReq[i] = Double.valueOf(itemParts[(spaceAtStart?1:0)]);
            String nameOfPart = "";
            for (int j = spaceAtStart?2:1; j < itemParts.length; j++) {
                nameOfPart+=itemParts[j];
                if(j<itemParts.length-1){
                    nameOfPart+=" ";
                }
            }
            recipe[i] = nameOfPart;
        }
        return createItem(recipe, recipeItemReq, name);
    }

    public static Item createItem(String[] recipe, double[] recipeItemReq, String name){
        Item[] recipeReal = new Item[recipe.length];
        for(int i = 0; i<recipe.length; i++){
            Item recipeItem = doesItemExsist(recipe[i]);
            if(recipeItem != null){
                recipeReal[i] = recipeItem;
            }else{
                recipeReal[i] = createItem(new String[0], new double[0], recipe[i]);
            }
        }
        Item currentItem = doesItemExsist(name);
        if(currentItem==null){
            return new Item(recipeReal, recipeItemReq, name);
        }else{
            currentItem.recipe = recipeReal; 
            currentItem.recipeItemReq = recipeItemReq; 
            currentItem.recipeNames = new String[recipe.length];
            for (int i = 0; i < recipe.length; i++) {
                currentItem.recipeNames[i] = currentItem.recipe[i].name;
            }
            currentItem.endItem = recipeReal.length==0;
        }
        return currentItem;
    }

    public static Item doesItemExsist(String name){
        for (Item item : allItems) {
            if(item.name.equals(name)) return item;
        }
        return null;
    }

    private Item(Item[] recipe, double[] recipeItemReq, String name){
        if(recipe.length!=recipeItemReq.length){
            throw new RuntimeException("Bad recipe: " + recipe.length +" items vs " + recipeItemReq.length +" item requirement.");
        }
        this.recipe = recipe;
        this.recipeItemReq = recipeItemReq;
        this.name = name;
        this.recipeNames = new String[recipe.length];
        for (int i = 0; i < recipe.length; i++) {
            this.recipeNames[i] = this.recipe[i].name;
        }
        endItem = recipe.length==0;
        allItems.add(this);
    }

    private static String CreateRecipeString(Item[] req, double[] count) {
        int iMax = req.length - 1;
        if (iMax == -1)
            return null;

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(count[i] +  " " + req[i].name);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Item)) return false;
        Item item = (Item) o;
        return item.name.equals(this.name);
    }
    @Override
    public String toString(){
        String recString = Item.CreateRecipeString(recipe, recipeItemReq);
        if(recString == null){
            return name;
        }
        return name + " \nCrafted With: " + recString;
    }

    public static void main(String[] args) {
        System.out.println(Item.createItem(new String[] {}, new double[] {}, "Iron Ingot"));
        System.out.println(Item.createItem(new String[] {"Iron Ingot"}, new double[] {2}, "Iron Pressure Plate"));
    }
}