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
import org.fest.assertions.Assertions;
import org.junit.Test;

/**
 * Test unit for class GTc
 *
 * @author gualtiero
 */
public class GTcTest {

    private static final String HELLO_WORLD = "Hello, world";

    @Test
    public void testCreate() {
        Assertions.assertThat(GTc.create()).isNotNull().isInstanceOf(GTc.class);
    }

    @Test
    public void testAdd() {
        final String exp1 = "<w:tc";
        final String exp2 = String.format("<w:t>%s</w:t>", HELLO_WORLD);
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD));

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testGridspan() {
        final long span = 2L;
        final String exp1 = "<w:tc";
        final String exp2 = String.format("<w:gridSpan w:val=\"%d\"/>", span);
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).gridspan(span);

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testShd() {
        final String exp1 = "<w:tc";
        final String exp2 = " <w:shd";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).shd("auto", "E6E6E6");

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testValign() {
        final String exp1 = "<w:tc";
        final String exp2 = "<w:vAlign w:val=\"center\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).valign(STVerticalJc.CENTER);

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testVmerge() {
        final String exp1 = "<w:tc";
        final String exp2 = "<w:vMerge w:val=\"RESTART\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).vmerge("RESTART");

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testVmerge2() {
        final String exp1 = "<w:tc";
        final String exp2 = "<w:vMerge/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).vmerge(null);

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testWidth() {
        final String exp1 = "<w:tc";
        final String exp2 = "<w:tcW w:w=\"300\" w:type=\"dxa\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).width(300L, "dxa");

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderTop() {
        final String exp1 = "<w:tcBorders>";
        final String exp2 = " <w:top w:val=\"single\" w:color=\"auto\" w:sz=\"4\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).borderTop(4L, STBorder.SINGLE, "auto");

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderBottom() {
        final String exp1 = "<w:tcBorders>";
        final String exp2 = " <w:bottom w:val=\"single\" w:color=\"auto\" w:sz=\"4\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).borderBottom(4L, STBorder.SINGLE, "auto");

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderLeft() {
        final String exp1 = "<w:tcBorders>";
        final String exp2 = " <w:left w:val=\"single\" w:color=\"auto\" w:sz=\"4\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).borderLeft(4L, STBorder.SINGLE, "auto");

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderRight() {
        final String exp1 = "<w:tcBorders>";
        final String exp2 = " <w:right w:val=\"single\" w:color=\"auto\" w:sz=\"4\"/>";
        final GTc iut = GTc.create().add(GP.create().text(HELLO_WORLD)).borderRight(4L, STBorder.SINGLE, "auto");

        Assertions.assertThat(iut).isNotNull().isInstanceOf(GTc.class);
        Assertions.assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

}
