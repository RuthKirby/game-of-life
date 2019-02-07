# Game of Life

An infinite evolving 2D grid of cells. Grid changes based on evolution rules. View documentation [here](https://ruthkirby.github.io/game-of-life/)

## Running the .jar

There is an executable version in the app folder. This can be 
launched from the command line:
`java -jar GameOfLife.jar`

If the command is followed by a non-negative integer it will
use the argument as the number of cells to seed the grid with e.g.
`java -jar GameOfLife.jar 12` will seed the grid with 12 live 
cells. If something other than a non-negative integer is used then the 
grid is seeded with a default number (3).

## Decisions
* Cells are all next to each other when initially seeded while leaving a blank edge so that
cell creation can be determined. 
* If a cell is created on the edge of grid then the grid expands so that the edge of grid is
always blank tiles. For example if a cell is created at coordinates (0,0) the grid creates a new
rows and columns so that what was at (0, 0) is now at (1, 1). This ensures grid can be 'infinite' in
its growth. 
* Initially I stored the grid in a 2D array but to achieve a dynamic structure 
for grid that could grow when needed and satisfy 'infinite' quality
I changed to using ArrayList.
* Scenarios that determine tile's state in next iteration are
stored in Enum items so that new scenarios could be easily added. 

## Built with

* [Maven](https://maven.apache.org/) - Management of dependencies
* [JavaFX](https://openjfx.io/) - GUI

## Example

When a grid is seeded with 3 cells:

![Grid seeded with three cells](/screenshots/seed_3_it_1.PNG)

When the 'Next Iteration' button is pressed:

![Grid seeded with three cells on second iteration](/screenshots/seed_3_it_2.PNG)

When the 'Next Iteration' button is pressed for a second time:

![Grid seeded with three cells](/screenshots/seed_3_it_3.PNG)
