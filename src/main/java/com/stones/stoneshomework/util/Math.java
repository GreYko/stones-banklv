package com.stones.stoneshomework.util;

import java.math.MathContext;
import java.math.RoundingMode;

public class Math {
    public static final RoundingMode appMoneyRounding = RoundingMode.HALF_UP;
    public static final MathContext calculationMathContext = new MathContext(34, appMoneyRounding);
}
