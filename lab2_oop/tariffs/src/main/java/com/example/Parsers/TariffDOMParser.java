package com.example.Parsers;

import com.example.CallPrice;
import com.example.Tariff;
import com.example.Parameters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class TariffDOMParser {

    public List<Tariff> parseXml(String filePath) {
        List<Tariff> tariffList = new ArrayList<>();

        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Tariff");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Element tariffElement = (Element) nodeList.item(temp);
                String name = getValue(tariffElement, "Name");
                String operatorName = getValue(tariffElement, "OperatorName");
                int payroll = Integer.parseInt(getValue(tariffElement, "Payroll"));
                double smsPrice = Double.parseDouble(getValue(tariffElement, "SmsPrice"));
                NodeList callPricesNodeList = tariffElement.getElementsByTagName("CallPrice");
                List<CallPrice> callPriceList = new ArrayList<>();

                for (int i = 0; i < callPricesNodeList.getLength(); i++) {
                    Element callPriceElement = (Element) callPricesNodeList.item(i);
                    double callCoast = Double.parseDouble(getValue(callPriceElement, "CallCoast"));
                    double offline = Double.parseDouble(getValue(callPriceElement, "Offline"));
                    double onLandlines = Double.parseDouble(getValue(callPriceElement, "OnLandlines"));
                    CallPrice callPriceObj = new CallPrice(callCoast, offline, onLandlines);
                    callPriceList.add(callPriceObj);
                }

                NodeList parametersNodeList = tariffElement.getElementsByTagName("Parameters");
                List<Parameters> parametersList = new ArrayList<>();
                for (int i = 0; i < parametersNodeList.getLength(); i++) {
                    Element parametersElement = (Element) parametersNodeList.item(i);
                    int favNumber = Integer.parseInt(getValue(parametersElement, "FavNumber"));
                    String tariffType = getValue(parametersElement, "TariffType");
                    double connectionFee = Double.parseDouble(getValue(parametersElement, "ConnectionFee"));

                    Parameters parametersObj = new Parameters(favNumber, tariffType, connectionFee);
                    parametersList.add(parametersObj);
                }

                Tariff tariffObj = new Tariff(name, operatorName, payroll, smsPrice, callPriceList, parametersList);
                tariffList.add(tariffObj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tariffList;
    }

    private String getValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Element tagElement = (Element) nodeList.item(0);
            if (tagElement != null && tagElement.getFirstChild() != null) {
                return tagElement.getFirstChild().getNodeValue();
            } else {
                System.out.println("Warning: Empty value for tag " + tagName);
                return "";
            }
        } else {
            System.out.println("Warning: Tag " + tagName + " not found");
            return "";
        }
    }

}
