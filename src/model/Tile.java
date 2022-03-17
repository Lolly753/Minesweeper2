package model;

public class Tile extends AbstractTile{
    private boolean bomba = false;
    private boolean open = false;
    private boolean flagged = false;

    public Tile (boolean isexplosive){
        this.bomba = isexplosive;
    }

    @Override
    public boolean open() {
        this.open = true;
        return this.bomba;
    }

    @Override
    public void flag() {
        this.flagged = true;
    }

    @Override
    public void unflag() {
        this.flagged = false;
    }

    @Override
    public boolean isFlagged() {
        return this.flagged;
    }

    @Override
    public boolean isExplosive() {
        return this.bomba;
    }

    @Override
    public boolean isOpened() {
        return this.open;
    }
}
