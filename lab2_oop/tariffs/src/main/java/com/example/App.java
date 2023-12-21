package com.example;

import java.util.List;

import com.example.Parsers.TariffDOMParser;
import com.example.Parsers.TariffSAXParser;
import com.example.Parsers.TariffStAXParser;

public class App {
    public static void main(String[] args) throws Exception {
        String filePath = "src//resources//TariffsInfo.xml";
        // путь к хмл файлу
        TariffDOMParser parser_dom = new TariffDOMParser();
        // создает объект нашего парсера
        List<Tariff> tariffList_dom = parser_dom.parseXml(filePath);
        // tariffList будет содержаться объекты Tariff, представляющие информацию о
        // тарифах, прочитанную из XML-файла

        System.out.println("==================DOM==================");
        for (Tariff tariff : tariffList_dom) {
            System.out.println("==========Tariff==========");
            System.out.println("Name: " + tariff.getName());
            System.out.println("Operator Name: " + tariff.getOperatorName());
            System.out.println("Payroll: " + tariff.getPayroll());
            System.out.println("SMS Price: " + tariff.getSmsPrice());

            System.out.println("Call Prices:");
            for (CallPrice callPrice : tariff.getCpList()) {
                System.out.println("  Call Coast: " + callPrice.getCallCoast());
                System.out.println("  Offline: " + callPrice.getOffline());
                System.out.println("  On Landlines: " + callPrice.getOnLandlines());
            }

            System.out.println("Parameters:");
            for (Parameters parameters : tariff.getParamList()) {
                System.out.println("  Favorite Number: " + parameters.getFavNumber());
                System.out.println("  Tariff Type: " + parameters.getTariffType());
                System.out.println("  Connection Fee: " + parameters.getConnectionFee());
            }

            System.out.println(); // Пустая строка для разделения тарифов
        }

        System.out.println("==================SAX==================");
        TariffSAXParser parser_sax = new TariffSAXParser();
        List<Tariff> tariffList_sax = parser_sax.parseXml(filePath);

        for (Tariff tariff : tariffList_sax) {
            System.out.println("Tariff Name: " + tariff.getName());
            System.out.println("Operator Name: " + tariff.getOperatorName());
            System.out.println("Payroll: " + tariff.getPayroll());
            System.out.println("Sms Price: " + tariff.getSmsPrice());

            // Перебор данных о ценах на звонки
            List<CallPrice> callPriceList = tariff.getCpList();
            for (CallPrice callPrice : callPriceList) {
                System.out.println("Call Coast: " + callPrice.getCallCoast());
                System.out.println("Offline: " + callPrice.getOffline());
                System.out.println("On Landlines: " + callPrice.getOnLandlines());
            }

            // Перебор данных о параметрах тарифа
            List<Parameters> parametersList = tariff.getParamList();
            for (Parameters parameters : parametersList) {
                System.out.println("Fav Number: " + parameters.getFavNumber());
                System.out.println("Tariff Type: " + parameters.getTariffType());
                System.out.println("Connection Fee: " + parameters.getConnectionFee());
            }

            System.out.println("====================");
        }
        TariffStAXParser staxParser = new TariffStAXParser();
        List<Tariff> tariffList_StAX = staxParser.parseXml(filePath);
        System.out.println("==================StAX==================");
        for (Tariff tariff : tariffList_StAX) {
            System.out.println("==========Tariff==========");
            System.out.println("Name: " + tariff.getName());
            System.out.println("Operator Name: " + tariff.getOperatorName());
            System.out.println("Payroll: " + tariff.getPayroll());
            System.out.println("SMS Price: " + tariff.getSmsPrice());

            System.out.println("Call Prices:");
            for (CallPrice callPrice : tariff.getCpList()) {
                System.out.println("  Call Coast: " + callPrice.getCallCoast());
                System.out.println("  Offline: " + callPrice.getOffline());
                System.out.println("  On Landlines: " + callPrice.getOnLandlines());
            }

            System.out.println("Parameters:");
            for (Parameters parameters : tariff.getParamList()) {
                System.out.println("  Favorite Number: " + parameters.getFavNumber());
                System.out.println("  Tariff Type: " + parameters.getTariffType());
                System.out.println("  Connection Fee: " + parameters.getConnectionFee());
            }

            System.out.println();
        }
    }
}
