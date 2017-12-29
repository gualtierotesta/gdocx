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

import it.gualtierotesta.gdocx.GFactory;
import it.gualtierotesta.gdocx.GP;
import java.awt.Color;
import java.io.File;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.JcEnumeration;

/**
 * Demo per the GP class
 *
 * @author Gualtiero
 */
public class GPDemo {

    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void main(final String[] args) throws Exception {

        // Create new document
        final WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        final MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();

        // Create document contents
        createFirstParagraph(mdp);
        createSecondParagraph(mdp);
        createThirdParagraph(mdp);
        createFourthParagraph(mdp);

        // Dump document XML on console
        System.out.println(XmlUtils.marshaltoString(mdp.getContents(), true, true));

        // Save document on temporary file
        final String filename = createNameForTemporaryFile();
        wordMLPackage.save(new File(filename));
        System.out.printf("Saved %s%n", filename);

    }

    private static String createNameForTemporaryFile() {
        return String.format("%s/gdocx_GPDemo.docx", System.getProperty("java.io.tmpdir"));
    }

    private static void createFourthParagraph(final MainDocumentPart pMdp) throws Docx4JException {
        final GP para4 = GP.create().text("Verdana 6 Italic justified",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nec dolor non erat lacinia porta. "
                + "Vestibulum condimentum eleifend euismod. In viverra lacus ut justo posuere ullamcorper vitae id ipsum. "
                + "Sed vestibulum sagittis ipsum, in rhoncus justo imperdiet et. Donec sed elementum elit. Proin id "
                + "tortor sed ipsum vehicula lacinia. Vivamus vulputate nisi at magna lobortis, quis vulputate purus "
                + "egestas. Integer dapibus lacus ultricies, semper elit mollis, tincidunt nisi. Vestibulum consectetur "
                + "ipsum pharetra ipsum scelerisque facilisis. Proin blandit massa nec tortor rutrum imperdiet. "
                + "Etiam fermentum faucibus dolor sed viverra. Etiam sed suscipit metus. Nullam iaculis quis turpis "
                + "et congue. Nulla egestas, odio sed aliquet placerat, risus enim euismod nisi, ut elementum dui "
                + "lacus a eros. Curabitur rutrum justo quis arcu tempor, eget faucibus sapien facilisis. "
        )
                .font("Verdana", 6L).italic().align(JcEnumeration.BOTH);
        pMdp.getContents().getBody().getContent().add(para4);
    }

    private static void createThirdParagraph(final MainDocumentPart pMdp) throws Docx4JException {
        final GP para3 = GP.create().text("Verdana 16 bold blue center aligned").font("Verdana", 16L).bold()
                .color(GFactory.color2hex(Color.blue)).align(JcEnumeration.CENTER);
        pMdp.getContents().getBody().getContent().add(para3);
    }

    private static void createSecondParagraph(final MainDocumentPart pMdp) throws Docx4JException {
        final GP para2 = GP.create().text("Tahoma 24 red right aligned").font("Tahoma", 24L).color("FF0000")
                .align(JcEnumeration.RIGHT);
        pMdp.getContents().getBody().getContent().add(para2);
    }

    private static void createFirstParagraph(final MainDocumentPart pMdp) throws Docx4JException {
        final GP para1 = GP.create().text("No formatting");
        pMdp.getContents().getBody().getContent().add(para1);
    }
}
