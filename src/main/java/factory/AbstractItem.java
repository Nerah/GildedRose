package factory;

public abstract class AbstractItem {
    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;
    public static final int MIN_SELLIN = 0;

    protected Item item;

    public AbstractItem(Item item) {
        this.item = item;
    }

    public void update() {
        updateSellIn();
        updateQuality();
    }

    public void updateSellIn() {
        item.sellIn--;
    }

    public abstract void updateQuality();

    public boolean hasReachSellInBoundary() {
        return item.sellIn < MIN_SELLIN;
    }

    public boolean hasReachMinQuality() {
        return item.quality <= MIN_QUALITY;
    }

    public boolean hasReachMaxQuality() {
        return item.quality >= MAX_QUALITY;
    }

}
