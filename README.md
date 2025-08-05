# This is a small project to make planning recipes in GTNH easier

## To use TXT files:

### create items and recipies in Input.txt in this pattern

Create Item:item name

Create Recipe:# item name 1, # item name 2| # item name 3, # item name 4

or

Create Recipe:# item name

for base items

and then call Calculate Item:item name # once  

### run IOHandler

### look in output.txt

## other things to put in Input.txt if you want

### SetIndent:whatever

sets the indent to use in Output

Default: "    "

### NumberOfLinebreaks:number

set the number of linebreak between lines

Default: 1

### RequireRoundCrafts:1 or 0

whether to round crafts up or not (e.g 0.3->1)

Default: 0
