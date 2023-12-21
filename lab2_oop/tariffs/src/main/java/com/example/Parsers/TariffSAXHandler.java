package com.example.Parsers;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.CallPrice;
import com.example.Parameters;
import com.example.Tariff;

public class TariffSAXHandler extends DefaultHandler {
    // список объектов Tariff, который будет содержать результаты парсинга
    private List<Tariff> tariffList;
    // Это текущий объект Tariff, который заполняется в процессе парсинга
    private Tariff currentTariff;
    // список объектов CallPrice, представляющих информацию о ценах на звонки для
    // текущего тарифа
    private List<CallPrice> callPriceList;
    // Это текущий объект CallPrice, который заполняется в процессе парсинга цен на
    // звонки
    private CallPrice currentCallPrice;
    // список объектов Parameters, представляющих информацию о параметрах текущего
    // тарифа
    private List<Parameters> parametersList;
    // текущий объект Parameters
    private Parameters cuParameters;
    // содержит имя текущего элемента, обрабатываемого в обработчике
    private String currentElement;

    public List<Tariff> getTariffList() {
        return tariffList;
    }

    public TariffSAXHandler() {
        tariffList = new ArrayList<>();
    }

    @Override
    // uri параметр может быть использован для определения, в каком пространстве
    // имен находится текущий элемент

    // localName Локальное имя элемента

    // qName Квалифицированное имя элемента

    // attributes Объект, представляющий атрибуты элемента
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;

        if ("Tariff".equals(qName)) {
            currentTariff = new Tariff();
            callPriceList = new ArrayList<>();
            parametersList = new ArrayList<>();
        } else if ("CallPrice".equals(qName)) {
            currentCallPrice = new CallPrice();
        } else if ("Parameters".equals(qName)) {
            cuParameters = new Parameters();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();

        if (!value.isEmpty()) {
            if ("Name".equals(currentElement)) {
                currentTariff.setName(value);
            } else if ("OperatorName".equals(currentElement)) {
                currentTariff.setOperatorName(value);
            } else if ("Payroll".equals(currentElement)) {
                currentTariff.setPayroll(Integer.parseInt(value));
            } else if ("SmsPrice".equals(currentElement)) {
                currentTariff.setSmsPrice(Double.parseDouble(value));
            } else if ("CallCoast".equals(currentElement)) {
                currentCallPrice.setCallCoast(Double.parseDouble(value));
            } else if ("Offline".equals(currentElement)) {
                currentCallPrice.setOffline(Double.parseDouble(value));
            } else if ("OnLandlines".equals(currentElement)) {
                currentCallPrice.setOnLandlines(Double.parseDouble(value));
            } else if ("FavNumber".equals(currentElement)) {
                cuParameters.setFavNumber(Integer.parseInt(value));
            } else if ("TariffType".equals(currentElement)) {
                cuParameters.setTariffType(value);
            } else if ("ConnectionFee".equals(currentElement)) {
                cuParameters.setConnectionFee(Double.parseDouble(value));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ("Tariff".equals(qName)) {
            currentTariff.setCpList(callPriceList);
            currentTariff.setParamList(parametersList);
            tariffList.add(currentTariff);
        } else if ("CallPrice".equals(qName)) {
            callPriceList.add(currentCallPrice);
        } else if ("Parameters".equals(qName)) {
            parametersList.add(cuParameters);
        }
    }
}
