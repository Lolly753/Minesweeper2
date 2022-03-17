package model;

public class Minesweeper extends AbstractMineSweeper {

    private AbstractTile[][] minefield;
    private int clicks;
    private boolean firstclickrule;
    @Override
    public int getWidth() {
        return minefield[0].length;
    }

    @Override
    public int getHeight() {
        return minefield.length;
    }
    @Override
    public void startNewGame(Difficulty level) {
        switch(level){
            case EASY:
                startNewGame(8,8,10);
                break;
            case MEDIUM:
                startNewGame(16,16,40);
                break;
            case HARD:
                startNewGame(16,30,99);
                break;
        }
    }

    @Override
    public void startNewGame(int row, int col, int explosionCount) {
        this.minefield = new AbstractTile[row][col];

        for(int i=0 ; i < col; i++ )
        {
            for(int j=0 ; j < row; j++ )
            {
                minefield[j][i] = new Tile(false);
            }
        }
        //placing the mines
        int bombaOnField = 0;

        while(bombaOnField < explosionCount) {
            int randcol = (int) (Math.random()*col);
            int randrow = (int) (Math.random()*row);

            AbstractTile randomTile = getTile(randcol, randrow);

            if (!randomTile.isExplosive())
            {
                minefield[randrow][randcol] = new Tile(true);
                bombaOnField ++;
            }
        }
    }

    @Override
    public void toggleFlag(int x, int y) {
        AbstractTile tile = getTile(x, y);
        if (tile.isFlagged())
        {
            tile.unflag();
        }
        else
        {
            getTile(x, y).flag();
        }
    }

    @Override
    public AbstractTile getTile(int x, int y) {
        if (x < 0)
            x += getWidth();
        else if (x > getWidth() - 1)
            x = getWidth()-1;
        if (y < 0)
            y += getHeight();
        else if (y > getHeight() - 1)
            y = getHeight()-1;
        return minefield[y][x];
    }

    @Override
    public void setWorld(AbstractTile[][] world) {
        minefield = world;
    }

    @Override
    public void open(int x, int y) {
        getTile(x,y).open();
        clicks ++;
    }

    @Override
    public void flag(int x, int y) {
        getTile(x,y).flag();
        clicks ++;
    }

    @Override
    public void unflag(int x, int y) {
        getTile(x,y).unflag();
        clicks ++;
    }

    @Override
    public void deactivateFirstTileRule() {
        firstclickrule = false;
    }

    @Override
    public AbstractTile generateEmptyTile() {
        return new Tile(false);
    }

    @Override
    public AbstractTile generateExplosiveTile() {
        return new Tile(true);
    }
}
