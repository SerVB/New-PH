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
package newph.core.staticFunction;

/**
 * Prints log messages.
 * TODO: Rewrite logging to human readable style.
 * TODO: Add useful features.
 */
public final class Logger {
    
    /**
     * Log Level.
     */
    public static enum LogLevel {
        
        /**
         * Debug.
         */
        DEBUG,
        
        /**
         * Info.
         */
        INFO,
        
        /**
         * Test.
         */
        TEST
        
    }
    
    /**
     * Prints log into console.
     * @param level Log Level.
     * @param in Place of logging (class and method names).
     * @param msg Message to display.
     */
    public final static void printLog(final LogLevel level, final String in, final String msg) {
        final String format;
        
        switch (level) {
            case DEBUG:
                format = "debug > ";
                break;
            case INFO:
                format = "info > ";
                break;
            case TEST:
                format = "test > ";
                break;
            default:
                format = "unknown > ";
                break;
        }
        
        final String output = "\n" +
                format + "In " + in + ":\n" +
                format + msg + "\n" +
                format + "end of message.";
        
        System.out.println(output);
    }
    
}
