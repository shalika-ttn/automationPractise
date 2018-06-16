package controller;

import common.APIHelper;
import constants.APIConstants;
import dto.request.PostAPI1SampleRequest;
import dto.response.PostAPI1SampleResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class PostAPISampleManager  extends APIHelper  implements APIConstants{


    public PostAPI1SampleRequest createPostRequest()
    {

        PostAPI1SampleRequest postAPI1SampleRequest = new PostAPI1SampleRequest();
        postAPI1SampleRequest.setEmail("abc@gmail.com");
        postAPI1SampleRequest.setPassword("check123");

        return postAPI1SampleRequest;
    }


    public PostAPI1SampleResponse getPostAPIResponse(PostAPI1SampleRequest postAPI1SampleRequest , String ip)
    {
        String body= convertToJson(postAPI1SampleRequest);
        String url=generateAPIUrl(CONFIGURATION_FILE_PATH,ip, PostAPIEndPoint);
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header("Accept","application/json"));
//        Response response= postResponseWithHeaders(body,headerList, ContentType.JSON,url,false);
        Response response= normalPost(headerList,body,url);
        return  convertFromJson(response.asString(), PostAPI1SampleResponse.class);


    }

    public boolean validatePostResponse(String ip)
    {

        PostAPI1SampleRequest postAPI1SampleRequest= createPostRequest();
        PostAPI1SampleResponse postAPI1SampleResponse = getPostAPIResponse(postAPI1SampleRequest,ip);
       return verifyRequestAndResponse(postAPI1SampleResponse,postAPI1SampleRequest);

    }

    public boolean verifyRequestAndResponse(PostAPI1SampleResponse postAPI1SampleResponse, PostAPI1SampleRequest postAPI1SampleRequest)
    {
        int count=0;
        if (!postAPI1SampleRequest.getEmail().equalsIgnoreCase(postAPI1SampleResponse.getEmail()))
            count++;

        if (!postAPI1SampleRequest.getPassword().equalsIgnoreCase(postAPI1SampleResponse.getPassword()))
            count++;

        return  count<=0;


    }
}
