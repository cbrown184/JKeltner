
<div align="center">
    <b><em>JKeltner</em></b><br>
</div>

[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)

Library for calculating [Keltner Channel](https://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:keltner_channels) crosses.

Keltner Channels are volatility-based bands that are placed on either side of an asset's price and can aid in determining the direction of a trend.
The exponential moving average (EMA) of a Keltner Channel is typically 20 periods, although this can be adjusted if desired.
The upper and lower bands are typically set two times the average true range (ATR) above and below the EMA, although the multiplier can also be adjusted based on personal preference.
Price reaching the upper Keltner Channel band is bullish, while reaching the lower band is bearish.
The angle of the Keltner Channel also aids in identifying the trend direction.
The price may also oscillate between the upper and lower Keltner Channel bands, which can be interpreted as resistance and support levels.

Keltner Channel Middle Line=EMA</br>
Keltner Channel Upper Band=EMA+2∗ATR</br>
Keltner Channel Lower Band=EMA−2∗ATR</br>
where:</br>
EMA=Exponential moving average (typically over 20 periods)</br>
ATR=Average True Range (typically over 10 or 20 periods)
