package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListLines {

    private List<BrickLine> lines;

    public ListLines() {
        lines = new ArrayList<>();

        lines.add(new BrickLine(50, Color.GREEN));
        lines.add(new BrickLine(115, Color.CYAN));
        lines.add(new BrickLine(180, Color.PINK));
        //lines.add(new BrickLine(245, Color.YELLOW));

    }

    public int getLength() {

        return lines.size();
    }

    public BrickLine getLine(int index) {
        return lines.get(index);
    }
}


