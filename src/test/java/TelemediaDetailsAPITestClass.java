import controller.TelemediaManger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TelemediaDetailsAPITestClass {


    final static String API_HOST="SELFCARE_API";

    TelemediaManger telmediaManger = new TelemediaManger();

    @Test
    public  void fetchTelemediaResponse()
    {
        String siNumber="01172086907_dsl";
        boolean test= telmediaManger.validateTelemediaResponse(siNumber,API_HOST);
        Assert.assertTrue(test,"error in selfcare api");

    }

}
