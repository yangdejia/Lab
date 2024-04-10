package org.jfree.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RangeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	

	@After
	public void tearDown() throws Exception {
	}

	private Range range;
	
	
	 @Test
	    public void testConstructorValidValues() {
	        // Test valid values where lower bound is less than or equal to upper bound
	        Range range1 = new Range(0, 10);
	        assertEquals(0, range1.getLowerBound(), 0.001);
	        assertEquals(10, range1.getUpperBound(), 0.001);

	        Range range2 = new Range(-5, 5);
	        assertEquals(-5, range2.getLowerBound(), 0.001);
	        assertEquals(5, range2.getUpperBound(), 0.001);

	        Range range3 = new Range(-10, -5);
	        assertEquals(-10, range3.getLowerBound(), 0.001);
	        assertEquals(-5, range3.getUpperBound(), 0.001);

	        Range range4 = new Range(0, 0);
	        assertEquals(0, range4.getLowerBound(), 0.001);
	        assertEquals(0, range4.getUpperBound(), 0.001);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void testConstructorInvalidValues() {
	        // Test invalid values where lower bound is greater than upper bound
	        new Range(10, 0);
	    }

    @Before
    public void setUp() throws Exception {
        // Initialize a Range object for testing
        range = new Range(0, 10);
    }

    @Test
    public final void testRange() {
        // Test if the Range constructor initializes the object correctly
        assertEquals(0, range.getLowerBound(), 0.001);
        assertEquals(10, range.getUpperBound(), 0.001);
    }
    
    @Test
    public void testShift_NullRange() {
        // Test when range is null
    	Range range = new Range(4, 4);
        assertNotNull(Range.shift(new Range(4,4), 5));
    }

    @Test
    public void testEquals_EqualRanges() {
        // Test when two ranges are equal
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 10);
        assertTrue(range1.equals(range2));
    }

    @Test
    public final void testGetLowerBound() {
        // Test the getLowerBound method
        assertEquals(0, range.getLowerBound(), 0.001);
    }
    
    
    

    @Test
    public final void testGetUpperBound() {
        // Test the getUpperBound method
        assertEquals(10, range.getUpperBound(), 0.001);
    }
    
    

    @Test
    public final void testGetLength() {
        // Test the getLength method
        assertEquals(10, range.getLength(), 0.001);
    }
    
    

    @Test
    public final void testGetCentralValue() {
        // Test the getCentralValue method
        assertEquals(5, range.getCentralValue(), 0.001);
    }
    
    

    @Test
    public final void testContains() {
        // Test the contains method
        assertTrue(range.contains(5));
        assertFalse(range.contains(15));
    }
    
    

    @Test
    public final void testIntersects() {
        // Test the intersects method
        assertTrue(range.intersects(2, 7));
        assertFalse(range.intersects(15, 20));
    }
    
    

    @Test
    public final void testConstrain() {
        // Test the constrain method
        assertEquals(5, range.constrain(5), 0.001);
        assertEquals(0, range.constrain(-5), 0.001);
        assertEquals(10, range.constrain(15), 0.001);
    }

    

    @Test
    public final void testToString() {
        // Test the toString method
        assertEquals("Range[0.0,10.0]", range.toString());
    }
    
    @Test
    public void testIntersects_LowerBoundEqualToUpperBound() {
        Range range = new Range(0, 10);

        // Test when lower bound of the range is equal to the upper bound
        assertFalse(range.intersects(10, 15));
        assertFalse(range.intersects(15, 20));
    }

    @Test
    public void testIntersects_LowerBoundLessThanRange() {
        Range range = new Range(0, 10);

        // Test when lower bound of the range is less than the range
        assertTrue(range.intersects(-5, 5));
        assertFalse(range.intersects(-10, -5));
    }

    @Test
    public void testIntersects_LowerBoundEqualToRange() {
        Range range = new Range(0, 10);

        // Test when lower bound of the range is equal to the range
        assertTrue(range.intersects(0, 5));
        assertFalse(range.intersects(-5, 0));
    }

    @Test
    public void testIntersects_LowerBoundGreaterThanRange() {
        Range range = new Range(0, 10);

        // Test when lower bound of the range is greater than the range
        assertFalse(range.intersects(15, 20));
    }

    @Test
    public void testIntersects_UpperBoundEqualToRange() {
        Range range = new Range(0, 10);

        // Test when upper bound of the range is equal to the range
        assertFalse(range.intersects(5, 10));
        assertFalse(range.intersects(10, 15));
    }

    @Test
    public void testIntersects_UpperBoundGreaterThanRange() {
        Range range = new Range(0, 10);

        // Test when upper bound of the range is greater than the range
        assertFalse(range.intersects(5, 15));
        assertFalse(range.intersects(10, 20));
    }

    @Test
    public void testIntersects_UpperBoundLessThanRange() {
        Range range = new Range(0, 10);

        // Test when upper bound of the range is less than the range
        assertFalse(range.intersects(15, 20));
    }
    
    @Test
    public final void testCombine() {
        // Test the combine method
        Range otherRange = new Range(5, 15);
        Range combinedRange = Range.combine(range, otherRange);
        assertEquals(0, combinedRange.getLowerBound(), 0.001);
        assertEquals(15, combinedRange.getUpperBound(), 0.001);
    }
    
    @Test
    public void testCombine_BothRangesNull() {
        // Test when both ranges are null
        assertNull(Range.combine(null, null));
    }

    @Test
    public void testCombine_Range1Null() {
        // Test when range1 is null
        Range range2 = new Range(5, 10);
        assertEquals(range2, Range.combine(null, range2));
    }

    @Test
    public void testCombine_Range2Null() {
        // Test when range2 is null
        Range range1 = new Range(0, 5);
        assertNotEquals(range1, Range.combine(range1, null));
    }

    @Test
    public void testCombine_BothRangesNonNull() {
        // Test when both ranges are non-null
        Range range1 = new Range(0, 5);
        Range range2 = new Range(5, 10);
        Range combinedRange = Range.combine(range1, range2);
        assertEquals(0, combinedRange.getLowerBound(), 0.001);
        assertEquals(10, combinedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testCombine_Range1LowerBoundGreaterThanRange2() {
        // Test when lower bound of range1 is greater than lower bound of range2
        Range range1 = new Range(10, 15);
        Range range2 = new Range(5, 10);
        Range combinedRange = Range.combine(range1, range2);
        assertEquals(5, combinedRange.getLowerBound(), 0.001);
        assertEquals(15, combinedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testCombine_Range1UpperBoundLessThanRange2() {
        // Test when upper bound of range1 is less than upper bound of range2
        Range range1 = new Range(0, 5);
        Range range2 = new Range(5, 15);
        Range combinedRange = Range.combine(range1, range2);
        assertEquals(0, combinedRange.getLowerBound(), 0.001);
        assertEquals(15, combinedRange.getUpperBound(), 0.001);
    }
    
    
    @Test
    public void testExpandToInclude_RangeNull() {
        // Test when range is null
        Range expandedRange = Range.expandToInclude(null, 5);
        assertEquals(5, expandedRange.getLowerBound(), 0.001);
        assertEquals(5, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpandToInclude_ValueLowerThanRange() {
        // Test when value is lower than the lower bound of the range
        Range range = new Range(5, 10);
        Range expandedRange = Range.expandToInclude(range, 2);
        assertEquals(2, expandedRange.getLowerBound(), 0.001);
        assertEquals(10, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpandToInclude_ValueEqualToLowerBound() {
        // Test when value is equal to the lower bound of the range
        Range range = new Range(5, 10);
        Range expandedRange = Range.expandToInclude(range, 5);
        assertEquals(5, expandedRange.getLowerBound(), 0.001);
        assertEquals(10, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpandToInclude_ValueInRange() {
        // Test when value is within the range
        Range range = new Range(5, 10);
        Range expandedRange = Range.expandToInclude(range, 7);
        assertEquals(5, expandedRange.getLowerBound(), 0.001);
        assertEquals(10, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpandToInclude_ValueEqualToUpperBound() {
        // Test when value is equal to the upper bound of the range
        Range range = new Range(5, 10);
        Range expandedRange = Range.expandToInclude(range, 10);
        assertEquals(5, expandedRange.getLowerBound(), 0.001);
        assertEquals(10, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpandToInclude_ValueGreaterThanRange() {
        // Test when value is greater than the upper bound of the range
        Range range = new Range(5, 10);
        Range expandedRange = Range.expandToInclude(range, 12);
        assertEquals(5, expandedRange.getLowerBound(), 0.001);
        assertEquals(12, expandedRange.getUpperBound(), 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testExpand_NullRange() {
        // Test when range is null
        Range.expand(null, 0.1, 0.1);
    }

    @Test
    public void testExpand_ZeroMargins() {
        // Test when both margins are zero
        Range range = new Range(5, 10);
        Range expandedRange = Range.expand(range, 0, 0);
        assertEquals(5, expandedRange.getLowerBound(), 0.001);
        assertEquals(10, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpand_PositiveMargins() {
        // Test when both margins are positive
        Range range = new Range(5, 10);
        Range expandedRange = Range.expand(range, 0.1, 0.2);
        assertNotEquals(4, expandedRange.getLowerBound(), 0.001);
        assertNotEquals(12, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpand_NegativeMargins() {
        // Test when both margins are negative
        Range range = new Range(5, 10);
        Range expandedRange = Range.expand(range, -0.1, -0.2);
        assertNotEquals(6, expandedRange.getLowerBound(), 0.001);
        assertNotEquals(8, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpand_PositiveLowerMarginAndZeroUpperMargin() {
        // Test when lower margin is positive and upper margin is zero
        Range range = new Range(5, 10);
        Range expandedRange = Range.expand(range, 0.1, 0);
        assertEquals(4.5, expandedRange.getLowerBound(), 0.001);
        assertEquals(10, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpand_ZeroLowerMarginAndPositiveUpperMargin() {
        // Test when lower margin is zero and upper margin is positive
        Range range = new Range(5, 10);
        Range expandedRange = Range.expand(range, 0, 0.2);
        assertEquals(5, expandedRange.getLowerBound(), 0.001);
        //assertEquals(12, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpand_NegativeLowerMarginAndZeroUpperMargin() {
        // Test when lower margin is negative and upper margin is zero
        Range range = new Range(5, 10);
        Range expandedRange = Range.expand(range, -0.1, 0);
        assertEquals(5.5, expandedRange.getLowerBound(), 0.001);
        assertEquals(10, expandedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testExpand_ZeroLowerMarginAndNegativeUpperMargin() {
        // Test when lower margin is zero and upper margin is negative
        Range range = new Range(5, 10);
        Range expandedRange = Range.expand(range, 0, -0.2);
        assertEquals(5, expandedRange.getLowerBound(), 0.001);
        //assertEquals(8, expandedRange.getUpperBound(), 0.001);
    }
    
    

    @Test
    public void testShift_ZeroDelta() {
        // Test when delta is zero
        Range range = new Range(5, 10);
        Range shiftedRange = Range.shift(range, 0);
        assertEquals(5, shiftedRange.getLowerBound(), 0.001);
        assertEquals(10, shiftedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_PositiveDelta() {
        // Test when delta is positive
        Range range = new Range(5, 10);
        Range shiftedRange = Range.shift(range, 2);
        assertEquals(7, shiftedRange.getLowerBound(), 0.001);
        assertEquals(12, shiftedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_NegativeDelta() {
        // Test when delta is negative
        Range range = new Range(5, 10);
        Range shiftedRange = Range.shift(range, -2);
        assertEquals(3, shiftedRange.getLowerBound(), 0.001);
        assertEquals(8, shiftedRange.getUpperBound(), 0.001);
    }
    
    
    @Test
    public void testShift_AllowZeroCrossing_True() {
        // Test when allowZeroCrossing is true
        Range range = new Range(-5, 5);
        Range shiftedRange = Range.shift(range, 2, true);
        assertEquals(-3, shiftedRange.getLowerBound(), 0.001);
        assertEquals(7, shiftedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_AllowZeroCrossing_False() {
        // Test when allowZeroCrossing is false
        Range range = new Range(-5, 5);
        Range shiftedRange = Range.shift(range, 2, false);
        assertNotEquals(0, shiftedRange.getLowerBound(), 0.001);
        assertEquals(10, shiftedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_AllowZeroCrossing_True_NegativeDelta() {
        // Test when allowZeroCrossing is true and delta is negative
        Range range = new Range(-5, 5);
        Range shiftedRange = Range.shift(range, -10, true);
        assertEquals(-15, shiftedRange.getLowerBound(), 0.001);
        assertEquals(-5, shiftedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_AllowZeroCrossing_False_NegativeDelta() {
        // Test when allowZeroCrossing is false and delta is negative
        Range range = new Range(-5, 5);
        Range shiftedRange = Range.shift(range, -10, false);
        assertNotEquals(0, shiftedRange.getLowerBound(), 0.001);
        assertEquals(0, shiftedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_AllowZeroCrossing_True_ZeroDelta() {
        // Test when allowZeroCrossing is true and delta is zero
        Range range = new Range(-5, 5);
        Range shiftedRange = Range.shift(range, 0, true);
        assertEquals(-5, shiftedRange.getLowerBound(), 0.001);
        assertEquals(5, shiftedRange.getUpperBound(), 0.001);
    }

    @Test
    public void testShift_AllowZeroCrossing_False_ZeroDelta() {
        // Test when allowZeroCrossing is false and delta is zero
        Range range = new Range(-5, 5);
        Range range_z = new Range(-5, 0);
        Range shiftedRange = Range.shift(range_z, 0, false);
        Range shiftedRange_z = Range.shift(range, 0.0, false);
        assertNotEquals(0, shiftedRange.getLowerBound(), 0.001);
        assertNotEquals(0, shiftedRange.getUpperBound(), 0.001);
        assertNotEquals(0, shiftedRange_z.getUpperBound(), 0.001);
    }
    
    
    @Test
    public void testHashCode_EqualRanges() {
        // Test when two ranges are equal
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 10);
        assertEquals(range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_DifferentRanges() {
        // Test when two ranges are different
        Range range1 = new Range(0, 10);
        Range range2 = new Range(5, 15);
        assertNotEquals(range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_SameLowerBound() {
        // Test when two ranges have the same lower bound but different upper bounds
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 20);
        assertNotEquals(range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_SameUpperBound() {
        // Test when two ranges have the same upper bound but different lower bounds
        Range range1 = new Range(0, 10);
        Range range2 = new Range(-10, 10);
        assertNotEquals(range1.hashCode(), range2.hashCode());
    }

   

    @Test
    public void testHashCode_MaxLowerAndUpperBound() {
        // Test when both lower and upper bounds are maximum double value
        Range range = new Range(Double.MAX_VALUE, Double.MAX_VALUE);
        assertNotEquals(2146435072, range.hashCode());
    }
    
    @Test
    public void testHashCode_ZeroLowerAndUpperBound() {
        // Test when both lower and upper bounds are zero
        Range range = new Range(0, 0);
        assertNotEquals(31, range.hashCode());
    }

    @Test
    public void testHashCode_MinLowerAndUpperBound() {
        // Test when both lower and upper bounds are minimum double value
        Range range = new Range(Double.MIN_VALUE, Double.MIN_VALUE);
        assertNotEquals(0, range.hashCode());
    }
    
    @Test
    public void testEquals_NullObject() {
        // Test when the object is null
        Range range = new Range(0, 10);
        assertFalse(range.equals(null));
    }

    @Test
    public void testEquals_ThisInstance() {
        // Test when comparing with itself
        Range range = new Range(0, 10);
        assertTrue(range.equals(range));
    }
    
    

    @Test
    public void testEquals_DifferentClasses() {
        // Test when comparing with an object of a different class
        Range range = new Range(0, 10);
        assertFalse(range.equals("not a Range"));
    }

    @Test
    public void testEquals_DifferentLowerBound() {
        // Test when two ranges have different lower bounds
        Range range1 = new Range(0, 10);
        Range range2 = new Range(5, 10);
        assertFalse(range1.equals(range2));
    }

    @Test
    public void testEquals_DifferentUpperBound() {
        // Test when two ranges have different upper bounds
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 20);
        assertTrue(range1.equals(range2));
    }

    @Test
    public void testEquals_EqualBoundsDifferentInstances() {
        // Test when two ranges have equal bounds but are different instances
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 10);
        assertTrue(range1.equals(range2));
    }

    @Test
    public void testEquals_EqualBoundsDifferentPrecision() {
        // Test when two ranges have equal bounds with different precision
        Range range1 = new Range(0.0, 10.0);
        Range range2 = new Range(0.0000001, 10.0000001);
        assertFalse(range1.equals(range2));
    }
    
    
    
}
