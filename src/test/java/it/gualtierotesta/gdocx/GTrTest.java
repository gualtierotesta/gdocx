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

import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.STHeightRule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unit for class GTr
 *
 * @author gualtiero
 */
public class GTrTest {

    private static final String HELLO_WORLD = "Hello, world";

    @Test
    public void testCreate() {
        assertThat(GTr.create()).isNotNull().isInstanceOf(GTr.class);
    }

    @Test
    public void testAdd() {
        final String exp1 = "<w:tr";
        final String exp2 = String.format("<w:t>%s</w:t>", HELLO_WORLD);
        final GTr iut = GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD)));

        assertThat(iut).isNotNull().isInstanceOf(GTr.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testHeight() {
        final String exp1 = "<w:tr";
        final String exp2 = "<w:trHeight w:val=\"100\" w:hRule=\"exact\"/>";
        final GTr iut = GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))).height(100L);

        assertThat(iut).isNotNull().isInstanceOf(GTr.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testHeightRule() {
        final String exp1 = "<w:tr";
        final String exp2 = "<w:trHeight w:val=\"100\" w:hRule=\"auto\"/>";
        final GTr iut =
                GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))).height(100L, STHeightRule.AUTO);

        assertThat(iut).isNotNull().isInstanceOf(GTr.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testAlign() {
        final String exp1 = "<w:tr";
        final String exp2 = "<w:jc w:val=\"right\"/>";
        final GTr iut = GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))).align(JcEnumeration.RIGHT);

        assertThat(iut).isNotNull().isInstanceOf(GTr.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testCanSplit() {
        final String exp1 = "<w:tr";
        final String exp2 = "<w:cantSplit/>";
        final GTr iut = GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))).cansplit();

        assertThat(iut).isNotNull().isInstanceOf(GTr.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }
}
