/*
 * Copyright 2021 - 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.sbm.mule.resource;

import org.springframework.sbm.project.resource.RewriteSourceFileHolder;
import lombok.Getter;
import org.mulesoft.schema.mule.core.MuleType;
import org.openrewrite.xml.tree.Xml;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class MuleXml extends RewriteSourceFileHolder<Xml.Document> {

    @Getter
    private final Document xmlDocument;

    private final XPathExpression flowNameXpath;
    private final XPathExpression pathXpath;
    private final XPathExpression payloadXpath;
    private final XPathExpression payloadMimeTypeXpath;
    private final XPathExpression hostXpath;
    private final XPathExpression portXpath;

    @Getter
    private MuleType muleType;

    public MuleXml(Path absoluteProjectDir, Xml.Document resource) {
        super(absoluteProjectDir.resolve(resource.getSourcePath()), resource);
        MuleXmlUnmarshaller muleXmlUnmarshaller = new MuleXmlUnmarshaller();
        muleType = muleXmlUnmarshaller.unmarshal(print());

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            xmlDocument = builder.parse(new ByteArrayInputStream(print().getBytes(StandardCharsets.UTF_8)));
            XPath xPath = XPathFactory.newInstance().newXPath();

            flowNameXpath = xPath.compile("//mule/flow/@name");
            pathXpath = xPath.compile("//mule/flow/listener/@path");
            payloadXpath = xPath.compile("//mule/flow/set-payload/@value");
            payloadMimeTypeXpath = xPath.compile("//mule/flow/set-payload/@mimeType");
            hostXpath = xPath.compile("//mule/listener-config/listener-connection/@host");
            portXpath = xPath.compile("//mule/listener-config/listener-connection/@port");

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFlowName() {
        return getString(flowNameXpath);
    }

    public String getPath() {
        return getString(pathXpath);
    }

    public String getPayload() {
        return getString(payloadXpath);
    }

    public String getPayloadMimeType() {
        return getString(payloadMimeTypeXpath);
    }

    public String getHost() {
        return getString(hostXpath);
    }

    public String getPort() {
        return getString(portXpath);
    }

    private String getString(XPathExpression xPathExpression) {
        try {
            NodeList nodeList = (NodeList) xPathExpression.evaluate(xmlDocument, XPathConstants.NODESET);
            Node item = nodeList.item(0);
            return item.getTextContent();
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }
}
