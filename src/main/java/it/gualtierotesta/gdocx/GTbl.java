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

import java.math.BigInteger;
import javax.annotation.Nonnull;
import org.apache.commons.lang.Validate;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.CTTblLayoutType;
import org.docx4j.wml.CTTblLook;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.STTblLayoutType;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.TblGridCol;
import org.docx4j.wml.TblWidth;

/**
 * GDocx extension to object Tbl
 *
 * @author gualtiero
 * @see org.docx4j.wml.Tbl
 */
public class GTbl extends Tbl {

    private static final ObjectFactory FACTORY = Context.getWmlObjectFactory();

    /**
     * Constructor
     */
    private GTbl() {
        tblPr = FACTORY.createTblPr();
    }

    /**
     * Static factory for empty table (Tbl)
     *
     * @return new GTbl instance
     */
    @Nonnull
    public static GTbl create() {
        return new GTbl();
    }

    /**
     * Add an object to the table
     *
     * @param obj object to be added
     * @return the same GTbl instance
     */
    @Nonnull
    public GTbl add(@Nonnull final Object obj) {

        Validate.notNull(obj, "Object not valid");

        getContent().add(obj);
        return this;
    }

    /**
     * Set horizontal alignment
     *
     * @param eAlign alignment type (for ex. JcEnumeration.CENTER )
     * @return same GTr instance
     */
    @Nonnull
    public GTbl align(@Nonnull final JcEnumeration eAlign) {

        Validate.notNull(eAlign, "Alignment not valid");

        final Jc jc = FACTORY.createJc();
        jc.setVal(eAlign);
        tblPr.setJc(jc);
        return this;
    }

    /**
     * Set the preferred width of the table
     *
     * @param lWidth the value of the preferred width of the table. If omitted,
     * the value is assumed to be zero.
     * @param sType type (unit) of the value Possible values are: auto, dxa,
     * nil, pict
     * @return same GTbl instance
     */
    @Nonnull
    public GTbl width(final long lWidth, @Nonnull final String sType) {

        Validate.isTrue(0L <= lWidth, "Width value not valid");
        Validate.notEmpty(sType, "Type not valid");

        final TblWidth tblWidth = FACTORY.createTblWidth();
        tblWidth.setType(sType);
        tblWidth.setW(BigInteger.valueOf(lWidth));
        tblPr.setTblW(tblWidth);
        return this;
    }

    /**
     * Set the preferred width of the table to auto
     *
     * @return same GTbl instance
     */
    @Nonnull
    public GTbl widthAuto() {
        return width(0L, "auto");
    }

    /**
     * Set table layout
     *
     * @param eType layout type (for ex. STTblLayoutType.FIXED)
     * @return same GTbl instance
     */
    @Nonnull
    public GTbl layout(@Nonnull final STTblLayoutType eType) {

        Validate.notNull(eType, "Layout type not valid");

        final CTTblLayoutType ctTblLayoutType = FACTORY.createCTTblLayoutType();
        ctTblLayoutType.setType(eType);
        tblPr.setTblLayout(ctTblLayoutType);
        return this;
    }

    /**
     * Set table look
     *
     * @param sLookValue look value (for ex. "01E0" )
     * @return same GTbl instance
     */
    @Nonnull
    public GTbl look(@Nonnull final String sLookValue) {

        Validate.notEmpty(sLookValue, "Look value not valid");

        final CTTblLook ctTblLook = FACTORY.createCTTblLook();
        ctTblLook.setVal(sLookValue);
        tblPr.setTblLook(ctTblLook);
        return this;
    }

    /**
     * Set table grid values
     *
     * @param aValues long array of grid values
     * @return same GTbl instance
     */
    @Nonnull
    public GTbl grid(@Nonnull final long... aValues) {

        Validate.notNull(aValues, "Grid values array not valid");

        if (0 < aValues.length) {
            tblGrid = FACTORY.createTblGrid();
            for (final long value : aValues) {
                final TblGridCol gridCol = FACTORY.createTblGridCol();
                gridCol.setW(BigInteger.valueOf(value));
                tblGrid.getGridCol().add(gridCol);
            }
        }
        return this;
    }

    /**
     * Create top border
     *
     * @param lSize size of the border
     * @param eBorderLine type of the border line (enum)
     * @param sColor color of the border line. can be null if no border line
     * @param lSpace space value
     * @return same GTbl instance
     */
    public GTbl borderTop(final long lSize, @Nonnull final STBorder eBorderLine, final String sColor,
            final Long lSpace) {
        getTblBorders().setTop(GFactory.buildBorder(lSize, eBorderLine, sColor, lSpace));
        return this;
    }

    /**
     * Create bottom border
     *
     * @param lSize size of the border
     * @param eBorderLine type of the border line (enum)
     * @param sColor color of the border line. can be null if no border line
     * @param lSpace space value
     * @return same GTbl instance
     */
    public GTbl borderBottom(final long lSize, @Nonnull final STBorder eBorderLine, final String sColor,
            final Long lSpace) {
        getTblBorders().setBottom(GFactory.buildBorder(lSize, eBorderLine, sColor, lSpace));
        return this;
    }

    /**
     * Create all borders
     *
     * @param lSize size of the border
     * @param eBorderLine type of the border line (enum)
     * @param sColor color of the border line. can be null if no border line
     * @param lSpace space value
     * @return same GTbl instance
     */
    public GTbl borders(final long lSize, @Nonnull final STBorder eBorderLine, final String sColor, final Long lSpace) {
        final CTBorder ctBorder = GFactory.buildBorder(lSize, eBorderLine, sColor, lSpace);
        getTblBorders().setTop(ctBorder);
        getTblBorders().setBottom(ctBorder);
        getTblBorders().setInsideH(ctBorder);
        getTblBorders().setInsideV(ctBorder);
        getTblBorders().setLeft(ctBorder);
        getTblBorders().setRight(ctBorder);
        return this;
    }

    /**
     * @return the XML of the current instance
     */
    public String xml() {
        return XmlUtils.marshaltoString(this, false, true);
    }

    private TblBorders getTblBorders() {
        if (null == tblPr.getTblBorders()) {
            tblPr.setTblBorders(FACTORY.createTblBorders());
        }
        return tblPr.getTblBorders();
    }
}
