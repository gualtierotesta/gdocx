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
import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import org.apache.commons.lang.Validate;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.wml.CTShd;
import org.docx4j.wml.CTVerticalJc;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.STShd;
import org.docx4j.wml.STVerticalJc;
import org.docx4j.wml.TblWidth;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcPrInner;

/**
 * GDocx extension to object Tc
 *
 * @author gualtiero
 * @see org.docx4j.wml.Tc
 */
public class GTc extends Tc {

    private static final ObjectFactory FACTORY = Context.getWmlObjectFactory();

    /**
     * Constructor
     */
    private GTc() {
        tcPr = FACTORY.createTcPr();
    }

    /**
     * Static factory for empty table cell (Tc)
     *
     * @return new GTc instance
     */
    @Nonnull
    public static GTc create() {
        return new GTc();
    }

    /**
     * Add an object to the table cell
     *
     * @param obj object to be added
     * @return the same GTc instance
     */
    @Nonnull
    public GTc add(@Nonnull final Object obj) {

        Validate.notNull(obj, "Object not valid");

        getContent().add(obj);
        return this;
    }

    /**
     * Set table cell grid span
     *
     * @param lSpan grid span value
     * @return the same GTc instance
     */
    @Nonnull
    public GTc gridspan(final long lSpan) {

        Validate.isTrue(0L < lSpan, "Grid span value not valid");

        final TcPrInner.GridSpan gridSpan = FACTORY.createTcPrInnerGridSpan();
        gridSpan.setVal(BigInteger.valueOf(lSpan));
        tcPr.setGridSpan(gridSpan);
        return this;
    }

    /**
     * Set shading cell info
     *
     * @param sColor cell color
     * @param sFill cell fill color
     * @return same GTc instance
     */
    @Nonnull
    public GTc shd(@Nonnull final String sColor, @Nonnull final String sFill) {

        Validate.notEmpty(sColor, "String color not valid");
        Validate.notEmpty(sFill, "String fill not valid");

        final CTShd cTShd = FACTORY.createCTShd();
        cTShd.setVal(STShd.CLEAR);
        cTShd.setColor(sColor);
        cTShd.setFill(sFill);
        tcPr.setShd(cTShd);
        return this;
    }

    /**
     * Set cell vertical alignment
     *
     * @param eVertAlign enum with vertical alignment requirement (for ex.
     * STVerticalJc.CENTER )
     * @return same GTc instance
     */
    @Nonnull
    public GTc valign(@Nonnull final STVerticalJc eVertAlign) {

        Validate.notNull(eVertAlign, "Vertical alignment not valid");

        final CTVerticalJc ctvj = FACTORY.createCTVerticalJc();
        ctvj.setVal(eVertAlign);
        tcPr.setVAlign(ctvj);
        return this;
    }

    /**
     * Set cell width
     *
     * @param lWidth width value (should be greater than 0)
     * @param sType type (unit) of the value (for ex. "dxa")
     * @return same GTc instance
     */
    @Nonnull
    public GTc width(final long lWidth, @Nonnull final String sType) {

        Validate.isTrue(0L < lWidth, "Width value not valid");
        Validate.notEmpty(sType, "Type not valid");

        final TblWidth cellWidth = FACTORY.createTblWidth();
        cellWidth.setType(sType);
        cellWidth.setW(BigInteger.valueOf(lWidth));
        tcPr.setTcW(cellWidth);
        return this;
    }

    /**
     * Create top border
     *
     * @param lSize size of the border
     * @param eBorderLine type of the border line (enum)
     * @param sColor color of the border line. can be null if no border line
     * @param lSpace space value
     * @return same GTc instance
     */
    public GTc borderTop(final long lSize, @Nonnull final STBorder eBorderLine, final String sColor,
            final Long lSpace) {
        getTcBorders().setTop(GFactory.buildBorder(lSize, eBorderLine, sColor, lSpace));
        return this;
    }

    /**
     * Create bottom border
     *
     * @param lSize size of the border
     * @param eBorderLine type of the border line (enum)
     * @param sColor color of the border line. can be null if no border line
     * @param lSpace space value
     * @return same GTc instance
     */
    public GTc borderBottom(final long lSize, @Nonnull final STBorder eBorderLine, final String sColor,
            final Long lSpace) {
        getTcBorders().setBottom(GFactory.buildBorder(lSize, eBorderLine, sColor, lSpace));
        return this;
    }

    /**
     * Create left border
     *
     * @param lSize size of the border
     * @param eBorderLine type of the border line (enum)
     * @param sColor color of the border line. can be null if no border line
     * @param lSpace space value
     * @return same GTc instance
     */
    public GTc borderLeft(final long lSize, @Nonnull final STBorder eBorderLine, final String sColor,
            final Long lSpace) {
        getTcBorders().setLeft(GFactory.buildBorder(lSize, eBorderLine, sColor, lSpace));
        return this;
    }

    /**
     * Create right border
     *
     * @param lSize size of the border
     * @param eBorderLine type of the border line (enum)
     * @param sColor color of the border line. can be null if no border line
     * @param lSpace space value
     * @return same GTc instance
     */
    public GTc borderRight(final long lSize, @Nonnull final STBorder eBorderLine, final String sColor,
            final Long lSpace) {
        getTcBorders().setRight(GFactory.buildBorder(lSize, eBorderLine, sColor, lSpace));
        return this;
    }

    /**
     * Set merge cell info for the current instance
     *
     * @param sMergeVal merge value
     * @return same GTc instance
     */
    public GTc vmerge(@CheckForNull final String sMergeVal) {
        final TcPrInner.VMerge vmerge = FACTORY.createTcPrInnerVMerge();
        if (null != sMergeVal) {
            vmerge.setVal(sMergeVal);
        }
        tcPr.setVMerge(vmerge);
        return this;
    }

    /**
     * @return the XML of the current instance
     */
    public String xml() {
        return XmlUtils.marshaltoString(this, false, true);
    }

    /**
     * @return cell TCBorders (create if null)
     */
    private TcPrInner.TcBorders getTcBorders() {
        if (null == tcPr.getTcBorders()) {
            tcPr.setTcBorders(FACTORY.createTcPrInnerTcBorders());
        }
        return tcPr.getTcBorders();
    }

}
