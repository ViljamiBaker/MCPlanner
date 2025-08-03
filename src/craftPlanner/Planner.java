package craftPlanner;

import java.util.Arrays;

// the class that does the planning
public class Planner {
    public static void balls(){
        Item.createItem("circuit board, 2 resistor, 2 steel plate, 1 resin printed circuit board, 2 vacuum tube, 3 red alloy wire coated");

        Item.createItem("resistor, 2 paper, 1 charcoal dust, 2 sticky resin, 2 fine copper wire");
        Item.createItem("paper, 1 sugar cane");
        Item.createItem("charcoal dust, 1 charcoal");
        Item.createItem("sticky resin, 1 siltake spore");
        Item.createItem("fine copper wire, 1 copper foil");
        Item.createItem("copper foil, 0.5 copper plate");
        Item.createItem("copper plate, 2 copper ingot");

        Item.createItem("steel plate, 2 steel ingot");
        Item.createItem("steel ingot, 1 charcoal, 1 iron ingot");

        Item.createItem("resin printed circuit board, 8 copper wire, 1 resin circuit board");
        Item.createItem("copper wire, 1 copper plate");
        Item.createItem("resin circuit board, 1 wood plank, 2 sticky resin");
        Item.createItem("wood plank, 3 wood slab");
        Item.createItem("wood slab, 3 wood");

        Item.createItem("vacuum tube, 3 copper wire, 2 steel bolt, 1 glass tube");
        Item.createItem("steel bolt, 0.5 steel rod");
        Item.createItem("steel rod, 1 steel ingot");
        Item.createItem("glass tube, 3 glass pane");
        Item.createItem("glass pane, 0.5 glass");
        Item.createItem("glass, 1 glass dust");
        Item.createItem("glass dust, 1 quarts sand, 0.125 flint dust");
        Item.createItem("quarts sand, 1 sand");
        Item.createItem("flint dust, 1 flint");

        Item.createItem("red alloy wire coated, 1 red alloy wire, 1 rubber sheet");
        Item.createItem("red alloy wire, 1 red alloy plate");
        Item.createItem("red alloy plate, 2 red alloy ingot");
        Item.createItem("red alloy ingot, 4 redstone, 1 copper ingot");
        Item.createItem("rubber sheet, 1 cured rubber");
        Item.createItem("cured rubber, 1 rubber, 0.3333333333 sulfur");

        
    }
    public static void main(String[] args) {
        balls();
        RecipePlan rec = RecipePlan.CreateRecipe("circuit board", 32);
        System.out.println(rec);
        System.out.println(Arrays.toString(rec.getBaseItemCost()));
    }
}
