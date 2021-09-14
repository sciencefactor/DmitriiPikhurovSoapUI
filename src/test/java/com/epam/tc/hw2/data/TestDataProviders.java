package com.epam.tc.hw2.data;

import lombok.experimental.UtilityClass;
import org.testng.annotations.DataProvider;

@UtilityClass
public class TestDataProviders {

    @DataProvider(name = "listName")
    Object[][] listName() {
        return new Object[][] {{"Green List"}};
    }

    @DataProvider(name = "boardNameListName")
    Object[][] boardNameListName() {
        return new Object[][] {{"White board", "Green List"}};
    }

}
