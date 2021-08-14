
<div align="center">
    <b><em>Keltner Channel Calculator</em></b><br>
</div>


<br>Big Decimal implementation.
<br>Pure java - no additional libraries.
<br>Works on Java 8 +.

## Description
https://en.wikipedia.org/wiki/Keltner_channel

Keltner Channels are volatility-based bands that are placed on either side of an asset's price and can aid in determining the direction of a trend.

Price reaching the upper Keltner Channel band is bullish, while reaching the lower band is bearish.

The angle of the Keltner Channel also aids in identifying the trend direction. The price may also oscillate between the upper and lower Keltner Channel bands, which can be interpreted as resistance and support levels.
## Installation

```xml
        <dependency>
            <groupId>io.github.cbrown184</groupId>
            <artifactId>jkeltner</artifactId>
            <version>1.0.2</version>
        </dependency>
```
## Usage

```java
  JKeltnerCalculator jKeltnerCalculator = new JKeltnerCalculator.JKeltnerBuilder()
                // The default moving average (EMA) of a Keltner Channel is 20 periods
                .setPeriod(3)
                // The upper and lower bands are typically set two times the average true range (ATR) above and below the EMA
                .setAtrMultiplier(2)
                // Precision of the floating point (BigDecimal) values returned
                .setScale(6)
                // Smoothing factor of EMA
                .setSmoothingFactor(2)
                .build();

 // Input candle data - high, low, close
 // The calculator returns Optional.Empty() until the number of candles supplied is equal to the set period
 jKeltnerCalculator.calculate(1.45, 1.41, 1.42); // -> Optional.empty
 jKeltnerCalculator.calculate(1.46, 1.42, 1.43); // -> Optional.empty
 jKeltnerCalculator.calculate(1.51, 1.41, 1.42); // -> Optional[KeltnerBand{upperBand=1.543333, midBand=1.423333, lowerBand=1.303333}]

```

