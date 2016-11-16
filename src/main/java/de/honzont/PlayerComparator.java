package main.java.de.honzont;

import java.util.Comparator;

/**
 * Created by Jens on 07.11.2016.
 */
class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player self, Player other) {
        return other.getHandValue().compareTo(self.getHandValue());
    }
}
