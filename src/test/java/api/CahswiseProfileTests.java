package api;

import org.junit.Assert;
import org.junit.Test;
import utilities.ApiRunner;

public class CahswiseProfileTests {

    @Test
    public void verifyProfileFields(){
        String path= "/api/myaccount/editors/get/profile";
        ApiRunner.runGET(path);
       // System.out.println(ApiRunner.getCustomResponse().getResponseBody());
        System.out.println(ApiRunner.getCustomResponse().getBusiness_area().getEnTitle());

        Assert.assertFalse(ApiRunner.getCustomResponse().getBusiness_area().getEnTitle().isEmpty());
        Assert.assertFalse(ApiRunner.getCustomResponse().getBusiness_area().getRuTitle().isEmpty());





    }




}
