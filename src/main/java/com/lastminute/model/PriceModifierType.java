package com.lastminute.model;

public enum PriceModifierType   {LOW_PRIORITY(31, 80),
                                STANDARD_PRIORITY(16, 100),
                                NEARBY_PRIORITY(3, 120),
                                INSTANT_PRIORITY(0, 150),
                                NO_PRIORITY(-1, 0);

    private final int minDays;
    private final int modifier;

    PriceModifierType (Integer minDays, int modifier) {
        this.minDays = minDays;
        this.modifier = modifier;
    }

    public int getMinDays() {
        return minDays;
    }

    public int getModifier() {
        return modifier;
    }

    public static PriceModifierType getPriceModifierTypeByDay(int day) {

        for (PriceModifierType priceModifierType : PriceModifierType.values()) {
            if (priceModifierType.getMinDays() <= day) {
                return priceModifierType;
            }
        }

        return PriceModifierType.NO_PRIORITY;
    }


}
