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
import org.docx4j.wml.STBorder;
import org.docx4j.wml.STTblLayoutType;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test unit for class GTbl
 *
 * @author gualtiero
 */
public class GTblTest {

    private static final String HELLO_WORLD = "Hello, world";

    @Test
    public void testCreate() {
        assertThat(GTbl.create()).isNotNull().isInstanceOf(GTbl.class);
    }

    @Test
    public void testAdd() {
        final String exp1 = "<w:tbl";
        final String exp2 = String.format("<w:t>%s</w:t>", HELLO_WORLD);
        final GTbl iut = GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))));

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testAlign() {
        final String exp1 = "<w:tbl";
        final String exp2 = "<w:jc w:val=\"right\"/>";
        final GTbl iut = GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))))
            .align(JcEnumeration.RIGHT);

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void width_Dxa() {
        final String exp1 = "<w:tbl";
        final String exp2 = "<w:tblW w:w=\"250\" w:type=\"dxa\"/>";
        final GTbl iut =
            GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD)))).width(250L, "dxa");

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void width_Pct() {
        final String exp1 = "<w:tbl";
        final String exp2 = "<w:tblW w:w=\"2500\" w:type=\"pct\"/>";
        final GTbl iut =
            GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))))
                .width(2500, "pct");

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void widthAuto() {
        final String exp1 = "<w:tbl";
        final String exp2 = "<w:tblW w:w=\"0\" w:type=\"auto\"/>";
        final GTbl iut =
            GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))))
                .widthAuto();

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testLayout() {
        final String exp1 = "<w:tbl";
        final String exp2 = "<w:tblLayout w:type=\"fixed\"/>";
        final GTbl iut = GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))))
            .layout(STTblLayoutType.FIXED);

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
     public void testLook() {
        final String exp1 = "<w:tbl";
        final String exp2 = "<w:tblLook w:val=\"01E0\"/>";
        final GTbl iut = GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))))
            .look("01E0");

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testGrid() {
        final String exp1 = "<w:tbl";
        final String exp2 = "<w:gridCol w:w=\"200\"/>";
        final GTbl iut = GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))))
            .grid(100L, 200L, 300L);

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderTop() {
        final String exp1 = "<w:tblBorders>";
        final String exp2 = "<w:top w:val=\"double\" w:color=\"F0E000\" w:sz=\"3\"/>";
        final GTbl iut = GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))))
            .borderTop(3L, STBorder.DOUBLE,"F0E000", null);

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorderBottom() {
        final String exp1 = "<w:tblBorders>";
        final String exp2 = "<w:bottom w:val=\"zigZag\" w:color=\"001122\" w:sz=\"10\" w:space=\"30\"/>";
        final GTbl iut = GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))))
            .borderBottom(10L, STBorder.ZIG_ZAG,"001122", 30L);

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBorders() {
        final String exp1 = "<w:tblBorders>";
        final String exp2 = "<w:insideH w:val=\"apples\" w:color=\"334455\" w:sz=\"3\" w:space=\"0\"/>";
        final GTbl iut = GTbl.create().add(GTr.create().add(GTc.create().add(GP.create().text(HELLO_WORLD))))
            .borders(3L, STBorder.APPLES,"334455", 0L);

        assertThat(iut).isNotNull().isInstanceOf(GTbl.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }
}
