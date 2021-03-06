//
//  StdOutErrLevel.java
//  ChatServer
//
//  Created by John McKisson on 5/7/08.
//  Copyright 2008 __MyCompanyName__. All rights reserved.
//
package com.presence.chat.log;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.util.logging.Level;

/**
 * Class defining 2 new Logging levels, one for STDOUT, one for STDERR,
 * used when multiplexing STDOUT and STDERR into the same rolling log file
 * via the Java Logging APIs.
 */
public class StdOutErrLevel extends Level {
    
	public static int OUTLVLINT = Level.INFO.intValue() + 53;
	public static int ERRLVLINT = Level.INFO.intValue() + 54;
	
    /**
     * Private constructor
     */
    private StdOutErrLevel(String name, int value) {
        super(name, value);
    }
    /**
     * Level for STDOUT activity.
     */
    public static Level STDOUT = new StdOutErrLevel("STDOUT", OUTLVLINT);
    /**
     * Level for STDERR activity
     */
    public static Level STDERR = new StdOutErrLevel("STDERR", ERRLVLINT);

    /**
     * Method to avoid creating duplicate instances when deserializing the
     * object.
     * @return the singleton instance of this <code>Level</code> value in this
     * classloader
     * @throws ObjectStreamException If unable to deserialize
     */
    protected Object readResolve()
        throws ObjectStreamException {
        if (this.intValue() == STDOUT.intValue())
            return STDOUT;
        if (this.intValue() == STDERR.intValue())
            return STDERR;
        throw new InvalidObjectException("Unknown instance :" + this);
    }        
    
}