package craftPlanner;

import java.util.Arrays;

// the class that does the planning
public class Planner {
    public static void balls(){
        Item.createItem("circuit board");
        Item.createItem("resistor");
        Item.createItem("steel plate");
        Item.createItem("resin printed circuit board");
        Item.createItem("vacuum tube");
        Item.createItem("red alloy wire coated");
        Item.createItem("paper");
        Item.createItem("sugar cane");
        Item.createItem("charcoal dust");
        Item.createItem("charcoal");
        Item.createItem("sticky resin");
        Item.createItem("fine copper wire");
        Item.createItem("copper foil");
        Item.createItem("copper plate");
        Item.createItem("copper ingot");
        Item.createItem("siltake spore");
        Item.createItem("steel ingot");
        Item.createItem("iron ingot");
        Item.createItem("copper wire");
        Item.createItem("resin circuit board");
        Item.createItem("wood plank");
        Item.createItem("wood slab");
        Item.createItem("wood");
        Item.createItem("steel bolt");
        Item.createItem("glass tube");
        Item.createItem("steel rod");
        Item.createItem("glass pane");
        Item.createItem("glass");
        Item.createItem("glass dust");
        Item.createItem("quarts sand");
        Item.createItem("flint dust");
        Item.createItem("sand");
        Item.createItem("flint");
        Item.createItem("red alloy wire");
        Item.createItem("rubber sheet");
        Item.createItem("red alloy plate");
        Item.createItem("red alloy ingot");
        Item.createItem("redstone");
        Item.createItem("cured rubber");
        Item.createItem("rubber");
        Item.createItem("sulfur");

        Recipe.createRecipe("1 circuit board| 2 resistor, 2 steel plate, 1 resin printed circuit board, 2 vacuum tube, 3 red alloy wire coated");

        Recipe.createRecipe("1 resistor| 2 paper, 1 charcoal dust, 2 sticky resin, 2 fine copper wire");
        Recipe.createRecipe("1 paper| 1 sugar cane");
        Recipe.createRecipe("1 sugar cane");
        Recipe.createRecipe("1 charcoal dust| 1 charcoal");
        Recipe.createRecipe("1 charcoal");
        Recipe.createRecipe("1 sticky resin| 1 siltake spore");
        Recipe.createRecipe("1 siltake spore");
        Recipe.createRecipe("1 fine copper wire| 1 copper foil");
        Recipe.createRecipe("1 copper foil| 0.5 copper plate");
        Recipe.createRecipe("1 copper plate| 2 copper ingot");
        Recipe.createRecipe("1 copper ingot");

        Recipe.createRecipe("1 steel plate| 2 steel ingot");
        Recipe.createRecipe("1 steel ingot| 1 charcoal, 1 iron ingot");
        Recipe.createRecipe("1 iron ingot");

        Recipe.createRecipe("1 resin printed circuit board| 8 copper wire, 1 resin circuit board");
        Recipe.createRecipe("1 copper wire| 1 copper plate");
        Recipe.createRecipe("1 resin circuit board| 1 wood plank, 2 sticky resin");
        Recipe.createRecipe("1 wood plank| 3 wood slab");
        Recipe.createRecipe("1 wood slab| 3 wood");
        Recipe.createRecipe("1 wood");

        Recipe.createRecipe("1 vacuum tube| 3 copper wire, 2 steel bolt, 1 glass tube");
        Recipe.createRecipe("1 steel bolt| 0.5 steel rod");
        Recipe.createRecipe("1 steel rod| 1 steel ingot");
        Recipe.createRecipe("1 glass tube| 3 glass pane");
        Recipe.createRecipe("1 glass pane| 0.5 glass");
        Recipe.createRecipe("1 glass| 1 glass dust");
        Recipe.createRecipe("1 glass dust| 1 quarts sand, 0.125 flint dust");
        Recipe.createRecipe("1 quarts sand| 1 sand");
        Recipe.createRecipe("1 sand");
        Recipe.createRecipe("1 flint dust| 1 flint");
        Recipe.createRecipe("1 flint");

        Recipe.createRecipe("1 red alloy wire coated| 1 red alloy wire, 1 rubber sheet");
        Recipe.createRecipe("1 red alloy wire| 1 red alloy plate");
        Recipe.createRecipe("1 red alloy plate| 2 red alloy ingot");
        Recipe.createRecipe("1 red alloy ingot| 4 redstone, 1 copper ingot");
        Recipe.createRecipe("1 redstone");
        Recipe.createRecipe("1 rubber sheet| 1 cured rubber");
        Recipe.createRecipe("1 cured rubber| 1 rubber, 0.3333333333 sulfur");
        Recipe.createRecipe("1 rubber");
        Recipe.createRecipe("1 sulfur");

        
    }
    public static void main(String[] args) {
        balls();
        RecipePlan rec = RecipePlan.createRecipe("circuit board", 32);
        System.out.println(rec);
        System.out.println(Arrays.toString(rec.getBaseCost()));
    }
}
