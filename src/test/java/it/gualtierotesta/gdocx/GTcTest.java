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

import org.docx4j.wml.STBorder;
import org.docx4j.wml.STVerticalJc;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Test unit for class GTc
 *
 * @author gualtiero
 */
public class GTcTest {

    private static final String HELLO_WORLD = "Hello, world";

    @Test
    public void testCreate() {
        assertThat(GTc.create()).isNotNull().isInstanceOf(GTc.class);
    }

    @Test
    public void testAdd() {
        final String exp1 = "<w:tc";
        final String exp2 = String.format("<w:t>%s</w:t>", HELLO_WORLD);
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD));

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testGridspan() {
        final long span = 2L;
        final String exp1 = "<w:tc";
        final String exp2 = String.format("<w:gridSpan w:val=\"%d\"/>", span);
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).gridspan(span);

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testShd() {
        final String exp1 = "<w:tc";
        final String exp2 = " <w:shd";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).shd("auto", "E6E6E6");

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testValign() {
        final String exp1 = "<w:tc";
        final String exp2 = "<w:vAlign w:val=\"center\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).valign(STVerticalJc.CENTER);

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testVmerge() {
        final String exp1 = "<w:tc";
        final String exp2 = "<w:vMerge w:val=\"RESTART\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).vmerge("RESTART");

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testVmerge2() {
        final String exp1 = "<w:tc";
        final String exp2 = "<w:vMerge/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).vmerge(null);

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testWidth() {
        final String exp1 = "<w:tc";
        final String exp2 = "<w:tcW w:w=\"300\" w:type=\"dxa\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).width(300L, "dxa");

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderTop() {
        final String exp1 = "<w:tcBorders>";
        final String exp2 = " <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).borderTop(4L, STBorder.SINGLE, "auto", null);

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderBottom() {
        final String exp1 = "<w:tcBorders>";
        final String exp2 = " <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"25\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).borderBottom(4L, STBorder.SINGLE, "auto", 25L);

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderLeft() {
        final String exp1 = "<w:tcBorders>";
        final String exp2 = " <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"100\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).borderLeft(4L, STBorder.SINGLE, "auto", 100L);

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderRight() {
        final String exp1 = "<w:tcBorders>";
        final String exp2 = " <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\" w:space=\"0\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).borderRight(4L, STBorder.SINGLE, "auto", 0L);

        assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

}
