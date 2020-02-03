package factory;

public class BackstagePasses extends AbstractItem {
    public static final int CLOSE_DATE = 10;
    public static final int IMMINENT_DATE = 5;

    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        item.quality++;
        if(hasReachCloseDate()) item.quality++;
        if(hasReachImminentDate()) item.quality++;
        if(hasReachSellInBoundary()) item.quality = MIN_QUALITY;
        if(hasReachMaxQuality()) item.quality = MAX_QUALITY;
    }

    protected boolean hasReachCloseDate() {
        return item.sellIn < CLOSE_DATE;
    }

    protected boolean hasReachImminentDate() {
        return item.sellIn < IMMINENT_DATE;
    }

}
