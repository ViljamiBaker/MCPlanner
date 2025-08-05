package craftPlanner;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

// scans input and output
public class IOHandler {
    public static String indent = "   ";
    public static String linebreak = "\n";
    public static boolean requireRoundCrafts = false;
    public static boolean automaticallyAddItems = true;
    public static boolean automaticallyAddRecipe = false;
    private static String debugOut = "";

    public static void addToDebug(String in){
        debugOut += in + "\n";
    }

    public static void main(String[] args) {
        String out = "";
        int lineIndex = 0;
        try {
            int enabled = 0;
            File input = new File(System.getProperty("user.dir") + "/src/craftPlanner/Input.txt");
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] lineSplit = line.split("[:]");
                switch (lineSplit[0]) {
                    case "Create Recipe":
                        try {
                            Recipe.createRecipe(lineSplit[1].trim());
                        } catch (Exception e) {
                            addToDebug(e.getMessage() + " Line " + lineIndex);
                        }
                        break;
                    case "Create Item":
                        Item.createItem(lineSplit[1].trim());
                        break;
                    case "Calculate Item":
                        String[] craftPerams = lineSplit[1].split("[,]");
                        try {
                            if(craftPerams.length!=2){
                                throw new RuntimeException("Calculate Item failed, too few or too many commas?");
                            }
                            RecipePlan plan = RecipePlan.createRecipe(craftPerams[0].trim(), Double.valueOf(craftPerams[1].trim()));
                            out += "------- To make " + Double.valueOf(craftPerams[1].trim()) + " of " + craftPerams[0].trim() + " -------" + linebreak + "\n";
                            out += plan.toString();
                            out += linebreak + "Total Items Needed:" + linebreak;
                            ItemCost[] basecost = plan.getBaseCost();
                            for (ItemCost ic : basecost) {
                                out += ic.toString()+linebreak;
                            }
                            out += "\n";
                        } catch (Exception e) {
                            addToDebug(e.getMessage() + " Line " + lineIndex);
                        }
                        break;
                    case "SetIndent":
                        indent = lineSplit[1];
                        break;
                    case "NumberOfLinebreaks":
                        int count = Integer.valueOf(lineSplit[1].trim());
                        linebreak = "";
                        for (int i = 0; i < count; i++) {
                            linebreak+="\n";
                        }
                        break;
                    case "RequireRoundCrafts":
                        enabled = Integer.valueOf(lineSplit[1].trim());
                        requireRoundCrafts = (enabled==1);
                        break;
                    case "AutomaticallyAddItems":
                        enabled = Integer.valueOf(lineSplit[1].trim());
                        automaticallyAddItems = (enabled==1);
                        break;
                    case "AutomaticallyAddRecipes":
                        enabled = Integer.valueOf(lineSplit[1].trim());
                        automaticallyAddRecipe = (enabled==1);
                        break;
                }
                lineIndex++;
            }
            sc.close();
        } catch (Exception e) {}
        try {
            FileWriter fwd = new FileWriter(new File(System.getProperty("user.dir") + "/src/craftPlanner/DebugOutput.txt"));
            fwd.write(debugOut);
            fwd.close();
            FileWriter fwo = new FileWriter(new File(System.getProperty("user.dir") + "/src/craftPlanner/Output.txt"));
            fwo.write(out);
            fwo.close();
        } catch (Exception e) {}
    }
}
