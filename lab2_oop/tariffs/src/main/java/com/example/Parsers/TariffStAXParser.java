package com.example.Parsers;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.example.CallPrice;
import com.example.Parameters;
import com.example.Tariff;

public class TariffStAXParser {
    public List<Tariff> parseXml(String filePath) {
        List<Tariff> tariffList = new ArrayList<>();

        Tariff tariff = null;
        CallPrice callPrice = null;
        Parameters parameters = null;
        String currentElement = null;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filePath));

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement = reader.getLocalName();
                        if ("Tariff".equals(currentElement)) {
                            tariff = new Tariff();
                            tariff.setCpList(new ArrayList<>());
                            tariff.setParamList(new ArrayList<>());
                        } else if ("CallPrice".equals(currentElement)) {
                            callPrice = new CallPrice();
                        } else if ("Parameters".equals(currentElement)) {
                            parameters = new Parameters();
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (!text.isEmpty()) {
                            processCharacters(currentElement, text, tariff, callPrice, parameters);
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        String endElement = reader.getLocalName();
                        if ("Tariff".equals(endElement)) {
                            tariffList.add(tariff);
                        } else if ("CallPrice".equals(endElement)) {
                            tariff.getCpList().add(callPrice);
                        } else if ("Parameters".equals(endElement)) {
                            tariff.getParamList().add(parameters);
                        }
                        currentElement = null;
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tariffList;
    }

    private void processCharacters(String currentElement, String text, Tariff tariff, CallPrice callPrice,
            Parameters parameters) {
        switch (currentElement) {
            case "Name":
                tariff.setName(text);
                break;
            case "OperatorName":
                tariff.setOperatorName(text);
                break;
            case "Payroll":
                tariff.setPayroll(Integer.parseInt(text));
                break;
            case "SmsPrice":
                tariff.setSmsPrice(Double.parseDouble(text));
                break;
            case "CallCoast":
                callPrice.setCallCoast(Double.parseDouble(text));
                break;
            case "Offline":
                callPrice.setOffline(Double.parseDouble(text));
                break;
            case "OnLandlines":
                callPrice.setOnLandlines(Double.parseDouble(text));
                break;
            case "FavNumber":
                parameters.setFavNumber(Integer.parseInt(text));
                break;
            case "TariffType":
                parameters.setTariffType(text);
                break;
            case "ConnectionFee":
                parameters.setConnectionFee(Double.parseDouble(text));
                break;
        }
    }
}
