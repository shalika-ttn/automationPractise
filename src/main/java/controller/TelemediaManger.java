package controller;

import common.APIHelper;
import constants.APIConstants;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TelemediaManger extends APIHelper implements APIConstants {

    public  List<Header>  createRequest(String siNumber)
    {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(1000);
        char  c = (char) (rand.nextInt(26) + 'a');
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header("adsHeader","ra"+c+"ads"+rand_int1+"sdisa"+c));
        headerList.add(new Header("iv-user",siNumber));
        return  headerList;
    }

    public boolean validateTelemediaResponse(String siNumber,String ipInitials)
    {
        HashMap<String,String> queryParams= new HashMap<>();
        queryParams.put("siNumber",siNumber);

        String serviceUrl=generateAPIUrl(CONFIGURATION_FILE_PATH, ipInitials, SELFCARE_TELEMEDIA_DETAIL_API);

        List<Header> headerList =createRequest(siNumber);

        Response response= getResponseWithHeaders(queryParams,headerList,serviceUrl,false);

        String decryptedAPIResponse= decryptAPIResponse(response.header("googleCookie"),response.then().extract().asString());

        System.out.println(decryptedAPIResponse);
        return !decryptedAPIResponse.equalsIgnoreCase("");
    }
}
