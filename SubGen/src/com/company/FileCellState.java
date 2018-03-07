package com.company;

import java.io.Serializable;

public class FileCellState implements Serializable {

    private static final long serialVersionUID = 1L;

    String textFieldName;
    int comboBoxIndex;
    boolean checkBoxActive;
    boolean toggleFocused;

    public FileCellState(String textFieldName, int comboBoxIndex) {
        this.textFieldName = textFieldName;
        this.comboBoxIndex = comboBoxIndex;
        this.checkBoxActive = false;
        this.toggleFocused = false;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FileCellState)) {
            return false;
        }

        FileCellState that = (FileCellState) other;

        // Custom equality check here:
        return this.textFieldName.equals(that.textFieldName)
                && this.comboBoxIndex == that.comboBoxIndex
                && this.checkBoxActive == that.checkBoxActive
                && this.toggleFocused == that.toggleFocused;
    }
}
