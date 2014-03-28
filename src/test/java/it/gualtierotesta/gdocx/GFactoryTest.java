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

import org.docx4j.wml.CTBorder;
import org.docx4j.wml.STBorder;
import org.junit.Test;

import java.awt.*;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;


public class GFactoryTest {

    private static final String COLOR = "FF0066";

    @Test
    public void convertAwtColorToHex() {
        assertThat(GFactory.color2hex(Color.WHITE)).isEqualTo("ffffff");
        assertThat(GFactory.color2hex(Color.BLACK)).isEqualTo("000000");
        assertThat(GFactory.color2hex(Color.RED)).isEqualTo("ff0000");
    }

    @Test
    public void singleLineBorder() {
        // given
        final STBorder borderLine = STBorder.SINGLE;
        Long space = Long.valueOf(12);
        // when
        final CTBorder iut = GFactory.buildBorder(4L, borderLine, COLOR, space);
        // then
        assertThat(iut).isNotNull().isInstanceOf(CTBorder.class);
        assertThat(iut.getVal()).isEqualTo(borderLine);
        assertThat(iut.getColor()).isEqualTo(COLOR);
        assertThat(iut.getSpace()).isEqualTo(BigInteger.valueOf(space));
    }

    @Test
    public void noLineBorder() {
        // given
        final STBorder borderLine = STBorder.NIL;
        // when
        final CTBorder iut = GFactory.buildBorder(12L, borderLine, COLOR, null);
        // then
        assertThat(iut).isNotNull().isInstanceOf(CTBorder.class);
        assertThat(iut.getVal()).isEqualTo(borderLine);
        assertThat(iut.getColor()).isNull();
        assertThat(iut.getSz()).isNull();
    }
}
