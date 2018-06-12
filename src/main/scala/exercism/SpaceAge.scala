package exercism

import java.math.{MathContext, RoundingMode}

/**
  *    - Earth: orbital period 365.25 Earth days, or 31557600 seconds
  *    - Mercury: orbital period 0.2408467 Earth years
  *    - Venus: orbital period 0.61519726 Earth years
  *    - Mars: orbital period 1.8808158 Earth years
  *    - Jupiter: orbital period 11.862615 Earth years
  *    - Saturn: orbital period 29.447498 Earth years
  *    - Uranus: orbital period 84.016846 Earth years
  *    - Neptune: orbital period 164.79132 Earth years
  */
object SpaceAge {

  val SCALE = new MathContext(2, RoundingMode.HALF_EVEN)
  val EARTH_SECONDS_YEAR = 31557600.0

  def onNeptune(seconds: BigDecimal): BigDecimal = op(seconds, 164.79132)
  def onUranus(seconds: BigDecimal): BigDecimal = op(seconds, 84.016846)
  def onSaturn(seconds: BigDecimal): BigDecimal = op(seconds, 29.447498)
  def onJupiter(seconds: BigDecimal): BigDecimal = op(seconds, 11.862615)
  def onMars(seconds: BigDecimal): BigDecimal = op(seconds, 1.8808158)
  def onVenus(seconds: BigDecimal): BigDecimal = op(seconds, 0.61519726)
  def onMercury(seconds: BigDecimal): BigDecimal = op(seconds, 0.2408467)
  def onEarth(seconds: BigDecimal): BigDecimal = op(seconds, 1.0)

  private def op(seconds: BigDecimal, frac: BigDecimal): BigDecimal =
    (seconds / EARTH_SECONDS_YEAR / frac).setScale(2, BigDecimal.RoundingMode.HALF_UP)
}
