package com.example.Parsers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.example.Tariff;

public class TariffSAXParser {

    public List<Tariff> parseXml(String filePath) {
        List<Tariff> tariffList = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            TariffSAXHandler handler = new TariffSAXHandler();
            saxParser.parse(filePath, handler);

            tariffList = handler.getTariffList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tariffList;
    }
}