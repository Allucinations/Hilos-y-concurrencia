package model;

import java.util.*;

public class Reel {
    private List<Symbol> symbols;
    private Symbol currentSymbol;
    private Random random = new Random();

    public Reel(List<Symbol> symbols) {
        this.symbols = symbols;
        spin();
    }

    public void spin() {
        int index = random.nextInt(symbols.size());
        currentSymbol = symbols.get(index);
    }

    public Symbol getCurrentSymbol() {
        return currentSymbol;
    }
}
