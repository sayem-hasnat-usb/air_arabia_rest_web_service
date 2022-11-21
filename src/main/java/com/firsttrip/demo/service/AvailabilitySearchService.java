package com.firsttrip.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.firsttrip.demo.request.SearchRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Availability Search Request Service
 *
 * @author Sayem Hasnat"
 */
@Service
public class AvailabilitySearchService {

    public Object getAvailability(SearchRequest searchRequest) {
        String responseReturn = "";
        try {
            String url = "http://airarabia.isaaviations.com/webservices/services/AAResWebServices";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                    "  <soap:Header>\n" +
                    "    <wsse:Security soap:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\n" +
                    "      <wsse:UsernameToken wsu:Id=\"UsernameToken-17855236\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
                    "        <wsse:Username>TRIPLOVERLIMITEDG9</wsse:Username>\n" +
                    "        <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">pass1234</wsse:Password>\n" +
                    "      </wsse:UsernameToken>\n" +
                    "    </wsse:Security>\n" +
                    "  </soap:Header>\n" +
                    "  <soap:Body xmlns:ns2=\"http://www.opentravel.org/OTA/2003/05\">\n" +
                    "    <ns2:OTA_AirAvailRQ EchoToken=\"11868765275150-1300257933\" PrimaryLangID=\"en-us\" SequenceNmbr=\"1\" Target=\"TEST\" TimeStamp=\"2022-12-25T04:55:27\" Version=\"20061.00\">\n" +
                    "      <ns2:POS>\n" +
                    "        <ns2:Source TerminalID=\"TestUser/Test Runner\">\n" +
                    "          <ns2:RequestorID ID=\"TRIPLOVERLIMITEDG9\" Type=\"4\" />\n" +
                    "          <ns2:BookingChannel Type=\"12\" />\n" +
                    "        </ns2:Source>\n" +
                    "      </ns2:POS>\n" +
                    "      <ns2:OriginDestinationInformation>\n" +
                    "        <ns2:DepartureDateTime>2022-12-15T00:00:00</ns2:DepartureDateTime>\n" +
                    "        <ns2:OriginLocation LocationCode=\"SHJ\" />\n" +
                    "        <ns2:DestinationLocation LocationCode=\"COK\" />\n" +
                    "      </ns2:OriginDestinationInformation>\n" +
                    "      <ns2:TravelerInfoSummary>\n" +
                    "        <ns2:AirTravelerAvail>\n" +
                    "          <ns2:PassengerTypeQuantity Code=\"ADT\" Quantity=\"1\" />\n" +
                    "        </ns2:AirTravelerAvail>\n" +
                    "      </ns2:TravelerInfoSummary>\n" +
                    "    </ns2:OTA_AirAvailRQ>\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>";
            con.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
            dataOutputStream.writeBytes(xml);
            dataOutputStream.flush();
            dataOutputStream.close();
            String responseStatus = con.getResponseMessage();
            System.out.println(responseStatus);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("response:" + response.toString());

            /**
             * The first step is similar to our first step when we use data binding.
             * This time, though, we'll use the readTree method:
             */
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode node = xmlMapper.readTree(xml.getBytes());

            /**
             * Having done this, we'll have a JsonNode which has 3 children, as we expected: name, color, and petals.
             *   Then, we can again use ObjectMapper, just sending our JsonNode instead:
             */
            ObjectMapper jsonMapper = new ObjectMapper();
            responseReturn = jsonMapper.writeValueAsString(node);
             System.out.println("json:" + responseReturn);

        } catch (Exception e) {
            System.out.println(e);
        }

        return responseReturn;
    }
}
