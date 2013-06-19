/*
 * Copyright (c) 2013. Gualtiero Testa
 *
 *     http://www.gualtierotesta.it
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.gualtierotesta.gdocx;

import org.apache.commons.lang.Validate;
import org.docx4j.jaxb.Context;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.STBorder;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.awt.*;
import java.math.BigInteger;

/**
 * Utility Factory
 *
 * @author Gualtiero
 */
public final class GFactory {

    public static final BooleanDefaultTrue BOOLEAN_TRUE = new BooleanDefaultTrue();
    public static final BooleanDefaultTrue BOOLEAN_FALSE;
    private static final ObjectFactory FACTORY = Context.getWmlObjectFactory();

    static {
        BOOLEAN_FALSE = FACTORY.createBooleanDefaultTrue();
        BOOLEAN_FALSE.setVal(Boolean.FALSE);
    }

    private GFactory() {
        // Static class
    }

    /**
     * Convert java.awt.Color in hex string code (RRGGBB)
     *
     * @param color the color to be converted
     * @return a string with color hex code
     */
    public static String color2hex(@Nonnull final Color color) {

        Validate.notNull(color, "Color not valid");

        final String rgb = Integer.toHexString(color.getRGB());
        return rgb.substring(2, rgb.length());
    }


    /**
     * Build a CT border
     *
     * @param lSize       size of the border
     * @param eBorderLine type of the border line (enum)
     * @param sColor      color of the border line. can be null if no border line
     * @return same GTc instance
     */
    public static CTBorder buildBorder(final long lSize, @Nonnull final STBorder eBorderLine,
        @CheckForNull final String sColor) {

        Validate.isTrue(0L < lSize, "Size value not valid");
        Validate.notNull(eBorderLine, "Border Line not valid");

        final CTBorder ctBorder = FACTORY.createCTBorder();
        ctBorder.setVal(eBorderLine);
        if (STBorder.NIL != eBorderLine) {
            if (null != sColor && !sColor.isEmpty()) {
                ctBorder.setColor(sColor);
            }
            ctBorder.setSz(BigInteger.valueOf(lSize));
        }
        return ctBorder;
    }
}
