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
import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class GFactoryTest {

    @Test
    public void testColor2Hex() {
        Assert.assertEquals("ffffff", GFactory.color2hex(Color.WHITE));
        Assert.assertEquals("000000", GFactory.color2hex(Color.BLACK));
        Assert.assertEquals("ff0000", GFactory.color2hex(Color.RED));
    }

    @Test
    public void testBuildBorder() {
        final CTBorder iut = GFactory.buildBorder(4L, STBorder.SINGLE, "auto");

        Assertions.assertThat(iut).isNotNull().isInstanceOf(CTBorder.class);
        Assertions.assertThat(iut.getVal()).isEqualTo(STBorder.SINGLE);
    }
}
