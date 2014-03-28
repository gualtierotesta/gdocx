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
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unit for class GP
 *
 * @author Gualtiero
 */
public class GPTest {

    private static final String HELLO_WORLD = "Hello, world";

    @Test
    public void testCreate() {
        assertThat(GP.create()).isNotNull().isInstanceOf(GP.class);
    }

    @Test
    public void testText() {
        final String exp = String.format("<w:t>%s</w:t>", HELLO_WORLD);
        final GP iut = GP.create().text(HELLO_WORLD);

        assertThat(iut).isNotNull().isInstanceOf(GP.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp);
    }

    @Test
    public void testTextArray() {
        final String[] aStr = {"Hello, world", "Hello, Mars"};
        final String exp = String.format("<w:t>%s</w:t>", aStr[1]);
        final GP iut = GP.create().text(aStr);

        assertThat(iut).isNotNull().isInstanceOf(GP.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp);
    }

    @Test
    public void testFont() {
        final String fontName = "Tahoma";
        final long fontSize = 12L;
        final String exp1 = String.format(" w:ascii=\"%s\"", fontName);
        final String exp2 = String.format("<w:sz w:val=\"%d\"/>", fontSize * 2L);
        final GP iut = GP.create().text(HELLO_WORLD).font(fontName, fontSize);

        assertThat(iut).isNotNull().isInstanceOf(GP.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp1).contains(exp2);
    }

    @Test
    public void testBold() {
        final String exp = String.format("<w:t>%s</w:t>", HELLO_WORLD);
        final String exp2 = "<w:b/>";
        final GP iut = GP.create().text(HELLO_WORLD).bold();

        assertThat(iut).isNotNull().isInstanceOf(GP.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp).contains(exp2);
    }

    @Test
    public void testItalic() {
        final String exp = String.format("<w:t>%s</w:t>", HELLO_WORLD);
        final String exp2 = "<w:i/>";
        final GP iut = GP.create().text(HELLO_WORLD).italic();

        assertThat(iut).isNotNull().isInstanceOf(GP.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp).contains(exp2);
    }

    @Test
    public void testColor() {
        final String exp = String.format("<w:t>%s</w:t>", HELLO_WORLD);
        final String exp2 = "<w:color w:val=\"F0F0F0\"/>";
        final GP iut = GP.create().text(HELLO_WORLD).color("F0F0F0");

        assertThat(iut).isNotNull().isInstanceOf(GP.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp).contains(exp2);
    }

    @Test
    public void testAlign() {
        final String exp = String.format("<w:t>%s</w:t>", HELLO_WORLD);
        final String exp2 = " <w:jc w:val=\"center\"/>";
        final GP iut = GP.create().text(HELLO_WORLD).align(JcEnumeration.CENTER);

        assertThat(iut).isNotNull().isInstanceOf(GP.class);
        assertThat(iut.xml()).isNotEmpty().contains(exp).contains(exp2);
    }

    @Test
    public void testXml() {
        final GP iut = GP.create().text(HELLO_WORLD);
        final String exp = "<?xml version=\"1.0\"";

        assertThat(iut).isNotNull().isInstanceOf(GP.class);
        assertThat(iut.xml()).isNotEmpty().startsWith(exp);
    }
}
