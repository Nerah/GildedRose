public class ConjuredItemUpdate {

    public void updateQuality(Item item){
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 2);
        }
        if (item.getQuality() < 0)
            item.setQuality(0);
    }
}
