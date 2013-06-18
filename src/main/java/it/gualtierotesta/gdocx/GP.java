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
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.wml.*;

import javax.annotation.Nonnull;
import java.math.BigInteger;

/**
 * Extension to object P
 *
 * @author gualtiero
 * @see org.docx4j.wml.P
 */
public class GP extends P {

    private static final ObjectFactory FACTORY = Context.getWmlObjectFactory();
    private RPr rPr;

    /**
     * Constructor
     */
    private GP() {
        pPr = FACTORY.createPPr();
        pPr.setRPr(FACTORY.createParaRPr());
    }

    /**
     * Static factory for empty paragraph (P)
     *
     * @return new GP instance
     */
    @Nonnull
    public static GP create() {
        return new GP();
    }

//    /**
//     * Add text to the current P as new Run
//     *
//     * @param sText text string to be added
//     * @return same GP instance
//     */
//    @Nonnull
//    public GP text(@Nonnull final String sText) {
//
//        Validate.notNull(sText, "Text is null");
//
//        final Text text = FACTORY.createText();
//        text.setValue(sText);
//        final R run = FACTORY.createR();
//        run.getContent().add(text);
//        run.setRPr(getRPr());
//        getContent().add(run);
//        return this;
//    }

    /**
     * Add all strings in the array, each on a separate Run
     *
     * @param aText array of text strings
     * @return same GP instance
     */
    @Nonnull
    public GP text(@Nonnull final String... aText) {

        Validate.notEmpty(aText, "String array not valid");

        final int length = aText.length;
        for (int i = 0; i < length; i++) {
            if (null != aText[i]) {
                final Text text = FACTORY.createText();
                text.setValue(aText[i]);
                final R run = FACTORY.createR();
                run.getContent().add(text);
                run.setRPr(getRPr());
                getContent().add(run);
                if (i + 1 != length) {
                    final R runBr = FACTORY.createR();
                    runBr.getContent().add(FACTORY.createBr());
                    getContent().add(runBr);
                }
            }
        }
        return this;
    }

    /**
     * Set font name and size
     *
     * @param sFontName name of the font
     * @param lFontSize size of the font
     * @return same GP instance
     */
    @Nonnull
    public GP font(final String sFontName, final long lFontSize) {
        return fontName(sFontName).fontSize(lFontSize);
    }

    /**
     * Set font name
     *
     * @param sFontName name of the font
     * @return same GP instance
     */
    @Nonnull
    public GP fontName(@Nonnull final String sFontName) {

        Validate.notEmpty(sFontName, "Font name not valid");

        final RFonts rFonts = FACTORY.createRFonts();
        rFonts.setAscii(sFontName);
        rFonts.setHAnsi(sFontName);
        rFonts.setCs(sFontName);
        getRPr().setRFonts(rFonts);
        return this;
    }

    /**
     * Set font size
     *
     * @param lFontSize size of the font
     * @return same GP instance
     */
    @Nonnull
    public GP fontSize(final long lFontSize) {

        Validate.isTrue(0L < lFontSize, "Font size not valid");

        final HpsMeasure hpsMeasure = FACTORY.createHpsMeasure();
        hpsMeasure.setVal(BigInteger.valueOf(lFontSize * 2L));
        getRPr().setSz(hpsMeasure);
        getRPr().setSzCs(hpsMeasure);
        return this;
    }

    /**
     * Enable bold
     *
     * @return same GP instance
     */
    @Nonnull
    public GP bold() {
        getRPr().setB(GFactory.BOOLEAN_TRUE);
        return this;
    }

    /**
     * Enable italic
     *
     * @return same GP instance
     */
    @Nonnull
    public GP italic() {
        getRPr().setI(GFactory.BOOLEAN_TRUE);
        return this;
    }

    /**
     * Set text color
     *
     * @param sColor color code as 6 char color2hex string (RRGGBB)
     * @return same GP instance
     */
    @Nonnull
    public GP color(@Nonnull final String sColor) {

        Validate.notEmpty(sColor, "String color not valid");

        final Color par = FACTORY.createColor();
        par.setVal(sColor);
        getRPr().setColor(par);
        return this;
    }

    /**
     * Set text alignment (right, left, center,...)
     *
     * @param eTextAlign enumeration with alignment options
     * @return same GP instance
     */
    @Nonnull
    public GP align(@Nonnull final JcEnumeration eTextAlign) {

        Validate.notNull(eTextAlign, "Text alignment not valid");

        final Jc jc = FACTORY.createJc();
        jc.setVal(eTextAlign);
        getPPr().setJc(jc);
        return this;
    }

    /**
     * @return the XML of the current instance
     */
    public String xml() {
        return XmlUtils.marshaltoString(this, false, true);
    }

    /**
     * @return the rPr (creating it if necessary)
     */
    private RPr getRPr() {
        if (null == rPr) {
            rPr = FACTORY.createRPr();
        }
        return rPr;
    }

}
