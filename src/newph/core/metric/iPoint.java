/*
 * MIT License
 *
 * Copyright (c) 2017
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package newph.core.metric;

import newph.core.staticFunction.MathOperations;
import newph.core.staticFunction.Logger;

/**
 * Point class. Contains coordinates "x" and "y".
 * TODO: Rename (refactor) to "Point" (after full source move).
 * @author SerVB
 * @since "GitHub new sources"
 */
public final class iPoint {

    /**
     * Constructs the object with zero coordinates.
     */
    public iPoint() {
        this(0, 0);
    }

    /**
     * Constructs the object with specified coordinates. 
     * @param x "x" coordinate.
     * @param y "y" coordinate.
     */
    public iPoint(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs the object by copying
     * parameters of the other Point object.
     * @param other Object to be copied. If null uses default constructor.
     */
    public iPoint(final iPoint other) {
        try {
            this.x = other.x;
            this.y = other.y;
        } catch (NullPointerException ex) {
            Logger.printLog(
                    Logger.LogLevel.DEBUG,
                    "Point(Point other)",
                    "other is null, nothing to copy!"
            );
        }
    }

    /**
     * Changes this object's coordinates:
     * Adds the pos'es coordinates to this object's coordinates.
     * @param pos Point object. If null nothing is done.
     */
    public final void add(final iPoint pos) {
        if (pos != null) {
            this.x += pos.x;
            this.y += pos.y;
        } else {
            Logger.printLog(
                    Logger.LogLevel.DEBUG,
                    "Point.add(Point pos)",
                    "pos is null, nothing to add!"
            );
        }
    }

    /**
     * Changes this object's coordinates:
     * Subtracts the pos'es coordinates from this objects's coordinates.
     * @param pos Point object. If null nothing is done.
     */
    public final void subtract(final iPoint pos) {
        if (pos != null) {
            this.x -= pos.x;
            this.y -= pos.y;
        } else {
            Logger.printLog(
                    Logger.LogLevel.DEBUG,
                    "Point.subtract(Point pos)",
                    "pos is null, nothing to subtract!"
            );
        }
    }

    /**
     * Constructs Point object.
     * Adds the second Point to the first Point object.
     * (Operator +)
     * @param first     The first Point.
     * @param second    The second Point.
     * @return          New Point object.
     */
    public static iPoint addToPoint(iPoint first, iPoint second) {
        // Check for nulls:
        if (first == null) {
            first = new iPoint();
        }
        if (second == null) {
            second = new iPoint();
        }
        
        // Calculate the result:
        iPoint res = new iPoint(first);
        res.add(second);
        
        return res;
    }

    /**
     * Constructs Point object.
     * Subtracts the second Point from the first Point object.
     * (Operator -)
     * @param first     The first Point.
     * @param second    The second Point.
     * @return          New Point object.
     */
    public static iPoint subtractFromPoint(iPoint first, iPoint second) {
        // Check for nulls:
        if (first == null) {
            first = new iPoint();
        }
        if (second == null) {
            second = new iPoint();
        }
        
        // Calculate the result:
        iPoint res = new iPoint(first);
        res.subtract(second);
        
        return res;
    }

    /**
     * Changes this object's coordinates:
     * Adds Size's metrics to object's metrics
     * ("w" to "x", "h" to "y").
     * @param size iSize object.
     */
    public final void add(final iSize size) {
        add(new iPoint(size));
    }

    /**
     * Change this object's coordinates:
     * Subtracts Size's metrics from object's metrics
     * ("w" from "x", "h" from "y").
     * @param size iSize object.
     */
    public final void subtract(final iSize size) {
        subtract(new iPoint(size));
    }

    /**
     * Adds value to each metric.
     * @param offs Value to be added.
     */
    public final void add(final int offs) {
        add(new iPoint(offs, offs));
    }

    /**
     * Subtracts values from metrics.
     * @param offs Value to be subtracted.
     */
    public final void subtract(final int offs) {
        subtract(new iPoint(offs, offs));
    }

    /**
     * Constructs Point object.
     * Adds the given value to the given Point object.
     * (Operator +)
     * @param point     The Point object.
     * @param increment The value.
     * @return          New Point object.
     */
    public static iPoint addToPoint(iPoint point, final int increment) {
        // Check for null:
        if (point == null) {
            point = new iPoint();
        }
        
        // Calculate the result:
        iPoint res = new iPoint(point);
        res.add(increment);
        
        return res;
    }

    /**
     * Constructs Point object.
     * Subtracts the given value from the given Point object.
     * (Operator -)
     * @param point     The Point object.
     * @param decrement The value.
     * @return          New Point object.
     */
    public static iPoint subtractFromPoint(iPoint point, final int decrement) {
        // Check for null:
        if (point == null) {
            point = new iPoint();
        }
        
        // Calculate the result:
        iPoint res = new iPoint(point);
        res.subtract(decrement);
        
        return res;
    }

    /**
     * Checks if the object is equal to the other object.
     * @param other Other object. If null then not equals to any object.
     * @return True if objects are equal, false if not.
     */
    public final boolean equals(final iPoint other) {
        if (other == null) {
            Logger.printLog(
                    Logger.LogLevel.DEBUG,
                    "Point.equals(Point other)",
                    "other is null, so not equals!"
            );
            return false;
        } else {
            return (this.x == other.x) && (this.y == other.y);
        }
    }

    /**
     * TODO: Write documentation.
     * TODO: Write test.
     * TODO: Rename (refactor) to "getSqDelta" (after full source move).
     * @param other
     * @return 
     */
    public final int GetSqDelta(final iPoint other) {
        if (other == null) {
            Logger.printLog(
                    Logger.LogLevel.DEBUG,
                    "Point.getSqDelta(Point other)",
                    "other is null, delta is 0!"
            );
            
            return 0;
        }
        
        return Math.max(
                MathOperations.dif(other.x, x),
                MathOperations.dif(other.y, y)
        );
    }

    /**
     * Returns the distance between this and other points (Pythagorean theorem).
     * TODO: Rename (refactor) to "getDelta" (after full source move).
     * @param other Other point. If null then returns 0.
     * @return Floored distance between two points.
     */
    public final int GetDelta(final iPoint other) {
        if (other == null) {
            Logger.printLog(
                    Logger.LogLevel.DEBUG,
                    "Point.getDelta(Point other)",
                    "other is null, delta is 0!"
            );
            
            return 0;
        }
        
        int dx = this.x - other.x;
        int dy = this.y - other.y;

        return (int) Math.sqrt((double)(dx * dx + dy * dy));
    }

    /**
     * Moves the "x" coordinate to the new position.
     * TODO: Rename (refactor) to "moveX" (after full source move).
     * @param offset_x Value to be added to the "x" coordinate.
     */
    public final void MoveX(final int offset_x) {
        this.x += offset_x;
    }

    /**
     * Moves the "y" coordinate to the new position.
     * TODO: Rename (refactor) to "moveY" (after full source move).
     * @param offset_y Value to be added to the "y" coordinate.
     */
    public final void MoveY(final int offset_y) {
        this.y += offset_y;
    }

    /**
     * Moves coordinates to the new position.
     * TODO: Rename (refactor) to "move" (after full source move).
     * @param offset_x Value to be added to the "x" coordinate.
     * @param offset_y Value to be added to the "y" coordinate.
     */
    public final void Move(final int offset_x, final int offset_y) {
        MoveX(offset_x);
        MoveY(offset_y);
    }
    
    /**
     * Constructs the Point object from the Size object.
     * "x" sets to "w", "y" sets to "h".
     * @param size The Size object. If null uses default constructor.
     */
    public iPoint(final iSize size) {
        if (size == null) {
            Logger.printLog(
                    Logger.LogLevel.DEBUG,
                    "Point(Size size)",
                    "size is null, set metrics to 0!"
            );
            
            this.x = 0;
            this.y = 0;
        } else {
            this.x = size.w;
            this.y = size.h;
        }
    }

    /**
     * "x" coordinate (Horizontal).
     */
    public int x;
    
    /**
     * "y" coordinate (Vertical).
     */
    public int y;

}
