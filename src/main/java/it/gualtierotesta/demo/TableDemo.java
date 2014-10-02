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
package it.gualtierotesta.demo;

import it.gualtierotesta.gdocx.GP;
import it.gualtierotesta.gdocx.GTbl;
import it.gualtierotesta.gdocx.GTc;
import it.gualtierotesta.gdocx.GTr;
import java.io.File;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.STBorder;

/**
 * Demo for table creation
 *
 * @author Gualtiero
 */
public class TableDemo {

    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void main(final String[] args) throws Docx4JException {

        // Create new document
        final WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        final MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();

        // Create document contents
        createFirstTable(mdp);

        createSecondTable(mdp);

        // Dump document XML on console
        System.out.println(XmlUtils.marshaltoString(mdp.getContents(), true, true));

        // Save document on temporary file
        final String filename = createNameForTemporaryFile();
        wordMLPackage.save(new File(filename));
        System.out.printf("Saved %s%n", filename);

    }

    private static String createNameForTemporaryFile() {
        return String.format("%s/gdocx_TableDemo.docx", System.getProperty("java.io.tmpdir"));
    }

    private static void createFirstTable(final MainDocumentPart pMdp) throws Docx4JException {
        final GP para1 = GP.create().text("Table with single line border width 12a and width set to auto");
        pMdp.getContents().getBody().getContent().add(para1);

        final GTbl tab = GTbl.create().borders(12L, STBorder.SINGLE, "FF0000", null).widthAuto();
        createRowsAndCells(tab);
        pMdp.getContents().getBody().getContent().add(tab);
    }

    private static void createSecondTable(final MainDocumentPart pMdp) throws Docx4JException {
        final GP para1 = GP.create().text("Table with no line border and 50% width");
        pMdp.getContents().getBody().getContent().add(para1);

        final GTbl tab = GTbl.create().borders(4L, STBorder.NIL, null, null).width(2500L, "pct");  // 2500 = 50%
        createRowsAndCells(tab);
        pMdp.getContents().getBody().getContent().add(tab);
    }

    private static void createRowsAndCells(final GTbl pTab) {
        final GTr row1
                = GTr.create().add(GTc.create().add(GP.create().text("R1C1"))).add(GTc.create().add(GP.create().text("R1C2")))
                .add(GTc.create().add(GP.create().text("R1C3")));
        pTab.add(row1);
        final GTr row2
                = GTr.create().add(GTc.create().add(GP.create().text("R2C1"))).add(GTc.create().add(GP.create().text("R2C2")))
                .add(GTc.create().add(GP.create().text("R2C3")));
        pTab.add(row2);
    }
}
