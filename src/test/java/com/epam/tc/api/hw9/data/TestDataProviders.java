package com.epam.tc.api.hw9.data;

import lombok.experimental.UtilityClass;
import org.testng.annotations.DataProvider;

@UtilityClass
public class TestDataProviders {

    @DataProvider(name = "boardName")
    Object[][] boardName(){
        return new Object[][]{{"RedBoard"}};
    }
}
