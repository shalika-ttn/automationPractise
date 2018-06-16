import controller.PostAPISampleManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class postAPISample1TestClass {


    final static String API_HOST="API_HOST";

    PostAPISampleManager postAPISampleManager = new PostAPISampleManager();


    @Test
    public void  postAPIHit()
    {

        boolean result= postAPISampleManager.validatePostResponse(API_HOST);

        Assert.assertTrue(result,"Post API Failure");
    }

}
