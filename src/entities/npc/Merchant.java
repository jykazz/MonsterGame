package entities.npc;

import entities.Trader;

public class Merchant implements Trader {
    @Override
    public String trade(Goods goods) {
        String result = "";
        if (goods == Goods.POTION) {
            result = "potion";
        }
        return result;
    }

    public enum Goods {
        POTION
    }
}
