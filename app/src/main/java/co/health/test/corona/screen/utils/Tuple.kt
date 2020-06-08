package co.health.test.corona.screen.utils

public data class Tuple<A, B, C>(
    public var first: A,
    public var second: B,
    public var third: C
) {

    /**
     * Returns string representation of the [Tuple] including its [first], [second] and [third] values.
     */
    public override fun toString(): String = "($first, $second, $third)"
}
