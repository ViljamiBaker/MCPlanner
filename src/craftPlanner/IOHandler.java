package craftPlanner;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

// scans input and output
public class IOHandler {
    public static String indent = "   ";
    public static String linebreak = "\n";
    public static boolean requireRoundCrafts = false;
    public static void main(String[] args) {
        try {
            File input = new File(System.getProperty("user.dir") + "/src/craftPlanner/Input.txt");
            FileWriter fw = new FileWriter(new File(System.getProperty("user.dir") + "/src/craftPlanner/Output.txt"));
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] lineSplit = line.split("[:]");
                switch (lineSplit[0]) {
                    case "Create Recipe":
                        try {
                            Recipe.createRecipe(lineSplit[1].trim());
                        } catch (Exception e) {
                            fw.append(e.getMessage());
                        }
                        break;
                    case "Create Item":
                        Item.createItem(lineSplit[1].trim());
                        break;
                    case "Calculate Item":
                        String[] craftPerams = lineSplit[1].split("[,]");
                        try {
                            RecipePlan plan = RecipePlan.createRecipe(craftPerams[0].trim(), Double.valueOf(craftPerams[1].trim()));
                            fw.write("To make " + Double.valueOf(craftPerams[1].trim()) + " of " + craftPerams[0].trim() + linebreak + "\n");
                            fw.write(plan.toString());
                            fw.write(linebreak + "Total Items Needed:" + linebreak);
                            ItemCost[] basecost = plan.getBaseCost();
                            for (ItemCost ic : basecost) {
                                fw.write(ic.toString()+linebreak);
                            }
                        } catch (Exception e) {
                            fw.append(e.getMessage());
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
                        int enabled = Integer.valueOf(lineSplit[1].trim());
                        requireRoundCrafts = (enabled==1);
                        break;
                }
            }
            sc.close();
            fw.close();
        } catch (Exception e) {
        }
    }
}
