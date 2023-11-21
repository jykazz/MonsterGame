package entities;

import entities.npc.Merchant;

public interface Trader {
    String trade(Merchant.Goods goods);
}
