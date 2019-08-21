package com.inventory.util;

import com.inventory.classifier.ItemState;

/**
 * Returning name of class, due to State of item, for index list
 */
public class StateTableConverter {
    public static String convertStateToClass(ItemState itemState) {
        switch (itemState) {
            case LENT:
                return "table-primary";
            case NORMAL:
                return "Default";
            case SERVICE:
                return "table-danger";
            case DECOMMISSIONED:
                return "table-warning";
            default:
                return "NORMAL";
        }
    }
}
