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
 * Extension to object Tr
 *
 * @author gualtiero
 * @see org.docx4j.wml.Tr
 */
public class GTr extends Tr {

    private static final ObjectFactory FACTORY = Context.getWmlObjectFactory();

    /**
     * Constructor
     */
    private GTr() {
        trPr = FACTORY.createTrPr();
    }

    /**
     * Static factory for empty table row (Tr)
     *
     * @return new GTr instance
     */
    @Nonnull
    public static GTr create() {
        return new GTr();
    }


    /**
     * Add an object to the table row
     *
     * @param obj object to be added
     * @return the same GTr instance
     */
    @Nonnull
    public GTr add(@Nonnull final Object obj) {

        Validate.notNull(obj, "Object not valid");

        getContent().add(obj);
        return this;
    }

    /**
     * Set row height with EXACT rule
     *
     * @param lHeight height of the row
     * @return same GTr instance
     */
    @Nonnull
    public GTr height(final long lHeight) {

        Validate.isTrue(0L < lHeight, "Height value not valid");

        final CTHeight cth = FACTORY.createCTHeight();
        cth.setVal(BigInteger.valueOf(lHeight));
        cth.setHRule(STHeightRule.EXACT);
        trPr.getCnfStyleOrDivIdOrGridBefore().add(FACTORY.createCTTrPrBaseTrHeight(cth));
        return this;
    }

    /**
     * Set row height with specified rule
     *
     * @param lHeight height of the row
     * @param eRule   height rule (for ex. STHeightRule.EXACT )
     * @return same GTr instance
     */
    @Nonnull
    public GTr height(final long lHeight, final STHeightRule eRule) {

        Validate.isTrue(0L < lHeight, "Height value not valid");
        Validate.notNull(eRule, "Rule not valid");

        final CTHeight cth = FACTORY.createCTHeight();
        cth.setVal(BigInteger.valueOf(lHeight));
        cth.setHRule(eRule);
        trPr.getCnfStyleOrDivIdOrGridBefore().add(FACTORY.createCTTrPrBaseTrHeight(cth));
        return this;
    }

    /**
     * Set horizontal alignment
     *
     * @param eAlign alignment type
     * @return same GTr instance
     */
    @Nonnull
    public GTr align(final JcEnumeration eAlign) {

        Validate.notNull(eAlign, "Alignment not valid");

        final Jc jc = FACTORY.createJc();
        jc.setVal(eAlign);
        trPr.getCnfStyleOrDivIdOrGridBefore().add(FACTORY.createCTTrPrBaseJc(jc));
        return this;
    }

    /**
     * Set "can split" attribute on the table row
     *
     * @return same GTr instance
     */
    @Nonnull
    public GTr cansplit() {
        trPr.getCnfStyleOrDivIdOrGridBefore().add(FACTORY.createCTTrPrBaseCantSplit(GFactory.BOOLEAN_TRUE));
        return this;
    }

    /**
     * @return the XML of the current instance
     */
    public String xml() {
        return XmlUtils.marshaltoString(this, false, true);
    }
}
